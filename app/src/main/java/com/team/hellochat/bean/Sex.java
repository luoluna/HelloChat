package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/11.
 * Email:sweventears@Foxmail.com
 */
public enum Sex {
    SECRECY("保密", 0xA0),
    MAN("男", 0xA1),
    WOMEN("女", 0xA2);

    public static final int _SECRECY = 0xA0;
    public static final int _MAN = 0xA1;
    public static final int _WOMEN = 0xA2;

    private String label;
    private int index;

    Sex(String label, int index) {
        this.label = label;
        this.index = index;
    }

    public static String getLabel(int index) {
        for (Sex sex : Sex.values()) {
            if (sex.getIndex() == index) {
                return sex.getLabel();
            }
        }
        return Sex.SECRECY.getLabel();
    }

    public static Sex getSex(int index) {
        for (Sex sex : Sex.values()) {
            if (sex.getIndex() == index) {
                return sex;
            }
        }
        return Sex.SECRECY;
    }

    public static Sex getSex(String label) {
        for (Sex sex : Sex.values()) {
            if (sex.getLabel().equals(label)) {
                return sex;
            }
        }
        return Sex.SECRECY;
    }

    public String getLabel() {
        return label;
    }

    public int getIndex() {
        return index;
    }
}
