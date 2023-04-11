package com.examportal.examportalbackend.core.utils.results;

public class Result {
    private boolean isSuccess;
    private String message;
    public Result(boolean isSuccess){
        this.isSuccess=isSuccess;
    }
    public Result(boolean isSuccess, String message) {
        this(isSuccess);
        this.message=message;
    }
    public boolean isSuccess() {
        return this.isSuccess;
    }
    public String getMessage() {
        return this.message;
    }
}
