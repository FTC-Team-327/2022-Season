package com.jtelaa.util.drive;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDrive extends Drive {

    // --------------------- Constructors

    /**
     * Drive constructor
     * 
     * @param front_left front left motor
     * @param front_right front right motor
     * @param rear_left rear left motor
     * @param rear_right rear right motor
     */

    public MecanumDrive(DcMotor front_left, DcMotor front_right, DcMotor rear_left, DcMotor rear_right) {
        super(front_left, front_right, rear_left, rear_right);

    }

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public MecanumDrive(DcMotor[] motors) {
        super(motors);

    }


    /**
     * Heavily inspired by https://github.com/brandon-gong/ftc-mecanum
     * 
     * takes in values from -1 to 1, for 3 axes.
     * 
     * @param front_back forward/backward motion
     * @param strafe left/right motion
     * @param rotate rotation
     * 
     */

    public void mecdrive(double forward, double strafe, double rotate) {

        // strafe is reversed
        strafe = strafe * -1;

        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).

        // You may need to multiply some of these by -1 to invert direction of
        // the motor.  This is not an issue with the calculations themselves.
        double[] speeds = {
            (forward + strafe + rotate),
            (forward - strafe - rotate),
            (forward - strafe + rotate),
            (forward + strafe - rotate)
        };

        // Because we are adding vectors and motors only take values between
        // [-1,1] we may need to normalize them.

        // Loop through all values in the speeds[] array and find the greatest
        // *magnitude*.  Not the greatest velocity.
        double max = Math.abs(speeds[0]);
        for(int i = 0; i < speeds.length; i++) {
            if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);

        }

        // If and only if the maximum is outside of the range we want it to be,
        // normalize all the other speeds based on the given speed value.
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