package org.firstinspires.ftc.teamcode;

/**
 * Class that holds the robot configuration information
 */

public class Constants {
	
	// -------------------- Drivetrain

	/** Front Left Drivetrain motor */
	public static final String front_left_motor_name = "front_left";
	
	/** Front Right Drivetrain motor */
	public static final String front_right_motor_name = "front_right";
	
	/** Rear Left Drivetrain motor */
	public static final String rear_left_motor_name = "back_left";
	
	/** Rear Right Drivetrain motor */
	public static final String rear_right_motor_name = "back_right";

	/** Wheel diameter */
	public static final double drive_wheel_diameter = 96;

	/** Encoder ticks per rev */
	public static final double drive_ticks_per_rev = 537.7;

	// -------------------- Chassis Sensors
	
	// ----------- Front Range Sensor

	/** Chassis Front Range Sensor */
	public static final String chassis_front_range_name = "front_range";
	
	/** Chassis Front Range Sensor Offset */
	public static final double chassis_front_range_offset = 0.0;
	
	/** Chassis Front Range Sensor Tolerance */
	public static final double chassis_front_range_tolerance = 0.0;
	
	
	// ----------- Right Range Sensor
	
	/** Chassis Left Range Sensor */
	public static final String chassis_right_range_name = "right_range";
	
	/** Chassis Right Range Sensor Offset */
	public static final double chassis_right_range_offset = 0.0;
	
	/** Chassis Right Range Sensor Tolerance */
	public static final double chassis_right_range_tolerance = 0.0;
	
	
	// ----------- Left Range Sensor
	
	/** Chassis Left Range Sensor */
	public static final String chassis_left_range_name = "left_range";
	
	/** Chassis Left Range Sensor Offset */
	public static final double chassis_left_range_offset = 0.0;
	
	/** Chassis Left Range Sensor Tolerance */
	public static final double chassis_left_range_tolerance = 0.0;
	
	
	

	// -------------------- Spinner

	/** Spinner Motor */
	public static final String spinner_motor_name = "spinner_motor";
	
	/** Spinner motor ticks per rev */
	public static final int spinner_motor_ticks_per_rev = 1120;
	
	/** Spinner motor revolutions per wheel revolution */
	public static final int motor_rev_to_wheel_rev = 8;
	
	/** Spinner Range Sensor */
	public static final String spinner_range_sensor_name = "spinner_range";
	
	/** Spinner Range Sensor Tolerance */
	public static final double spinner_range_sensor_min = 3.0;



	// -------------------- Elevator 

	/** Elevator Slider Motor */
	public static final String elevator_motor_name = "slider_motor";

	/** Elevator Slider Motor */
	public static final String elevator_bottom_limit_switch_name = "slider_bottom_limit";

	/** Elevator Scoop Servo */
	public static final String scoop_servo_name = "scoop_servo";
	
	/** Elevator top limit value */
	public static final double elevator_top_limit = 0.0;
	
	/** Elevator top limit value tolerance */
	public static final double elevator_top_limit_tolerance = 0.0;


	// -------------------- Intake 

	/** Intake Motor */
	public static final String intake_motor_name = "intake_motor";

	/** Intake Presence detector */
	public static final String intake_sensor_name = "intake_presence";

	/** Intake Presence detector range */
	public static final double intake_sensor_max_threshold = 0.0;

	/** Intake Presence detector offet */
	public static final double intake_sensor_offset = 0.0;

	// -------------------- Topper 

	/** Intake Motor */
	public static final String topper_servo_name = "topper_servo";

	
}
