package org.firstinspires.ftc.teamcode.goodsh;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "fakeautoleft", group = "Concept")
public class fakeautoleft extends LinearOpMode {

    hardware358 robot;
    // Distance values in inches calculated based on 1.30 meters and 2.3 meters
    double distanceForward1 = 51.18;  // Convert 1.30 meters to inches (1 meter = 39.3701 inches)
    double distanceForward2 = 155;  // Convert 2.3 meters to inches

    double DRIVE_SPEED = 0.6; // Define DRIVE_SPEED
    double TURN_SPEED = 0.5; // Define TURN_SPEED

    @Override
    public void runOpMode() {
        robot = new hardware358(hardwareMap);  // Initializing the robot instance

        robot.LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        // Assuming drive is a method in DriveController
        drive Drive = new drive(); // Create an instance of the DriveController class

        // Drive forward 1.30 meters (51.18 inches)
        Drive.encoderDrive(DRIVE_SPEED, distanceForward1, distanceForward1, 5.0);

        // Turn right 90 degrees (90-degree turn)
        Drive.encoderDrive(TURN_SPEED, -14.14, 14.14, 4.0);  // Assuming the robot's wheelbase diameter is 18 inches

        // Drive forward 2.3 meters (90.55 inches)
        Drive.encoderDrive(DRIVE_SPEED, distanceForward2, distanceForward2, 5.0);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);  // pause to display final telemetry message.
    }
}

