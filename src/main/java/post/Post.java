package post;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Post {
    private int id;
    private String name;
    private String title;
    private String content;
    private LocalDate dates;

    public Post(int id, String name, String title, String content, LocalDate dates) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.dates = dates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public LocalDate getDates() {
        return dates;
    }
}
