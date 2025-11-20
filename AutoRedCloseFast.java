package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Direction;
import org.firstinspires.ftc.teamcode.BotConfig;
import org.firstinspires.ftc.teamcode.Auto;


@Autonomous(name = "Red Close Fast"/*, preselectTeleOp="Your Drive Code Here"*/, group="red")
//@Disabled
public class AutoRedCloseFast extends Auto {

    public Action[] getActions() {
        Action[] actions = {
            // ======================= AUTO START ======================= //

            // Get to firing position
            new CloseStopper(this),
            new SpinLauncher(this),
            new Move(this, -450, 1120, -45),
            
            // Wait for launcher to get up to speed
            new Wait(this, 500),
            new WaitForLauncher(this),
            new Wait(this, 500),
            
            // Engage pusher
            new SpinPusher(this),
            
            // Launch!
            new OpenStopper(this),
            new Wait(this, 500),
            new CloseStopper(this),
            new Wait(this, 500),
            new WaitForLauncher(this),
            new Wait(this, 500),
            
            // Engage third artifact
            new SpinIntake(this),
            new Move(this, -475, 1095, -45),
            new StopIntake(this),
            
            // Launch the rest!
            new OpenStopper(this),
            new Wait(this, 2500),
            new CloseStopper(this),
            new Wait(this, 750),
            
            // Collect second batch
            new SpinIntake(this),
            new Move(this, -650, 650, 90),
            new Move(this, -650, 100, 90),
            new Wait(this, 1000),
            new StopIntake(this),
            
            // Get to firing position (x2)
            new CloseStopper(this),
            new SpinLauncher(this),
            new Move(this, -450, 1120, -45),
            
            // Wait for launcher to get up to speed (x2)
            new Wait(this, 500),
            new WaitForLauncher(this),
            new Wait(this, 500),
            
            // Engage pusher (x2)
            new SpinPusher(this),
            
            // Launch! (x2)
            new OpenStopper(this),
            new Wait(this, 500),
            new CloseStopper(this),
            new Wait(this, 500),
            new WaitForLauncher(this),
            new Wait(this, 500),
            
            // Engage third artifact (x2)
            new SpinIntake(this),
            new Move(this, -475, 1095, -45),
            new StopIntake(this),
            
            // Launch the rest! (x2)
            new OpenStopper(this),
            new Wait(this, 2500),
            new CloseStopper(this),
            new Wait(this, 750),
            
            // Evacuate launch zone
            new Move(this, -650, 300, 90),
            
            // ======================== AUTO END ======================== //
        };
        
        return actions;
    }
}

