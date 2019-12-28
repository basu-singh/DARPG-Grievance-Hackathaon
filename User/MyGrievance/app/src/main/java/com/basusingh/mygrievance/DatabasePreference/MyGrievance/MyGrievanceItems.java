package com.basusingh.mygrievance.DatabasePreference.MyGrievance;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class MyGrievanceItems implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    public int uid;

    @ColumnInfo(name = "subject")
    public String subject;

    @ColumnInfo(name = "message")
    public String message;

    @ColumnInfo(name = "ministry")
    public String ministry;

    @ColumnInfo(name = "image1")
    public String image1;

    @ColumnInfo(name = "image2")
    public String image2;

    @ColumnInfo(name = "image3")
    public String image3;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @ColumnInfo(name = "timestamp")
    public String timestamp;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMinistry() {
        return ministry;
    }

    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }
}
