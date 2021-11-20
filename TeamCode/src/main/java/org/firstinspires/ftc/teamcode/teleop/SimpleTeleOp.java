package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.*;

@TeleOp(name="Test Robot", group="Linear Opmode")

public class SimpleTeleOp extends LinearOpMode {

    // Declare OpMode members.
	private ElapsedTime runtime = new ElapsedTime();

	public void runOpMode() {
		// Post update
		telemetry.addData("Status", "Initialized");
		telemetry.update();
		
		DriveTrain drive = new DriveTrain(new String{"fl", "fr", "rl", "rr"});
		
		// Wait for game to start
		waitForStart();
		runtime.reset();
		
		// Run until the end of the match (driver presses STOP)
		while (opModeIsActive()) {
			// Run
			drive.simpledrive(gamepad1.right_stick_x, gamepad1.left_stick_y);
			
			telemetry.addData("Status", "Run Time: " + runtime.toString());
		}
	}
    
}
