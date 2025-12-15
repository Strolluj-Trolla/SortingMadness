package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.rest.dto.SortRequestDTO;
import pl.put.poznan.transformer.rest.dto.SortResponseDTO;

@RestController
@RequestMapping("/sort")
public class SortingController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public SortResponseDTO sort(@RequestBody SortRequestDTO request) {
        SortService sortService = new SortService(request);
        return sortService.handleRequest();
    }

}
