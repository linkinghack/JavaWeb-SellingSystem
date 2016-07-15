package com.linkinghack.SellingSystem.meta;

/**
 * Created by linking on 16-7-15.
 */
public class UploadStatus {
    private int code;
    private String message;
    private String result;

    public UploadStatus(){}

    public UploadStatus(int code, String message, String result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
