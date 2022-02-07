package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Drive Train
 */

public class DriveTrain {

	// --------------------- Motor array indexes

	/** Front Left Dc_Motor */
	private final int FRONT_LEFT_MOTOR = 0;
	
	/** Front Right Dc_Motor */
	private final int FRONT_RIGHT_MOTOR = 1;
	
	/** Rear Left Dc_Motor */
	private final int REAR_LEFT_MOTOR = 2;
	
	/** Rear Right Dc_Motor */
	private final int REAR_RIGHT_MOTOR = 3;

	// --------------------- Motor objects

	/** Motors */
	private DcMotor[] motors;
	
	// --------------------- Telemetry

	/** Telemetry */
	private Telemetry telemetry;

	// --------------------- Constructors

	/**
	 * Drive constructor (See public ints for indexes)
	 * 
	 * @param motors Motor array
	 */

	public DriveTrain(DcMotor[] motors, Telemetry telemetry) {
		// Set the global motor list
		this.motors = motors;
		
		// Telemetry
		this.telemetry = telemetry;

		// Config motors
		configureMotors();

	}
	
	/**
	 * Drive constructor (See public ints for indexes)
	 * 
	 * @param motor_ids Motor ids from hardware map
	 * @param hardware_map Hardware mapper
	 */

	public DriveTrain(String[] motors, HardwareMap hardware_map, Telemetry telemetry) {
		// Set the global motor list
		this.motors = new DcMotor[motors.length];
		
		// Telemetry
		this.telemetry = telemetry;
		
		// Map all motors
		for (int i = 0; i < motors.length; i++) {
			this.motors[i] = hardware_map.get(DcMotor.class, motors[i]);
			
		}

		// Config motors
		configureMotors();

	}

	/**
	 * Drive constructor
	 * 
	 * @param front_left_motor front left motor
	 * @param front_right_motor front right motor
	 * @param rear_left_motor rear left motor
	 * @param rear_right_motor rear right motor
	 * @param telemetry telemetry
	 */

	public DriveTrain(DcMotor front_left_motor, DcMotor front_right_motor, DcMotor rear_left_motor, DcMotor rear_right_motor, Telemetry telemetry) {
		// Telemetry
		this.telemetry = telemetry;
		
		// Motor setup
		motors = new DcMotor[4];
		
		// Set motors
		motors[FRONT_LEFT_MOTOR] = front_left_motor;
		motors[FRONT_LEFT_MOTOR] = front_right_motor;
		motors[REAR_LEFT_MOTOR]  = rear_left_motor;
		motors[REAR_RIGHT_MOTOR] = rear_right_motor;
		
		// Config motors
		configureMotors();

	}
	
	/**
	 * Configure the motors (called from instructor)
	 */
	
	private void configureMotors() {
		// Set every other motor to forwards
		for (int i = 0; i < motors.length; i++) {
			if (i % 2 == 0) {
				motors[i].setDirection(DcMotor.Direction.FORWARD);

			}
		}

		// Set every other motor to revers
		for (int i = 0; i < motors.length; i++) {
			if (i % 2 == 1) {
				motors[i].setDirection(DcMotor.Direction.REVERSE);

			}
		}

		// Zero power behavior
		for (DcMotor motor : motors) {
			motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		}
	}

	/**
	 * Brake
	 */

	public void brake() {
		for (DcMotor motor : motors) {
			motor.setPower(0);

		}
	}

	/**
	 * Drive
	 * 
	 * @param motor Motor index to control
	 * 
	 */

	public void setPower(int motor, double power) {
		motors[motor].setPower(power);
		telemetry.addData("Motor " + motor, "Power " + power);

	}

	/**
	 * Simple drive 
	 * 
	 * @param left left motor power (left motors have even index)
	 * @param right right motor power (right motors have even index)
	 */

	public void tankDrive(double left, double right) {
		for (int i = 0; i < motors.length; i++) {
			if (i % 2 == 0) {
				setPower(i, left);
				
			}
		}

		for (int i = 0; i < motors.length; i++) {
			if (i % 2 == 1) {
				setPower(i, right);
				
			}
		}
		
	}

	/**
	 * takes in values from -1 to 1, for 3 axes.
	 * 
	 * @param front_back forward/backward motion
	 * @param strafe left/right motion
	 * @param rotate rotation
	 * 
	 */
	 
	public void mecDrive(double forward, double strafe, double rotate) {
		// strafe is reversed
		strafe = strafe * -1;

		double[] speeds = {
			(forward + strafe + rotate),
			(forward - strafe - rotate),
			(forward - strafe + rotate),
			(forward + strafe - rotate)
		};

		
		double max = Math.abs(speeds[0]);
		for(int i = 0; i < speeds.length; i++) {
			if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);

		}

		if (max > 1) {
			for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
		}

		// apply the calculated values to the motors.
		for (int i = 0; i < motors.length; i++) {
			setPower(i, speeds[i]);
			
		}
		
		//motors[FRONT_LEFT_MOTOR].setPower(speeds[0]);
		//motors[FRONT_RIGHT_MOTOR].setPower(speeds[1]);
		//motors[REAR_LEFT_MOTOR].setPower(speeds[2]);
		//motors[REAR_RIGHT_MOTOR].setPower(speeds[3]);

	}
	
}
