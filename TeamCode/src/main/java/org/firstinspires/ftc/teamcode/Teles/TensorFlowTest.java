package org.firstinspires.ftc.teamcode.Teles;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

@TeleOp(name = "TensorFlowTest", group = "Test")
public class TensorFlowTest extends LinearOpMode {
    private static final boolean USE_WEBCAM = true;
    private static final int RESOLUTION_WIDTH = 640;
    private static final int RESOLUTION_HEIGHT = 480;

    private VisionPortal visionPortal;
    TfodProcessor tfodProcessor; //images can be derived from here
    private static final String MODEL_ASSET = "MobileNetV2-320-v1.tflite";
    private static final String[] LABELS = {"pos1", "pos2", "pos3"};


    @Override
    public void runOpMode(){
        initTFOD();

        waitForStart();

        while(opModeIsActive()){

        }
    }

    private void initTFOD(){
        //Can customize initialzation with new TfodProcessor.Builder()...
        tfodProcessor = new TfodProcessor.Builder()
                .setIsModelTensorFlow2(true)
                .setModelInputSize(320)
                .setModelAssetName(MODEL_ASSET)
                .setModelLabels(LABELS)
                .build();
        tfodProcessor.setMinResultConfidence(0.8f);

        if(USE_WEBCAM){
            visionPortal = new VisionPortal.Builder()
                    .setCamera(hardwareMap.get(CameraName.class, "Webcam 1"))
                    .addProcessor(tfodProcessor)
                    .setCameraResolution(new Size(RESOLUTION_WIDTH, RESOLUTION_HEIGHT))
                    .build();
        }else {
            visionPortal = new VisionPortal.Builder()
                    .setCamera(BuiltinCameraDirection.BACK)
                    .addProcessor(tfodProcessor)
                    .setCameraResolution(new Size(RESOLUTION_WIDTH, RESOLUTION_HEIGHT))
                    .build();
        }
    }
}
