package org.firstinspires.ftc.teamcode.teleop;

import org.firstinspires.ftc.teamcode.*;
import org.firstinspires.ftc.robotcore.external.Const;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Teleop", group="Linear Opmode")

public class Teleop extends LinearOpMode {

	// Declare OpMode members.
	private ElapsedTime runtime = new ElapsedTime();

	public void runOpMode() {
		OurRobot robot = new OurRobot(telemetry);
		robot.init(hardwareMap);
		
		// Drive Values
		double forward;
		double strafe;
		double rotate;
		
		// Wait for game to start
		waitForStart();
		runtime.reset();
		
		// Run until the end of the match (driver presses STOP)
		while (opModeIsActive()) {

			// Run
			double x_multiplier = 1;
			double y_multiplier = 1;
			
			// Driver gamepad speed modifier
			if (gamepad1.left_trigger > (long) 0.10)  { x_multiplier = 2; }
			if (gamepad1.right_trigger > (long) 0.10) { y_multiplier = 2; }

			// Drive speeds
			forward =   (gamepad1.left_stick_y) * y_multiplier;
			strafe  =	(gamepad1.left_stick_x) * x_multiplier;
			rotate  =	(gamepad1.right_stick_x) * -1;
			
			// Strafe
			if (gamepad1.left_bumper)		 { strafe = -1; } 
			else if (gamepad1.right_bumper)	 { strafe = 1; }

			// Drive
			robot.drivetrain.mecDrive(forward, strafe, rotate);

			// Elevator
			if (gamepad1.dpad_up) { robot.elevator.runElevator(1.0); }
			if (gamepad1.dpad_down) { robot.elevator.runElevator(-1.0); }

			// Spinner
			if (gamepad1.dpad_left) { robot.spinner.runSpinner(1.0); }
			if (gamepad1.dpad_right) { robot.spinner.runSpinner(-1.0); }

			// Scoop
			if (gamepad1.a) { robot.elevator.setScoopPos(0); }
			if (gamepad1.y) { robot.elevator.setScoopPos(180); }

			// Intake
			if (gamepad1.x) { robot.elevator.runIntake(1); }
			if (gamepad1.b) { robot.elevator.runIntake(-1); }

			
			// Update telemetry
			telemetry.update();
			
		}
	}
	
}

