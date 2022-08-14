package com.ussd.ussdcode.models;

public class ModelTarifRejalar {
    String status,name,coin,code,urlcompany,alldata;



    public ModelTarifRejalar(String name, String coin, String code,String status,String urlcompany,String alldata) {
        this.name = name;
        this.coin = coin;
        this.code = code;
        this.status = status;
        this.urlcompany = urlcompany;
        this.alldata = alldata;
    }

    public String getAlldata() {
        return alldata;
    }

    public void setAlldata(String alldata) {
        this.alldata = alldata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrlcompany() {
        return urlcompany;
    }

    public void setUrlcompany(String urlcompany) {
        this.urlcompany = urlcompany;
    }
}
