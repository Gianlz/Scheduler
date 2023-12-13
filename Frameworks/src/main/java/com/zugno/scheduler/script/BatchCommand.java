

package com.zugno.scheduler.script;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class BatchCommand implements Command {
    private Path batchFile;
    private LocalDateTime scheduleDate;

    public BatchCommand(Path batchFile, LocalDateTime scheduleDate) {
        this.batchFile = batchFile;
        this.scheduleDate = scheduleDate;
    }

    /**
     * This overridden method retrieves the date of the schedule.
     *
     * @return    The LocalDateTime object representing the schedule date.
     */

    @Override
    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    /**
     * Executes a batch file command. If the batch file does not exist, an
     * IllegalArgumentException is thrown. The function builds and starts a
     * new process to execute the batch command, and waits for its termination.
     * If an IOException or InterruptedException occurs during execution, a
     * RuntimeException is thrown with the corresponding error message.
     *
     * This function does not accept any parameters and does not return any
     * value.
     */
    @Override
    public void execute() {
        if (!Files.exists(batchFile)) {
            throw new IllegalArgumentException("The batch file does not exist: " + batchFile);
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", batchFile.toString());
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred while executing the batch command: " + e.getMessage(), e);
        }
    }


    /**
     * Overrides the existing method to update the schedule date.
     *
     * @param  newSchedule	The new schedule in String format
     *
     */
    @Override
    public void updateSchedule(String newSchedule) {
        this.scheduleDate = LocalDateTime.parse(newSchedule);
    }
}
