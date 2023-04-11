package com.examportal.examportalbackend.core.utils.results;

public class ResultDataError<T> extends ResultData<T> {

    public ResultDataError(T data) {
        super(data, false);
    }
    public ResultDataError(String message) {
        super(null,false,message);
    }
    public ResultDataError(T data ,String message) {
        super(data,false,message);
    }

}
