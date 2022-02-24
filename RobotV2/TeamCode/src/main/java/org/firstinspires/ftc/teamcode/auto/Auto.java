import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.OurRobot;
import org.firstinspires.ftc.teamcode.Constants;

@Autonomous(name="Auto", group = "Linear Opmode")

public class Auto extends LinearOpMode {
	
	// timer
	private ElapsedTime runtime = new ElapsedTime();
	
	// Robot 
	private OurRobot robot;
	
	/**
	 * OP Mode
	 */ 

	public void runOpMode() {
		robot = new OurRobot(telemetry);
		robot.init(hardwareMap);
		
		// Wait for game to start
		waitForStart();
		runtime.reset();
		
		
		robot.spinner.rotateSpinner(1);
		
		sleep((long) ((30 - runtime.seconds()) * 1000));
		
	}
	
	 
}