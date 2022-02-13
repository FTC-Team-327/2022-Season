package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
	
	// ---------------- Telemetry
	
	private Telemetry telemetry;

	// ---------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param motor Motor to assign to the Elevator
	 * @param servo Servo to assign to the scoop
	 * @param telemetry Telemetry pass through
	 */

	public Elevator(DcMotor motor, Servo servo, Telemetry telemetry) {
		this.motor = motor;
		this.servo = servo;
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.servo.setDirection(Servo.Direction.REVERSE);

	}

	/**
	 * Constructor
	 * 
	 * @param motor Name of the motor from the robot configuration for the elevator
	 * @param servo Name of the servo from the robot configuration for the scoop
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Elevator(String motor, String servo, HardwareMap hardware_map, Telemetry telemetry) {
		this.motor = hardware_map.get(DcMotor.class, motor);
		this.servo = hardware_map.get(Servo.class, servo);
		
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
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.servo.setDirection(Servo.Direction.REVERSE);

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
	 * Run the Elevator
	 * 
	 * @param power Power (-1, 1) to run motor
	 */

	public void runElevator(double power) {
		motor.setPower(power);
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