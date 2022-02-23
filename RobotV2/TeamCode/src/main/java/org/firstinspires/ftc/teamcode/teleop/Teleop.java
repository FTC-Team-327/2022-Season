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

			// Drive speeds
			forward =   (gamepad1.left_stick_y + gamepad2.left_stick_y) * y_multiplier;
			strafe  =	(
							(gamepad1.left_stick_x + gamepad1.left_trigger - gamepad1.right_trigger) +
							(gamepad2.left_stick_x + gamepad2.left_trigger - gamepad2.right_trigger)

						) * x_multiplier;

			rotate  =	(gamepad1.right_stick_x + gamepad2.right_stick_x) * -1;

			// Drive
			robot.drivetrain.mecDrive(forward, strafe, rotate);

			// Intake
			if (gamepad1.dpad_up || gamepad2.dpad_up) { robot.intake.runIntake(1) }
			if (gamepad1.dpad_down || gamepad2.dpad_down) { robot.intake.runIntake(-1) }

			// Topper
			if (gamepad1.dpad_left || gamepad2.dpad_left) { robot.topper.runTopper(1.0); }
			if (gamepad1.dpad_right || gamepad2.dpad_right) { robot.topper.runTopper(-1.0); }

			// Elevator
			if (gamepad1.a || gamepad2.a) { robot.elevator.runElevator(1); }
			if (gamepad1.y || gamepad2.y) { robot.elevator.runElevator(-1); }

			// Scoop
			if (gamepad1.x || gamepad2.x) { robot.elevator.setScoopPos(0); }
			if (gamepad1.b || gamepad2.b) { robot.elevator.setScoopPos(180); }

			// Spinner
			if (gamepad1.left_bumper || gamepad2.left_bumper) { robot.spinner.runSpinner(1); }
			if (gamepad1.right_bumper || gamepad2.right_bumper) { robot.spinner.runSpinner(-1); }

			
			// Update telemetry
			telemetry.update();
			
		}
	}
	
}

