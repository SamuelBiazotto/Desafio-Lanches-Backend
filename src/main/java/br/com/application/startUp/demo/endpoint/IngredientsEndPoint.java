package br.com.application.startUp.demo.endpoint;

import br.com.application.startUp.demo.model.Ingredients;
import br.com.application.startUp.demo.repository.IngredientsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A class used to be an end point of ingredients.
 *
 * @author Samuel Biazotto de Oliveira.
 **/

@Controller
@RestController
@RequestMapping("ingredients")
public class IngredientsEndPoint {

    private final IngredientsRepository ingredientsRepository;

    /**
     * A default constructor for extraIngredientsRepository.
     *
     * @param ingredientsRepository constructor default for IngredientsRepository.
     * @author Samuel Biazotto de Oliveira.
     **/
    public IngredientsEndPoint(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    /**
     * Return a list of all default ingredients that are stored in the database.
     *
     * @throws IllegalAccessException if can't get all ingredients
     * @return a list of all default ingredients
     **/
    @GetMapping
    public List<Ingredients> getAllIngredients() throws IllegalAccessException {
        try {
            return ingredientsRepository.findAll();
        }catch (Exception e){
            throw new IllegalAccessException();
        }
    }
}
