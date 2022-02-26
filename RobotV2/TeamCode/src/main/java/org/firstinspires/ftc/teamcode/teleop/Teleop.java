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
			double controller_2_multiplier = 0.2;

			// Drive speeds
			forward =   (-gamepad1.left_stick_y + (gamepad2.left_stick_y * controller_2_multiplier)) * y_multiplier;
			strafe  =	(
							(/*gamepad1.left_stick_x*/ - gamepad1.left_trigger + gamepad1.right_trigger) +
							((/*gamepad2.left_stick_x*/ - gamepad2.left_trigger + gamepad2.right_trigger) * controller_2_multiplier)

						) * x_multiplier;

			rotate  =	(gamepad1.right_stick_x + (gamepad2.right_stick_x * controller_2_multiplier)) * -1;

			// Drive
			robot.drivetrain.mecDrive(forward, strafe, rotate);

			// Elevator
			if (gamepad1.dpad_up || gamepad2.dpad_up) { 
				robot.elevator.runElevator(0.75);
				
			} else if (gamepad1.dpad_down || gamepad2.dpad_down) { 
				robot.elevator.runElevator(-0.75);
				
			} else {
				robot.elevator.runElevator(0);
				
			}
			
			// Topper
			if (gamepad1.dpad_left || gamepad2.dpad_left) { 
				robot.topper.runTopper(0.25); 
				
			} else if (gamepad1.dpad_right || gamepad2.dpad_right) { 
				robot.topper.runTopper(-0.25); 
				
			} else {
				robot.topper.runTopper(0);
				
			}
			
			// Intake
			if (gamepad1.a || gamepad2.a) { 
				robot.intake.runIntake(1); 
				
			} else if (gamepad1.y || gamepad2.y) { 
				robot.intake.runIntake(-1); 
				
			} else {
				robot.intake.runIntake(0);
				
			}
			
			// Scoop
			if (gamepad1.x || gamepad2.x) {
				robot.elevator.forwardScoopDirection();
				robot.elevator.runScoop();

			} else if (gamepad1.b || gamepad2.b) {
				robot.elevator.reverseScoopDirection();
				robot.elevator.runScoop();

			} else {
				robot.elevator.stopScoop();

			}

			// Spinner
			if (gamepad1.left_bumper || gamepad2.left_bumper) { 
				robot.spinner.forwardDirection();
				robot.spinner.runSpinner();
				
			} else if (gamepad1.right_bumper || gamepad2.right_bumper) { 
				robot.spinner.reverseDirection();
				robot.spinner.runSpinner();
			
			} else {
				robot.spinner.stopSpinner();

			}

			robot.drivetrain.getEncoderPosition();
			robot.intake.detectPresence();
			robot.spinner.detectPresence();
			//robot.sensors.pollRange();
			
			// Update telemetry
			telemetry.update();
			
		}
	}
	
}

