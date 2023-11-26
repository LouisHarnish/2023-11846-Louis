package teamcode.Susbsystems;

import android.view.TouchDelegate;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class SensorSubsystem {

    public RevColorSensorV3 colorSensor;

    public TouchSensor touchSensor;

    public DigitalChannel magnetSensor;

    public SensorSubsystem(HardwareMap hardwareMap){
        colorSensor = hardwareMap.get(RevColorSensorV3.class,"colorSensor");
        touchSensor = hardwareMap.get(TouchSensor.class,"armSensor");
        magnetSensor = hardwareMap.get(DigitalChannel.class,"eleSensor");
    }

    public int ReturnColor() {
        return colorSensor.argb();

    }

    public int get() {
        return colorSensor.red();
    }

    public int GetColor(){
        int r = colorSensor.red();
        int g = colorSensor.green();
        int b = colorSensor.blue();

        if(r >= g && r >= b){
            // Red
            return 2;
        } else if(g >= b && g >= r){
            //Green
            return 1;
        } else if(b > r && b > g){
            //blue
            return 3;
        }
        return 1;
    }
}
