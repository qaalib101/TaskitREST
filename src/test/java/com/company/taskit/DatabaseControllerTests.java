package com.company.taskit;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

@SpringBootTest
public class DatabaseControllerTests {

    DatabaseController dbc;

    ArrayList<TaskItem> tasks = new ArrayList<TaskItem>();
    String payload = "[{\"title\":\"New Task\", \"description\":\"Work on this new item\", " +
            "\"dueDate\":\"01/01/3000\", \"comments\":\"\"}]";

    @Before
    public void setup(){
        this.dbc = new DatabaseController();
        Date dueDate = new Date();
        TaskItem task1 = new TaskItem("New title", dueDate,"Solve issue #124", "");
        TaskItem task2 = new TaskItem("Issue #136", dueDate,"Solve issue #136", "");
        tasks.add(task1);
        tasks.add(task2);
    }

    @Test
    public void tasksToJSONTest() throws Exception{
        String actual = dbc.tasksToJSON(tasks);
        String expected = "";
        assertEquals(expected, actual);
    }

    @Test
    public void newTaskTest() throws Exception{
//        LinkedList<TaskItem> list = (LinkedList<TaskItem>) dbc.newTask(payload);
        setup();
        TaskItem task1 = tasks.get(0);

        when(dbc.newTask(payload))
                .thenAnswer(i -> i.getArguments()[0]);
    }
}
