package org.firstinspires.ftc.teamcode.subsystems;

import com.jtelaa.util.drive.TankDrive;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveTrain extends MecanumDrive {

    // --------------------- Constructors

    /**
     * Drive constructor
     * 
     * @param front_left front left motor
     * @param front_right front right motor
     * @param rear_left rear left motor
     * @param rear_right rear right motor
     */

    public DriveTrain(DcMotor front_left, DcMotor front_right, DcMotor rear_left, DcMotor rear_right) {
        super(front_left, front_right, rear_left, rear_right);

    }

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public DriveTrain(DcMotor[] motors) {
        super(motors);

    }
    
}