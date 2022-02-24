package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Topper game piece
 * 
 * By FTC TEAM 327
 */

public class Topper {

	// ---------------- Declarations


	/** Servo for the scoop  */
	private CRServo servo;
	
	// ---------------- Telemetry
	
	private Telemetry telemetry;

	// ---------------- Constructors

	/**
	 * Constructor
	 * 
	 * @param servo Servo to assign to the topper
	 * @param telemetry Telemetry pass through
	 */

	public Topper(CRServo servo, Telemetry telemetry) {
		this.servo = servo;
		
		this.telemetry = telemetry;

		this.servo.setDirection(CRServo.Direction.REVERSE);

	}

	/**
	 * Constructor
	 * 
	 * @param servo Name of the servo from the robot configuration for the topper
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Topper(String servo, HardwareMap hardware_map, Telemetry telemetry) {
		this.servo = hardware_map.get(CRServo.class, servo);
		
		this.telemetry = telemetry;

		this.servo.setDirection(CRServo.Direction.REVERSE);

	}

	/**
	 * Constructor. Uses the assumed motor from the Constants class
	 * 
	 * @see org.firstinspires.ftc.teamcode.Constants
	 * 
	 * @param hardware_map Maps the hardware given the string name of the motor
	 * @param telemetry Telemetry pass through
	 */

	public Topper(HardwareMap hardware_map, Telemetry telemetry) {
		this.servo = hardware_map.get(CRServo.class, Constants.topper_servo_name);
		
		this.telemetry = telemetry;

		this.servo.setDirection(CRServo.Direction.REVERSE);

	}

	// ---------------- Move
	
	/**
	 * Reverses the servo direction
	 */

	public void reverseTopperDirection() {
		servo.setDirection(CRServo.Direction.REVERSE);

	}

	/**
	 * Un-reverses servo direction
	 */

	public void forwardTopperDirection() {
		servo.setDirection(CRServo.Direction.FORWARD);

	}

	 /**
	 * Run the topper
	 * 
	 * @param power Power
	 */

	public void runTopper(double power) {
		servo.setPower(power);

	}
	
	
	

}