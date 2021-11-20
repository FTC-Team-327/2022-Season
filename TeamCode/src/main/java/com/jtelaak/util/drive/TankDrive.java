package com.jtelaa.util.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


public class TankDrive extends Drive {

    // --------------------- Constructors

    /**
     * Drive constructor
     * 
     * @param front_left front left motor
     * @param front_right front right motor
     * @param rear_left rear left motor
     * @param rear_right rear right motor
     */

    public TankDrive(DcMotor front_left, DcMotor front_right, DcMotor rear_left, DcMotor rear_right) {
        super(front_left, front_right, rear_left, rear_right);

    }

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public TankDrive(DcMotor[] motors) {
        super(motors);

    }

    // --------------------- Drive

    /**
     * 
     */

    public void driveJoystick(double x, double y) {
        y = -y;
        
        double leftPower;
        double rightPower;
        
        // Calculate Power (Drive Train)
        leftPower = Range.clip(y + x, -1.0, 1.0) ;
        rightPower = Range.clip(y - x, -1.0, 1.0) ;
        
        simpledrive(leftPower, rightPower);

    }
    
}
