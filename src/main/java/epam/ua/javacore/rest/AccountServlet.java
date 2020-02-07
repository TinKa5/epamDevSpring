package epam.ua.javacore.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import epam.ua.javacore.exeption.NotFoundException;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.service.AccountService;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Objects;


@WebServlet(name = "AccountServ", urlPatterns = "/account")
public class AccountServlet extends HttpServlet {
    Gson gson = new Gson();
    AccountService service = new AccountService();
    private static final Logger log = Logger.getLogger(AccountServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        log.info("doGet in Servlet");

        try {
            Long id =getId(req);
            try {
                Account account=service.get(id);
                String s=gson.toJson(account);

               // FileWriter fw=new FileWriter("./src/test/resources/mockData.txt");
                //fw.write(s);
               // fw.flush();
                pw.println(s);
            }catch (NotFoundException e){
                log.warn(e.getMessage());
                resp.sendError(561, e.getMessage());
            }
        }catch (NullPointerException e){
            Collection<Account> collection=service.getAll();
            collection.stream().forEach((x) -> pw.println(gson.toJson(x)));
        }

        //pw.flush();
       // pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = null;
        log.info("doPost in Servlet");
        try {
            Account ac=gson.fromJson(req.getReader(), Account.class);
            account = service.add(ac);
        } catch (JsonParseException e) {
            log.warn("Incorrect Json format");
            resp.sendError(551, "Incorrect Json format");
        } catch (NotFoundException e) {
            log.warn(e.getMessage());
            resp.sendError(561, e.getMessage());
        }
        PrintWriter pw=resp.getWriter();
        pw.println(gson.toJson(account));
        pw.flush();
       // pw.close();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doDelete in Servlet");
        try{
            service.delete(getId(req));
            resp.sendRedirect("/account");
        }catch (NullPointerException e){
            log.warn("Incorrect id parameter");
            resp.sendError(552, "Incorrect id parameter");
        }catch (NotFoundException e){
            log.warn(e.getMessage());
            resp.sendError(561, e.getMessage());
        }


    }


    private Long getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Long.parseLong(paramId);
    }
}
