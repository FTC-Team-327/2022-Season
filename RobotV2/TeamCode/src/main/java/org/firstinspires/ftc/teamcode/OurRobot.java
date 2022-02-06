package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.EventLoopManager;
import org.firstinspires.ftc.robotcore.external.Const;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
    
    /** Spinner */
    public Spinner spinner;
    
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
        
        // Initialize Spinner
        initSpinner();
        
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
        drivetrain = new DriveTrain(new DcMotor[] {
            hardware_map.get(DcMotor.class, Constants.front_left_motor_name),
            hardware_map.get(DcMotor.class, Constants.front_right_motor_name),
            hardware_map.get(DcMotor.class, Constants.rear_left_motor_name),
            hardware_map.get(DcMotor.class, Constants.rear_right_motor_name)
            
        });
    }
    
    /**
     * Initialize Spinner
     */
     
    private void initSpinner() {
        // Spinner
        spinner = new Spinner(hardware_map.get(DcMotor.class, Constants.spinner_motor_name));
        
    }

}
