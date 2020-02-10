package epam.ua.javacore.rest;


import epam.ua.javacore.exception.NotFoundException;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.AccountStatus;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.service.DeveloperService;
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
import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServletTest extends Mockito{

    @InjectMocks
    private static DeveloperServlet servlet;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    @Mock
    DeveloperService service;

    Developer entity1;
    {
        Skill sk = new Skill("Java");
        sk.setId(1L);
        Account ac = new Account("tinka@gmail.com", AccountStatus.valueOf("ACTIVE"));
        ac.setId(1L);
        entity1 = new Developer("Oskar", new HashSet<>(Arrays.asList(sk)), ac);
    }

    private String entity2="./src/test/resources/mockDeveloper.txt";


    @BeforeClass
    public static void init() {
        servlet = new DeveloperServlet();
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
    public void testGetAll() throws  IOException {

        when(request.getParameter("id")).thenReturn(null);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        List<Developer> collection=new ArrayList<Developer>(){{
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
        PrintWriter pw=new PrintWriter(System.out);
        when(response.getWriter()).thenReturn(pw);
        when(service.add(isNotNull(Developer.class))).thenReturn(entity1);
        servlet.doPost(request, response);
        pw.flush();
        verify(service, atLeast(1)).add(isNotNull(Developer.class));
    }

    @Test
    public void doDelete() throws IOException,NotFoundException, ServletException {

        Long id = 3L;
        when(request.getParameter("id")).thenReturn(id.toString());
        doNothing().when(service).delete(isA(Long.class));
        servlet.doDelete(request, response);
        verify(request, atLeast(1)).getParameter("id");
        verify(service, atLeast(1)).delete(id);
    }
}