package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Spinner {

    // ---------------- Declarations

    /** */
    private DcMotor motor;

    // ---------------- Constructors

    /**
     * 
     */

    public Spinner(DcMotor motor) {
        this.motor = motor;

        this.motor.setDirection(DcMotor.Direction.REVERSE);

    }

    // ---------------- Move

    /**
     * 
     */

    public void runSpinner(float press, double power) {
        if (press > 0) {
            motor.setPower(power);

        } else {
            stopSpinner();

        }
    }

    /**
     * 
     */

    public void stopSpinner() {
        motor.setPower(0);

    }

}