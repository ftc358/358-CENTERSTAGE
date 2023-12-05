package org.firstinspires.ftc.teamcode.irrelevant;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class testOuttake extends LinearOpMode {
    DcMotor LeftFront, RightFront, LeftBack, RightBack;

    @Override
    public void runOpMode() {
        LeftFront = hardwareMap.get(DcMotor.class, "lf");
        RightFront = hardwareMap.get(DcMotor.class, "rf");
        LeftBack = hardwareMap.get(DcMotor.class, "lb");
        RightBack = hardwareMap.get(DcMotor.class, "rb");

        waitForStart();

        while (opModeIsActive()) {
            double axial = 0.75 * (-gamepad1.left_stick_y);
            double lateral = 0.75 * gamepad1.left_stick_x;
            double yaw = 0.75 * gamepad1.right_stick_x;

            double LeftFrontPower = axial + lateral + yaw;
            double RightFrontPower = axial - lateral - yaw;
            double LeftBackPower = axial - lateral + yaw;
            double RightBackPower = axial + lateral - yaw;

            double max = Math.max(Math.abs(LeftFrontPower), Math.abs(RightFrontPower));
            max = Math.max(max, Math.abs(LeftBackPower));
            max = Math.max(max, Math.abs(RightBackPower));

            if (max > 1.0) {
                LeftFrontPower /= max;
                RightFrontPower /= max;
                LeftBackPower /= max;
                RightBackPower /= max;
            }

            LeftFront.setPower(LeftFrontPower);
            RightFront.setPower(RightFrontPower);
            LeftBack.setPower(LeftBackPower);
            RightBack.setPower(RightBackPower);
        }
    }
}



