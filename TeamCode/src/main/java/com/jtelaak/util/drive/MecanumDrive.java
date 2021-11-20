public class MecanumDrive {

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

    public void strafeRight(double rotations, double power, int heading){
        double target=(rotations*360)+motor[REAR_RIGHT_MOTOR].getCurrentPosition();
        
        while((double)motor[REAR_RIGHT_MOTOR].getCurrentPosition()<target){
            motor[FRONT_RIGHT_MOTOR].setTargetPosition((int)target);
            motor[FRONT_RIGHT_MOTOR].setPower(-power);

            motor[REAR_RIGHT_MOTOR].setTargetPosition((int)target);
            motor[REAR_RIGHT_MOTOR].setPower(power);

            motor[FRONT_LEFT_MOTOR].setTargetPosition((int)target);
            motor[FRONT_LEFT_MOTOR].setPower(-power);

            motor[REAR_LEFT_MOTOR].setTargetPosition((int)target);
            motor[REAR_LEFT_MOTOR].setPower(power);

            // printData();

            if(angles.firstAngle>convert(2+heading)){
                motor[REAR_RIGHT_MOTOR].setPower(power/divisor);
                motor[REAR_LEFT_MOTOR].setPower(power/divisor);

            } else if(angles.firstAngle<convert(-2+heading)){
                motor[FRONT_RIGHT_MOTOR].setPower(-power/divisor);
                motor[FRONT_LEFT_MOTOR].setPower(-power/divisor);
                
                //strafe left = right side spin outwards; left side spins inwards
            }
        }

        brake();
    }
    
    public void strafeLeft(double rotations, double power, int heading){
        double target=(-rotations*360)+motor[REAR_RIGHT_MOTOR].getCurrentPosition();

        while((double)motor[REAR_RIGHT_MOTOR].getCurrentPosition()>target){
            motor[FRONT_RIGHT_MOTOR].setTargetPosition((int)target);
            motor[FRONT_RIGHT_MOTOR].setPower(power);

            motor[REAR_RIGHT_MOTOR].setTargetPosition((int)target);
            motor[REAR_RIGHT_MOTOR].setPower(-power);

            motor[FRONT_LEFT_MOTOR].setTargetPosition((int)target);
            motor[FRONT_LEFT_MOTOR].setPower(power);

            motor[REAR_LEFT_MOTOR].setTargetPosition((int)target);
            motor[REAR_LEFT_MOTOR].setPower(-power);

            // printData();

            if(angles.firstAngle>convert(2+heading)){
                motor[FRONT_RIGHT_MOTOR].setPower(power/divisor);
                motor[FRONT_LEFT_MOTOR].setPower(power/divisor);

            } else if(angles.firstAngle<convert(-2+heading)){
                motor[REAR_RIGHT_MOTOR].setPower(-power/divisor);
                motor[REAR_LEFT_MOTOR].setPower(-power/divisor);

            }
        }

        brake();

    }
    
}
