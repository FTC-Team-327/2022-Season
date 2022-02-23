package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Intake game piece
 * 
 * By FTC TEAM 327
 */

public class Intake {

	// ---------------- Declarations

	/** Motor for the intake wheel */
	private DcMotor motor;

	/** Color sensor to detect cargo presence */
	private ColorSensor color_sensor;

	// ---------------- Telemetry
	
	private Telemetry telemetry;

	// ---------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param motor Motor to assign to the intake
	 * @param color_sensor Color sensor to detect cargo
	 * @param telemetry Telemetry pass through
	 */

	public Intake(DcMotor motor, ColorSensor color_sensor, Telemetry telemetry) {
		this.motor = motor;
		this.color_sensor = color_sensor;

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	/**
	 * Constructor
	 * 
	 * @param motor Name of the motor from the robot configuration
	 * @param color_sensor Color sensor to detect cargo
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Intake(String motor, String color_sensor, HardwareMap hardware_map, Telemetry telemetry) {
		this.motor = hardware_map.get(DcMotor.class, motor);
		this.color_sensor = hardware_map.get(ColorSensor.class, color_sensor);

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	/**
	 * Constructor. Uses the assumed motor from the Constants class
	 * 
	 * @see org.firstinspires.ftc.teamcode.Constants
	 * 
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Intake(HardwareMap hardware_map, Telemetry telemetry) {
		this.motor = hardware_map.get(DcMotor.class, Constants.intake_motor_name);
		this.color_sensor = hardware_map.get(ColorSensor.class, Constants.intake_sensor_name);

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	// ---------------- Presence

	/**
	 * Checks the distance on the color sensor
	 */

	public double pollDistance() {
		double distance = color_sensor.getDistance(DistanceUnit.CM));
		telemetry.addData("Cargo Presence Distance (cm)", String.format(Locale.US, "%.02f", distance);

		return distance;

	}

	/**
	 * Detect if cargo is present
	 */

	public boolean detectPresence() {
		boolean presence = pollDistance() > Constants.intake_sensor_offset && pollDistance < Constants.intake_sensor_max_threshold;
		telemetry.addData("Cargo Present: " + presence);

		return presence;
		
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