package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Spinner game piece that turns the duck wheel
 * 
 * By FTC TEAM 327
 */

public class Spinner {

	// ---------------- Declarations

	/** Motor for the spinner wheel */
	private DcMotor motor;

	/** Color sensor to detect cargo presence */
	private ColorSensor color_sensor;

	// ---------------- Telemetry
	
	private Telemetry telemetry;

	// ---------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param motor Motor to assign to the spinner
	 * @param color_sensor Color sensor to detect spinner
	 * @param telemetry Telemetry pass through
	 */

	public Spinner(DcMotor motor, ColorSensor color_sensor, Telemetry telemetry) {
		this.motor = motor;
		this.color_sensor = color_sensor;
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	/**
	 * Constructor
	 * 
	 * @param color_sensor Color sensor to detect spinner
	 * @param telemetry Telemetry pass through
	 * @param hardware_map Maps the hardware given the string name of the motor
	 */

	public Spinner(String motor, String color_sensor, HardwareMap hardware_map, Telemetry telemetry) {
		this.motor = hardware_map.get(DcMotor.class, motor);
		this.color_sensor = hardware_map.get(ColorSensor.class, color_sensor);

		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	/**
	 * Constructor. Uses the assumed motor from the Constants class
	 * 
	 * @see org.firstinspires.ftc.teamcode.Constants
	 * 
	 * @param hardware_map Maps the hardware given the string name of the motor
	 */

	public Spinner(HardwareMap hardware_map) {
		this.motor = hardware_map.get(DcMotor.class, Constants.intake_motor_name);
		this.color_sensor = hardware_map.get(ColorSensor.class, Constants.spinner_range_sensor_name);

		this.motor.setDirection(DcMotor.Direction.REVERSE);

	}

	// ---------------- Presence

	/**
	 * Checks the distance on the color sensor
	 */

	public double pollDistance() {
		double distance = sensorDistance.getDistance(DistanceUnit.CM));
		telemetry.addData("Spinner Presence Distance (cm)", String.format(Locale.US, "%.02f", distance);

		return distance;

	}

	/**
	 * Detect if cargo is present
	 */

	public boolean detectPresence() {
		boolean presence = pollDistance() > Constants.spinner_range_sensor_offset && pollDistance < Constants.spinner_range_sensor_tolerance;
		telemetry.addData("Spinner Present: " + presence);

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
	 * Disable encoders
	 */

	private void disableEncoders() {
		motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

	}

	/**
	 * Reset encoders
	 */

	private void resetEncoders() {
		motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

	}

	/**
	 * Run the spinner
	 * 
	 * @param power Power (-1, 1) to run motor
	 */

	public void runSpinner(double power) {
		motor.setPower(power);
	}

	/**
	 * Run the spinner at max speed
	 */

	public void runSpinner() {
		runSpinner(1.0);
	}

	/**
	 * Stop the spinner
	 */

	public void stopSpinner() {
		motor.setPower(0);

	}

	/**
	 * Get the encoder position
	 * 
	 * @return Encoder position
	 */

	private double getEncoderPosition() {
		double position = motor.getCurrentPosition();
		telemetry.addData("Spinner Motor Position: ", position);

		return position;

	}

}