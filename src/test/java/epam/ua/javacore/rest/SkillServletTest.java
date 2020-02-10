package epam.ua.javacore.rest;


import epam.ua.javacore.exception.NotFoundException;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.service.SkillService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SkillServletTest extends Mockito{

    @InjectMocks
    private static SkillServlet servlet;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    @Mock
    SkillService service;


    private Skill entity1 = new Skill("Java");
    private String entity2="./src/test/resources/mockSkill.txt";


    @BeforeClass
    public static void init() {
        servlet = new SkillServlet();
    }

    @Test
    public void testGetId() throws NotFoundException, IOException {

        Long id = 2L;
        when(request.getParameter("id")).thenReturn(id.toString());

        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        when(service.get(id)).thenReturn(entity1);

        servlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(service, atLeast(1)).get(id);

    }

    @Test
    public void testGetIdNotFound() throws NotFoundException, IOException {

        Long id = 2L;
        when(request.getParameter("id")).thenReturn(id.toString());
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        when(service.get(id)).thenThrow(NotFoundException.class);
        servlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(service, atLeast(1)).get(id);
        verify(response,atLeast(1)).sendError(eq(561),anyString());

    }

    @Test
    public void testGetAll() throws NotFoundException, IOException {

        when(request.getParameter("id")).thenReturn(null);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));

        List<Skill> collection=new ArrayList<Skill>(){{
            add(entity1);
        }};
        when(service.getAll()).thenReturn(collection);

        servlet.doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(service, atLeast(1)).getAll();

    }

    @Test
    public void testAdd() throws IOException, ServletException,NotFoundException{

        BufferedReader br=new BufferedReader(new FileReader(new File(entity2)));
        when(request.getReader()).thenReturn(br);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        when(service.add(isNotNull(Skill.class))).thenReturn(entity1);
        servlet.doPost(request, response);
        verify(service, atLeast(1)).add(isNotNull(Skill.class));
    }

    @Test
    public void doDelete() throws IOException,NotFoundException, ServletException {

        Long id = 3L;
        when(request.getParameter("id")).thenReturn(id.toString());
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        doNothing().when(service).delete(isA(Long.class));

        servlet.doDelete(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(service, atLeast(1)).delete(id);
    }
}