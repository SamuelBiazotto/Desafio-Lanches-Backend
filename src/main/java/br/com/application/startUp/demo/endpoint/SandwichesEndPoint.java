package br.com.application.startUp.demo.endpoint;


import br.com.application.startUp.demo.model.Sandwiches;
import br.com.application.startUp.demo.repository.SandwichesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("sandwiches")
public class SandwichesEndPoint {

    private final SandwichesRepository sandwichesRepository;

    public SandwichesEndPoint(SandwichesRepository sandwichesRepository) {
        this.sandwichesRepository = sandwichesRepository;
    }

    @GetMapping
    public List<Sandwiches> getAll() throws IllegalAccessException {
        try {
            return sandwichesRepository.findAll();
        }catch (Exception e){
            throw new IllegalAccessException();
        }
    }

}
