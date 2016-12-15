package com.example.nhan.todonote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nhan on 12/14/2016.
 */

public class RegisterModel {
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private int result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
