package org.firstinspires.ftc.teamcode.goodsh;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Math;

@TeleOp(name="Robot: teleop", group="Robot")
public class teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        hardware358 map = new hardware358(hardwareMap);
        map.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.


            double axial = Math.pow(-gamepad1.left_stick_y, 3);  // Note: pushing stick forward gives negative value
            double lateral = Math.pow(gamepad1.left_stick_x, 3);
            double yaw = Math.pow(gamepad1.right_stick_x, 3);


            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double LeftFrontPower = axial + lateral + yaw;
            double RightFrontPower = axial - lateral - yaw;
            double LeftBackPower   = axial - lateral + yaw;
            double RightBackPower  = axial + lateral - yaw;


            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(LeftFrontPower), Math.abs(RightFrontPower));
            max = Math.max(max, Math.abs(LeftBackPower));
            max = Math.max(max, Math.abs(RightBackPower));

            if (max > 1.0) {
                LeftFrontPower /= max;
                RightFrontPower /= max;
                LeftBackPower   /= max;
                RightBackPower  /= max;
            }
//jacques
            if (gamepad1.left_stick_x > 0.1 || gamepad1.left_stick_y > 0.1 || gamepad1.left_stick_x < -0.1 || gamepad1.left_stick_y < -0.1 || gamepad1.right_stick_x > 0.1 || gamepad1.right_stick_x < -0.1) {
                map.LeftFront.setPower(LeftFrontPower);
                map.RightFront.setPower(RightFrontPower);
                map.LeftBack.setPower(LeftBackPower);
                map.RightBack.setPower(RightBackPower);
            }
            else
                map.LeftFront.setPower(0);
                map.RightFront.setPower(0);
                map.LeftBack.setPower(0);
                map.RightBack.setPower(0);
//
//            }
//
//
//            map.LeftFront.setPower(LeftFrontPower * LeftFrontPower * LeftFrontPower);
//            map.RightFront.setPower(RightFrontPower * RightFrontPower * RightFrontPower);
//            map.LeftBack.setPower(LeftBackPower * LeftBackPower * LeftBackPower);
//            map.RightBack.setPower(RightBackPower * RightBackPower * RightBackPower);

//            map.LeftFront.setPower(LeftFrontPower);
//            map.RightFront.setPower(RightFrontPower);
//            map.LeftBack.setPower(LeftBackPower);
//            map.RightBack.setPower(RightBackPower);


            //Intake
            if (gamepad2.left_bumper) {
                hardware358.intake.setPower(1);
            } else if (gamepad2.left_trigger > 0.05) {
                hardware358.intake.setPower(-1);
            } else {
                hardware358.intake.setPower(0);
            }


            //lift
            if (gamepad2.right_trigger > 0.1) {
                hardware358.lift.setPower(gamepad2.right_trigger);
            } else if (gamepad2.right_bumper) {
                hardware358.lift.setPower(-1);
            } else {
                hardware358.lift.setPower(0);
            }


            //drone_launcher
            if (gamepad1.dpad_up) {
                hardware358.launcher.setPosition(0.5);
                telemetry.addData(">", "launching drone");
            } else {
                hardware358.launcher.setPosition(0.0);
                telemetry.addData(">", "nothing");
            }


            //lift turn (larger radius)
            if (gamepad2.a) {
                hardware358.out1.setPosition(0.53);
                hardware358.out2.setPosition(0.17);
//                telemetry.addData(">", "lift servo moving up");
//                sleep(500);
                hardware358.rotate.setPosition(0.18);

            } else {
                hardware358.out1.setPosition(0.18);
                hardware358.out2.setPosition(0.53);
                hardware358.rotate.setPosition(0);
                telemetry.addData(">", "lift servo at rest");
            }

//
//            boolean pressed = false;
//
//            if ((gamepad2.a) && (pressed == false)){
//                //do something-098765`11`234567890
//                pressed = true;
//            }
//            else if ((gamepad2.a) && (pressed == true)){
//                //do something
//                pressed = false;
//            }
//
////
//            //bucket_turn (smaller radius)
//            if (gamepad2.b) {
//                hardware358.rotate.setPosition(0);
//                telemetry.addData(">", "releasing pixel");
//            } else {
//                hardware358.rotate.setPosition(1);
//                telemetry.addData(">", "not moving");
//            }


            //Bucket_release
            if (gamepad2.dpad_right) {
                hardware358.release.setPower(0.8);
            } else if (gamepad2.dpad_left) {
                hardware358.release.setPower(-0.8);
            } else {
                hardware358.release.setPower(0);
            }

            //hang
//            boolean activated = false;
            double vm1 = 0.0;

            if (gamepad1.left_trigger > 0.05) {
                vm1 = gamepad1.left_trigger;
                telemetry.addData(">", "lifting...");
            } else if (gamepad1.right_trigger > 0.05) {
                // Check if the right stick is pushed forward or backward
                vm1 = -gamepad1.right_trigger;
            }

//                activated = true;
//                telemetry.addData(">", "unlifting...");
//                long wait = System.currentTimeMillis();
//                while (System.currentTimeMillis() - wait < 5000) {
//                }
//                vm1 = -1.0;
//            } else if (activated) {
//                vm1 = -1;
//                telemetry.addData(">", "hanging");
//            } else {
//                // set vm to default to stay up
//                vm1 = 0.0;
//                telemetry.addData(">", "staying still");
//            }

            hardware358.hang1.setPower(vm1);
            hardware358.hang2.setPower(-vm1);
            telemetry.update();

        }
    }
}




