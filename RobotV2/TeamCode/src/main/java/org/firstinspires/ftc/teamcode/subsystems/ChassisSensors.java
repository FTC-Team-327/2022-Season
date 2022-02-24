package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;

public class ChassisSensors {

	// --------------------- Range Sensors

	/** Chassis range sensors */
	private ModernRoboticsI2cRangeSensor[] range_sensors;

	/** Ultrasonic range sensor data */
	private double[] range_sensor_data_ultrasonic;

	/** Optical range sensor data */
	private double[] range_sensor_data_optical;

	/** Are the sensors initialized? */
	public boolean range_sensors_initialized = false;

	/** Front range sensor array index */
	public static final int FRONT_RANGE_SENSOR = 0;

	/** Left range sensor array index */
	public static final int LEFT_RANGE_SENSOR = 1;

	/** Right range sensor array index */
	public static final int RIGHT_RANGE_SENSOR = 2;


	// --------------------- Telemetry

	/** Telemetry */
	private Telemetry telemetry;

	// --------------------- Hardware Map

	/** Hardware Map */
	public HardwareMap hardware_map;

	// --------------------- Constructors

	/**
	 * Setup the chassis sensors
	 */

	public ChassisSensors(Telemetry telemetry, HardwareMap hardware_map) {
		this.telemetry = telemetry;
		this.hardware_map = hardware_map;
		
		initRange();

	}

	// --------------------- Init

	/**
	 * Initialize the chassis range sensors
	 */

	private void initRange() {
		// Setup sensors
		range_sensors = new ModernRoboticsI2cRangeSensor[3];
		range_sensors[FRONT_RANGE_SENSOR] = hardware_map.get(ModernRoboticsI2cRangeSensor.class, Constants.chassis_front_range_name);
		range_sensors[LEFT_RANGE_SENSOR] = hardware_map.get(ModernRoboticsI2cRangeSensor.class, Constants.chassis_left_range_name);
		range_sensors[RIGHT_RANGE_SENSOR] = hardware_map.get(ModernRoboticsI2cRangeSensor.class, Constants.chassis_right_range_name);

		range_sensor_data_ultrasonic = new double[range_sensors.length];
		range_sensor_data_optical = new double[range_sensors.length];

		// Set boolean
		range_sensors_initialized = true;

		// Poll rage
		pollRange();

	}

	// --------------------- Operations

	/**
	 * Poll the chassis range sensors
	 */

	public void pollRange() {
		for (int i = 0; i < range_sensors.length; i++) {
			pollRange(i);
			
		}
	}
	
	/**
	 * Poll the chassis range sensor
	 * 
	 * @param sensor Sensor to poll
	 */
	
	public double pollRange(int sensor) {
		if (!range_sensors_initialized) { return 0; }
		
		range_sensor_data_ultrasonic[sensor] = range_sensors[sensor].rawUltrasonic();
		range_sensor_data_optical[sensor] = range_sensors[sensor].rawOptical();

		telemetry.addData("Range Sensor " + sensor, range_sensor_data_ultrasonic[sensor]);
		
		return range_sensor_data_ultrasonic[sensor];
		
	}
	
}
