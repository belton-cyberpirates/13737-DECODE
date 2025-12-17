package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Direction;
import org.firstinspires.ftc.teamcode.BotConfig;
import org.firstinspires.ftc.teamcode.Auto;


@Autonomous(name = "Blue Far - w/ 13399"/*, preselectTeleOp="Your Drive Code Here"*/, group="blue")
//@Disabled
public class AutoBlueFar13399 extends Auto {

    public Action[] getActions() {
        
        Action[] launchSequence = {
            // Get ready for launching
            new OpenStopper(this),
            new SpinLauncherFast(this),
            
            // Move to shooting position
            new Move(this, 180, 0, -90-24.5),
            
            // Launch!
            new WaitForLauncher(this),
            new Wait(this, 500),
            
            new SpinPusher(this),
            new SpinIntake(this),
            new Wait(this, 750),
            new SpinIntake(this, -.3),
            new Wait(this, 350),
            new SpinIntake(this),
            new SpinPusher(this, 3),
            
            new Wait(this, 1500),
            
            // Reset
            new StopLauncher(this),
            new StopIntake(this),
            new StopPusher(this)
        };

        Action[] humanPlayerGrabSequence = {
            // Move to human player zone
            new Move(this, 100, 300, -90),

            // Get ready to grab
            new CloseStopper(this),
            new SpinIntake(this),
            new SpinPusher(this),

            // Try grabbing
            new Move(this, 100, 1100, -110),
            new Wait(this, 250),

            // Try grabbing some more
            new Move(this, 100, 850, -110),
            new Move(this, 100, 1100, -110),
            new Wait(this, 250),

            // Turn off intake
            new StopPusher(this),
            new StopIntake(this),
        }
        
        Action[] actions = {
            // ======================= AUTO START ======================= //
            
            // Launch!
            new ActionSequence(this, launchSequence),
            
            // Move to first line
            new Move(this, 680, 300, -90),

            // Intake artifacts
            new CloseStopper(this),
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, 680, 1100, -90),
            new Wait(this, 750),
            new StopPusher(this),
            new StopIntake(this),
            
            new ActionSequence(this, launchSequence),

            new ActionSequence(this, humanPlayerGrabSequence),
            new ActionSequence(this, launchSequence),

            new ActionSequence(this, humanPlayerGrabSequence),
            new ActionSequence(this, launchSequence),

            new ActionSequence(this, humanPlayerGrabSequence),
            new ActionSequence(this, launchSequence),
            
            // End sequence
            // Move out of triangle
            new Move(this, 50, 500, -90),
            
            // ======================== AUTO END ======================== //
        };
        
        return actions;
    }
}
