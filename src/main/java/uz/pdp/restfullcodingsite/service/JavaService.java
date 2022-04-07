package uz.pdp.restfullcodingsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.restfullcodingsite.entity.Prob;
import uz.pdp.restfullcodingsite.entity.Theme;
import uz.pdp.restfullcodingsite.respository.ProbRepo;
import uz.pdp.restfullcodingsite.respository.ThemeRepo;

import java.util.Optional;

@Service
public class JavaService {

    @Autowired
    ThemeRepo themeRepo;
    @Autowired
    ProbRepo probRepo;


    /**
     * Show all Java themes
     *
     * @return
     */
    public ResponseEntity<?> javaPage(){
         return ResponseEntity.ok(themeRepo.findByLanguage_Name("Java"));
    }


    /**
     * Show all probs of one theme
     *
     * @param name
     * @return list
     */
    public ResponseEntity<?> java_themePage(String name){
        //Check theme name
        Optional<Theme> optionalTheme = themeRepo.findByName(name);
        if(!optionalTheme.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(themeRepo.findByLanguage_Name("Java"));
        Object allByTheme_name = probRepo.findAllByTheme_Name(name);
        return ResponseEntity.ok(allByTheme_name);
    }


}
