package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Direction;
import org.firstinspires.ftc.teamcode.BotConfig;
import org.firstinspires.ftc.teamcode.Auto;


@Autonomous(name = "Blue Close Fast"/*, preselectTeleOp="Your Drive Code Here"*/, group="blue")
//@Disabled
public class AutoBlueCloseFast extends Auto {
    
    Action[] launchSequence = {
        // Get ready for launching
        new OpenStopper(this),
        new SpinLauncher(this),
        
        // Move to shooting position
        new Move(this, -700, -950, -135),
        
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
        

    public Action[] getActions() {
        Action[] actions = {
            // ======================= AUTO START ======================= //
            // Launch!
            new ActionSequence(this, launchSequence),
            
            // Move to first line
            new CloseStopper(this),
            new Move(this, -720, -650, -90),
            
            // Intake artifacts
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, -720, -100, -90),
            new Wait(this, 750),
            new StopPusher(this),
            new StopIntake(this),
            
            new ActionSequence(this, launchSequence),
            
            // Move to second line
            new CloseStopper(this),
            new Move(this, -1380, -800, -90),
            
            // Intake artifacts
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, -1390, 100, -90),
            new Wait(this, 750),
            new StopPusher(this),
            new StopIntake(this),
            
            new ActionSequence(this, launchSequence),
            
            // End sequence
            
            // Move out of triangle
            new CloseStopper(this),
            new Move(this, -1300, -1050, -90),
            new Wait(this, 1000)
            
            // ======================== AUTO END ======================== //
        };
        
        return actions;
    }
}

