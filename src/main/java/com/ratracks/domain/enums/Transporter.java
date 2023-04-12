package com.ratracks.domain.enums;

public enum Transporter {
    CORREIOS("correios");

    private final String value;

    Transporter(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
