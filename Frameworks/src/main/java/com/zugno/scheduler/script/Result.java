package com.zugno.scheduler.script;

public class Result {
    private int exitCode;
    private String output;
    private String error;

    public Result(int exitCode, String output, String error) {
        this.exitCode = exitCode;
        this.output = output;
        this.error = error;
    }

    // getters and setters here
}
