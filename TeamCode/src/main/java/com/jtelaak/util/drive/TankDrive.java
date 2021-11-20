public class TankDrive extends Drive {

    // --------------------- Constructors

    /**
     * Drive constructor
     * 
     * @param front_left front left motor
     * @param front_right front right motor
     * @param rear_left rear left motor
     * @param rear_right rear right motor
     */

    public Drive(DcMotor front_left, DcMotor front_right, DcMotor rear_left, DcMotor rear_right) {
        super(DcMotor front_left, DcMotor front_right, DcMotor rear_left, DcMotor rear_right);

    }

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public Drive(DcMotor[] motors) {
        super(motors);

    }

    /**
     * Drive constructor
     * 
     * @param front_left front left motor
     * @param front_right front right motor
     * @param rear_left rear left motor
     * @param rear_right rear right motor
     */

    public Drive(String front_left, String front_right, String rear_left, String rear_right) {
        super(String front_left, String front_right, String rear_left, String rear_right)

    }

    /**
     * Drive constructor (See public ints for indexes)
     * 
     * @param motors Motor array
     */

    public Drive(String[] motors) {
        super(motors);

    }

    // --------------------- Drive

    /**
     * 
     */

    public void driveJoystick(double x, double y) {
		y = -y;
		
		double leftPower;
		double rightPower;
		
		// Calculate Power (Drive Train)
		leftPower = Range.clip(y + x, -1.0, 1.0) ;
		rightPower = Range.clip(y - x, -1.0, 1.0) ;
		
		simpledrive(leftPower, rightPower);

    }
    
}
