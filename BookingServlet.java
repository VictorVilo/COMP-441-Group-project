/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

import com.DAO.*;
import com.java_classes.Booking;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class BookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //get the data from the booking form
        String faculty = request.getParameter("faculty");
        String department = request.getParameter("department");
        String course_title = request.getParameter("course_title");
        String unit_code = request.getParameter("unit_code");
        String unit_title = request.getParameter("unit_title");
        String departure_date = request.getParameter("departure_date");
        String return_date = request.getParameter("return_date");
        String destination = request.getParameter("destination");
        int no_of_students = Integer.parseInt(request.getParameter("no_of_students"));
        int no_of_lecturers = Integer.parseInt(request.getParameter("no_of_lecturers"));
        String trip_description = request.getParameter("trip_description");
        
        
        
       //Validate the date to ensure that the user does not book in dates which are already past
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	String currentDate = dateFormat.format(date);
		
	LocalDate date1 = LocalDate.parse(currentDate, formatter);
	LocalDate date2 = LocalDate.parse(departure_date, formatter);

	//get the difference between the two dates
	long daysBtn = ChronoUnit.DAYS.between(date1, date2);
        
        if(daysBtn < 7){
            //if the departure date is before seven date, send error message to the user
            request.setAttribute("date_error", "Booking should be done 7 (seven) days before the date of the trip");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            
        } else{
            // assign trip refernce number to the trip
            int reference_no = reference_no();
            
            //create an object of Booking
            Booking trip_details = new Booking(reference_no,faculty, department, course_title, unit_code, unit_title, departure_date, return_date, destination, no_of_students, no_of_lecturers, trip_description);
            
            //send object to DAO for storage into database
            DAO trip_dao = new DAO();
            trip_dao.insert_booking_details(trip_details);
            
            //set message to display to the user and redirect to home page
            request.setAttribute("booked_successfully", "Your request has been submitted. Keep checking your email for progress!");
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
            
            
        }
    }
    
    
    //a method that produces a trip refence number that is not the database
    
    public int reference_no(){
		
		Random rand = new Random();
		int ref = 180 + rand.nextInt(1000000);
		
		try{
                    
                    //Create an object of DAO
                    DAO dao = new DAO();
                    
                    Connection con = dao.getConnection();
                    
                    if(con != null){
                        String query = "SELECT reference_no from trip_bookings";
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs = stmt.executeQuery();
                        
                        //if the result set has value, then the reference number is set so recall the method using recursion
                        if(rs.next()){
                            reference_no();
                        }
                    }
		} catch(Exception ex){
			System.out.println(ex);
		}
		
		return ref;
	
	}
    
    

}
