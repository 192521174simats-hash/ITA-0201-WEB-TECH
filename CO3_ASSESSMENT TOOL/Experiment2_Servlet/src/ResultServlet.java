import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Experiment Question 2 - Servlet-Based Student Result Processing
 *
 * Accepts Name, Register Number and marks in three subjects through an
 * HTML form (POST). Calculates Total, Average, Highest Mark and Pass/Fail
 * status. Validates missing values and marks outside the range 0-100.
 *
 * Request-specific data is kept in LOCAL variables inside doPost() so the
 * servlet remains safe for concurrent requests (no shared/instance state
 * is used for student data).
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // ---- Read form values (local variables - thread-safe per request) ----
        String name  = request.getParameter("name");
        String regNo = request.getParameter("regNo");
        String m1Str = request.getParameter("mark1");
        String m2Str = request.getParameter("mark2");
        String m3Str = request.getParameter("mark3");

        StringBuilder errors = new StringBuilder();

        // ---- Validate missing values ----
        if (isEmpty(name))  errors.append("Name is required.<br>");
        if (isEmpty(regNo)) errors.append("Register Number is required.<br>");
        if (isEmpty(m1Str)) errors.append("Subject 1 mark is required.<br>");
        if (isEmpty(m2Str)) errors.append("Subject 2 mark is required.<br>");
        if (isEmpty(m3Str)) errors.append("Subject 3 mark is required.<br>");

        int mark1 = 0, mark2 = 0, mark3 = 0;
        boolean numericOk = true;

        // ---- Validate numeric format ----
        if (!isEmpty(m1Str)) {
            try { mark1 = Integer.parseInt(m1Str.trim()); }
            catch (NumberFormatException e) { errors.append("Subject 1 mark must be a number.<br>"); numericOk = false; }
        }
        if (!isEmpty(m2Str)) {
            try { mark2 = Integer.parseInt(m2Str.trim()); }
            catch (NumberFormatException e) { errors.append("Subject 2 mark must be a number.<br>"); numericOk = false; }
        }
        if (!isEmpty(m3Str)) {
            try { mark3 = Integer.parseInt(m3Str.trim()); }
            catch (NumberFormatException e) { errors.append("Subject 3 mark must be a number.<br>"); numericOk = false; }
        }

        // ---- Validate range 0-100 ----
        if (numericOk) {
            if (mark1 < 0 || mark1 > 100) errors.append("Subject 1 mark must be between 0 and 100.<br>");
            if (mark2 < 0 || mark2 > 100) errors.append("Subject 2 mark must be between 0 and 100.<br>");
            if (mark3 < 0 || mark3 > 100) errors.append("Subject 3 mark must be between 0 and 100.<br>");
        }

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Student Result</title>");
        out.println("<style>");
        out.println("body{font-family:Arial,sans-serif;background:#f0f2f5;padding:40px;display:flex;justify-content:center;}");
        out.println(".card{background:#fff;padding:30px 40px;border-radius:10px;box-shadow:0 4px 15px rgba(0,0,0,0.1);width:450px;}");
        out.println("h2{color:#2c3e50;text-align:center;}");
        out.println("p{color:#34495e;font-size:15px;}");
        out.println(".pass{color:#27ae60;font-weight:bold;}");
        out.println(".fail{color:#e74c3c;font-weight:bold;}");
        out.println(".err{color:#e74c3c;background:#fdecea;padding:15px;border-radius:6px;}");
        out.println("a{display:inline-block;margin-top:15px;text-decoration:none;color:#3498db;}");
        out.println("</style></head><body><div class='card'>");

        if (errors.length() > 0) {
            // ---- Validation failed: show errors ----
            out.println("<h2>Validation Error</h2>");
            out.println("<div class='err'>" + errors.toString() + "</div>");
        } else {
            // ---- All valid: perform calculations ----
            int total = mark1 + mark2 + mark3;
            double average = total / 3.0;
            int highest = Math.max(mark1, Math.max(mark2, mark3));

            // Pass/Fail: pass only if every subject mark is >= 40 (typical rule)
            boolean pass = (mark1 >= 40 && mark2 >= 40 && mark3 >= 40);

            out.println("<h2>Student Result</h2>");
            out.println("<p><b>Name:</b> " + escape(name) + "</p>");
            out.println("<p><b>Register Number:</b> " + escape(regNo) + "</p>");
            out.println("<p><b>Subject 1:</b> " + mark1 + "</p>");
            out.println("<p><b>Subject 2:</b> " + mark2 + "</p>");
            out.println("<p><b>Subject 3:</b> " + mark3 + "</p>");
            out.println("<hr>");
            out.println("<p><b>Total:</b> " + total + "</p>");
            out.println("<p><b>Average:</b> " + String.format("%.2f", average) + "</p>");
            out.println("<p><b>Highest Mark:</b> " + highest + "</p>");
            out.println("<p><b>Status:</b> <span class='" + (pass ? "pass" : "fail") + "'>"
                    + (pass ? "PASS" : "FAIL") + "</span></p>");
        }

        out.println("<a href='index.html'>&larr; Submit another result</a>");
        out.println("</div></body></html>");
        out.close();
    }

    // GET requests are simply redirected to the form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    // Basic HTML-escaping to avoid breaking the output markup
    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
