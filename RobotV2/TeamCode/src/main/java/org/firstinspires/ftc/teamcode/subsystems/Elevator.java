package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.Constants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Elevator game piece
 * 
 * By FTC TEAM 327
 */

public class Elevator {

    // ---------------- Declarations

    /** Motor for the Elevator  */
    private DcMotor motor;

    // ---------------- Constructors

    /**
     * Constructor
     * 
     * @param motor Motor to assign to the Elevator
     */

    public Elevator(DcMotor motor) {
        this.motor = motor;

        this.motor.setDirection(DcMotor.Direction.REVERSE);

    }

    /**
     * Constructor
     * 
     * @param motor Name of the motor from the robot configuration
     * @param hardware_map Maps the hardware given the string name of the motor
     */

    public Elevator(String motor, HardwareMap hardware_map) {
        this.motor = hardware_map.get(DcMotor.class, motor);

        this.motor.setDirection(DcMotor.Direction.REVERSE);

    }

    /**
     * Constructor. Uses the assumed motor from the Constants class
     * 
     * @see org.firstinspires.ftc.teamcode.Constants
     * 
     * @param hardware_map Maps the hardware given the string name of the motor
     */

    public Elevator(HardwareMap hardware_map) {
        this.motor = hardware_map.get(DcMotor.class, Constants.elevator_motor_name);

        this.motor.setDirection(DcMotor.Direction.REVERSE);

    }

    // ---------------- Move

    /**
     * Reverses the motor direction
     */

    public void reverseDirection() {
        motor.setDirection(DcMotor.Direction.REVERSE);

    }

    /**
     * Un-reverses motor direction
     */

    public void forwardDirection() {
        motor.setDirection(DcMotor.Direction.FORWARD);

    }

    /**
     * Run the Elevator
     * 
     * @param power Power (-1, 1) to run motor
     */

    public void runElevator(double power) {
        motor.setPower(power);
    }

    /**
     * Run the Elevator at max speed
     */

    public void runElevator() {
        runElevator(1.0);
    }

    /**
     * Stop the Elevator
     */

    public void stopElevator() {
        motor.setPower(0);

    }

}