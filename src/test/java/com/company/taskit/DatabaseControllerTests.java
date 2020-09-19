package com.company.taskit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@SpringBootTest
public class DatabaseControllerTests {

    DatabaseController dbc = new DatabaseController();

    ArrayList<TaskItem> tasks = new ArrayList<TaskItem>();
    String payload = "[{\"title\":\"New Task\", \"description\":\"Work on this new item\", " +
            "\"dueDate\":\"01/01/3000\", \"comments\":\"\"}]";

    public void setup(){
        Date dueDate = new Date();
        TaskItem task1 = new TaskItem("New title", dueDate,"Solve issue #124", "");
        TaskItem task2 = new TaskItem("Issue #136", dueDate,"Solve issue #136", "");
        tasks.add(task1);
        tasks.add(task2);
    }

    @Test
    public void tasksToJSONTest() throws Exception{
        setup();
        String actual = dbc.tasksToJSON(tasks);
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void newTaskTest() throws Exception{
        ArrayList<TaskItem> list = (ArrayList<TaskItem>) dbc.newTask(payload);
        TaskItem task1 = tasks.get(0);
        for(TaskItem task: list){
            assertEquals(task.getTitle(), task1.getTitle());
        }
    }
}
