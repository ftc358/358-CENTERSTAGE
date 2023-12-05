package org.firstinspires.ftc.teamcode.goodsh;

import android.graphics.Canvas;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class redDetection implements VisionProcessor {
    Telemetry telemetry;
    Mat mat = new Mat();

    Scalar lowRedHSV = new Scalar(0, 50, 50);
    Scalar highRedHSV = new Scalar(30, 255, 255);

    static int readout = 0;

    Scalar Blue = new Scalar(0, 0, 255);
    Scalar Green = new Scalar(0, 255, 0);

    double thresh = 0.24;

    static final Rect LEFT_ROI = new Rect(
            new Point(140+50, 210),
            new Point(140+130, 200+80));
    static final Rect CENTER_ROI = new Rect(
            new Point(230+60,210),
            new Point(230+160,200+50));
    static final Rect RIGHT_ROI = new Rect(
            new Point(320+80, 200),
            new Point(320+160, 200+85));



    public redDetection(Telemetry telemetry) {this.telemetry = telemetry;}
    public void init(int width, int height, CameraCalibration calibration) {
    }

    public Object processFrame(Mat frame, long captureTimeNanos) {

        Imgproc.cvtColor(frame, mat, Imgproc.COLOR_RGB2HSV);
        Core.inRange(mat, lowRedHSV, highRedHSV, mat);

        Mat left = mat.submat(LEFT_ROI);
        Mat center = mat.submat(CENTER_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        int leftPixels = Core.countNonZero(left);
        int rightPixels = Core.countNonZero(right);
        int centerPixels = Core.countNonZero(center);

        int leftValue = Core.countNonZero(left);
        int rightValue = Core.countNonZero(right);
        int centerValue = Core.countNonZero(center);

        left.release();
        center.release();
        right.release();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        Imgproc.rectangle(mat, LEFT_ROI, readout == 1? Green:Blue);
        Imgproc.rectangle(mat, CENTER_ROI, readout == 2? Green:Blue);
        Imgproc.rectangle(mat, RIGHT_ROI, readout == 3? Green:Blue);

        if (leftPixels>rightPixels && leftPixels>centerPixels&&(leftValue>thresh)){
            readout = 1;
        }else if (centerPixels>rightPixels && centerPixels>leftPixels&&(centerValue>thresh)){
            readout = 2;
        }else if (rightPixels>leftPixels && rightPixels>centerPixels&&(rightValue>thresh)){
            readout = 3;
        }else{
            readout = 0;
        }

        mat.copyTo(frame);
        mat.release();
        return null;
    }
    public static int getReadout(){return readout;}
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        // Not useful either
    }


}
