package teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import teamcode.Susbsystems.ArmSubsystem;
import teamcode.Susbsystems.MecanumSubsystem;
import teamcode.Susbsystems.SensorSubsystem;

@TeleOp
public class TeleopB extends OpMode {

    ArmSubsystem armSubsystem;
    MecanumSubsystem mecanumSubsystem;

    public void init(){
        armSubsystem = new ArmSubsystem(hardwareMap);
        mecanumSubsystem = new MecanumSubsystem(hardwareMap);
    }

    @Override
    public void loop(){

        telemetry.addData("PIVOTPOS", armSubsystem.pivotPos());
        telemetry.addData("EXTENDPOS", armSubsystem.elePos());
        telemetry.update();
        double forward = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        double pivot = gamepad2.left_stick_y;
        double extend = gamepad2.right_stick_y;
        double intake = gamepad2.right_trigger;

        mecanumSubsystem.TeleOperatedDrive(forward,strafe,turn);
        armSubsystem.pivotM(pivot);
        armSubsystem.extendM(extend);
        armSubsystem.intake(intake);

        if(gamepad2.a){
            armSubsystem.launchServo.setPosition(0.65);
        }else if(gamepad2.right_bumper){
            armSubsystem.grabPixel();
        }else if(gamepad2.left_bumper){
            armSubsystem.releasePixel();
        }else if(gamepad2.dpad_up){
            armSubsystem.scorePixelWrist();
        }else if(gamepad2.dpad_down){
            armSubsystem.grabPixelWrist();
        }else if(gamepad2.x){
            armSubsystem.resetPivot();
        }else if(gamepad2.y){
            armSubsystem.resetExtend();
        }



        telemetry.update();
    }
}
