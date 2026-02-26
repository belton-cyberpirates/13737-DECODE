package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Direction;
import org.firstinspires.ftc.teamcode.BotConfig;
import org.firstinspires.ftc.teamcode.Auto;


@Autonomous(name = "Blue Close Cycle"/*, preselectTeleOp="Your Drive Code Here"*/, group="blue")
//@Disabled
public class AutoBlueCloseCycle extends Auto {
    
    Action[] launchSequence = {
        // Get ready for launching
        new StopPusher(this),
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
        new StopPusher(this),
        new CloseStopper(this)
    };
    
    Action[] cycleSequence = {
        // Move to gate
        new SpinIntake(this),
        new SpinPusher(this), 
        new Move(this, -1340, -500, -60, 1, 1),
        new Move(this, -1340, 90, -60, 1, 2),
        
        // Wait for the artifacts
        new Wait(this, 2000),
        new StopIntake(this),
    };

    public Action[] getActions() {
        Action[] actions = {
            // ======================= AUTO START ======================= //
            // Launch!
            new ActionSequence(this, launchSequence),
            
            // Move to line 2
            new CloseStopper(this),
            new Move(this, -1380, -800, -90, 1, 2),
            
            // Grab line
            new SpinIntake(this),
            new SpinPusher(this), 
            new Move(this, -1380, 100, -90, 1, 1.5),
            new StopPusher(this),
            new StopIntake(this),
            
            // Launch!
            new ActionSequence(this, launchSequence),
            
            // Cycle 'n launch
            new ActionSequence(this, cycleSequence),
            new ActionSequence(this, launchSequence),
            
            // Run it back
            new ActionSequence(this, cycleSequence),
            new ActionSequence(this, launchSequence),
            
            // Leave
            new CloseStopper(this),
            new Move(this, -1300, -1050, -90),
            new Wait(this, 1000)
            
            // ======================== AUTO END ======================== //
        };
        
        return actions;
    }
}

