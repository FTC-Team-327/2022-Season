package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

public class Arm {

    // ---------------- Declarations

    /** */
    private DcMotor arm;
    private CRServo intake;
    private double angle;

    private double ENCODER_TICKS = 537.7;

    // ---------------- Constructors

    /**
     * 
     */

    public Arm(DcMotor arm, CRServo intake) {
        this.arm = arm;
        this.intake = intake;

        this.arm.setDirection(DcMotor.Direction.FORWARD);
        //this.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


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

    public void runArm(double power) {
        arm.setPower(power);
        
    }

    /**
     * 
     */

    public void stopArm() {
        arm.setPower(0);

    }

    /**
     * Reset the encoders
     */

    public void resetEncoders() {
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    /**
     * Reset the encoders
     */

    public void resetEncoderMode() {
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    /**
     * Enable the encoders
     */

    public void enableEncoders() {
        arm.setTargetPosition(0);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(.1);
        angle_offset = getAngle();
        angle = angle_offset;
    }
    
    private double angle_offset;

    /**
     * @return arm angle in degrees
     */
    public double getAngle() { return (arm.getCurrentPosition() / ENCODER_TICKS) * 360.0; }

    public double desiredAngle() { return angle; }

    public double angleToTicks(double angle) { return (angle / 360.0) * ENCODER_TICKS; }

    public void setAngle(double angle) { arm.setTargetPosition((int)angleToTicks(angle)); }

    public double encoderValue() { return arm.getCurrentPosition(); }

    /**
     * Set the arm position
     */

    public void setArmPosition(double a) {
        angle = a + angle_offset;
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