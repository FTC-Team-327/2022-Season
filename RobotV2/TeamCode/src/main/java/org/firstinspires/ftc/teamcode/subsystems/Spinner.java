package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;

/**
 * Spinner game piece that turns the duck wheel
 * 
 * By default uses the run to position
 * 
 * By FTC TEAM 327
 */

public class Spinner {

	// ---------------- Declarations

	/** Motor for the spinner wheel */
	private DcMotor motor;

	/** Color sensor to detect cargo presence */
	private DistanceSensor distance_sensor;

	// ---------------- Telemetry
	
	private Telemetry telemetry;

	// ---------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param motor Motor to assign to the spinner
	 * @param distance_sensor Color sensor to detect spinner
	 * @param telemetry Telemetry pass through
	 */

	public Spinner(DcMotor motor, DistanceSensor distance_sensor, Telemetry telemetry) {
		this.motor = motor;
		this.distance_sensor = distance_sensor;
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		resetEncoders();
		disableEncoders();

	}

	/**
	 * Constructor
	 * 
	 * @param distance_sensor Color sensor to detect spinner
	 * @param telemetry Telemetry pass through
	 * @param hardware_map Maps the hardware given the string name of the motor
	 */

	public Spinner(String motor, String distance_sensor, HardwareMap hardware_map, Telemetry telemetry) {
		this.motor = hardware_map.get(DcMotor.class, motor);
		this.distance_sensor = hardware_map.get(DistanceSensor.class, distance_sensor);

		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		resetEncoders();
		disableEncoders();

	}

	/**
	 * Constructor. Uses the assumed motor from the Constants class
	 * 
	 * @see org.firstinspires.ftc.teamcode.Constants
	 * 
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Spinner(HardwareMap hardware_map, Telemetry telemetry) {
		this.motor = hardware_map.get(DcMotor.class, Constants.intake_motor_name);
		this.distance_sensor = hardware_map.get(DistanceSensor.class, Constants.spinner_range_sensor_name);

		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		resetEncoders();
		disableEncoders();

	}

	// ---------------- Presence

	/**
	 * Checks the distance on the color sensor
	 */

	public double pollDistance() {
		double distance = distance_sensor.getDistance(DistanceUnit.CM);
		telemetry.addData("Spinner Presence Distance (cm)", String.format(Locale.US, "%.02f", distance));

		return distance;

	}

	/**
	 * Detect if spinner wheel is underneath
	 */

	public boolean detectPresence() {
		double distance = pollDistance();
		boolean presence = (distance < Constants.spinner_range_sensor_min);
		telemetry.addData("Spinner Present: ", presence);

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

	public void disableEncoders() {
		motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

	}
	
	/**
	 * Enable encoders
	 */

	public void enableEncoders() {
		motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

	}

	/**
	 * Reset encoders
	 */

	public void resetEncoders() {
		motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

	}
	
	/**
	 * Run to position
	 */
	
	public void encodersRunToPos() {
		motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		
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
	 * Run spinner with ticks
	 * 
	 * @param ticks motor ticks
	 */
	
	private void runSpinnerToPos(int ticks, double power) {
		resetEncoders();
		encodersRunToPos();
		
		motor.setTargetPosition(ticks);
		runSpinner(power);
		
	}
	
	/**
	 * Rotates the spinner wheel 
	 */
	
	public void rotateSpinner() { rotateSpinner(1); }
	
	/**
	 * Rotates the spinner wheel 
	 * 
	 * @param rotations Number of rotations of the spinner wheel
	 */
	
	public void rotateSpinner(int rotations) {
		int tpr = Constants.spinner_motor_ticks_per_rev;
		int mvw = Constants.motor_rev_to_wheel_rev;
		
		runSpinnerToPos(rotations * tpr * mvw, 1);
		
	}

	/**
	 * Get the encoder position
	 * 
	 * @return Encoder position
	 */

	public double getEncoderPosition() {
		double position = motor.getCurrentPosition();
		telemetry.addData("Spinner Motor Position: ", position);

		return position;

	}

}