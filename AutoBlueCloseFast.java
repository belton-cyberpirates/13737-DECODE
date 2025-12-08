package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Direction;
import org.firstinspires.ftc.teamcode.BotConfig;
import org.firstinspires.ftc.teamcode.Auto;


@Autonomous(name = "Blue Close Fast"/*, preselectTeleOp="Your Drive Code Here"*/, group="blue")
//@Disabled
public class AutoBlueCloseFast extends Auto {

    public Action[] getActions() {
        Action[] actions = {
            // ======================= AUTO START ======================= //

            // Get to firing position
            new OpenStopper(this),
            new SpinLauncher(this),
            new Move(this, -450, -1120, -135),
            
            // Launch!
            new WaitForLauncher(this),
            new Wait(this, 500),
            new SpinPusher(this),
            new Wait(this, 500),
            new SpinIntake(this, -.3),
            new Wait(this, 300),
            new SpinIntake(this),
            
            new Wait(this, 3000),
            
            // Collect second batch
            new CloseStopper(this),
            new SpinIntake(this),
            new Move(this, -650, -650, -90),
            new Move(this, -650, -100, -90),
            new Wait(this, 1000),
            new StopIntake(this),
            new StopPusher(this),
            
            // Get to firing position (x2)
            new OpenStopper(this),
            new SpinLauncher(this),
            new Move(this, -475, -1120, -135),
            
            // Launch!
            new WaitForLauncher(this),
            new Wait(this, 500),
            new SpinPusher(this),
            new Wait(this, 500),
            new SpinIntake(this, -.3),
            new Wait(this, 300),
            new SpinIntake(this),
            
            new Wait(this, 3000),
            
            // Evacuate launch zone
            new Move(this, -650, -300, -90),
            
            // ======================== AUTO END ======================== //
        };
        
        return actions;
    }
}

