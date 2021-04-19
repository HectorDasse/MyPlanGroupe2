package com.example.myPlan.Controller;


//import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class MainController {

    @GetMapping(path="/toto")
    public @ResponseBody String toto() {
        // This returns a JSON or XML with the users
        return "toto";
    }

    //TODO : Mapping erreur 404

    //Aucun des mapping erreur ci-dessous ne fonctionne

    /*
    @GetMapping(value="server.error.path")
    public @ResponseBody String error() {
        // This returns a JSON or XML with the users
        return "Ç'est pété";
    }
     */

    /*
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("Ç'est pété", ex.getMessage());
        mv.setViewName("error/404");

        return mv;
    }
     */

    /*
    @RequestMapping("/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "/error/404, c'est pété";
    }
     */

    /*
    @GetMapping(value="server.error.path")
    public @ResponseBody String error() {
        // This returns a JSON or XML with the users
        return "Ç'est pété";
    }
    */

}