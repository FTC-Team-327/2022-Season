package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

public class Arm {

    // ---------------- Declarations

    /** */
    private DcMotor motor;
    private CRServo intake;

    private int ENCODER_TICKS = 537.7;

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
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * @return arm angle in degrees
     */
    public int getAngle() { return (arm.getCurrentPosition() / ENCODER_TICKS) * 360 }

    public int angleToTicks(int angle) { return (angle / 360) * ENCODER_TICKS }

    public void setAngle(int angle) { arm.setTargetPosition(angleToTicks(angle) }

    }

    /**
     * Set the arm position
     */

    public void setArmPosition(double angle) {
        setAngle(angle)
        
    }

}