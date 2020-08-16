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
    private Date dueDate;
    private Date CreationDate;


    public TaskItem(String title, Date dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.CreationDate = new Date();
    }

    public TaskItem() {

    }
}
