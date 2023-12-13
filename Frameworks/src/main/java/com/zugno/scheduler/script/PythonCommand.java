
package com.zugno.scheduler.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class PythonCommand implements Command {
    private Path pythonFile;
    private LocalDateTime scheduleDate;

    public PythonCommand(Path pythonFile, LocalDateTime scheduleDate) {
        validatePythonFile(pythonFile);
        this.pythonFile = pythonFile;
        this.scheduleDate = scheduleDate;
    }

    /**
     * This method is used to fetch the scheduled date.
     *
     * @return LocalDateTime 	Returns the scheduled date
     */
    @Override
    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    /**
     * This method updates the schedule date of the object by parsing
     * the newSchedule string into a LocalDateTime object.
     * It validates the newSchedule string before parsing it and throws
     * an IllegalArgumentException if the string is not a valid date-time.
     *
     * @param  newSchedule	The new schedule date as a String
     * @throws IllegalArgumentException if the newSchedule string is not a valid date-time
     */
    @Override
    public void updateSchedule(String newSchedule) {
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(newSchedule);
            this.scheduleDate = parsedDate;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date-time format: " + newSchedule, e);
        }
    }

    /**
     * This function executes a Python script file using PowerShell. It first
     * checks if the Python file exists. If it does, it creates a new process
     * to execute the Python file. It then waits for the process to complete
     * and reads both the output and error streams. If the process is
     * interrupted, it throws an IOException. The function outputs the exit
     * code, output, and any errors to the console.
     *
     * This function does not take any parameters and does not return any
     * value as it is a void function.
     */
    @Override
    public void execute() throws IOException {
        if (!Files.exists(pythonFile)) {
            throw new IllegalArgumentException("The Python file does not exist: " + pythonFile);
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe", "-Command", "python", pythonFile.toString());
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            String output = ""; // initialize output
            String error = ""; // initialize error

            // Read output and error streams
            try (BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

                String line;
                while ((line = outputReader.readLine()) != null) {
                    output += line + "\n";
                }

                while ((line = errorReader.readLine()) != null) {
                    error += line + "\n";
                }
            }

            // You can print the result here or handle it as per your requirements
            System.out.println("Exit Code: " + exitCode);
            System.out.println("Output: " + output);
            System.out.println("Error: " + error);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Process interrupted", e);
        }
    }

    /**
     * This method validates the pythonFile path in the constructor.
     * If the file does not exist or is not a Python script, it throws an IllegalArgumentException.
     *
     * @param pythonFile The path to the Python file
     * @throws IllegalArgumentException if the pythonFile does not exist or is not a Python script
     */
    private void validatePythonFile(Path pythonFile) {
        if (!Files.exists(pythonFile) || !pythonFile.toString().endsWith(".py")) {
            throw new IllegalArgumentException("Invalid Python file: " + pythonFile);
        }
    }
}