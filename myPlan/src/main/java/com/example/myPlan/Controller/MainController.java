package com.example.myPlan.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {

    @GetMapping(path="/toto")
    public @ResponseBody String toto() {
        // This returns a JSON or XML with the users
        return "toto";
    }
    
}
