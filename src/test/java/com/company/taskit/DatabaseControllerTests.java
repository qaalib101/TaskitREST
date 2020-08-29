package com.company.taskit;

import org.junit.jupiter.api.Test;
import static org.junit.Asee

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@SpringBootTest
public class DatabaseControllerTests {
    DatabaseController dbc = new DatabaseController();
    ArrayList<TaskItem> tasks = new ArrayList<TaskItem>();
    public void setup(){
        Date dueDate = new Date();
        TaskItem task1 = new TaskItem("New title", dueDate,"Solve issue #124", "");
        TaskItem task2 = new TaskItem("Issue #136", dueDate,"Solve issue #136", "");
        tasks.add(task1);
        tasks.add(task2);
    }

    @Test
    public tasksToJSONTest(ta) throws Exception{
        setup();
        String jsonString = dbc.tasksToJSON(tasks);
        ass
    }
}
