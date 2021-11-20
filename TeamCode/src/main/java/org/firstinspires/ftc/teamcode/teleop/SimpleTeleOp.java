package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="327 Robot", group="Linear Opmode")

public class SimpleTeleOp extends LinearOpMode {

    // Declare OpMode members.
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

        Spinner spinner = new Spinner(hardwareMap.get(DcMotor.class, "spinner"));
        Arm arm = new Arm(hardwareMap.get(DcMotor.class, "arm"), hardwareMap.get(CRServo.class, "intake"));

        //drive.addImu(hardwareMap.get(BNO055IMU.class, "imu"));
        
        // Wait for game to start
        waitForStart();
        runtime.reset();
        
        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Run
            
            double x_multiplier = 1.5;
            double y_multiplier = 1.5;
            
            if (gamepad1.left_trigger > 0) { x_multiplier = 2; }
            
            if (gamepad1.right_trigger > 0) { y_multiplier = 2; }
            
            drive.driveJoystick(gamepad1.right_stick_x*x_multiplier, gamepad1.left_stick_y*y_multiplier);
            
            if (gamepad1.left_bumper) {
                drive.mecdrive(1, 1, 0);
                
            } else if (gamepad1.right_bumper) {
                drive.mecdrive(1, -1, 0);
                
            }
            
            if (gamepad1.dpad_up)           { arm.changeAngle(10); }  
            else if (gamepad1.dpad_down)    { arm.changeAngle(10); }

            
            if (gamepad1.dpad_left)         { spinner.runSpinner(1, 1); } 
            else if (gamepad1.dpad_right)   { spinner.runSpinner(1, -1); }
            
            if (gamepad1.x) { arm.stopArm(); }
            
            if (gamepad1.b) { arm.stopIntake(); }
            
            if (gamepad1.y) { arm.runIntake(1); }
            
            if (gamepad1.a) { arm.runIntake(-1); }
        
            
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
    
}
