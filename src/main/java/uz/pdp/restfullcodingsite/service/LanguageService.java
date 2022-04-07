package uz.pdp.restfullcodingsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.restfullcodingsite.entity.Language;
import uz.pdp.restfullcodingsite.payload.ApiResponse;
import uz.pdp.restfullcodingsite.payload.LanguageDto;
import uz.pdp.restfullcodingsite.respository.LanguageRepo;

import java.util.Optional;


@Service
public class LanguageService {
    @Autowired
    LanguageRepo languageRepo;


    /**
     * Add language
     * @param languageDto
     * @return String message,boolean success
     */
    public ResponseEntity<ApiResponse> addLanguage(LanguageDto languageDto){

        if(languageRepo.existsByName(languageDto.getName())) // Check name of language to be unique
            return ResponseEntity.status(HttpStatus.SEE_OTHER).body(new ApiResponse("Name of language already exist! Rename",false));

        Language language=new Language();
        language.setName(languageDto.getName());
        language.setHelpSection(languageDto.getHelpSection());
        languageRepo.save(language);
        return ResponseEntity.ok(new ApiResponse("Language saved",true));
    }


    /**
     * Get languages
     * @return Languages
     */
    public ResponseEntity<?> getLanguages(int page){
        Pageable pageable= PageRequest.of(page,10);
        return ResponseEntity.ok( languageRepo.findAll(pageable));
    }


    /**
     * Get language by id
     * @return Language
     */
    public ResponseEntity<?> getLanguageByName(String name){
        Optional<Language> optionalLanguage = languageRepo.findByName(name);
        if(!optionalLanguage.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Name of language not found",false));
        return ResponseEntity.status(HttpStatus.FOUND).body(optionalLanguage.get());
    }


    /**
     * Update Language
     * @param languageDto
     * @param id
     * @return String message , boolean success
     */
    public ResponseEntity<ApiResponse> updateLanguage(LanguageDto languageDto,int id){
        //Check language id from repository
        Optional<Language> optionalLanguage = languageRepo.findById(id);
        if(!optionalLanguage.isPresent())
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Such language not found",false));
        //Check language name from repository
        if(languageRepo.existsByNameAndIdNot(languageDto.getName(),id))
            return   ResponseEntity.status(HttpStatus.SEE_OTHER).body(new ApiResponse("Name  alresdy exist",false));
        Language language=optionalLanguage.get();
        language.setName(languageDto.getName());
        language.setHelpSection(languageDto.getHelpSection());
        languageRepo.save(language);
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse("Language updates",true));

    }


    /**
     * Delete Language
     * @param id
     * @return String message ,boolean success
     */
    public ResponseEntity<ApiResponse> deleteLanguage(int id){
        //Check language id from repository
        Optional<Language> optionalLanguage = languageRepo.findById(id);
        if(!optionalLanguage.isPresent())
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Such language not found",false));

        languageRepo.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Language deleted",true));
    }

}
