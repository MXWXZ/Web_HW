import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/BookInfo")
public class BookInfo extends HttpServlet {
    private String books;

    @Override
    public void init() throws ServletException {
        String url = "jdbc:mariadb://localhost:3306/store";
        String user = "root";
        String passwd = "123456";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, passwd);
            System.out.println(con.getTransactionIsolation());

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from book");

            List<BookObj> obj = new ArrayList<BookObj>();
            while (res.next()) {
                obj.add(new BookObj(
                        res.getString("book_name"),
                        res.getString("book_author"),
                        res.getInt("book_price"),
                        res.getString("book_isbn"),
                        res.getString("book_img")));
            }
            books = JSON.toJSONString(obj);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
