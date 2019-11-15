/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.java_classes.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class DAO {
    
    //database credentials
    private String url="jdbc:mysql://localhost:3306/academic_trip_project";
    private String dbname = "root";
    private String pass = "";
    
    private String connection_failure = "Failure connecting to the database";
 
    //default constructor
    public DAO(){
        
    }
    
    //create a connection to the database
    public Connection getConnection(){
			
	try{
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            //connection to the database
            Connection con = DriverManager.getConnection(this.url, this.dbname, this.pass);
            
            return con;
	
        } catch(Exception ex){
            System.out.println(ex);
	}
	return null;
    }
    
    public void createTable(){
        
        Connection con = getConnection();
        //check for the connection
        if(con != null){
            try{
            
            String query1 = "CREATE TABLE IF NOT EXISTS trip_bookings("
		+ "id int NOT NULL AUTO_INCREMENT PRIMARY KEY, "
		+ "reference_no int NOT NULL,"
                + "faculty VARCHAR(100) NOT NULL"
		+ "department varchar(100) NOT NULL, "
		+ "course_title varchar(100) NOT NULL, "
                + "unit_code varchar(100) NOT NULL, "
		+ "unit_title varchar(300) NOT NULL, "
		+ "departure_date varchar(30) NOT NULL, "
                + "return_date varchar(30) NOT NULL, "
                + "destination varchar(50) NOT NULL, "
		+ "no_of_students int NOT NULL, "
		+ "no_of_lecturers int NOT NULL, "
		+ "trip_description varchar(500) NOT NULL)";
            
            PreparedStatement statement1 = con.prepareStatement(query1);
            statement1.executeUpdate();
            }
        catch(Exception ex){
           
            System.out.println(ex);
        }
        }
        else{
            
            //print error message
            System.out.println(connection_failure);
        }
        
    }
    
    //a method for inserting data into the database
        public void insert_booking_details(Booking trip){
            
            //get a connection to the database
            Connection con = getConnection();
            //check for connection
            if(con != null){
                
                //create table if it does not exist
                createTable();
                
                //query for inserting data into the database
                String query = "INSERT INTO trip_bookings(reference_no,faculty,department,course_title,unit_code,unit_title,departure_date,return_date,destination, no_of_students, no_of_lecturers, trip_description) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                
                try {
                    PreparedStatement stmt1 = con.prepareStatement(query);
                    
                    //set the date to the query
                    stmt1.setInt(1, trip.getReference_no());
                    stmt1.setString(2, trip.getFaculty());
                    stmt1.setString(3, trip.getDepartment());
                    stmt1.setString(4, trip.getCourse_title());
                    stmt1.setString(5, trip.getUnit_code());
                    stmt1.setString(6, trip.getUnit_title());
                    stmt1.setString(7, trip.getDeparture_date());
                    stmt1.setString(8, trip.getReturn_date());
                    stmt1.setString(9, trip.getDestination());
                    stmt1.setInt(10, trip.getNo_of_students());
                    stmt1.setInt(11, trip.getNo_of_lecturers());
                    stmt1.setString(12, trip.getTrip_description());
                    
                    //execute the query to insert the data into the database
                    stmt1.executeQuery();
                    
                    stmt1.close();
                    con.close();
                } catch (Exception ex) {
                    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex);
                }
                
                
            } else{
                //print error message
                System.out.println(connection_failure);
            }
            
        }
    
}
