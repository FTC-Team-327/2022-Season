package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Drive Train
 */

public class DriveTrain {

    // --------------------- Mec Drive Values

    public double forward;
    public double strafe;
    public double rotate;

    // --------------------- Motor array indexes

    /** Front Left Dc_Motor */
    public final int FRONT_LEFT_MOTOR = 0;
    
    /** Front Right Dc_Motor */
    public final int FRONT_RIGHT_MOTOR = 1;
    
    /** Rear Left Dc_Motor */
    public final int REAR_LEFT_MOTOR = 2;
    
    /** Rear Right Dc_Motor */
    public final int REAR_RIGHT_MOTOR = 3;

    // --------------------- Motor objects

    /** Motors */
    public DcMotor[] motors;

    // --------------------- Constructors

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public DriveTrain(DcMotor[] motors) {
        // Set the global motor list
        this.motors = motors;

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

        //resetEncoders();
        //enableEncoders();

    }
    
    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motor_ids Motor ids from hardware map
     * @param hardware_map Hardware mapper
     */

    public DriveTrain(String[] motors, HardwareMap hardware_map) {
        // Set the global motor list
        DcMotors defined_motors = new DcMotor[motors.length];
        
        for (int i = 0; i < motors.length; i++) {
            defined_motors[i] = hardware_map.get(DcMotos.class, motors[i]);
            
        }

        this(defined_motors);

    }

    /**
     * Drive constructor
     * 
     * @param front_left_motor front left motor
     * @param front_right_motor front right motor
     * @param rear_left_motor rear left motor
     * @param rear_right_motor rear right motor
     */

    public DriveTrain(DcMotor front_left_motor, DcMotor front_right_motor, DcMotor rear_left_motor, DcMotor rear_right_motor) {
        motors = new DcMotor[4];
        
        motors[FRONT_LEFT_MOTOR] = front_left_motor;
        motors[FRONT_LEFT_MOTOR] = front_right_motor;
        motors[REAR_LEFT_MOTOR]  = rear_left_motor;
        motors[REAR_RIGHT_MOTOR] = rear_right_motor;

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
        motors[FRONT_LEFT_MOTOR].setPower(speeds[0]);
        motors[FRONT_RIGHT_MOTOR].setPower(speeds[1]);
        motors[REAR_LEFT_MOTOR].setPower(speeds[2]);
        motors[REAR_RIGHT_MOTOR].setPower(speeds[3]);

    }
    
}
