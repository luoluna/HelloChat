package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/4/10.
 * Email:sweventears@Foxmail.com
 */
public enum MessageType {
    TEXT("文本", 0x101),
    IMAGE("图像", 0x102),
    VIDEO("视频", 0x103),
    VOICE("声音", 0x104),
    CARD("卡片", 0x105);
    public static final int _TEXT = 0x101;
    public static final int _IMAGE = 0x102;
    public static final int _VIDEO = 0x103;
    public static final int _VOICE = 0x104;
    public static final int _CARD = 0x105;

    private String name;
    private int index;

    MessageType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (MessageType type : MessageType.values()) {
            if (index == type.getIndex()) {
                return type.getName();
            }
        }
        return null;
    }

    public static MessageType getType(String name) {
        for (MessageType type : MessageType.values()) {
            if (name.equals(type.getName())) {
                return type;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipName() {
        return "[" + name + "]";
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
