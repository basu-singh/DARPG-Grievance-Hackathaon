package com.basusingh.mygrievance.Helper;

import java.io.Serializable;

public class HelpCenterItems implements Serializable {

    private String title, description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
