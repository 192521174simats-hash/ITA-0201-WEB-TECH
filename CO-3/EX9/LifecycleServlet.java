import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class LifecycleServlet extends HttpServlet {
    private String message;
    public void init() throws ServletException { message="Servlet initialized successfully."; }
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        out.println("<html><body style='font-family:Arial'>");
        out.println("<h2>Servlet Lifecycle Demonstration</h2>");
        out.println("<p>1. init() - "+message+"</p>");
        out.println("<p>2. service() - Request received and routed to doGet()</p>");
        out.println("<p>3. doGet() - Response generated.</p>");
        out.println("<p>Refresh this page to observe repeated requests.</p>");
        out.println("</body></html>");
    }
    public void destroy(){System.out.println("destroy() called - Servlet removed.");}
}