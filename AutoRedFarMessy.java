package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Direction;
import org.firstinspires.ftc.teamcode.BotConfig;
import org.firstinspires.ftc.teamcode.Auto;


@Autonomous(name = "Blue Far - MESSY PROTOCOL"/*, preselectTeleOp="Your Drive Code Here"*/, group="blue")
//@Disabled
public class AutoBlueFarMessy extends Auto {

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
        
        Action[] launchFromPos = {
            // Get ready for launching
            new OpenStopper(this),
            new SpinLauncherSab(this),
            
            // Launch!
            new WaitForLauncher(this),
            new Wait(this, 500),
            new SpinPusher(this),
            
            new Wait(this, 750),
            
            // Reset
            new StopLauncher(this),
            new StopIntake(this),
            new StopPusher(this)
        };
        
        Action[] actions = {
            // ======================= AUTO START ======================= //
            
            // EXECUTE MESSY PROTOCOL
            // Second line
            new Move(this, 1475, -110, 0),
            new ActionSequence(this, launchFromPos),
            
            // First line
            new Move(this, 845, -110, 0),
            new ActionSequence(this, launchFromPos),
            
            // Third line
            // new Move(this, 2075, -110, 0),
            // new ActionSequence(this, launchFromPos),
            
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

            // Move to second line
            new Move(this, 1200, 300, -90),

            // Intake artifacts
            new CloseStopper(this),
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, 1190, 1100, -90),
            new Wait(this, 750),
            new StopPusher(this),
            new StopIntake(this),
            
            new ActionSequence(this, launchSequence),

            // End sequence

            // Move out of triangle
            new Move(this, 50, 500, -90),
            
            // ======================== AUTO END ======================== //
        };
        
        return actions;
    }
}
