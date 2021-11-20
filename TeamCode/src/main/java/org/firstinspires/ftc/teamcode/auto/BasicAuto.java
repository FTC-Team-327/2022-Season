package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Drive Left", group = "Linear Opmode")

public class Auto extends LinearOpMode {
	
	// timer
    private ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        // Post update
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        DriveTrain drive = new DriveTrain(new DcMotor[] {
            hardwareMap.get(DcMotor.class, "frontLeft"),
            hardwareMap.get(DcMotor.class, "frontRight"),
            hardwareMap.get(DcMotor.class, "backLeft"),
            hardwareMap.get(DcMotor.class, "backRight")
            
        });

		waitForStart();
		runtime.reset();

		while (opModeIsActive()) {
			// Run
			if (runtime.seconds() < 2) drive.drive(0, -.5, 0);
		}

	}

}