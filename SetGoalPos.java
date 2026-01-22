package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.AutoTeleopData;


public class SetGoalPos extends Action {
    Auto auto;

    public SetGoalPos(Auto auto, double goalX, double goalY) {
        this.auto = auto;
        
        AutoTeleopData.SetGoal(goalX, goalY);
    }

    public void onStart() {}

    public boolean isDone() {
        return true;
    }
}
