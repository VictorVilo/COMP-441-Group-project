/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java_classes;

/**
 *
 * @author root
 */
public class Booking {
    
    private int reference_no;
    private String faculty;
    private String department;
    private String course_title;
    private String unit_code;
    private String unit_title;
    private String departure_date;
    private String return_date;
    private String destination;
    private int no_of_students;
    private int no_of_lecturers;
    private String trip_description;

    public Booking(int reference_no, String faculty, String department, String course_title, String unit_code, String unit_title, String departure_date, String return_date, String destination, int no_of_students, int no_of_lecturers, String trip_description) {
        this.reference_no = reference_no;
        this.faculty = faculty;
        this.department = department;
        this.course_title = course_title;
        this.unit_code = unit_code;
        this.unit_title = unit_title;
        this.departure_date = departure_date;
        this.return_date = return_date;
        this.destination = destination;
        this.no_of_students = no_of_students;
        this.no_of_lecturers = no_of_lecturers;
        this.trip_description = trip_description;
    }
    

   

    //setter methods for the attributes
    
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }
    

    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }

    public void setUnit_title(String unit_title) {
        this.unit_title = unit_title;
    }
    

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNo_of_students(int no_of_students) {
        this.no_of_students = no_of_students;
    }

    public void setNo_of_lecturers(int no_of_lecturers) {
        this.no_of_lecturers = no_of_lecturers;
    }

    public void setTrip_description(String trip_description) {
        this.trip_description = trip_description;
    }
    
    
    //getter methods for the attributes

    
    public int getReference_no() {
        return reference_no;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getDepartment() {
        return department;
    }

    public String getCourse_title() {
        return course_title;
    }
    

    public String getUnit_code() {
        return unit_code;
    }

    public String getUnit_title() {
        return unit_title;
    }
    
    
    public String getDeparture_date() {
        return departure_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public String getDestination() {
        return destination;
    }

    public int getNo_of_students() {
        return no_of_students;
    }

    public int getNo_of_lecturers() {
        return no_of_lecturers;
    }

    public String getTrip_description() {
        return trip_description;
    }
     
    
    
}
