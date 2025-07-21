
package com.tka;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submitform")
public class Student extends HttpServlet {

    // GET request par sirf message dikhayega
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h3>This URL only supports POST method. Please submit the form.</h3>");
    }

    // POST request handle karega
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ---- Integer fields ----
        int stud_id = 0;
        String id = request.getParameter("stud_id");
        if (id != null && !id.isEmpty()) {
            stud_id = Integer.parseInt(id);
        }

        long phone = 0;
        String mobile = request.getParameter("mobile_no");
        if (mobile != null && !mobile.isEmpty()) {
            phone = Long.parseLong(mobile);
        }

        int pin = 0;
        String pincode = request.getParameter("pincode");
        if (pincode != null && !pincode.isEmpty()) {
            pin = Integer.parseInt(pincode);
        }

        int stud_marks = 0;
        String marks = request.getParameter("marks");
        if (marks != null && !marks.isEmpty()) {
            stud_marks = Integer.parseInt(marks);
        }

        // ---- String fields ----
        String name = request.getParameter("stud_name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String clg = request.getParameter("clg_name");
        String branch = request.getParameter("branch");
        String course = request.getParameter("course");
        String licence = request.getParameter("driving_licence");

        // ---- Console print ----
        System.out.println(stud_id);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(age);
        System.out.println(phone);
        System.out.println(email);
        System.out.println(city);
        System.out.println(state);
        System.out.println(pin);
        System.out.println(clg);
        System.out.println(branch);
        System.out.println(course);
        System.out.println(stud_marks);
        System.out.println(licence);

        // ---- Response to browser ----it will take only content not whole data
//        response.setContentType("text/html");
//        response.getWriter().println("<h2>Registration Successful</h2>");
//        response.getWriter().println("<p>Welcome, " + name + "!</p>");
        
        //--print on console if i want to display my data on browser
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  

        out.println("<h2>Student Registration Details</h2>");
        out.println("<p><b>Student ID:</b> " + stud_id + "</p>");
        out.println("<p><b>Name:</b> " + name + "</p>");
        out.println("<p><b>Gender:</b> " + gender + "</p>");
        out.println("<p><b>Age:</b> " + age + "</p>");
        out.println("<p><b>Mobile No:</b> " + phone + "</p>");
        out.println("<p><b>Email:</b> " + email + "</p>");
        out.println("<p><b>City:</b> " + city + "</p>");
        out.println("<p><b>State:</b> " + state + "</p>");
        out.println("<p><b>Pincode:</b> " + pin + "</p>");
        out.println("<p><b>College Name:</b> " + clg + "</p>");
        out.println("<p><b>Branch:</b> " + branch + "</p>");
        out.println("<p><b>Course:</b> " + course + "</p>");
        out.println("<p><b>Marks:</b> " + stud_marks + "</p>");
        out.println("<p><b>Driving Licence:</b> " + licence + "</p>");
        
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/batch1043","root","$vkm");
			PreparedStatement ps = c.prepareStatement(
				    "INSERT INTO csr_data (stud_id, stud_name, city, state, clg_name, branch, gender, age, mobile_no, email, course, pincode, marks, driving_licence) " +
				    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				);

				ps.setInt(1, stud_id);           // stud_id
				ps.setString(2, name);           // stud_name
				ps.setString(3, city);           // city
				ps.setString(4, state);          // state
				ps.setString(5, clg);            // clg_name
				ps.setString(6, branch);         // branch
				ps.setString(7, gender);         // gender
				ps.setString(8, age);            // age (keep String if not converted to int)
				ps.setLong(9, phone);            // mobile_no
				ps.setString(10, email);         // email
				ps.setString(11, course);        // course
				ps.setInt(12, pin);              // pincode
				ps.setInt(13, stud_marks);       // marks
				ps.setString(14, licence);       // driving_licence

			
			ps.executeUpdate();
			c.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

        
    }
}
