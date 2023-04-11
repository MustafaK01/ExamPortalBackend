package com.examportal.examportalbackend.core.utils.results;

public class ResultData<T> extends Result{
    private T data;
    public ResultData(T data,boolean isSuccess) {
        super(isSuccess);
        this.data=data;
    }
    public ResultData(T data,boolean isSuccess,String message) {
        super(isSuccess,message);
        this.data = data;
    }
    public T getData() {
        return data;
    }
}
