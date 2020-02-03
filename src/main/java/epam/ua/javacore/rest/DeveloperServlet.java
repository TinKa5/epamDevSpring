package epam.ua.javacore.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import epam.ua.javacore.exeption.NotFoundException;
import epam.ua.javacore.model.Developer;

import epam.ua.javacore.service.DeveloperService;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;


@WebServlet(name = "DeveloperServ", urlPatterns = "/developer")
public class DeveloperServlet extends HttpServlet {
    Gson gson = new Gson();
    DeveloperService service = new DeveloperService();
    private static final Logger log = Logger.getLogger(DeveloperServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        log.info("doGet in Servlet");

        try {
            Long id =getId(req);
            try {
                pw.println(gson.toJson(service.get(id)));
            }catch (NotFoundException e){
                log.warn(e.getMessage());
                resp.sendError(561, e.getMessage());
            }
        }catch (NullPointerException e){
            service.getAll().stream().forEach((x) -> pw.println(gson.toJson(x)));
        }

        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Developer dev = null;
        log.info("doPost in Servlet");
        try {
            dev = service.add(gson.fromJson(req.getReader(), Developer.class));
        } catch (JsonParseException e) {
            log.warn("Incorrect Json format");
            resp.sendError(551, "Incorrect Json format");
        } catch (NotFoundException e) {
            log.warn(e.getMessage());
            resp.sendError(561, e.getMessage());
        }
        resp.getWriter().println(gson.toJson(dev));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doDelete in Servlet");
        try{
            service.delete(getId(req));
            resp.sendRedirect("/developer");
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
