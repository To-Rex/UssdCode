package com.ussd.ussdcode.models;

public class ModelUssdCode {
    String Code,Name,Status;

    public ModelUssdCode(String code, String name, String status) {
        Code = code;
        Name = name;
        Status = status;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
