package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

public class Arm {

    // ---------------- Declarations

    /** */
    private DcMotor motor;
    private CRServo intake;

    // ---------------- Constructors

    /**
     * 
     */

    public Arm(DcMotor motor, CRServo intake) {
        this.motor = motor;
        this.intake = intake;

        this.motor.setDirection(DcMotor.Direction.REVERSE);

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

}