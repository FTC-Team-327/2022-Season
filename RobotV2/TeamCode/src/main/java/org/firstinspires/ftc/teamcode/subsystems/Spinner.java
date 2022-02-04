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
    
}
