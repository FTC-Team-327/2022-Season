package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
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
	private CRServo servo;

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

	public Elevator(DcMotor motor, CRServo servo, TouchSensor limit_switch, Telemetry telemetry) {
		this.motor = motor;
		this.servo = servo;
		this.limit_switch = limit_switch;
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.servo.setDirection(CRServo.Direction.REVERSE);
		
		this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
		this.servo = hardware_map.get(CRServo.class, servo);
		this.limit_switch = hardware_map.get(TouchSensor.class, limit_switch);
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.servo.setDirection(CRServo.Direction.REVERSE);
		
		this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
		this.servo = hardware_map.get(CRServo.class, Constants.scoop_servo_name);
		this.limit_switch = hardware_map.get(TouchSensor.class, Constants.elevator_bottom_limit_switch_name);
		
		this.telemetry = telemetry;

		this.motor.setDirection(DcMotor.Direction.REVERSE);
		this.servo.setDirection(CRServo.Direction.REVERSE);
		
		this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

	}

	// ---------------- Limit Switch

	/**
	 * Check if the limit switch is pressed
	 * 
	 * @return limit switch press status
	 */

	public boolean bottomLimit() {
		boolean bottom_limit = limit_switch.isPressed();
		
		if (bottom_limit) {
			resetEncoders();
			enableEncoders();
			
		}
		
		return bottom_limit;

	}
	
	/**
	 * Top limit
	 */
	
	public boolean topLimit() {
		double top = Constants.elevator_top_limit;
		double tol = Constants.elevator_top_limit_tolerance;
		
		double pos = getEncoderPosition();
		
		boolean top_limit = (pos > (top + (top * tol))) || (pos > (top - (top * tol)));
		
		if (top_limit) {
			resetEncoders();
			enableEncoders();
			
		}
		
		return top_limit;
		
	}

	// ---------------- Move

	/** Reverses the motor direction */
	private void reverseElevatorDirection() { motor.setDirection(DcMotor.Direction.REVERSE); }

	/** Un-reverses motor direction */
	private void forwardElevatorDirection() { motor.setDirection(DcMotor.Direction.FORWARD); }
	
	/** Reverses the servo direction */
	public void reverseScoopDirection() { servo.setDirection(CRServo.Direction.REVERSE); }

	/** Un-reverses servo direction */
	public void forwardScoopDirection() { servo.setDirection(CRServo.Direction.FORWARD); }

	/** Disable encoders */
	public void disableEncoders() { motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); }
	
	/** Enable encoders */
	public void enableEncoders() { motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); }

	/** Reset encoders */
	public void resetEncoders() { motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); }

	/**
	 * Run the Elevator
	 * 
	 * @param power Power (-1, 1) to run motor
	 */

	public void runElevator(double power) {
		// If the bottom limit switch is pressed
		if (bottomLimit()) {
			// If power level makes elevator go up
			if (power > 0) {
				motor.setPower(power);
				
			}
		// If the top limit switch is pressed
		} else if (topLimit()) {
			// If power level makes elevator go down
			if (power < 0) {
				motor.setPower(power);
				
			}

		// If no switch is pressed
		} else {
			motor.setPower(power);
			
		}
	}

	/** Run the Elevator at max speed */
	public void runElevator() {
		runElevator(1.0);
	}

	/** Stop the Elevator */
	public void stopElevator() { motor.setPower(0); }

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

	// ---------------- Move

	/**
	 * Run the scoop
	 *
	 * @param power Power
	 */

	public void runScoop(double power) {
		telemetry.addData("Scoop Motor Power", power);
		servo.setPower(power);

	}

	/** Run the scoop */
	public void runScoop() { runScoop(0.5); }

	/** Stop the scoop */
	public void stopScoop() { runScoop(0); }

}