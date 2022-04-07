package uz.pdp.restfullcodingsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.restfullcodingsite.entity.Prob;
import uz.pdp.restfullcodingsite.entity.Theme;
import uz.pdp.restfullcodingsite.payload.ApiResponse;
import uz.pdp.restfullcodingsite.payload.ProbDto;
import uz.pdp.restfullcodingsite.respository.ProbRepo;
import uz.pdp.restfullcodingsite.respository.ThemeRepo;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@Service
public class ProbService {

    @Autowired
    ProbRepo probRepo;
    @Autowired
    ThemeRepo themeRepo;


    /**
     * Add Prob
     *
     * @param probDto
     * @return String message, boolean success
     */
    public ResponseEntity<ApiResponse> addProb(ProbDto probDto) {
        //Check code for unique
        if (!probRepo.existsByCode(probDto.getCode()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Please select unique code for" + probDto.getTitle(),
                    false));
        //Check Theme id
        Optional<Theme> optionalTheme = themeRepo.findById(probDto.getThemesId());
        if (!optionalTheme.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Theme not found",
                    false));


        Prob prob = new Prob();
        prob.setCode(probDto.getCode());
        prob.setQuestion(probDto.getQuestion());
        prob.setSolution(probDto.getSolution());
        prob.setCompleted(probDto.isCompleted());
        prob.setTitle(probDto.getTitle());
        prob.setTheme(optionalTheme.get());
        probRepo.save(prob);
        return ResponseEntity.ok(new ApiResponse("Problem saved successfully", true));
    }


    /**
     * Get all Probs
     *
     * @return List
     */
    public ResponseEntity<List> getProbs() {
        return ResponseEntity.ok(probRepo.findAll());
    }


    /**
     * Get Prob
     *
     * @param code
     * @return exist ? Prob : ApiResponse
     */
    public ResponseEntity<?> getProb(String code) {
        Optional<Prob> optionalProb = probRepo.findByCode(code);
        if (!optionalProb.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Prob not found", false));
        return ResponseEntity.ok(optionalProb.get());
    }


    /**
     * Update Prob
     *
     * @param probDto
     * @return String message, boolean success
     */
    public ResponseEntity<ApiResponse> updateProb(int id, ProbDto probDto) {

        //Check Prob id
        Optional<Prob> optionalProb = probRepo.findById(id);
        if (!optionalProb.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Problem not found",
                    false));

        //Check code for unique
        if (!probRepo.existsByCodeAndIdNot(probDto.getCode(), id))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Please select unique code for" + probDto.getTitle(),
                    false));

        //Check Theme id
        Optional<Theme> optionalTheme = themeRepo.findById(probDto.getThemesId());
        if (!optionalTheme.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Theme not found",
                    false));


        Prob prob = optionalProb.get();
        prob.setCode(probDto.getCode());
        prob.setQuestion(probDto.getQuestion());
        prob.setSolution(probDto.getSolution());
        prob.setCompleted(probDto.isCompleted());
        prob.setTitle(probDto.getTitle());
        prob.setTheme(optionalTheme.get());
        probRepo.save(prob);
        return ResponseEntity.ok(new ApiResponse("Problem updated successfully", true));
    }


    /**
     * Delete Prob
     *
     * @param id
     * @return String message, boolean success
     */
    public ResponseEntity<ApiResponse> deleteProb(int id) {
        //Check Prob id
        if (probRepo.existsById(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Problem not found",
                    false));
        probRepo.deleteById(id);
        return ResponseEntity.ok(new ApiResponse("Problem deleted successfully", true));

    }

}

