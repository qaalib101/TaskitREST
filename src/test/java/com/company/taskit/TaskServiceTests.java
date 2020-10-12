package com.company.taskit;
import ie.corballis.fixtures.annotation.Fixture;
import org.h2.util.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

@SpringBootTest
public class TaskServiceTests {

    @Autowired
    TaskService service;

    ArrayList<TaskItem> tasks = new ArrayList<TaskItem>();

    public void setup(){
        Date dueDate = new Date();
        TaskItem task1 = new TaskItem("New title", dueDate,"Solve issue #124", "");
        TaskItem task2 = new TaskItem("Issue #136", dueDate,"Solve issue #136", "");
        tasks.add(task1);
        tasks.add(task2);
    }


    @Test
    @Transactional
    public void saveAll() throws Exception{
        setup();
        ArrayList<TaskItem> list = (ArrayList<TaskItem>) service.saveAll(tasks);

        long Id = list.get(0).getId();
        for(int i = 0; i < list.size(); i++){
            assertEquals(list.get(i).getTitle(), tasks.get(i).getTitle());
        }
    }

    @Test
    @Transactional
    public void testDeleteTasks() throws Exception {
        setup();
        service.saveAll(tasks);
        service.deleteById(tasks.get(0).getId());
        long count = service.getCount();
        assertEquals(1, count);
    }

    @Test
    @Transactional
    public void testFindAll() throws Exception{
        setup();
        service.saveAll(tasks);
        ArrayList<TaskItem> list = (ArrayList<TaskItem>) service.findAll();
        for(int i = 0; i < list.size(); i++){
            assertEquals(list.get(i).getId(), tasks.get(i).getId());
            assertEquals(list.get(i).getTitle(), tasks.get(i).getTitle());
            assertEquals(list.get(i).getDescription(), tasks.get(i).getDescription());
        }
    }
}
