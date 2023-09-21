package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Twist2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;

public class LocalizationTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            while (opModeIsActive()) {
                drive.setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -gamepad1.left_stick_y,
                                -gamepad1.left_stick_x
                        ),
                        -gamepad1.right_stick_x
                ));

                drive.updatePoseEstimate();

                telemetry.addData("x", drive.pose.position.x);
                telemetry.addData("y", drive.pose.position.y);
                telemetry.addData("heading", drive.pose.heading);
                telemetry.update();
            }
        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            while (opModeIsActive()) {
                drive.setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -gamepad1.left_stick_y,
                                0.0
                        ),
                        -gamepad1.right_stick_x
                ));

                drive.updatePoseEstimate();

                telemetry.addData("x", drive.pose.position.x);
                telemetry.addData("y", drive.pose.position.y);
                telemetry.addData("heading", drive.pose.heading);
                telemetry.update();
            }
        } else {
            throw new AssertionError();
        }
    }
    public static AprilTagLibrary getCenterStageTagLibrary()
    {
        return new AprilTagLibrary.Builder()
                .addTag(1,"BlueAllianceLeft",
                        2,new VectorF(60.25f, 41.41f,4f), DistanceUnit.INCH,
                        new Quaternion(0.683f,-0.183f,0.183f,0.683f,0))
                .addTag(2,"BlueAllianceCenter",
                        2,new VectorF(60.25f, 35.41f,4f), DistanceUnit.INCH,
                        new Quaternion(0.683f,-0.183f,0.183f,0.683f,0))
                .addTag(3,"BlueAllianceRight",
                        2,new VectorF(60.25f, 29.41f,4f), DistanceUnit.INCH,
                        new Quaternion(0.683f,-0.183f,0.183f,0.683f,0))
                .addTag(4,"BlueAllianceLeft",
                        2,new VectorF(60.25f, -29.41f,4f), DistanceUnit.INCH,
                        new Quaternion(0.683f,-0.183f,0.183f,0.683f,0))
                .addTag(5,"BlueAllianceCenter",
                        2,new VectorF(60.25f, -35.41f,4f), DistanceUnit.INCH,
                        new Quaternion(0.683f,-0.183f,0.183f,0.683f,0))
                .addTag(6,"BlueAllianceRight",
                        2,new VectorF(60.25f, -41.41f,4f), DistanceUnit.INCH,
                        new Quaternion(0.683f,-0.183f,0.183f,0.683f,0))
                .addTag(7,"RedAudienceWallLarge",
                        2,new VectorF(-70.25f, -40.625f,5.5f), DistanceUnit.INCH,
                        new Quaternion(0.7071f,0,0,-7.071f,0))
                .addTag(8,"RedAudienceWallSmall",
                        2,new VectorF(-70.25f, -35.125f,4f), DistanceUnit.INCH,
                        new Quaternion(0.7071f,0,0,-7.071f,0))
                .addTag(9,"BlueAudienceWallSmall",
                        2,new VectorF(-70.25f, 35.125f,4f), DistanceUnit.INCH,
                        new Quaternion(0.7071f,0,0,-7.071f,0))
                .addTag(10,"BlueAudienceWallLarge",
                        2,new VectorF(-70.25f, 40.625f,5.5f), DistanceUnit.INCH,
                        new Quaternion(0.7071f,0,0,-7.071f,0))
                .build();

    }
}


