package org.firstinspires.ftc.teamcode.util.exceptions;

public class InvalidMotorNameException extends Exception {

    public InvalidMotorNameException(String name) {
        super("Invalid motor name: " + name);

    }

}
