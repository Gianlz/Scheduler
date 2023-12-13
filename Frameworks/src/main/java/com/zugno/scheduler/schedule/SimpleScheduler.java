

package com.zugno.scheduler.schedule;

import com.zugno.scheduler.script.Command;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.*;

public class SimpleScheduler implements Scheduler {
    private final ScheduledExecutorService executor;
    private final Map<Command, ScheduledFuture<?>> scheduledTasks;

    public SimpleScheduler() {
        executor = Executors.newScheduledThreadPool(1);
        scheduledTasks = new ConcurrentHashMap<>();
    }

    /**
     * Schedules a provided command to be executed at the time specified by the
     * command's schedule date. If an IOException occurs during the execution of the
     * command, it logs the error message.
     *
     * @param  command 	The command to be scheduled for execution
     */

    @Override
    public void schedule(Command command) {
        ScheduledFuture<?> future = executor.schedule(() -> {
            try {
                command.execute();
            } catch (IOException exception) {
                System.err.println("Error executing command: " + exception.getMessage());
            }
        }, Duration.between(LocalDateTime.now(), command.getScheduleDate()).toMillis(), TimeUnit.MILLISECONDS);

        scheduledTasks.put(command, future);
    }




    /**
     * Shuts down the executor service.
     *
     * This method is typically invoked when no more tasks
     * should be submitted to the executor.
     *
     * This method does not take any parameters and does not
     * return any value.
     */
    @Override
    public void shutdown() {
        executor.shutdown();
    }


}