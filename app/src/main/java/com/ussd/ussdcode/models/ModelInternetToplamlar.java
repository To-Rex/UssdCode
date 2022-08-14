package com.ussd.ussdcode.models;

public class ModelInternetToplamlar {
    String name,code,muddati,narx,status;

    public ModelInternetToplamlar(String name, String code, String muddati, String narx, String status) {
        this.name = name;
        this.code = code;
        this.muddati = muddati;
        this.narx = narx;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMuddati() {
        return muddati;
    }

    public void setMuddati(String muddati) {
        this.muddati = muddati;
    }

    public String getNarx() {
        return narx;
    }

    public void setNarx(String narx) {
        this.narx = narx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
