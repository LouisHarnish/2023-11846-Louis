package teamcode.Susbsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.A;

public class ArmSubsystem {

    SensorSubsystem sensorSubsystem;
    ArmConstants armConstants;
    private DcMotorEx pivotMotor, extendMotor, intakeMotor;

    public Servo grabServo, launchServo;
    public Servo wristServo;

    public ArmSubsystem(HardwareMap hardwareMap){
        armConstants = new ArmConstants();
        sensorSubsystem = new SensorSubsystem(hardwareMap);
        pivotMotor = hardwareMap.get(DcMotorEx.class,"pivotMotor");
        extendMotor = hardwareMap.get(DcMotorEx.class,"extendMotor");
        intakeMotor = hardwareMap.get(DcMotorEx.class,"intakeMotor");
        grabServo = hardwareMap.get(Servo.class,"grabServo");
        launchServo = hardwareMap.get(Servo.class,"airplaneServo");
        wristServo = hardwareMap.get(Servo.class,"wristServo");

        pivotMotor.setPower(0);
        extendMotor.setPower(0);
        intakeMotor.setPower(0);

        pivotMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private double converttoec(double inches) {
        double convert = 537.7 / (Math.PI * 3.77953);
        return convert;
    }

    private void wait(double time) {
        ElapsedTime elapsedTime = new ElapsedTime();
        elapsedTime.reset();
        while (elapsedTime.milliseconds() < time) {

        }
    }

    public void positions(int v, int v1, double v2, double v3){
        pivotMotor.setTargetPosition(v);
        extendMotor.setTargetPosition(v1);
        wristServo.setPosition(v2);
        grabServo.setPosition(v3);
    }

//    public void Score(){
//        positions(ArmConstants.ARM_POSITIONS.PIVOT.SCORE_POSITION,ArmConstants.ARM_POSITIONS.EXTEND.SCORE_POSITION,
//                ArmConstants.ARM_POSITIONS.WRIST.SCORE, ArmConstants.ARM_POSITIONS.GRAB.RELEASE);
//    }
//
//    public void Grab() throws InterruptedException {
//        positions(ArmConstants.ARM_POSITIONS.PIVOT.GRAB_POSITION,ArmConstants.ARM_POSITIONS.EXTEND.GRAB_POSITION,
//                ArmConstants.ARM_POSITIONS.WRIST.GRAB, ArmConstants.ARM_POSITIONS.GRAB.RELEASE);
//
//        wait(300);
//
//        grabServo.setPosition(ArmConstants.ARM_POSITIONS.GRAB.GRAB);
//    }
//
//    public void GrabStack() throws InterruptedException {
//        positions(ArmConstants.ARM_POSITIONS.PIVOT.STACK_GRAB_POSITION,ArmConstants.ARM_POSITIONS.EXTEND.STACK_GRAB_POSITION,
//                ArmConstants.ARM_POSITIONS.WRIST.GRAB, ArmConstants.ARM_POSITIONS.GRAB.RELEASE);
//        wait(300);
//        grabServo.setPosition(ArmConstants.ARM_POSITIONS.GRAB.GRAB);
//    }
    public void pivotM(double p){
        pivotMotor.setPower(p);
    }

    public void pivotA(double power, double inches){
        pivotM(power);
        converttoec(inches);
        pivotM(0);
    }
    public void extendM(double p){
        extendMotor.setPower(p);
    }

    public void extendA(double power, double inches){
        extendM(power);
        converttoec(inches);
        extendM(0);
    }

    public void grabPixel(){
        grabServo.setPosition(0);
    }

    public void releasePixel(){
        grabServo.setPosition(1);
    }

    public void grabPixelWrist(){
        wristServo.setPosition(0.46);
    }

    public void scorePixelWrist(){
        wristServo.setPosition(1);
    }

    public void grabStackWrist(){
        wristServo.setPosition(0.5);
    }

    public void launch(){
        launchServo.setPosition(0.6);
    }

    public void intake(double power){
        intakeMotor.setPower(power);
    }

    public void intakeA(double power, double time){
        intakeMotor.setPower(power);
        wait(time);
        intakeMotor.setPower(0);
    }

//    public void Score(){
//        pivotMotor.setTargetPosition(0);
//    }


    public int pivotPos(){
        return pivotMotor.getCurrentPosition();
    }

    public int elePos(){
        return extendMotor.getCurrentPosition();
    }


    public void resetPivot(){
        pivotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetExtend(){
        extendMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extendMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
