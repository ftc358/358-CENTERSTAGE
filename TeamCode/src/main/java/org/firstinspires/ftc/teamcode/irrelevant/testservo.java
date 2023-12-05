package org.firstinspires.ftc.teamcode.irrelevant;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class testservo extends LinearOpMode {
    CRServo lift_servo2;
    CRServo lift_servo1;

    public void runOpMode() {
        waitForStart();
        lift_servo2 = hardwareMap.get(CRServo.class, "lift_servo2");
        lift_servo1 = hardwareMap.get(CRServo.class, "lift_servo1");

        while (opModeIsActive()) {
            if (gamepad2.dpad_up) {
                //map.lift_servo1.setPower(0.3);
                lift_servo2.setPower(-0.5);
                lift_servo1.setPower(0.5);
                telemetry.addData(">", "Lift servo moving up");
            } else if (gamepad2.dpad_down) {
                //map.lift_servo1.setPower(-0.3);
                lift_servo2.setPower(0.5);
                lift_servo1.setPower(-0.5);
                telemetry.addData(">", "Lift servo moving down");
            } else {
                //ardware358.lift_servo1.setPower(0);
                lift_servo2.setPower(0);
                telemetry.addData(">", "Lift servo at rest");
            }

            // Update telemetry to show the current state
            telemetry.update();

        }


    }

}

