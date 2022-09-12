package com.ussd.ussdcode.models;

public class ModelDaqiqalar {
    String Name,Code,Muddati,Narxi,Status,Links;

    public ModelDaqiqalar(String name, String code, String muddati, String narxi, String status, String links) {
        Name = name;
        Code = code;
        Muddati = muddati;
        Narxi = narxi;
        Status = status;
        Links = links;
    }

    public String getLinks() {
        return Links;
    }

    public void setLinks(String links) {
        Links = links;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMuddati() {
        return Muddati;
    }

    public void setMuddati(String muddati) {
        Muddati = muddati;
    }

    public String getNarxi() {
        return Narxi;
    }

    public void setNarxi(String narxi) {
        Narxi = narxi;
    }
}
