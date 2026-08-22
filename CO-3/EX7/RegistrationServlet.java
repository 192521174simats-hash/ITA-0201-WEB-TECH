import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class RegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        out.println("<html><body style='font-family:Arial'>");
        out.println("<h2>Student Registration Form</h2>");
        out.println("<form method='post' action='registration'>");
        out.println("Name: <input name='name'><br><br>");
        out.println("Register No: <input name='reg'><br><br>");
        out.println("Email: <input type='email' name='email'><br><br>");
        out.println("Department: <input name='dept'><br><br>");
        out.println("Semester: <input name='sem'><br><br>");
        out.println("<input type='submit' value='Register'></form></body></html>");
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        String name=req.getParameter("name"), reg=req.getParameter("reg"), email=req.getParameter("email");
        String dept=req.getParameter("dept"), sem=req.getParameter("sem");
        out.println("<html><body style='font-family:Arial'><h2>Registration Details</h2>");
        if(name==null||name.isBlank()||reg==null||reg.isBlank()||email==null||email.isBlank()||
           dept==null||dept.isBlank()||sem==null||sem.isBlank()){
            out.println("<p style='color:red'>Please fill all fields.</p>");
        }else{
            out.println("<p>Name: "+name+"</p><p>Register No: "+reg+"</p><p>Email: "+email+"</p>");
            out.println("<p>Department: "+dept+"</p><p>Semester: "+sem+"</p><h3>Registration successful.</h3>");
        }
        out.println("</body></html>");
    }
}