public class AutoPaths {

    public enum StartingPos {
        FAR_BLUE(1),
        CLOSE_BLUE(1),
        FAR_RED(-1),
        CLOSE_RED(-1),
    }


    LinearOpMode auto;
    StartingPos startPos;


    public AutoPaths(LinearOpMode auto, StartingPos startPos) {
        this.auto = auto;
        this.startPos = startPos;
    }


    private Move getShootPosMove() {
        switch (startPos) {
            case StartingPos.FAR_BLUE:
                return new Move(auto, 180, 0, -90-24.5);
            case StartingPos.FAR_RED:
                return new Move(auto, 180, 80, -90+19);
            case StartingPos.CLOSE_BLUE:
                return new Move(auto, -700, -950, -90-55);
            case StartingPos.CLOSE_RED:
                return new Move(auto, -510, 1050, -90+40);
        }
    }


    public Action[] GetLaunchSequence(LinearOpMode auto, StartingPos startPos) {
        return {
            // Get ready for launching
            new OpenStopper(auto),
            new SpinLauncherFast(auto),
            
            // Move to shooting position
            getShootPosMove(auto, startPos),
            
            // Launch!
            new WaitForLauncher(auto),
            new Wait(auto, 500),
            
            new SpinPusher(auto),
            new SpinIntake(auto),
            new Wait(auto, 750),
            new SpinIntake(auto, -.3),
            new Wait(auto, 350),
            new SpinIntake(auto),
            new SpinPusher(this, 3),
            
            new Wait(this, 1500),
            
            // Reset
            new StopLauncher(this),
            new StopIntake(this),
            new StopPusher(this)
        }
    }


    public Action[] GetHumanGrabSequence(LinearOpMode auto, StartingPos startPos) {
        // Move in front of the human player zone
        new Move(this, 300, 300*(int)(startPos), -100*(int)(startPos)),

        // Get ready to grab
        new CloseStopper(this),
        new SpinIntake(this),
        new SpinPusher(this),

        // Try grabbing
        new Move(this, 100, 1150*(int)(startPos), -110*(int)(startPos), 1, 1),

        // Try grabbing some more
        new Move(this, 100, 850*(int)(startPos), -110*(int)(startPos), 1, 0.5),
        new Move(this, 100, 1150*(int)(startPos), -110*(int)(startPos), 1, 0.75)
    };
}