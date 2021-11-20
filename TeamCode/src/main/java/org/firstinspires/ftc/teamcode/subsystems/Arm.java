package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

public class Arm {

    // ---------------- Declarations

    /** */
    private DcMotor motor;
    private CRServo intake;
    private double angle;

    private double ENCODER_TICKS = 537.7;

    // ---------------- Constructors

    /**
     * 
     */

    public Arm(DcMotor motor, CRServo intake) {
        this.motor = motor;
        this.intake = intake;

        this.motor.setDirection(DcMotor.Direction.REVERSE);
        this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    // ---------------- Move

    /**
     * 
     */

    public void runIntake(double power) {
        intake.setPower(power);

    }

    /**
     * 
     */

    public void stopIntake() {
        intake.setPower(0);

    }

    /**
     * 
     */

    public void runArm(float press, double power) {
        if (press > 0) {
            motor.setPower(power);

        } else {
            stopArm();

        }
    }

    /**
     * 
     */

    public void stopArm() {
        motor.setPower(0);

    }

    /**
     * Reset the encoders
     */

    public void resetEncoders() {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    /**
     * Reset the encoders
     */

    public void resetEncoderMode() {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    /**
     * Enable the encoders
     */

    public void enableEncoders() {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * @return arm angle in degrees
     */
    public double getAngle() { return (motor.getCurrentPosition() / ENCODER_TICKS) * 360.0; }

    public double angleToTicks(double angle) { return (angle / 360.0) * ENCODER_TICKS; }

    public void setAngle(double angle) { motor.setTargetPosition((int) angle); }

    /**
     * Set the arm position
     */

    public void setArmPosition(double a) {
        angle = a;
        setAngle(angle);
        
    }

    /**
     * Increment arm position
     */
    public void changeAngle(double inc) {
        angle += inc;
        setAngle(angle);
    }

}