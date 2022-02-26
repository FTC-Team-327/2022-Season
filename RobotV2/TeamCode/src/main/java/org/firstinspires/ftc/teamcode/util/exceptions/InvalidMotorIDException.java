package org.firstinspires.ftc.teamcode.util.exceptions;

public class InvalidMotorIDException extends Exception {

    public InvalidMotorIDException(int id) {
        super("Invalid motor id " + id);

    }
}
