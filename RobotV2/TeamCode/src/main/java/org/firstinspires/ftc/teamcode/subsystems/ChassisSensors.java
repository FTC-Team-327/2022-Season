public class ChassisSensors {

    // --------------------- Range Sensors

    /** Chassis range sensors */
    private ModernRoboticsI2cRangeSensor[] range_sensors;

    /** Ultrasonic range sensor data */
    private double[] range_sensor_data_ultrasonic;

    /** Optical range sensor data */
    private double[] range_sensor_data_optical;

    /** Are the sensors initialized? */
    public boolean range_sensors_initialized = false;

    /** Front range sensor array index */
    public static final int FRONT_RANGE_SENSOR = 0;

    /** Left range sensor array index */
    public static final int LEFT_RANGE_SENSOR = 1;

    /** Right range sensor array index */
    public static final int RIGHT_RANGE_SENSOR = 2;


    // --------------------- Telemetry

	/** Telemetry */
	private Telemetry telemetry;

    // --------------------- Hardware Map

    /** Hardware Map */
	public HardwareMap hardware_map;

	// --------------------- Constructors

    /**
     * Setup the chassis sensors
     */

    public ChassisSensors(Telemetry telemetry, HardwareMap hardware_map) {
        this.telemetry = telemetry;
        this.hardware_map = hardware_map;

    }

    // --------------------- Init

    /**
     * Initialize the chassis range sensors
     */

    public void initRange() {
        // Setup sensors
        range_sensors[FRONT_RANGE_SENSOR] = hardware_map.get(ModernRoboticsI2cRangeSensor.class, Constants.chassis_front_range_name);
        range_sensors[LEFT_RANGE_SENSOR] = hardware_map.get(ModernRoboticsI2cRangeSensor.class, Constants.chassis_left_range_name);
        range_sensors[RIGHT_RANGE_SENSOR] = hardware_map.get(ModernRoboticsI2cRangeSensor.class, Constants.chassis_right_range_name);

        // Set boolean
        range_sensors_initialized = true;

        // Poll rage
        pollRange();

    }

    // --------------------- Operations

    /**
     * Poll the chassis range sensors
     */

    public void pollRange() {
        if (!range_sensors_initialized) { return; }

        for (int i = 0; i < range_sensors.length; i++) {
            range_sensor_data_ultrasonic[i] = range_sensors[i].rawUltrasonic();
            range_sensor_data_optical[i] = range_sensors[i].rawOptical();

        }
    }
    
}
