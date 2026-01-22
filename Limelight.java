package org.firstinspires.ftc.teamcode;

public class Limelight {
    private Limelight3A limelight;
    private final DifferentialDrivePoseEstimator m_poseEstimator =
      new DifferentialDrivePoseEstimator(
          m_kinematics,
          m_gyro.getRotation2d(),
          m_leftEncoder.getDistance(),
          m_rightEncoder.getDistance(),
          new Pose2d(),
          VecBuilder.fill(0.05, 0.05, Units.degreesToRadians(5)),
          VecBuilder.fill(0.5, 0.5, Units.degreesToRadians(30)));

    public Limelight(LinearOpMode auto) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);

        /*
         * Starts polling for data.
         */
        limelight.start();
    }


    public void process() {
        LLResult result = limelight.getLatestResult();

        limelight.updateRobotOrientation(this.auto.driveMotors.getHeading(AngleUnit.DEGREES));

        LLResult result = limelight.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                Pose3D botpose = result.getBotpose_MT2();
                // Use botpose data
            }
        }

        if (result != null) {
            if (result.isValid()) {
                Pose3D botpose = result.getBotpose_MT2();
                telemetry.addData("Botpose", botpose.toString());
                
                LimelightHelpers.SetRobotOrientation("limelight", m_poseEstimator.getEstimatedPosition().getRotation().getDegrees(), 0, 0, 0, 0, 0);
                LimelightHelpers.PoseEstimate mt2 = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight");
                
                // if our angular velocity is greater than 360 degrees per second, ignore vision updates
                if(Math.abs(m_gyro.getRate()) > 360) {
                    doRejectUpdate = true;
                }
                if(mt2.tagCount == 0) {
                    doRejectUpdate = true;
                }
                if(!doRejectUpdate) {
                    m_poseEstimator.setVisionMeasurementStdDevs(VecBuilder.fill(.7,.7,9999999));
                    m_poseEstimator.addVisionMeasurement(
                        mt2.pose,
                        mt2.timestampSeconds);
                }
            }
        }
    }


    private void forwardPorts() {
        // (robotIP):5801 will now point to a Limelight3A's (id 0) web interface stream:
        // (robotIP):5800 will now point to a Limelight3A's (id 0) video stream:
        PortForwarder.getInstance().add(5801, "172.29.0.1", 5801);
        PortForwarder.getInstance().add(5802, "172.29.0.1", 5802);
        PortForwarder.getInstance().add(5803, "172.29.0.1", 5803);
        PortForwarder.getInstance().add(5804, "172.29.0.1", 5804);
        PortForwarder.getInstance().add(5805, "172.29.0.1", 5805);
        PortForwarder.getInstance().add(5806, "172.29.0.1", 5806);
        PortForwarder.getInstance().add(5807, "172.29.0.1", 5807);
        PortForwarder.getInstance().add(5808, "172.29.0.1", 5808);
        PortForwarder.getInstance().add(5809, "172.29.0.1", 5809);

        // (robotIP):5811 will now point to a Limelight3A's (id 1) web interface stream:
        // (robotIP):5810 will now point to a Limelight3A's (id 1) video stream:
        PortForwarder.getInstance().add(5811, "172.29.1.1", 5801);
        PortForwarder.getInstance().add(5812, "172.29.1.1", 5802);
        PortForwarder.getInstance().add(5813, "172.29.1.1", 5803);
        PortForwarder.getInstance().add(5814, "172.29.1.1", 5804);
        PortForwarder.getInstance().add(5815, "172.29.1.1", 5805);
        PortForwarder.getInstance().add(5816, "172.29.1.1", 5806);
        PortForwarder.getInstance().add(5817, "172.29.1.1", 5807);
        PortForwarder.getInstance().add(5818, "172.29.1.1", 5808);
        PortForwarder.getInstance().add(5819, "172.29.1.1", 5809);
    }
}