package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;


public class Move extends Action {
    Auto auto;
    double xPos;
    double yPos;
    double heading;
    double speedMult = 1;
    double expectedTime = 0;
    ElapsedTime startTime;
    
    public Move(Auto auto, double xPos, double yPos, double heading) {
        this.auto = auto;
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.heading = heading;
    }
    
    
    public Move(Auto auto, double xPos, double yPos, double heading, double speedMult) {
        this.auto = auto;
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.heading = heading;
        this.speedMult = speedMult;
    }
    
    
    public Move(Auto auto, double xPos, double yPos, double heading, double speedMult, double expectedTime) {
        this.auto = auto;
        
        this.xPos = xPos;
        this.yPos = yPos;
        this.heading = heading;
        this.speedMult = speedMult;
        this.expectedTime = expectedTime;
    }
        

    // public Move(Auto auto, double xPos, double yPos, double heading, double expectedTime) {
    //     this.auto = auto;
        
    //     this.xPos = xPos;
    //     this.yPos = yPos;
    //     this.heading = heading;
    //     this.expectedTime = expectedTime;
    // }

    public void onStart() {
        startTime = new ElapsedTime();
        auto.driveMotors.Move(xPos, yPos, heading, this.speedMult);
    }
    
    public boolean isDone() {
        return auto.driveMotors.isDone() || (expectedTime != 0 && startTime.time() >= expectedTime);
    }

    public void process() {
        auto.telemetry.addData("Waiting for base...", startTime.time() - expectedTime);
    }

}
