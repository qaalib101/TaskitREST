package com.company.taskit;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class TaskController {

    @Autowired
    DatabaseController db;

    @RequestMapping("/")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/getTasks")
    public String getTasks(@RequestBody String payload) throws JsonProcessingException {
        try{
            String tasks = db.GetAllTasks(Integer.parseInt(payload));
            return tasks;
        }catch (Exception e){
            String message = e.toString();
            return "{\"Response\":\"Failed\", \"Information\" :\""+ message +"\"}";
        }
    }

    @RequestMapping(value = "/newTask", method = RequestMethod.POST, consumes = "text/plain")
    public String newtasks(@RequestBody String payload) throws ParseException, JSONException {
        try{
            TaskItem task = db.newTask(payload);
            return "New Task created: " + task.toString();
        }catch (Exception e){
            String message = e.toString();
            return "{\"Response\":\"Failed\", \"Information\" :\""+ message +"\"}";        }
    }

    @RequestMapping(value = "/updateTasks", method = RequestMethod.POST, consumes = "text/plain")
    public String updateTasks(@RequestBody String payload){
        try{
            ArrayList<TaskItem> list = (ArrayList<TaskItem>) db.updateTask(payload);
            return "{\"Response\": \"Success\"}";
        }catch (Exception e){
            String message = e.toString();
            return "{\"Response\":\"Failed\", \"Information\" :\""+ message +"\"}";
        }
    }

    @RequestMapping(value = "/deleteTasks", method = RequestMethod.POST, consumes = "text/plain")
    public String deleteTasks(@RequestBody String payload){
        try{
            db.deleteTaskItems(payload);
            return "{\"Response\": \"Success\"}";
        }catch (Exception e){
            String message = e.toString();
            return "{\"Response\":\"Failed\", \"Information\" :\""+ message +"\"}";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "text/plain")
    public String search(@RequestBody String payload) throws JSONException, JsonProcessingException {
        String tasks = db.findTasks(payload);
        return tasks;
    }

    @RequestMapping(value = "/maxId", method = RequestMethod.GET)
    public String maxId(@RequestBody String payload) throws JSONException, JsonProcessingException {
        String tasks = db.findTasks(payload);
        return tasks;
    }
}
