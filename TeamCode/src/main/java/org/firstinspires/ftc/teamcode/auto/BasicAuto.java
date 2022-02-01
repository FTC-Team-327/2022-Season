package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.subsystems.*;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Drive Left", group = "Linear Opmode")

public class BasicAuto extends LinearOpMode {
    
    // timer
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        // Post update
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        DriveTrain drive = new DriveTrain(new DcMotor[] {
            hardwareMap.get(DcMotor.class, "frontLeft"),
            hardwareMap.get(DcMotor.class, "frontRight"),
            hardwareMap.get(DcMotor.class, "backLeft"),
            hardwareMap.get(DcMotor.class, "backRight")
            
        });
        
        // Arm
        Arm arm = new Arm(hardwareMap.get(DcMotor.class, "arm"), hardwareMap.get(CRServo.class, "intake"));
        arm.resetEncoders();
        arm.enableEncoders();

        waitForStart();
        runtime.reset();
        
        arm.changeAngle(-90);
        sleep(2000);

        drive.mecdrive(-1, 0, 0);
        sleep(1000);
        drive.mecdrive(0, 0, 0);
        
        sleep((long) ((30 - runtime.seconds()) * 1000));

    }

}