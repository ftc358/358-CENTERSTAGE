
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Driving358 extends LinearOpMode {


    hardware358 robot = new hardware358(hardwareMap);
    int hi=8;
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 537.6 ;    // eg: REV Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.14);
    static final double     DRIVE_SPEED             = 0.5;
    static final double     TURN_SPEED              = 0.5;
    static final double     INCHES_FOR_RIGHT_ANGLE  = 4;

    static final double     LIFT_COUNTS_FULL_REVOLVE= 1440 /4;

    final double DESIRED_DISTANCE = 8.0; //  this is how close the camera should get to the target (inches)
    //  The GAIN constants set the relationship between the measured position error,
    //  and how much power is applied to the drive motors.  Drive = Error * Gain
    //  Make these values smaller for smoother control.
    final double SPEED_GAIN =   0.02 ;   //  Speed Control "Gain". eg: Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double TURN_GAIN  =   0.01 ;   //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MM_PER_INCH = 25.40 ;   //  Metric conversion

    public void motorStop() {
        robot.LeftFront.setPower(0);
        robot.RightFront.setPower(0);
        robot.LeftBack.setPower(0);
        robot.RightBack.setPower(0);
//        robot.lift.setPower(0);

        //robot.rotateLeft.setPower(0);
        //robot.rotateRight.setPower(0);
//        robot.carousel.setPower(0);
        reset();
    }
    public void reset(){
        robot.LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //.m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //     robot.rotateRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //     robot.rotateRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void runToPosition(){
        robot.LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void runUsingEncoders()
    {
        robot.LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.LeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    //
    public void liftconeauto(int direction, double power,char remaining){
        //remaining is for the number of cones remaining in the stack
        int tickConversion = (int)(COUNTS_PER_MOTOR_REV/(3.14));//145; //How many ticks per 1cm of string pulled
        int cmMoveauto = 0;
        int ticks;
//        if (position.equals("low")){
//            cmMove  = 35;
//        }
//        else if (position.equals("mid")){
//            cmMove  = 60;
//        }
//        else if (position.equals("high")){
//            cmMove  = 85;
//        }





//        switch (remaining){
//            case '5':
//                cmMoveauto = 11;
//                ticks = tickConversion * cmMoveauto * direction;
//                //robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//                robot.lift.setTargetPosition((ticks));
//                robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                robot.lift.setPower(power);
//
//                while (robot.lift.isBusy())
//                {
//
//                }
//                robot.lift.setPower(0);
//                robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//                break;
//
//            case '4':
//                cmMoveauto = 9;
//                ticks = tickConversion * cmMoveauto * direction;
//                robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                robot.lift.setTargetPosition((ticks));
//                robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                robot.lift.setPower(power);
//
//                while (robot.lift.isBusy())
//                {
//
//                }
//                robot.lift.setPower(0);
//                robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                break;
//
//            case '3':
//                cmMoveauto = 7;
//                ticks = tickConversion * cmMoveauto * direction;
//                robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                robot.lift.setTargetPosition((ticks));
//                robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                robot.lift.setPower(power);
//
//                while (robot.lift.isBusy())
//                {
//
//                }
//                robot.lift.setPower(0);
//                robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                break;
//            case '2':
//                cmMoveauto = 5;
//                ticks = tickConversion * cmMoveauto * direction;
//                robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                robot.lift.setTargetPosition((ticks));
//                robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                robot.lift.setPower(power);
//
//                while (robot.lift.isBusy())
//                {
//
//                }
//                robot.lift.setPower(0);
//                robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                break;
//
//            case '1':
//                cmMoveauto = 3;
//                ticks = tickConversion * cmMoveauto * direction;
//                robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                robot.lift.setTargetPosition((ticks));
//                robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                robot.lift.setPower(power);
//
//                while (robot.lift.isBusy())
//                {
//
//                }
//                robot.lift.setPower(0);
//                robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                break;
//        }
//
//
//    }


    public void liftdown(){

        robot.lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        long starting= System.currentTimeMillis();


        robot.lift.setPower(-0.2);
        while (starting - System.currentTimeMillis()<100){
            if(robot.touch.isPressed()){
                robot.lift.setPower(0);
                telemetry.addData("touch pressed",1);
                break;
            }

        }
        robot.lift.setPower(0);


//        while (robot.lift.isBusy()) {
//            if(robot.touch.isPressed()){
//                robot.lift.setPower(0);
//                break;
//            }
//
//        }
//        robot.lift.setPower(-0.2);
//        while (!robot.touch.isPressed()){
//            telemetry.addData("button is pressed doen",1);
//        }

    }


    public void move(double power, char direction, double distance){
        double ticks = COUNTS_PER_INCH * distance/3;
//        double ticks = 7.5* distance;
        switch(direction){
            case 'f':
                //to go forward

                //set target position
                robot.LeftFront.setTargetPosition((int)(ticks));
                robot.LeftBack.setTargetPosition((int)(ticks));
                robot.RightFront.setTargetPosition((int)(ticks));
                robot.RightBack.setTargetPosition((int)(ticks));
                //set run to position
                runToPosition();

                //set drive power for forward
                robot.LeftFront.setPower(power);
                robot.RightFront.setPower(power);
                robot.LeftBack.setPower(power);
                robot.RightBack.setPower(power);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy())
                {

                }
                motorStop();
                runUsingEncoders();
                break;
            case 'b':
                //setting power of motors to go backward
                //set target position
                robot.LeftFront.setTargetPosition((int) -ticks);
                robot.LeftBack.setTargetPosition((int) -ticks);
                robot.RightFront.setTargetPosition((int) -ticks);
                robot.RightBack.setTargetPosition((int) -ticks);
                //set run to position
                runToPosition();

                //set drive power for forward
                robot.LeftFront.setPower(-power);
                robot.RightFront.setPower(-power);
                robot.LeftBack.setPower(-power);
                robot.RightBack.setPower(-power);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy())
                {
                    telemetry.clear();
                    telemetry.addData("Front Left Pos", robot.LeftFront.getCurrentPosition());
                    telemetry.addData("Front Right Pos", robot.RightFront.getCurrentPosition());
                    telemetry.addData("Back Left Pos", robot.LeftBack.getCurrentPosition());
                    telemetry.addData("Back Right (Mephistopheles) Pos", robot.RightBack.getCurrentPosition());
                    telemetry.update();

                }
                motorStop();
                runUsingEncoders();
                break;

            case 'r':
                //to strafe right


                //set target position
                robot.LeftFront.setTargetPosition((int) ticks);
                robot.LeftBack.setTargetPosition((int)-ticks);
                robot.RightFront.setTargetPosition((int)-ticks);
                robot.RightBack.setTargetPosition((int) ticks);
                //set run to position
                runToPosition();
                //set drive power for forward
                robot.LeftFront.setPower(power);
                robot.RightFront.setPower(-power);
                robot.LeftBack.setPower(-power);
                robot.RightBack.setPower(power);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy())
                {

                }
                motorStop();
                runUsingEncoders();
                break;
            case 'l' :
                // to strafe left

                //set target position
                robot.LeftFront.setTargetPosition((int)-ticks);
                robot.LeftBack.setTargetPosition((int)ticks);
                robot.RightFront.setTargetPosition((int)ticks);
                robot.RightBack.setTargetPosition((int)-ticks);
                //set run to position
                runToPosition();

                //set drive power for forward
                robot.LeftFront.setPower(-power);
                robot.RightFront.setPower(power);
                robot.LeftBack.setPower(power);
                robot.RightBack.setPower(-power);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy())
                {

                }
                motorStop();
                runUsingEncoders();
                break;

            default:
                motorStop();
        }
    }


    public void liftlevel(double power, char direction){
        int tickConversion = (int)(Driving358.COUNTS_PER_MOTOR_REV/(3.14));
        int cmMovelift;
        int ticks;
        switch(direction){
            case '1' :
                cmMovelift  = 5;
                ticks = tickConversion * cmMovelift;
                if (power<0){
                    ticks*=-1;
                }
                robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.lift.setTargetPosition((ticks));
                robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lift.setPower(power);
                while (robot.lift.isBusy()) {

                }

                robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                break;
            case '2' ://u for up
                cmMovelift  = 9;

                ticks = tickConversion * cmMovelift;
                if (power<0){
                    ticks*=-1;
                }
                robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.lift.setTargetPosition((ticks));
                robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lift.setPower(power);
                while (robot.lift.isBusy()) {

                }
                robot.lift.setPower(0);
                robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //cmMovelift = 0;
                break;
            case '3'://u for up
                cmMovelift = 3;
                ticks = tickConversion * cmMovelift;
                if (power<0){
                    ticks*=-1;
                }
                robot.lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.lift.setTargetPosition((ticks));
                robot.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.lift.setPower(power);
                while (robot.lift.isBusy()) {

                }
                robot.lift.setPower(0);
                robot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //cmMovelift = 0;
                break;
        }

    }

    public void clawrotate(String position){
        switch (position){
            case "close":
                //robot.leftServo.setPosition(0.95);
                //robot.rightServo.setPosition(0.95);
                robot.clawServo.setPosition(0.0);
                break;
            case "open":
                //robot.leftServo.setPosition(0.6);
                //robot.rightServo.setPosition(0.34);
                robot.clawServo.setPosition(0.5);
                break;
        }


    }
