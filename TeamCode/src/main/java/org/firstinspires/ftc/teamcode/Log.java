package org.firstinspires.ftc.teamcode;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * A simple way to log data to a file.
 * 
 * @author Joseph Telaak
 */

public class Log {
    /** Base folder */
    private static final String BASE_FOLDER_NAME = "FIRST";

    /** File writer */
    private Writer fileWriter;

    /** Line */
    private String line = "";

    /** Log the time? */
    private boolean logTime;
    /** Log start time */
    private long startTime;

    /** Disable */
    private boolean disabled = false;

    /** 
     * Setup log
     * 
     * @param filename File Name to write to
     * @param logTime Log timestamps?
     */

    Log(String filename, boolean logTime) {
        // If using log timestamps
        this.logTime = logTime;
        if (logTime) {
            // Set the epoch
            startTime = System.nanoTime();
        }
        
        // Set directory path
        String directoryPath = Environment.getExternalStorageDirectory().getPath()+"/"+BASE_FOLDER_NAME;
        
        // Create log directory
        File directory = new File(directoryPath);
        directory.mkdir();
        
        // Try to make the file
        try {
            fileWriter = new FileWriter(directoryPath + "/" + filename + ".csv");

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Check if the log is disabled
     * 
     * @return log status
     */

    public boolean isDisabled() {
        return disabled;

    }

    /**
     * Set the log status 
     * 
     * @param disabled log status
     */

    public void setDisable(boolean disabled) {
        this.disabled = disabled;

    }

    /**
     * Close the log
     */

    public void close() {
        try {
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Update the log
     */

    public void update() {
        // Exit if disabled
        if (disabled) {
            return;
        }

        try {
            // Log the time
            if (logTime) {
                long timeDifference = System.nanoTime() - startTime;
                line = (timeDifference / 1E9) + "," + line;

            }

            // Write the line
            fileWriter.write(line+"\n");
            line = "";

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(String data) {
        // Exit if disabled
        if (disabled) {
            return;
        }

        // Add comma to the line
        if (!line.equals("")) {
            line += ",";
        }

        // Add info to the line
        line += data;

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(Object data) {
        addData(data.toString());

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(boolean data) {
        addData(String.valueOf(data));

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(byte data) {
        addData(String.valueOf(data));

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(char data) {
        addData(String.valueOf(data));

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(short data) {
        addData(String.valueOf(data));

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(int data) {
        addData(String.valueOf(data));

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(long data) {
        addData(String.valueOf(data));

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(float data) {
        addData(String.valueOf(data));

    }

    /**
     * Add data to log
     * 
     * @param data adds to current log line
     */

    public void addData(double data) {
        addData(String.valueOf(data));

    }
}
