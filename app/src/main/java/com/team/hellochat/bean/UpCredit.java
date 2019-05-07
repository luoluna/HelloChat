package com.team.hellochat.bean;

/**
 * Created by Sweven on 2019/5/7.
 * Email:sweventears@Foxmail.com
 */
public class UpCredit {
    private String label;
    private String method;
    private boolean isGoto;

    public UpCredit(String label, String method) {
        this.label = label;
        this.method = method;
    }

    public UpCredit(String label, String method, boolean isGoto) {
        this.label = label;
        this.method = method;
        this.isGoto = isGoto;
    }

    public boolean isGoto() {
        return isGoto;
    }

    public void setGoto(boolean aGoto) {
        isGoto = aGoto;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
