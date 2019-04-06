import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BookInfo")
public class BookInfo extends HttpServlet {
    String books;

    @Override
    public void init() throws ServletException {
        books="[{\"name\":\"The Man Who Changed China\",\"author\":\"Robert Lawrence Kuhn\",\"price\":4800,\"isbn\":\"7-5327-3654-7518\",\"img\":\"book1.jpg\",\"url\":\"/book1\"},{\"name\":\"Nineteen Eighty-Four\",\"author\":\"George Orwell\",\"price\":1950,\"isbn\":\"9-7875-4471-1647\",\"img\":\"book2.jpg\",\"url\":\"/book2\"},{\"name\":\"No Longer Human\",\"author\":\"Osamu Dazai\",\"price\":2500,\"isbn\":\"9-7875-0638-0263\",\"img\":\"book3.jpg\",\"url\":\"/book3\"}]";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(books);
        out.flush();
        out.close();
    }
}
