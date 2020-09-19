package com.company.taskit;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
class TaskItApplicationTests {
    String taskExamples = "[{\"title\":\"New Task\", \"description\":\"Work on this new item\", " +
            "\"dueDate\":\"01/01/3000\", \"comments\":\"\"}]";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }

    @Test
    public void addingNewTask() throws Exception{
        this.mockMvc.perform(post("/newTasks").content(taskExamples)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("title")));
    }
}
