import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class BasicServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        out.println("<html><body style='font-family:Arial'>");
        out.println("<h2>Basic Servlet for Dynamic Content Generation</h2>");
        out.println("<p><b>Student Name:</b> Priya</p>");
        out.println("<p><b>Course:</b> Web Technology</p>");
        out.println("<p><b>Date:</b> "+LocalDate.now()+"</p>");
        out.println("<p><b>Time:</b> "+LocalTime.now()+"</p>");
        out.println("<p>Dynamic content generated using HttpServlet.</p>");
        out.println("</body></html>");
    }
}