package uz.pdp.restfullcodingsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restfullcodingsite.entity.Prob;
import uz.pdp.restfullcodingsite.entity.Theme;
import uz.pdp.restfullcodingsite.payload.ApiResponse;
import uz.pdp.restfullcodingsite.payload.ProbDto;
import uz.pdp.restfullcodingsite.service.ProbService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prob")
public class ProbController {
    @Autowired
    ProbService probService;


    /**
     * Add Prob
     *
     * @param probDto
     * @return String message, boolean success
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addProbController(@RequestBody ProbDto probDto) {
        return probService.addProb(probDto);
    }


    /**
     * Get all Probs
     *
     * @return List
     */
    @GetMapping
    public ResponseEntity<List> getProbsController() {
        return probService.getProbs();
    }


    /**
     * Get Prob
     *
     * @param code
     * @return exist ? Prob : ApiResponse
     */
    @GetMapping("/{code}")
    public ResponseEntity<?> getProbController(@PathVariable String code) {
        return probService.getProb(code);
    }


    /**
     * Update Prob
     *
     * @param probDto
     * @return String message, boolean success
     */
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProbController(@PathVariable int id, @RequestBody ProbDto probDto) {
        return probService.updateProb(id, probDto);
    }


    /**
     * Delete Prob
     *
     * @param id
     * @return String message, boolean success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProbController(@PathVariable int id) {
        return probService.deleteProb(id);
    }

    /**
     * Handle exception
     *
     * @param ex
     * @return http message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
