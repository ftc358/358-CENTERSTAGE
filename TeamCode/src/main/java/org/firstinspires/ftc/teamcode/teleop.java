package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp
public class teleop extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        hardware358 map= new hardware358(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            double max;
            double test;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = axial + lateral + yaw;
            double RightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double RightBackPower  = axial + lateral - yaw;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(RightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(RightBackPower));

            if (max > 1.0) {
                leftFrontPower  /= max;
                RightFrontPower /= max;
                leftBackPower   /= max;
                RightBackPower  /= max;
            }

            // This is test code:
            //
            // Uncomment the following code to test your motor directions.
            // Each button should make the corresponding motor run FORWARD.
            //   1) First get all the motors to take to correct positions on the robot
            //      by adjusting your Robot Configuration if necessary.
            //   2) Then make sure they run in the correct direction by modifying the
            //      the setDirection() calls above.
            // Once the correct motors move in the correct direction re-comment this code.

            /*
            leftFrontPower  = gamepad1.x ? 1.0 : 0.0;  // X gamepad
            leftBackPower   = gamepad1.a ? 1.0 : 0.0;  // A gamepad
            rightFrontPower = gamepad1.y ? 1.0 : 0.0;  // Y gamepad
            rightBackPower  = gamepad1.b ? 1.0 : 0.0;  // B gamepad
            */

            // Send calculated power to wheels
            map.LeftFront.setPower(leftFrontPower);
            map.RightFront.setPower(RightFrontPower);
            map.LeftBack.setPower(leftBackPower);
            map.RightBack.setPower(RightBackPower);
/*
            map.LeftFront.setPower(leftFrontPower);
            map.RightFront.setPower(rightFrontPower);
            map.LeftBack.setPower(leftBackPower);
            map.RightBack.setPower(rightBackPower);
           */

            telemetry.update();

            ////////////////// INTAKE //////////////////

            // at rest //
            if (gamepad2.left_stick_y == 0) {
                hardware358.intake.setPower(0);
                telemetry.addData(">", "not intaking");
                telemetry.update();
            }
            // IN //
            else if (gamepad2.left_stick_y < -0.5) {
                hardware358.intake.setPower(0.3);
                telemetry.addData(">", "intaking...");
                telemetry.update();
            }
            // OUT //
            else if (gamepad2.left_stick_y > 0.5){
                hardware358.intake.setPower(-0.3);
                telemetry.addData(">", "spitting out pixels...");
                telemetry.update();
            }
        }
    }
}


