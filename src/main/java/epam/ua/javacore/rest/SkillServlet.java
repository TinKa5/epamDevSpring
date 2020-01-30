package epam.ua.javacore.rest;

import com.google.gson.Gson;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.service.AccountService;
import epam.ua.javacore.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name="SkillServ",urlPatterns = "/skill")
public class SkillServlet extends HttpServlet {
Gson gson=new Gson();
SkillService service =new SkillService();

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

        Skill skill = service.add(gson.fromJson(req.getReader(),Skill.class));
        resp.getWriter().println(gson.toJson(skill));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id=req.getParameter("id");
        if (id!=null){
            service.delete(Long.parseLong(id));
            resp.sendRedirect("/skill");
        }else {
            resp.sendError(555,"Incorrect id");
        }
    }
}
