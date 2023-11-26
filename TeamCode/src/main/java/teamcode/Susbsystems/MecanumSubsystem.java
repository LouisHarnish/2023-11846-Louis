package teamcode.Susbsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MecanumSubsystem {

    public DcMotorEx leftFront, leftRear, rightFront, rightRear;

    public void Drive(double v, double v1, double v2, double v3){
        leftFront.setPower(v);
        leftRear.setPower(v1);
        rightFront.setPower(v2);
        rightRear.setPower(v3);
    }

    public MecanumSubsystem(HardwareMap hardwareMap){

        leftFront = hardwareMap.get(DcMotorEx.class,"left_front");
        leftRear = hardwareMap.get(DcMotorEx.class,"left_rear");
        rightFront = hardwareMap.get(DcMotorEx.class,"right_front");
        rightRear = hardwareMap.get(DcMotorEx.class,"right_rear");

        Drive(0,0,0,0);

        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

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

    public void TeleOperatedDrive(double forward, double strafe, double turn) {

        double[] speeds = {
                (forward + strafe + turn),
                (forward - strafe - turn),
                (forward - strafe + turn),
                (forward + strafe - turn)
        };

        double max = Math.abs(speeds[0]);
        for(int i = 0; i < speeds.length; i++) {
            if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
        }

        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }

        leftFront.setPower(speeds[0]);
        rightFront.setPower(-1*speeds[1]);
        leftRear.setPower(speeds[2]);
        rightRear.setPower(-1*speeds[3]);
    }


    public void ForwardA(double p, double i){
        TeleOperatedDrive(p,0,0);
        converttoec(i);
        TeleOperatedDrive(0,0,0);
    }

    public void TurnA(double p, double i){
        TeleOperatedDrive(0,0,p);
        converttoec(i);
        TeleOperatedDrive(0,0,0);
    }

    public void SlideA(double p, double i){
        TeleOperatedDrive(0,p,0);
        converttoec(i);
        TeleOperatedDrive(0,0,0);
    }

}
