package com.company.taskit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data
public class TaskItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String description;
    private String comments;
    private Date dueDate;
    private Date CreationDate;


    public TaskItem(String title, Date dueDate, String description, String comments) {
        this.title = title;
        this.comments = comments;
        this.description = description;
        this.dueDate = dueDate;
        this.CreationDate = new Date();
    }

    public TaskItem(JSONObject obj) throws ParseException, JSONException {
        this.title = (String) obj.get("title");
        this.comments = (String) obj.get("comments");
        this.description = (String) obj.get("description");
        String date = (String) obj.get("dueDate");
        this.dueDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        this.CreationDate = new Date();
    }

    public TaskItem() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }


}
