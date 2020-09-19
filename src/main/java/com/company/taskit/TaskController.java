package com.company.taskit;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;

@RestController
public class TaskController {
    DatabaseController db = new DatabaseController();

    @RequestMapping("/")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/getTasks")
    public String getTasks() throws JsonProcessingException {
        String tasks = db.GetAllTasks();
        return tasks;
    }
    @RequestMapping(value = "/newTasks", method = RequestMethod.POST)
    public String newtasks(@RequestBody String payload) throws ParseException, JSONException {
        db.newTask(payload);
        return payload;
    }
}
