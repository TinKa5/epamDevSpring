package epam.ua.javacore.rest;


import epam.ua.javacore.exeption.NotFoundException;
import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.AccountStatus;
import epam.ua.javacore.service.AccountService;
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
public class AccountServletTest extends Mockito{

    @InjectMocks
    private static AccountServlet servlet;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    @Mock
    AccountService service;


    private Account entity1 = new Account("romik@gmail.com", AccountStatus.valueOf("ACTIVE"));
    private String entity2="./src/test/resources/mockData.txt";


    @BeforeClass
    public static void init() {
        servlet = new AccountServlet();
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

        List<Account> collection=new ArrayList<Account>(){{
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
        when(service.add(isNotNull(Account.class))).thenReturn(entity1);
        servlet.doPost(request, response);
        verify(service, atLeast(1)).add(isNotNull(Account.class));
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