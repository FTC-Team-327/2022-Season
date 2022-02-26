package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.util.exceptions.InvalidMotorIDException;
import org.firstinspires.ftc.teamcode.util.exceptions.InvalidMotorNameException;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

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
		 * @throws InvalidMotorIDException Motor index out of bounds
		 */

		public static MOTOR_ID getMotorEnum(int id) throws InvalidMotorIDException {
			// Look through all defined motors
			for (MOTOR_ID e : values()) {
				if (e.getID() == id) {
					return e;

				}
			}

			throw new InvalidMotorIDException(id);
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

			} catch (InvalidMotorIDException e) {
				return "Null Motor";

			}
		}

		/**
		 * Get the motor id by the name
		 *
		 * @param name Name of the motor
		 *
		 * @return Motor id
		 *
		 * @throws InvalidMotorNameException If name is not recognized
		 */

		public static int getIDByName(String name) throws InvalidMotorNameException {
			for (MOTOR_ID e : values()) {
				if (name.equalsIgnoreCase(e.toString())) {
					return e.getID();
				}
			}

			throw new InvalidMotorNameException(name);

		}

		/**
		 * Get the motor ids if the string is contained in the motor name
		 *
		 * @param name String in the of the motor
		 *
		 * @return Motor ids
		 *
		 * @throws InvalidMotorNameException If name is not recognized
		 */

		public static int[] getIDsByName(String name) throws InvalidMotorNameException {
			ArrayList<Integer> ids = new ArrayList<Integer>();

			for (MOTOR_ID e : values()) {
				if (name.contains(e.toString())) {
					ids.add(e.getID());

				}
			}

			if (ids.size() > 0) {
				int[] ids_ar = new int[ids.size()];
				for (int i = 0; i < ids_ar.length; i++) { ids_ar[i] = ids.get(i); }

				return ids_ar;

			} else {
				throw new InvalidMotorNameException(name);

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
		setMecDrive();

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
		setMecDrive();
		setBrakeMode();

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
		setBrakeMode();
		setMecDrive();

	}
	
	/**
	 * Configure the motors (called from instructor)
	 */
	
	private void setMecDrive() {
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

		// Reset and disable encoders
		resetEncoders();
		disableEncoders();
		
	}

	/**
	 * Set zero power brake mode
	 */

	private void setBrakeMode() {
		// Zero power behavior
		for (DcMotor motor : motors) {
			motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		}
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

	/** Run to position */
	public void encodersRunToPos() { setEncoderMode(DcMotor.RunMode.RUN_TO_POSITION); }

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

	/**
	 * Get the encoder position of specific motor ids
	 *
	 * @param ids Ids of the motors
	 *
	 * @return Encoder positions of those motors
	 */

	public double[] getEncoderPosition(int[] ids) {
		double[] values = new double[ids.length];

		for (int i = 0; i < values.length; i++) {
			values[i] = getEncoderPosition(ids[i]);

		}

		return values;
	}

	/** Brake */
	public void brake() {
		for (int i = 0; i < motors.length; i++) {
			setPower(i, 0);

		}
	}

	/**
	 * Run to position
	 *
	 * @param ticks Encoder ticks
	 * @param power Motor power
	 */

	public void runToPosition(int ticks, double power) {
		// Telemetry
		telemetry.addData("Drive Target Position",  ticks);

		// Set run mode
		resetEncoders();
		setEncoderMode(DcMotor.RunMode.RUN_TO_POSITION);

		// Set target position
		for (DcMotor motor : motors) {
			motor.setTargetPosition(ticks);

		}

		// Set power
		setPower(power);

	}

	/**
	 * Drive distance
	 *
	 * @param distance Distance to drive
	 * @param power Drive power
	 */

	public void driveDistance(double distance, double power) {
		double wheel_circumference = Constants.drive_wheel_diameter * Math.PI / 2;
		int ticks = (int) Math.round(Constants.drive_ticks_per_rev * distance / wheel_circumference);

		runToPosition(ticks, power);

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
	 * Set power to all motors
	 *
	 * @param power Motor power
	 */

	public void setPower(double power) {
		for (int i = 0; i < motors.length; i++) {
			setPower(i, power);

		}
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
