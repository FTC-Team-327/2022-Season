package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Topper game piece
 * 
 * By FTC TEAM 327
 */

public class Elevator {

	// ---------------- Declarations


	/** Servo for the scoop  */
	private Servo Topper;
	
	// ---------------- Telemetry
	
	private Telemetry telemetry;

	// ---------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param servo Servo to assign to the topper
	 * @param telemetry Telemetry pass through
	 */

	public Elevator(Servo servo, Telemetry telemetry) {
		this.servo = servo;
		
		this.telemetry = telemetry;

		this.servo.setDirection(Servo.Direction.REVERSE);

	}

	/**
	 * Constructor
	 * 
	 * @param servo Name of the servo from the robot configuration for the topper
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Elevator(String servo, HardwareMap hardware_map, Telemetry telemetry) {
		this.servo = hardware_map.get(Servo.class, servo);
		
		this.telemetry = telemetry;

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
		this.servo = hardware_map.get(Servo.class, Constants.topper_servo_name);
		
		this.telemetry = telemetry;

		this.servo.setDirection(Servo.Direction.REVERSE);

	}

	// ---------------- Move
	
	/**
	 * Reverses the servo direction
	 */

	public void reverseTopperDirection() {
		servo.setDirection(Servo.Direction.REVERSE);

	}

	/**
	 * Un-reverses servo direction
	 */

	public void forwardTopperDirection() {
		servo.setDirection(Servo.Direction.FORWARD);

	}

	 /**
	 * Run the topper
	 * 
	 * @param pos position
	 */

	public void setTopperPos(double pos) {
		servo.setPosition(pos);
		telemetry.addData("Topper Position: ", getTopperPos());

	}
	
	/**
	 * Get the topper position
	 * 
	 * @return topper position
	 */

	public double getTopperPos() {
		return servo.getPosition();
		
	}

}