//    public void slidemove(String position){
//        switch (position){
//            case "front":
//
//                robot.slideServo.setPower(0.1);
//                break;
//            case "back":
//
//
//                robot.slideServo.setPower(-0.1);
//                break;
//        }


//    }




    public void rotate(double power, char direction, double angle) {
        double ticks = COUNTS_PER_INCH * angle / 90 * INCHES_FOR_RIGHT_ANGLE;
//        double ticks = 7.5* distance;
        switch(direction){
            case 'r':
                //to turn clockwise
                robot.LeftFront.setTargetPosition((int)ticks);
                robot.LeftBack.setTargetPosition((int)ticks);
                robot.RightFront.setTargetPosition((int)-ticks);
                robot.RightBack.setTargetPosition((int)-ticks);
                //set run to position
                runToPosition();
                //set drive power for forward
                robot.LeftFront.setPower(power);
                robot.RightFront.setPower(-power);
                robot.LeftBack.setPower(power);
                robot.RightBack.setPower(-power);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy())
                {

                }
                motorStop();
                runUsingEncoders();
                break;
            case 'l':
                // to turn counter clockwise
                robot.LeftFront.setTargetPosition((int)-ticks);
                robot.LeftBack.setTargetPosition((int) -ticks);
                robot.RightFront.setTargetPosition((int)ticks);
                robot.RightBack.setTargetPosition((int) ticks);
                //set run to position
                runToPosition();

                //set drive power for forward
                robot.LeftFront.setPower(-power);
                robot.RightFront.setPower(power);
                robot.LeftBack.setPower(-power);
                robot.RightBack.setPower(power);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy())
                {
                    telemetry.clear();
                    telemetry.addData("Front Left Pos", robot.LeftFront.getCurrentPosition());
                    telemetry.addData("Front Right Pos", robot.RightFront.getCurrentPosition());
                    telemetry.addData("Back Left Pos", robot.LeftBack.getCurrentPosition());
                    telemetry.addData("Back Right (Mephistopheles) Pos", robot.RightBack.getCurrentPosition());
                    telemetry.update();
                }
                motorStop();
                runUsingEncoders();
                break;
            default:
                motorStop();
        }
    }
    public void diagonal(double power, char direction, long distance){
        double ticks = 1120/7.5 * distance;
        switch(direction) {
            case '1':
                //forward right

                //set target position

                robot.LeftFront.setTargetPosition((int) (ticks));
                robot.LeftBack.setTargetPosition(0);
                robot.RightFront.setTargetPosition(0);
                robot.RightBack.setTargetPosition((int) ticks);
                //set run to position
                runToPosition();

                //set drive power for forward
                robot.LeftFront.setPower(power);
                robot.RightFront.setPower(0);
                robot.LeftBack.setPower(0);
                robot.RightBack.setPower(power);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy()) {

                }
                motorStop();
                runUsingEncoders();
                break;

            case '2':
                //forward left

                //set target position

                robot.LeftFront.setTargetPosition(0);
                robot.LeftBack.setTargetPosition((int) ticks);
                robot.RightFront.setTargetPosition((int) ticks);
                robot.RightBack.setTargetPosition(0);
                //set run to position
                runToPosition();

                //set drive power for forward
                robot.LeftFront.setPower(0);
                robot.RightFront.setPower(power);
                robot.LeftBack.setPower(power);
                robot.RightBack.setPower(0);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy()) {

                }
                motorStop();
                runUsingEncoders();
                break;
            case '3':
                // go back right

                robot.LeftFront.setTargetPosition(0);
                robot.LeftBack.setTargetPosition((int) -ticks);
                robot.RightFront.setTargetPosition((int) -ticks);
                robot.RightBack.setTargetPosition(0);
                //set run to position
                runToPosition();

                //set drive power for forward
                robot.LeftFront.setPower(0);
                robot.RightFront.setPower(-power);
                robot.LeftBack.setPower(-power);
                robot.RightBack.setPower(0);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy()) {

                }
                motorStop();
                runUsingEncoders();


                break;

            case '4':
//back left
                robot.LeftFront.setTargetPosition((int) -ticks);
                robot.LeftBack.setTargetPosition(0);
                robot.RightFront.setTargetPosition(0);
                robot.RightBack.setTargetPosition((int) -ticks);
                //set run to position
                runToPosition();

                //set drive power for forward
                robot.LeftFront.setPower(-power);
                robot.RightFront.setPower(0);
                robot.LeftBack.setPower(0);
                robot.RightBack.setPower(-power);

                while (robot.LeftFront.isBusy() && robot.LeftBack.isBusy() && robot.RightFront.isBusy() && robot.RightBack.isBusy()) {

                }
                motorStop();
                runUsingEncoders();


                break;
            default:
                motorStop();

        }
    }

}

