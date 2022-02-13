package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Intake game piece
 * 
 * By FTC TEAM 327
 */

public class Intake {

	// ---------------- Declarations

	/** Motor for the intake wheel */
	private DcMotor motor;

	// ---------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param motor Motor to assign to the intake
	 */

	public Intake(DcMotor motor) {
		this.motor = motor;

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	/**
	 * Constructor
	 * 
	 * @param motor Name of the motor from the robot configuration
	 * @param hardware_map Maps the hardware given the string name of the motor
	 */

	public Intake(String motor, HardwareMap hardware_map) {
		this.motor = hardware_map.get(DcMotor.class, motor);

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	/**
	 * Constructor. Uses the assumed motor from the Constants class
	 * 
	 * @see org.firstinspires.ftc.teamcode.Constants
	 * 
	 * @param hardware_map Maps the hardware given the string name of the motor
	 */

	public Intake(HardwareMap hardware_map) {
		this.motor = hardware_map.get(DcMotor.class, Constants.intake_motor_name);

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	// ---------------- Move

	/**
	 * Reverses the motor direction
	 */

	public void reverseDirection() {
		motor.setDirection(DcMotor.Direction.REVERSE);

	}

	/**
	 * Un-reverses motor direction
	 */

	public void forwardDirection() {
		motor.setDirection(DcMotor.Direction.FORWARD);

	}

	/**
	 * Run the spinner
	 * 
	 * @param power Power (-1, 1) to run motor
	 */

	public void runIntake(double power) {
		motor.setPower(power);
	}

	/**
	 * Run the spinner at max speed
	 */

	public void runIntake() {
		runIntake(1.0);
	}

	/**
	 * Stop the spinner
	 */

	public void stopIntake() {
		motor.setPower(0);

	}

}