package com.jtelaa.util.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * 
 */

public class Drive {

    // --------------------- Motor array indexes

    /** */
    public static final int FRONT_LEFT_MOTOR = 0;
    /** */
    public static final int FRONT_RIGHT_MOTOR = 1;
    /** */
    public static final int REAR_LEFT_MOTOR = 2;
    /** */
    public static final int REAR_RIGHT_MOTOR = 3;

    // --------------------- Motor objects

    /** Motors */
    private DcMotor[] motors;

    // --------------------- Constructors

    /**
     * Drive constructor
     * 
     * @param front_left_motor front left motor
     * @param front_right_motor front right motor
     * @param rear_left_motor rear left motor
     * @param rear_right_motor rear right motor
     */

    public Drive(DcMotor front_left_motor, DcMotor front_right_motor, DcMotor rear_left_motor, DcMotor rear_right_motor) {
        motors[FRONT_LEFT_MOTOR] = front_left_motor;
        motors[FRONT_LEFT_MOTOR] = front_right_motor;
        motors[REAR_LEFT_MOTOR]  = rear_left_motor;
        motors[REAR_RIGHT_MOTOR] = rear_right_motor;

        resetEncoders();
        enableEncoders();

    }

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public Drive(DcMotor[] motors) {
        this.motors = motors;

        resetEncoders();
        enableEncoders();

    }

    /**
     * Drive constructor
     * 
     * @param front_left_motor front left motor
     * @param front_right_motor front right motor
     * @param rear_left_motor rear left motor
     * @param rear_right_motor rear right motor
     */

    public Drive(String front_left_motor, String front_right_motor, String rear_left_motor, String rear_right_motor) {
        this(
            hardwareMap.get(DcMotor.class, front_left_motor),
            hardwareMap.get(DcMotor.class, front_right_motor),
            hardwareMap.get(DcMotor.class, rear_left_motor),
            hardwareMap.get(DcMotor.class, rear_right_motor)

        );

    }

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public Drive(String[] motors) {
        DcMotor[] motors_objects = new DcMotor[motors.length];

        for (int i = 0; i < motors.length; i++) {
            motors_objects[i] = hardwareMap.get(DcMotor.class, motors[i]);

        }
        
        this(motors_objects);

    }

    // --------------------- Utils

    /**
     * Reset the encoders
     */

    public void resetEncoders() {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        }
    }

    /**
     * Reset the encoders
     */

    public void enableEncoders() {
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    // --------------------- Drive

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

    public simpledrive(double left, double right) {
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
     * 
     */

    public void forward(double rotations, double power, int heading)v{
        //one rotation goes 11.87in
        double target= (rotations * 360) + motors[FRONT_RIGHT_MOTOR].getCurrentPosition();

        while(motors[FRONT_RIGHT_MOTOR].getCurrentPosition() < target) {    //right side positive lefts side negative
            motors[FRONT_RIGHT_MOTOR].setTargetPosition((int) target);
            motors[FRONT_RIGHT_MOTOR].setPower(power);

            motors[REAR_RIGHT_MOTOR].setTargetPosition((int) target);
            motors[REAR_RIGHT_MOTOR].setPower(power);

            motors[FRONT_LEFT_MOTOR].setTargetPosition((int) target);
            motors[FRONT_LEFT_MOTOR].setPower(-power);

            motors[REAR_LEFT_MOTOR].setTargetPosition((int) target);
            motors[REAR_LEFT_MOTOR].setPower(-power);

            // printData();

            if(angles.firstAngle>convert(2+heading)){
                motors[FRONT_RIGHT_MOTOR].setPower(power/divisor);
                motors[REAR_RIGHT_MOTOR].setPower(power/divisor);

            } else if(angles.firstAngle<convert(-2+heading)){
                motors[FRONT_LEFT_MOTOR].setPower(-power/divisor);
                motors[REAR_LEFT_MOTOR].setPower(-power/divisor);

            }
        }

        brake();

    }
    
    /**
     * 
     */
    
    public void backward(double rotations, double power, int heading) {

        double target=(-rotations*360)+motors[FRONT_RIGHT_MOTOR].getCurrentPosition(); //one rotation goes 11.87in

        while(motors[FRONT_RIGHT_MOTOR].getCurrentPosition()>target){//right side positive lefts side negative
            motors[FRONT_RIGHT_MOTOR].setTargetPosition((int)target);
            motors[FRONT_RIGHT_MOTOR].setPower(-power);

            motors[REAR_RIGHT_MOTOR].setTargetPosition((int)target);
            motors[REAR_RIGHT_MOTOR].setPower(-power);

            motors[FRONT_LEFT_MOTOR].setTargetPosition((int)target);
            motors[FRONT_LEFT_MOTOR].setPower(power);

            motors[REAR_LEFT_MOTOR].setTargetPosition((int)target);
            motors[REAR_LEFT_MOTOR].setPower(power);

            // printData();

            if(angles.firstAngle>convert(2+heading)){
                motors[FRONT_LEFT_MOTOR].setPower(power/divisor);
                motors[REAR_LEFT_MOTOR].setPower(power/divisor);

            } else if(angles.firstAngle<convert(-2+heading)){
                motors[FRONT_RIGHT_MOTOR].setPower(-power/divisor);
                motors[REAR_RIGHT_MOTOR].setPower(-power/divisor);

            }
        }

        brake();

    }

    /**
     * 
     */

    public void turnRight(double power, int heading){
        angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        
        while(angles.firstAngle>heading){
            motors[FRONT_LEFT_MOTOR].setPower(-0.2);
            motors[REAR_LEFT_MOTOR].setPower(-0.2);
            motors[FRONT_RIGHT_MOTOR].setPower(-0.2);
            motors[REAR_RIGHT_MOTOR].setPower(-0.2);

            // printData();

        }

        brake();

    }

    /**
     * 
     */
    
    public void turnLeft(double power, int heading){
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        
        while(angles.firstAngle<heading){
            motors[FRONT_LEFT_MOTOR].setPower(0.2);
            motors[REAR_LEFT_MOTOR].setPower(0.2);
            motors[FRONT_RIGHT_MOTOR].setPower(0.2);
            motors[REAR_RIGHT_MOTOR].setPower(0.2);

            // printData();

        }

        brake();

    }
    
}
