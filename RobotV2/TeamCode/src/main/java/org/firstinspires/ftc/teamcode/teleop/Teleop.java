package org.firstinspires.ftc.teamcode.teleop;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Teleop", group="Linear Opmode")

public class Teleop extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        // Post update
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        // Setup Drivetrain
        DriveTrain drive = new DriveTrain(new DcMotor[] {
            hardwareMap.get(DcMotor.class, ),
            hardwareMap.get(DcMotor.class, ),
            hardwareMap.get(DcMotor.class, ),
            hardwareMap.get(DcMotor.class, )
            
        });

        // Spinner
        Spinner spinner = new Spinner(hardwareMap.get(DcMotor.class, ));
        
        // Wait for game to start
        waitForStart();
        runtime.reset();
        
        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            
        }
    }
    
}
