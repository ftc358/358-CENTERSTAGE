package org.firstinspires.ftc.teamcode.irrelevant;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class testhang extends LinearOpMode {
    DcMotor hang1;
    DcMotor hang2;

    public void runOpMode() {
        waitForStart();
        hang1 = hardwareMap.get(DcMotor.class, "hang1");
        hang2 = hardwareMap.get(DcMotor.class, "hang2");

        while (opModeIsActive()) {
            double vm1 = 0.0;
            boolean firstActivated = false;

            if (gamepad2.left_trigger > 0.05) {
                // Check if the right stick is pushed forward or backward

                telemetry.addData(">", "lifting...");
                telemetry.addData("power is",-vm1);
            }
            else if (gamepad2.right_trigger > 0.05) {
                // Check if the right stick is pushed forward or backward
                vm1 = -gamepad2.right_trigger;
                firstActivated = true;
                telemetry.addData(">", "unlifting...");
                long wait =System.currentTimeMillis();
                while (((System.currentTimeMillis()-wait<5000))){
                }
                vm1=1.0;

            }else if (firstActivated){
                vm1 = 1;
            }
            else {
                // set vm to default to stay up
                vm1 = 0.0;
                telemetry.addData(">", "staying still");
            }
            hang1.setPower(-vm1);
            hang2.setPower(-vm1);
            //hang2.setPower(vm1);
            telemetry.update();

        }
    }
}
