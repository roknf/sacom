package post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection connection;

    public PostDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Oracle JDBC ����̹��� ã�� �� �����ϴ�.");
            }

            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("�����ͺ��̽� ���� ����"); // ������ �����ϸ� ���
        }
    }

    private void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public List<Post> getAllPosts() throws SQLException {
        connect();

        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts ORDER BY id DESC";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                java.sql.Date datesSql = resultSet.getDate("dates");

                LocalDate dates = null;
                if (datesSql != null) {
                    dates = datesSql.toLocalDate();
                }

                Post post = new Post(id, name, title, content, dates);
                posts.add(post);
            }
        } // try-with-resources�� ����Ͽ� statement�� resultSet�� �ڵ����� close()

        disconnect();

        return posts;
    }


    public void addPost(String name, String title, String content) throws SQLException {
        connect();

        String sql = "INSERT INTO posts (name, title, content, Dates) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, title);
            statement.setString(3, content);
            
            LocalDate currentDate = LocalDate.now();  // ���� ��¥ ��������
            statement.setString(4, currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            statement.executeUpdate();
        } // try-with-resources�� ����Ͽ� statement�� �ڵ����� close()

        disconnect();
        System.out.println("��� ����");
    }


}
