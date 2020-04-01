package com.inuol.entity;

public class InformationWithBLOBs extends Information {
    private String context;

    private String contextAbstract;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getContextAbstract() {
        return contextAbstract;
    }

    public void setContextAbstract(String contextAbstract) {
        this.contextAbstract = contextAbstract == null ? null : contextAbstract.trim();
    }
}