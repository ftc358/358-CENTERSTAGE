package org.firstinspires.ftc.teamcode.irrelevant;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class testintake extends LinearOpMode {
    DcMotor intake;
    public void runOpMode() {
        waitForStart();
        intake = hardwareMap.get(DcMotor.class, "intake");

        while (opModeIsActive()) {
            ////////////////// INTAKE //////////////////

            // IN //
            if (gamepad2.left_stick_y < -0.5) {
                intake.setPower(1);
                telemetry.addData(">", "intaking...");

            }
            // OUT //
            else if (gamepad2.left_stick_y > 0.5){
                intake.setPower(-1);
                telemetry.addData(">", "spitting out pixels...");
            }
            // at rest //
            else {
                intake.setPower(0);
                telemetry.addData(">", "not intaking");

            }
        }
        // Update telemetry to show the current state
        telemetry.update();

        }
}

