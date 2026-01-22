package org.firstinspires.ftc.teamcode;

public static class AutoTeleopData {
    static double goalX;
    static double goalY;

    static int pattern;
    

    public static void SetGoal(double goalX, double goalY) {
        goalX = goalX;
        goalY = goalY;
    }


    public static void SetPattern(int pattern) {
        pattern = pattern;
    }


    public static double GetGoalX() {
        return goalX;
    }

    public static double GetGoalY() {
        return goalY;
    }

    public static int GetPattern() {
        return pattern;
    }
}