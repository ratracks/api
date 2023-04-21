package com.ratracks.domain.enums;

public enum Status {
    IN_PROGRESS("in_progress"), FINISHED("finished");

    private final String value;

    Status(String value){
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
