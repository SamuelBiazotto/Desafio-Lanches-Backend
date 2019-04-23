package br.com.application.startUp.demo.endpoint;


import br.com.application.startUp.demo.model.ExtraIngredients;
import br.com.application.startUp.demo.repository.ExtraIngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * A class used to be an end point of extra ingredients.
 *
 * @author Samuel Biazotto de Oliveira.
**/

@Controller
@RestController
@RequestMapping("extra_ ingredients")
public class ExtraIngredientEndPoint {

    private final ExtraIngredientsRepository extraIngredientsRepository;

    /**
     * A default constructor for extraIngredientsRepository.
     *
     * @param extraIngredientsRepository constructor default for ExtraIngredientRepository.
     * @author Samuel Biazotto de Oliveira.
    **/
    @Autowired
    public ExtraIngredientEndPoint(ExtraIngredientsRepository extraIngredientsRepository) {
        this.extraIngredientsRepository = extraIngredientsRepository;
    }

    /**
     * Return a list of all extra ingredients that are stored in the database.
     * @throws IllegalAccessException if can't get all extra ingredinets
     * @return a list of all extra ingredients
    **/
    @GetMapping
    public List<ExtraIngredients> getAllExtraIngredients() throws IllegalAccessException {
        try {
            return extraIngredientsRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalAccessException();
        }
    }
}
