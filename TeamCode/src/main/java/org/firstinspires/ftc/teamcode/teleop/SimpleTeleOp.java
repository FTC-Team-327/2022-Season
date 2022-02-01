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

        // Spinner
        Spinner spinner = new Spinner(hardwareMap.get(DcMotor.class, "spinner"));

        // Arm
        Arm arm = new Arm(hardwareMap.get(DcMotor.class, "arm"), hardwareMap.get(CRServo.class, "intake"));
        arm.resetEncoders();
        arm.enableEncoders();

        double forward;
        double strafe;
        double rotate;

        //drive.addImu(hardwareMap.get(BNO055IMU.class, "imu"));
        
        // Wait for game to start
        waitForStart();
        runtime.reset();
        
        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // ----------------------- Drive

            // Run
            double x_multiplier = 1;
            double y_multiplier = 1;
            
            // Driver gamepad speed modifier
            if (gamepad1.left_trigger > (long) 0.10)  { x_multiplier = 2; }
            if (gamepad1.right_trigger > (long) 0.10) { y_multiplier = 2; }

            // Manipulator gamepad speed modifier
            if (gamepad2.left_trigger > (long) 0.10)  { x_multiplier = 1; }
            if (gamepad2.right_trigger > (long) 0.10) { y_multiplier = 1; }

            // Drive speeds
            forward =   (gamepad1.left_stick_y + gamepad2.left_stick_y) * y_multiplier;
            strafe =    (gamepad1.left_stick_x + gamepad2.left_stick_x) * x_multiplier;
            rotate =    -(gamepad1.right_stick_x + gamepad2.right_stick_x);
            
            // Strafe
            if (gamepad1.left_bumper || gamepad2.left_bumper)           { strafe = -1; } 
            else if (gamepad1.right_bumper || gamepad2.right_bumper)    { strafe = 1; }

            // Drive
            drive.mecdrive(forward, strafe, rotate);
            
            // ----------------------- Arm/Spinner Control

            if (gamepad1.y || gamepad2.y)         { arm.changeAngle(5); }  
            else if (gamepad1.x || gamepad2.x)    { arm.changeAngle(-5); }
            
            if (gamepad1.dpad_left || gamepad2.dpad_left)           { spinner.runSpinner(.75); } 
            else if (gamepad1.dpad_right || gamepad2.dpad_right)    { spinner.runSpinner(-.75); }
            else                                                    { spinner.stopSpinner(); }
            
            if (gamepad1.b || gamepad2.b)       { arm.runIntake(-2); }
            else if (gamepad1.a || gamepad2.a)  { arm.runIntake(2); }
            else                                { arm.stopIntake(); }
            
            telemetry.addData("Encoder Value", arm.encoderValue() );
            telemetry.addData("Arm Angle", arm.getAngle() );
          // telemetry.addData("Desired Angle", arm.detsiredAngle());
            telemetry.update();
        }
    }
    
}
