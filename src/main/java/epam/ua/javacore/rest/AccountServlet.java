package epam.ua.javacore.rest;

import com.google.gson.Gson;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name="AccountServ",urlPatterns = "/account")
public class AccountServlet extends HttpServlet {
Gson gson=new Gson();
AccountService service =new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw=resp.getWriter();

        if (req.getParameter("id")!=null){
            Long id=Long.parseLong(req.getParameter("id"));
            pw.println(gson.toJson(service.get(id)));

        }else {
            service.getAll().stream().forEach((x)->pw.println(gson.toJson(x)));
        }
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account= service.add(gson.fromJson(req.getReader(),Account.class));
        resp.getWriter().println(gson.toJson(account));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id=req.getParameter("id");
        if (id!=null){
            service.delete(Long.parseLong(id));
            resp.sendRedirect("/account");
        }else {
            resp.sendError(555,"Incorrect id");
        }
    }
}
