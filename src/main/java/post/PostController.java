package post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostController")
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PostDAO postDAO;

    public void init() {
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String jdbcUsername = "negi";
        String jdbcPassword = "123123";

        postDAO = new PostDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listPosts(request, response);
                break;
            case "showForm":
                showForm(request, response);
                break;
            case "add":
                addPost(request, response);
                break;
            default:
                listPosts(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        try {
            postDAO.addPost(name, title, content);
            response.sendRedirect("PostController");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            // You can redirect to an error page or show an error message
        }
    }


    private void listPosts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        List<Post> posts = postDAO.getAllPosts();
        request.setAttribute("posts", posts);
        

        System.out.println(posts.size());
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("add-post.jsp").forward(request, response);
    }

    private void addPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        try {
            postDAO.addPost(name, title, content);
            response.sendRedirect("PostController?action=list");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
