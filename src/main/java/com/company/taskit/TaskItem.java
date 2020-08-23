package com.company.taskit;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Data;
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

    public TaskItem() {

    }
}
