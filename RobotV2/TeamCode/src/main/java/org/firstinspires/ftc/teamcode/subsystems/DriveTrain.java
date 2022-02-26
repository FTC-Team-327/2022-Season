package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Drive Train
 */

public class DriveTrain {

	// --------------------- Motor array indexes

	/**
	 * Public enumerator of drive motors ids
	 */

	public enum MOTOR_ID {
		/** Front Left Dc_Motor */
		FRONT_LEFT_MOTOR(0),

		/** Front Right Dc_Motor */
		FRONT_RIGHT_MOTOR(1),

		/** Rear Left Dc_Motor */
		REAR_LEFT_MOTOR(2),

		/** Rear Right Dc_Motor */
		REAR_RIGHT_MOTOR(3)

		;

		/** id of the motor within the drivetrain array */
		private final int id;

		/**
		 * Constructor for the motor id index enum
		 *
		 * @param id id of the motor within the drivetrain array
		 */

		MOTOR_ID(int id) { this.id = id; }

		/**
		 * Get the id of the motor
		 *
		 * @return motor id
		 */

		public int getID() { return id; }

		/**
		 * Get the object given the id number
		 *
		 * @param id Id of the motor
		 *
		 * @return Motor enum object
		 *
		 * @throws IndexOutOfBoundsException Motor index out of bounds
		 */

		public static MOTOR_ID getMotorEnum(int id) throws IndexOutOfBoundsException {
			// Look through all defined motors
			for (MOTOR_ID e : values()) {
				if (e.getID() == id) {
					return e;

				}
			}

			throw new IndexOutOfBoundsException();
		}

		/**
		 * Gets the name of the motor
		 *
		 * @param id Id of the motor
		 *
		 * @return name of the motor
		 */

		public static String getMotorName(int id) {
			try {
				return getMotorEnum(id).toString().toLowerCase().replace("_", " ");

			} catch (IndexOutOfBoundsException e) {
				return "Null Motor";

			}
		}

	}

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
	 * @param telemetry Robot telemetry pass-through
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
	 * @param motors Motor ids from hardware map
	 * @param hardware_map Hardware mapper
	 * @param telemetry Robot telemetry pass-through
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
	 * Drive constructor (See public ints for indexes). Uses assumed names from constants
	 * 
	 * @see org.firstinspires.ftc.teamcode.Constants
	 * 
	 * @param hardware_map Hardware mapper
	 * @param telemetry Robot telemetry pass-through
	 */

	public DriveTrain(HardwareMap hardware_map, Telemetry telemetry) {
		this(
			new String[] { 
				Constants.front_left_motor_name,  
				Constants.front_right_motor_name,
				Constants.rear_left_motor_name,
				Constants.rear_right_motor_name
				
			},

			hardware_map,
			telemetry

		);

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
		motors[MOTOR_ID.FRONT_LEFT_MOTOR.getID()] = front_left_motor;
		motors[MOTOR_ID.FRONT_RIGHT_MOTOR.getID()] = front_right_motor;
		motors[MOTOR_ID.REAR_LEFT_MOTOR.getID()]  = rear_left_motor;
		motors[MOTOR_ID.REAR_RIGHT_MOTOR.getID()] = rear_right_motor;
		
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

		// Reset and disable encoders
		resetEncoders();
		disableEncoders();
		
	}

	/**
	 * Set the encoder mode on all motors
	 *
	 * @param mode Run mode
	 */

	public void setEncoderMode(DcMotor.RunMode mode) {
		for (DcMotor motor : motors) {
			motor.setMode(mode);

		}
	}

	/** Disable encoders */
	public void disableEncoders() { setEncoderMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); }
	
	/** Enable encoders */
	public void enableEncoders() { setEncoderMode(DcMotor.RunMode.RUN_USING_ENCODER); }

	/** Reset encoders */
	public void resetEncoders() { setEncoderMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); }

	/**
	 * Get the encoder position
	 * 
	 * @param motor Motor to get position from
	 * 
	 * @return Encoder position
	 */

	public double getEncoderPosition(int motor) {
		// Get position
		double position = motors[motor].getCurrentPosition();

		// Output position
		telemetry.addData("Motor " + motor + " Position: ", position);
		return position;

	} 

	/**
	 * Return an array of encoder positions
	 * 
	 * @return encoder positions
	 */

	public double[] getEncoderPosition() {
		// Encoder value array
		double[] encoder_values = new double[motors.length];

		// Get values
		for (int i = 0; i < motors.length; i++) {
			encoder_values[i] = getEncoderPosition(i);

		}

		// Return
		return encoder_values;

	}

	/** Brake */
	public void brake() {
		for (int i = 0; i < motors.length; i++) {
			setPower(i, 0);

		}
	}

	/**
	 * Drive
	 * 
	 * @param motor Motor index to control
	 * @param power Motor power
	 */

	public void setPower(int motor, double power) {
		motors[motor].setPower(power);
		telemetry.addData(MOTOR_ID.getMotorName(motor) + " Power",power);

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
	 * @param forward forward/backward motion
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
