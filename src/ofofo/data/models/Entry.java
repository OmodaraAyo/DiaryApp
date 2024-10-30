package ofofo.data.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entry {
    private int id;
    private String title;
    private String content;
    private final String date;

    public Entry(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return getTitle() +", "+ getContent();
    }
}
