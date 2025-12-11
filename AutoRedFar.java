package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Direction;
import org.firstinspires.ftc.teamcode.BotConfig;
import org.firstinspires.ftc.teamcode.Auto;


@Autonomous(name = "Red Far"/*, preselectTeleOp="Your Drive Code Here"*/, group="red")
//@Disabled
public class AutoRedFar extends Auto {

    public Action[] getActions() {
        
        Action[] launchSequence = {
            // Get ready for launching
            new OpenStopper(this),
            new SpinLauncherFast(this),
            
            // Move to shooting position
            new Move(this, 180, 80, -90+20),
            
            // Launch!
            new WaitForLauncher(this),
            new Wait(this, 500),
            new SpinPusher(this),
            new Wait(this, 500),
            new SpinIntake(this, -.3),
            new Wait(this, 250),
            new SpinIntake(this),
            new SpinPusher(this, 3),
            
            new Wait(this, 2000),
            
            // Reset
            new StopLauncher(this),
            new StopIntake(this),
            new StopPusher(this)
        };
        
        Action[] actions = {
            // ======================= AUTO START ======================= //
            
            // Launch!
            new ActionSequence(this, launchSequence),
            
            // Move to first line
            new CloseStopper(this),
            new Move(this, 680, -300, 90),
            
            // Intake artifacts
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, 680, -1100, 90),
            new Wait(this, 750),
            new StopPusher(this),
            new StopIntake(this),
            
            // Launch!
            new ActionSequence(this, launchSequence),
            
            // Move to second line
            new CloseStopper(this),
            new Move(this, 1200, -300, 90),
            
            // Intake artifacts
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, 1190, -1100, 90),
            new Wait(this, 750),
            new StopPusher(this),
            new StopIntake(this),
            
            // Launch!
            new ActionSequence(this, launchSequence),
            
            // Move out of triangle
            new CloseStopper(this),
            new Move(this, 50, -500, 90),
            
            // ======================== AUTO END ======================== //
        };
        
        return actions;
    }
}