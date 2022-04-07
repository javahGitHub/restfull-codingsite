package uz.pdp.restfullcodingsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.restfullcodingsite.service.JavaService;
import uz.pdp.restfullcodingsite.service.PythonService;

@RestController
@RequestMapping("/python")
public class PythonController {
    @Autowired
    PythonService pythonService;


    /**
     * Show Java themes
     *
     * @return List
     */
    @GetMapping
    public ResponseEntity<?> javaPageController(){
        return pythonService.pythonPage();
    }


    /**
     * Show one Theme
     *
     * @param name
     * @return exist ? Theme : exception
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> java_themePage(@PathVariable String name){
        return   pythonService.python_themePage(name);
    }


}
