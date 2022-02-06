package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Spinner game piece
 * 
 * Turns the duck wheel
 */

public class Spinner {

    // ---------------- Declarations

    /** Motor for the spinner wheel */
    private DcMotor motor;

    // ---------------- Constructors

    /**
     * Basic spinner mode that holds the 
     */

    public Spinner(DcMotor motor) {
        this.motor = motor;

        this.motor.setDirection(DcMotor.Direction.REVERSE);

    }

    // ---------------- Move

    /**
     * Run the spinner
     */

    public void runSpinner(double power) {
        motor.setPower(power);
    }

    /**
     * Stop the spinner
     */

    public void stopSpinner() {
        motor.setPower(0);

    }

}