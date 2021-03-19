package com.luxoft.dbapp.enums;

public enum SpecialFeatures {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private String desc;
    SpecialFeatures(String desc){
        this.desc = desc;
    }
}
