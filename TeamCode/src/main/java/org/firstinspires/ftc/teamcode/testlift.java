package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class testlift extends LinearOpMode {
    DcMotor lift;

    public void runOpMode() {
        waitForStart();
        lift = hardwareMap.get(DcMotor.class, "lift");

        while (opModeIsActive()) {
            double vm = 0.0;
            if (gamepad2.right_stick_y > 0.05 || gamepad2.right_stick_y < -0.05) {
                // Check if the right stick is pushed forward or backward
                vm = -gamepad2.right_stick_y;
                telemetry.addData(">", "lifting...");

                // Limit the maximum speed
                if (vm > 0.5)
                {
                    vm = -0.5;
                }

            } else {
                // set vm to default to stay up
                vm = -0.05;
                telemetry.addData(">", "staying still");
            }
            lift.setPower(vm);
            telemetry.update();

        }
    }
}