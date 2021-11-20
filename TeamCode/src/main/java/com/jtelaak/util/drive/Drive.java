package com.jtelaa.util.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.hardware.bosch.BNO055IMU;

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
    public DcMotor[] motors;

    // --------------------- Angles / IMU

    public Orientation angles;
    public BNO055IMU imu;

    // --------------------- Constructors

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public Drive(DcMotor[] motors) {
        this.motors = motors;

        for (int i = 0; i < motors.length; i++) {
            if (i % 2 == 0) {
                motors[i].setDirection(DcMotor.Direction.FORWARD);

            }
        }

        for (int i = 0; i < motors.length; i++) {
            if (i % 2 == 1) {
                motors[i].setDirection(DcMotor.Direction.REVERSE);

            }
        }

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

    public Drive(DcMotor front_left_motor, DcMotor front_right_motor, DcMotor rear_left_motor, DcMotor rear_right_motor) {
        motors = new DcMotor[4];
        
        motors[FRONT_LEFT_MOTOR] = front_left_motor;
        motors[FRONT_LEFT_MOTOR] = front_right_motor;
        motors[REAR_LEFT_MOTOR]  = rear_left_motor;
        motors[REAR_RIGHT_MOTOR] = rear_right_motor;

    }

    /**
     * s
     */

    public void addIMU(BNO055IMU imu) {
        this.imu = imu;

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
     * 
     */

    public int convert(int angle){
        if(angle>180){
            return angle-360;
        }
        else if(angle<-180){
            return angle+360;   
        }
        else{
            return angle;
        }
    }

    /**
     * Simple drive 
     * 
     * @param left left motor power (left motors have even index)
     * @param right right motor power (right motors have even index)
     */

    public void simpledrive(double left, double right) {
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

    public void driveJoystick(double x, double y) {
        y = -y;
        
        double leftPower;
        double rightPower;
        
        // Calculate Power (Drive Train)
        leftPower = Range.clip(y + x, -1.0, 1.0) ;
        rightPower = Range.clip(y - x, -1.0, 1.0) ;
        
        simpledrive(leftPower, rightPower);

    }

    /**
     * 
     */

    public void forward(double rotations, double power, int heading) {
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
                motors[FRONT_RIGHT_MOTOR].setPower(power);
                motors[REAR_RIGHT_MOTOR].setPower(power);

            } else if(angles.firstAngle<convert(-2+heading)){
                motors[FRONT_LEFT_MOTOR].setPower(-power);
                motors[REAR_LEFT_MOTOR].setPower(-power);

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
                motors[FRONT_LEFT_MOTOR].setPower(power);
                motors[REAR_LEFT_MOTOR].setPower(power);

            } else if(angles.firstAngle<convert(-2+heading)){
                motors[FRONT_RIGHT_MOTOR].setPower(-power);
                motors[REAR_RIGHT_MOTOR].setPower(-power);

            }
        }

        brake();

    }

    /**
     * 
     */

    public void turnRight(double power, int heading){
        //angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        
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
        //angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        
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
