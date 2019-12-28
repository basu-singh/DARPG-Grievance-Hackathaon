package com.basusingh.mygrievanceadmin.Helper;

import java.io.Serializable;

public class ViewGrievanceItems implements Serializable {

    private String registration_no, ministry_department, country_name, state_name, distname, subject_content, diarydate, closing_date,
    sourceName, rating, comments, ratingdate;

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public String getMinistry_department() {
        return ministry_department;
    }

    public void setMinistry_department(String ministry_department) {
        this.ministry_department = ministry_department;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getDistname() {
        return distname;
    }

    public void setDistname(String distname) {
        this.distname = distname;
    }

    public String getSubject_content() {
        return subject_content;
    }

    public void setSubject_content(String subject_content) {
        this.subject_content = subject_content;
    }

    public String getDiarydate() {
        return diarydate;
    }

    public void setDiarydate(String diarydate) {
        this.diarydate = diarydate;
    }

    public String getClosing_date() {
        return closing_date;
    }

    public void setClosing_date(String closing_date) {
        this.closing_date = closing_date;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRatingdate() {
        return ratingdate;
    }

    public void setRatingdate(String ratingdate) {
        this.ratingdate = ratingdate;
    }
}
