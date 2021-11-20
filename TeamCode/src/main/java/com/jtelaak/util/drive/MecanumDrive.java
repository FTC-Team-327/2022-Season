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

    // --------------------- Drive

    public void strafeRight(double rotations, double power, int heading){
        double target=(rotations*360)+motors[REAR_RIGHT_MOTOR].getCurrentPosition();
        
        while((double)motors[REAR_RIGHT_MOTOR].getCurrentPosition()<target){
            motors[FRONT_RIGHT_MOTOR].setTargetPosition((int)target);
            motors[FRONT_RIGHT_MOTOR].setPower(-power);

            motors[REAR_RIGHT_MOTOR].setTargetPosition((int)target);
            motors[REAR_RIGHT_MOTOR].setPower(power);

            motors[FRONT_LEFT_MOTOR].setTargetPosition((int)target);
            motors[FRONT_LEFT_MOTOR].setPower(-power);

            motors[REAR_LEFT_MOTOR].setTargetPosition((int)target);
            motors[REAR_LEFT_MOTOR].setPower(power);

            // printData();

            if(angles.firstAngle>convert(2+heading)){
                motors[REAR_RIGHT_MOTOR].setPower(power);
                motors[REAR_LEFT_MOTOR].setPower(power);

            } else if(angles.firstAngle<convert(-2+heading)){
                motors[FRONT_RIGHT_MOTOR].setPower(-power);
                motors[FRONT_LEFT_MOTOR].setPower(-power);
                
                //strafe left = right side spin outwards; left side spins inwards
            }

        }

        brake();
    }
    
    public void strafeLeft(double rotations, double power, int heading){
        double target=(-rotations*360)+motors[REAR_RIGHT_MOTOR].getCurrentPosition();

        while((double)motors[REAR_RIGHT_MOTOR].getCurrentPosition()>target){
            motors[FRONT_RIGHT_MOTOR].setTargetPosition((int)target);
            motors[FRONT_RIGHT_MOTOR].setPower(power);

            motors[REAR_RIGHT_MOTOR].setTargetPosition((int)target);
            motors[REAR_RIGHT_MOTOR].setPower(-power);

            motors[FRONT_LEFT_MOTOR].setTargetPosition((int)target);
            motors[FRONT_LEFT_MOTOR].setPower(power);

            motors[REAR_LEFT_MOTOR].setTargetPosition((int)target);
            motors[REAR_LEFT_MOTOR].setPower(-power);

            // printData();

            if(angles.firstAngle>convert(2+heading)){
                motors[FRONT_RIGHT_MOTOR].setPower(power);
                motors[FRONT_LEFT_MOTOR].setPower(power);

            } else if(angles.firstAngle<convert(-2+heading)){
                motors[REAR_RIGHT_MOTOR].setPower(-power);
                motors[REAR_LEFT_MOTOR].setPower(-power);

            }
        }

        brake();

    }
    
}
