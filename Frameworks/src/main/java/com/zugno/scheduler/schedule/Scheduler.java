package com.zugno.scheduler.schedule;


import com.zugno.scheduler.script.Command;

public interface Scheduler {
    void schedule(Command command);
    void shutdown();
}