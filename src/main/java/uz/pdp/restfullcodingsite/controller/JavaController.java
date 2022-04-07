package uz.pdp.restfullcodingsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restfullcodingsite.service.JavaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/java")
public class JavaController {


    @Autowired
    JavaService javaService;


    /**
     * Show Java themes
     *
     * @return List
     */
    @GetMapping
    public ResponseEntity<?> javaPageController(){
        return javaService.javaPage();
    }


    /**
     * Show one Theme
     *
     * @param name
     * @return exist ? Theme : exception
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> java_themePage(@PathVariable String name){
      return   javaService.java_themePage(name);
    }

}
