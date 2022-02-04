package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="(blue) wheel, cargo, park", group = "Linear Opmode")

public class BetterAuto extends LinearOpMode {
	
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

        Spinner spinner = new Spinner(hardwareMap.get(DcMotor.class, "spinner"));
        Arm arm = new Arm(hardwareMap.get(DcMotor.class, "arm"), hardwareMap.get(CRServo.class, "intake"));


		waitForStart();
		runtime.reset();

		while (opModeIsActive()) {
			// Run

            // move to wheel
            if (runtime.seconds() < .5) {drive.drive(0, .5, 0) }

            //spin wheel
            if (runtime.seconds() > .5 && runtime.seconds( < 2) { spinner.runSpinner(1, .5) }


            //drive forwards
			if (runtime.seconds() > 2 && runtime.seconds < 2.25) { drive.drive(.2, 0, 0) }
		}

	}

}