package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class testlauncher extends LinearOpMode {
    Servo launcher;

    public void runOpMode() {
        waitForStart();
        launcher = hardwareMap.get(Servo.class, "launcher");

        while (opModeIsActive()) {

            if (gamepad2.right_bumper) {
                launcher.setPosition(1.0);
                telemetry.addData(">", "launching drone");
            }
            else {
                launcher.setPosition(0.0);
                telemetry.addData(">", "nothing");
            }
            telemetry.update();
        }
    }
}










