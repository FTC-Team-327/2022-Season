package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Elevator game piece
 * 
 * By FTC TEAM 327
 */

public class Elevator {

	// ---------------- Declarations

	/** Motor for the Elevator  */
	private DcMotor motor;

	/** Servo for the scoop  */
	private Servo servo;

	/** Limit switch */
	private TouchSensor limit_switch;
	
	// ---------------- Telemetry
	
	private Telemetry telemetry;

	// ---------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param motor Motor to assign to the Elevator
	 * @param servo Servo to assign to the scoop
	 * @param limit_switch Touch sensor to assign as limit switch
	 * @param telemetry Telemetry pass through
	 */

	public Elevator(DcMotor motor, Servo servo, TouchSensor limit_switch, Telemetry telemetry) {
		this.motor = motor;
		this.servo = servo;
		this.limit_switch = limit_switch;
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.servo.setDirection(Servo.Direction.REVERSE);

	}

	/**
	 * Constructor
	 * 
	 * @param motor Name of the motor from the robot configuration for the elevator
	 * @param servo Name of the servo from the robot configuration for the scoop
	 * @param limit_switch Touch sensor to assign as limit switch
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Elevator(String motor, String servo, String limit_switch, HardwareMap hardware_map, Telemetry telemetry) {
		this.motor = hardware_map.get(DcMotor.class, motor);
		this.servo = hardware_map.get(Servo.class, servo);
		this.limit_switch = hardware_map.get(TouchSensor.class, limit_switch);
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.servo.setDirection(Servo.Direction.REVERSE);

	}

	/**
	 * Constructor. Uses the assumed motor from the Constants class
	 * 
	 * @see org.firstinspires.ftc.teamcode.Constants
	 * 
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Elevator(HardwareMap hardware_map, Telemetry telemetry) {
		this.motor = hardware_map.get(DcMotor.class, Constants.elevator_motor_name);
		this.servo = hardware_map.get(Servo.class, Constants.scoop_servo_name);
		this.limit_switch = hardware_map.get(TouchSensor.class, Constants.elevator_bottom_limit_switch_name);
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.servo.setDirection(Servo.Direction.REVERSE);

	}

	// ---------------- Limit Switch

	/**
	 * Check if the limit switch is pressed
	 * 
	 * @return limit switch press status
	 */

	public boolean pollLimit() {
		return limit_switch.isPressed();

	}

	// ---------------- Move

	/**
	 * Reverses the motor direction
	 */

	public void reverseElevatorDirection() {
		motor.setDirection(DcMotor.Direction.REVERSE);

	}

	/**
	 * Un-reverses motor direction
	 */

	public void forwardElevatorDirection() {
		motor.setDirection(DcMotor.Direction.FORWARD);

	}
	
	/**
	 * Reverses the servo direction
	 */

	public void reverseScoopDirection() {
		servo.setDirection(Servo.Direction.REVERSE);

	}

	/**
	 * Un-reverses servo direction
	 */

	public void forwardScoopDirection() {
		servo.setDirection(Servo.Direction.FORWARD);

	}

	/**
	 * Disable encoders
	 */

	public void disableEncoders() {
		motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

	}

	/**
	 * Reset encoders
	 */

	public void resetEncoders() {
		motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

	}

	/**
	 * Run the Elevator
	 * 
	 * @param power Power (-1, 1) to run motor
	 */

	public void runElevator(double power) {
		if (power > 0 || !pollLimit()) {
			motor.setPower(power);

		}
	}

	/**
	 * Run the Elevator at max speed
	 */

	public void runElevator() {
		runElevator(1.0);
	}

	/**
	 * Stop the Elevator
	 */

	public void stopElevator() {
		motor.setPower(0);

	}

	/**
	 * Get the encoder position
	 * 
	 * @return Encoder position
	 */

	public double getEncoderPosition() {
		double position = motor.getCurrentPosition();
		telemetry.addData("Elevator Motor Position: ", position);

		return position;

	}

	 /**
	 * Run the scoop
	 * 
	 * @param pos position
	 */

	public void setScoopPos(double pos) {
		servo.setPosition(pos);
		telemetry.addData("Scoop Position: ", getScoopPos());

	}
	
	/**
	 * Get the scoop position
	 * 
	 * @return scoop position
	 */

	public double getScoopPos() {
		return servo.getPosition();
		
	}

}