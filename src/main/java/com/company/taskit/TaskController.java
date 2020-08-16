package com.company.taskit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@RestController
public class TaskController {
    @RequestMapping("/")
    public String hello() {
        return "Hello World";
    }
    @RequestMapping("/getTasks")
    public String getTasks(){

    }
    @RequestMapping("/newtasks"){
        
    }
}
