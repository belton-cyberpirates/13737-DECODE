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
        
        Action[] actions = {
            // ======================= AUTO START ======================= //
            
            // Get ready for launching
            new OpenStopper(this),
            new SpinLauncherFast(this),
            
            // Move to shooting position
            new Move(this, 180, 0, -90+20),
            
            // Launch!
            new WaitForLauncher(this),
            new Wait(this, 500),
            new SpinPusher(this),
            new Wait(this, 500),
            new SpinIntake(this, -.2),
            new Wait(this, 250),
            new SpinIntake(this),
            
            new Wait(this, 3000),
            
            // Reset
            new StopLauncher(this),
            new StopIntake(this),
            new StopPusher(this),
            
            // Move to first line
            new Move(this, 680, -300, 90),
            
            // Intake artifacts
            new CloseStopper(this),
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, 680, -1100, 90),
            new Wait(this, 500),
            new StopPusher(this),
            
            // Get ready for launching
            new OpenStopper(this),
            new SpinLauncherFast(this),
            
            // Move back to shooting position, while intaking to not lose artifacts
            new Move(this, 180, 0, -90+20), // same as first shooting position
            
            // Launch!
            new WaitForLauncher(this),
            new Wait(this, 500),
            new SpinPusher(this),
            new Wait(this, 500),
            new SpinIntake(this, -.2),
            new Wait(this, 300),
            new SpinIntake(this),
            new Wait(this, 3500),
            
            // Reset
            new StopLauncher(this),
            new StopIntake(this),
            new StopPusher(this),
            
            // Move to second line
            new Move(this, 1280, -300, 90),
            
            // Intake artifacts
            new CloseStopper(this),
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, 1250, -1100, 90),
            new Wait(this, 500),
            new StopPusher(this),
            
            // Get ready for launching
            new OpenStopper(this),
            new SpinLauncherFast(this),
            
            // Move back to shooting position, while intaking to not lose artifacts
            new Move(this, 180, 0, -90+20), // same as first shooting position
            
            // Launch!
            new WaitForLauncher(this),
            new Wait(this, 500),
            new SpinPusher(this),
            new Wait(this, 500),
            new SpinIntake(this, -.2),
            new Wait(this, 300),
            new SpinIntake(this),
            
            new Wait(this, 3500),
            
            // End sequence
            
            // Move out of triangle
            new Move(this, 50, -500, 0),
            
            // ======================== AUTO END ======================== //
        };
        
        return actions;
    }
}