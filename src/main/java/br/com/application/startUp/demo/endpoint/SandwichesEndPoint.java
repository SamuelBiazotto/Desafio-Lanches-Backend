package br.com.application.startUp.demo.endpoint;


import br.com.application.startUp.demo.model.Sandwiches;
import br.com.application.startUp.demo.repository.SandwichesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A class used to be an end point of sandwiches.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
@Controller
@RestController
@RequestMapping("sandwiches")
public class SandwichesEndPoint {

    private final SandwichesRepository sandwichesRepository;

    /**
     * A default constructor for sandwichesRepository.
     *
     * @param sandwichesRepository constructor default for SandwichesRepository.
     * @author Samuel Biazotto de Oliveira.
     **/
    public SandwichesEndPoint(SandwichesRepository sandwichesRepository) {
        this.sandwichesRepository = sandwichesRepository;
    }

    /**
     * Method which return a list of all default sandwiches stored in the database.
     *
     * @throws IllegalAccessException if cant get all sandwiches
     * @author Samuel Biazotto de Oliveira.
     * @return List of all default sandwiches.
     **/
    @GetMapping
    public List<Sandwiches> getAll() throws IllegalAccessException {
        try {
            return sandwichesRepository.findAll();
        }catch (Exception e){
            throw new IllegalAccessException();
        }
    }

}
