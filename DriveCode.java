package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name="Field Centric (main)", group="DriveCodes")
public class DriveCode extends LinearOpMode {
    
    // Drive constants

    DriveMotors driveMotors;
    
    DcMotorEx intake;
    DcMotorEx pusher;
    Servo stopper;
    DcMotorEx flywheel;
    
    double headingOffset = 0;
    boolean stopperPos = false;
    boolean prevRightTrigger = false;

    @Override
    public void runOpMode() throws InterruptedException {
        
        driveMotors = new DriveMotors(this);
        intake = hardwareMap.get(DcMotorEx.class, BotConfig.INTAKE_NAME);
        pusher = hardwareMap.get(DcMotorEx.class, BotConfig.PUSHER_NAME);
        stopper = hardwareMap.get(Servo.class, BotConfig.STOPPER_NAME);
        flywheel = hardwareMap.get(DcMotorEx.class, BotConfig.LAUNCHER_NAME);

        // Wait for the start button to be pressed
        waitForStart();

        while (opModeIsActive()) {
            // Reset yaw when y button pressed so restarting is not needed if it needs a reset
            if (gamepad1.y) {
                headingOffset = driveMotors.heading;
            }
            

            // Process classes
            double deltaTime = driveMotors.process();

            
            // P1 variables
            double leftStickXGP1 = gamepad1.left_stick_x;
            double leftStickYGP1 = gamepad1.left_stick_y;
            double rightStickXGP1 = gamepad1.right_stick_x;
            double rightStickYGP1 = gamepad1.right_stick_y;

            // Get the speed the bot would go with the joystick pushed all the way
            double maxSpeed = calcMaxSpeed(gamepad1.right_trigger - gamepad1.left_trigger, BotConfig.BASE_SPEED, BotConfig.MAX_BOOST);
            
            double joystickLength = Math.sqrt( Math.pow(gamepad1.right_stick_y, 2) + Math.pow(gamepad1.right_stick_x, 2) );
            double joystickAngle = -Math.atan2(gamepad1.right_stick_y, gamepad1.right_stick_x) - Math.PI/2;
            
            double turnPower = 
            joystickLength > 10 ?
                driveMotors.imuPidController.PIDControlRadians(
                    joystickAngle,
                    driveMotors.heading,
                    deltaTime
                )
            : 
                -gamepad1.right_stick_x;

            // Virtually rotate the joystick by the angle of the robot
            double heading = driveMotors.heading - headingOffset;
            
            double rotatedX =
                leftStickXGP1 * Math.cos(heading) -
                leftStickYGP1 * Math.sin(heading);
            double rotatedY =
                leftStickXGP1 * Math.sin(heading) +
                leftStickYGP1 * Math.cos(heading);
            
            // strafing is slower than rolling, bump horizontal speed
            rotatedX *= BotConfig.STRAFE_MULT;
            
            // Set the power of the wheels based off the new joystick coordinates
            // y+x+stick <- [-1,1]
            driveMotors.DriveWithPower(
                ( rotatedY + rotatedX + ( turnPower )) * maxSpeed, // Back left
                ( rotatedY - rotatedX + ( turnPower )) * maxSpeed, // Front left
                (-rotatedY - rotatedX + ( turnPower )) * maxSpeed, // Front right
                (-rotatedY + rotatedX + ( turnPower )) * maxSpeed  // Back right
            );
            
            // P2 variables
            double leftStickYGP2 = gamepad2.left_stick_y;
            double rightStickYGP2 = gamepad2.right_stick_y;
            
            // Intake
            intake.setPower(leftStickYGP2 < 0 ? -leftStickYGP2 : -leftStickYGP2 / 3);
            
            // Pusher
            pusher.setPower(-leftStickYGP2 / 2);
            // if (gamepad2.left_bumper) {
            //     pusher.setPower(.75);
            // }
            // else if (gamepad2.right_bumper) {
            //     pusher.setPower(.45);
            // }
            // else {
            //     pusher.setPower(0);
            // }
            
            // Stopper
            stopper.setPosition(gamepad2.right_trigger > 0.5 ? BotConfig.STOPPER_OPEN_POS : BotConfig.STOPPER_CLOSE_POS);
            // if (gamepad2.right_trigger > 0.5 && !prevRightTrigger) {
            //     stopperPos = !stopperPos;
            //     stopper.setPosition(stopperPos ? BotConfig.STOPPER_OPEN_POS : BotConfig.STOPPER_CLOSE_POS);
            // }
            // prevRightTrigger = gamepad2.right_trigger > 0.5;
            // if (gamepad2.right_trigger > .5) {
            //     stopper.setPosition(BotConfig.STOPPER_OPEN_POS);
            // }
            // else if (gamepad2.left_trigger > .5) {
            //     stopper.setPosition(BotConfig.STOPPER_CLOSE_POS);
            // }
            
            // Flywheel
            if (rightStickYGP2 > .5) {
                flywheel.setVelocity(0);
            } 
            else if (rightStickYGP2 < -.5) {
                flywheel.setVelocity(1500);
            }
            else {
                flywheel.setVelocity(1000);
            }
            
            // Telemetry
            // Odometry values
            telemetry.addData("X pos", driveMotors.odometry.getPosX(DistanceUnit.MM));
            telemetry.addData("Y pos", driveMotors.odometry.getPosY(DistanceUnit.MM));
            telemetry.addData("X encoder", driveMotors.odometry.getEncoderX());
            telemetry.addData("Y encoder", driveMotors.odometry.getEncoderY());
            telemetry.addData("Heading", driveMotors.odometry.getHeading(AngleUnit.DEGREES));

            // Turning values
            telemetry.addData("joystickAngle", joystickAngle);
            telemetry.addData("turnPower", turnPower);

            telemetry.addData("thingy", ( rotatedY + rotatedX + ( turnPower )) * maxSpeed);
            
            telemetry.update();
        }
    }


    /**
     * if boost trigger unpressed, return base_speed,
     * else return base_speed + boost amount
     */
    double calcMaxSpeed(double triggerVal, double BASE_SPEED, double MAX_BOOST) {
        double boostRatio = triggerVal * MAX_BOOST;
        double boostSpeed = boostRatio * BASE_SPEED;
        return BASE_SPEED + boostSpeed;
    }
}
