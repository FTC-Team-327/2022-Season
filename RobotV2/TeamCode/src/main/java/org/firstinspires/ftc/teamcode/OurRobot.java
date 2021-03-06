package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.subsystems.*;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.eventloop.EventLoopManager;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Robot hardware definition
 */

public class OurRobot {
	
	// ------ FTC Controller 
	
	/** Telemetry info */
	public Telemetry telemetry;
	
	/** Hardware Map */
	public HardwareMap hardware_map;
	
	// ------ Robot Hardware
	
	/** Drive Train */
	public DriveTrain drivetrain;
	
	/** Chassis Sensors */
	public ChassisSensors sensors;
	
	/** Spinner */
	public Spinner spinner;

	/** Intake */
	public Intake intake;

	/** Elevator */
	public Elevator elevator;

	/** Topper */
	public Topper topper;
	
	// ------ Init
	
	/**
	 * Robot Constructor
	 * 
	 * @param telemetry Telemetry object from opmode
	 */

	public OurRobot(Telemetry telemetry) {
		// Pass through telemetry
		this.telemetry = telemetry;
		
		// Post update
		telemetry.addData("Status", "Hardware Object Created");
		telemetry.update();
		
	}
	
	/**
	 * Initialize robot hardware
	 * 
	 * @param hardware_map Hardware mapper from opmode
	 */
	
	public void init(HardwareMap hardware_map) {
		// Post update
		telemetry.addData("Status", "Initializing");
		telemetry.update();
		
		// Hardware Map
		this.hardware_map = hardware_map;
		
		// Initialize DriveTrain
		initDrivetrain();
		
		// Initialize Chassis Sensors
		//initChassisSensors();
		
		// Initialize Spinner
		initSpinner();

		// Initialize Intake
		initIntake();

		// Initialize Elevator
		initElevator();

		// Initialize Topper
		initTopper();
		
		// Post update
		telemetry.addData("Status", "Initialized");
		telemetry.update();
		
	}
	
	/**
	 * Initialize robot hardware
	 * 
	 * @param hardware_map Hardware mapper from opmode
	 */
	
	public void init_driveonly(HardwareMap hardware_map) {
		// Post update
		telemetry.addData("Status", "Initializing");
		telemetry.update();
		
		// Hardware Map
		this.hardware_map = hardware_map;
		
		// Initialize DriveTrain
		initDrivetrain();
		
		// Post update
		telemetry.addData("Status", "Initialized");
		telemetry.update();
		
	}
	
	/**
	 * Initialize Drivetrain
	 */
	
	private void initDrivetrain() {
		// Setup Drivetrain
		drivetrain = new DriveTrain(
			new DcMotor[] {
				hardware_map.get(DcMotor.class, Constants.front_left_motor_name),
				hardware_map.get(DcMotor.class, Constants.front_right_motor_name),
				hardware_map.get(DcMotor.class, Constants.rear_left_motor_name),
				hardware_map.get(DcMotor.class, Constants.rear_right_motor_name)
			
			},
			telemetry
			
		);
	}
	
	/**
	 * Initialize Sensors
	 */
	
	private void initChassisSensors() {
		// Setup chassis sensors
		sensors = new ChassisSensors(telemetry, hardware_map);
		
	}
	
	/**
	 * Initialize Spinner
	 */
	 
	private void initSpinner() {
		// Spinner
		spinner = new Spinner(
			hardware_map.get(DcMotor.class, Constants.spinner_motor_name),
			hardware_map.get(DistanceSensor.class, Constants.spinner_range_sensor_name),
			telemetry
			
		);
	}

	/**
	 * Initialize Intake
	 */
	 
	private void initIntake() {
		// Intake
		intake = new Intake(
			hardware_map.get(DcMotor.class, Constants.intake_motor_name),
			hardware_map.get(DistanceSensor.class, Constants.intake_sensor_name),
			telemetry

		);
	}

	/**
	 * Initialize Elevator
	 */
	 
	private void initElevator() {
		// Elevator
		elevator = new Elevator(
			hardware_map.get(DcMotor.class, Constants.elevator_motor_name),
			hardware_map.get(CRServo.class, Constants.scoop_servo_name),
			hardware_map.get(TouchSensor.class, Constants.elevator_bottom_limit_switch_name),
			telemetry

		);
	}

	/**
	 * Initialize Topper
	 */
	 
	private void initTopper() {
		// Topper
		topper = new Topper(hardware_map.get(CRServo.class, Constants.topper_servo_name), telemetry);
		
	}

}
