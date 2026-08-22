import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class ResultServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out=res.getWriter();
        out.println("<html><body style='font-family:Arial'><h2>Online Student Result</h2>");
        out.println("<form method='post' action='result'>");
        out.println("Student Name: <input name='name'><br><br>");
        for(int i=1;i<=5;i++) out.println("Subject "+i+" Mark: <input type='number' name='m"+i+"' min='0' max='100'><br><br>");
        out.println("<input type='submit' value='Calculate Result'></form></body></html>");
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException{
        res.setContentType("text/html;charset=UTF-8"); PrintWriter out=res.getWriter();
        String name=req.getParameter("name"); int total=0; boolean valid=true;
        for(int i=1;i<=5;i++){
            try{int m=Integer.parseInt(req.getParameter("m"+i)); if(m<0||m>100) valid=false; total+=m;}
            catch(Exception e){valid=false;}
        }
        out.println("<html><body style='font-family:Arial'><h2>Result</h2>");
        if(!valid){out.println("<p style='color:red'>Enter valid marks between 0 and 100 for all five subjects.</p>");}
        else{
            double avg=total/5.0; String grade=avg>=90?"A+":avg>=80?"A":avg>=70?"B":avg>=60?"C":avg>=50?"D":"F";
            String status=avg>=50?"PASS":"FAIL";
            out.println("<p>Name: "+name+"</p><p>Total: "+total+"/500</p><p>Average: "+avg+"</p>");
            out.println("<p>Grade: "+grade+"</p><p>Status: "+status+"</p>");
        }
        out.println("</body></html>");
    }
}