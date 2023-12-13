
package com.zugno.scheduler.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public interface Command {
    void execute() throws IOException;
    LocalDateTime getScheduleDate();
    void updateSchedule(String newSchedule);
}