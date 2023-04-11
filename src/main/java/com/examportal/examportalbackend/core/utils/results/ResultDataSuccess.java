package com.examportal.examportalbackend.core.utils.results;

public class ResultDataSuccess<T> extends ResultData<T> {

    public ResultDataSuccess(T data, String message) {
        super(data,true,message);
    }
    public ResultDataSuccess(String message) {
        super(null,true,message);
    }
    public ResultDataSuccess(T data) {
        super(data,true);
    }

}
