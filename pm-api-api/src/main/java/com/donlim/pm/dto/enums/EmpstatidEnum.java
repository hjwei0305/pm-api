package com.donlim.pm.dto.enums;

/**
 * @author Carol
 * @date 2022/8/15 16:16
 * @description
 */
public enum EmpstatidEnum {
    // 1 实习 PRACTICE 2 试用 TRY 3 考察 INSPECT 4 正式 OFFICIAL 12 离职 LEAVE
    PRACTICE("1","试用"),
    TRY("2","正式"),
    INSPECT("3","考察"),
    OFFICIAL("4","正式"),
    LEAVE("12","离职");

    private String value;
    private String note;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    EmpstatidEnum(String value, String note) {
        this.value = value;
        this.note = note;
    }
}
