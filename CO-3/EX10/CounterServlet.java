import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterServlet extends HttpServlet {
    private final AtomicInteger count=new AtomicInteger(0);
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        int visitors=count.incrementAndGet();
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        out.println("<html><body style='font-family:Arial'>");
        out.println("<h2>Thread-Safe Concurrent Visitor Counter</h2>");
        out.println("<p>Current visitor count: <b>"+visitors+"</b></p>");
        out.println("<p>AtomicInteger provides thread-safe increment operations.</p>");
        out.println("</body></html>");
    }
}