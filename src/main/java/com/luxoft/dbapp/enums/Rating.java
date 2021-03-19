package com.luxoft.dbapp.enums;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private String desc;
    Rating(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return this.desc;
    }
}
