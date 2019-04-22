package br.com.application.startUp.demo.services;

import br.com.application.startUp.demo.model.Ingredients;
import br.com.application.startUp.demo.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

/**
 * A class who defines the services to Ingredients class.
 *
 * @author Samuel Biazotto de Oliveira.
 **/

@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;


    /**
     * A default constructor to use ingredientsRepository.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param ingredientsRepository repository of the class ingredients.
     **/
    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }


    /**
     * Method to return an double value of an ingredient if found by his id.
     *
     * @param id an Long id of a ingredient.
     * @return a double value of a ingredient found.
     * @author Samuel Biazotto de Oliveira.
     **/
    public Double returnValueOfAnExtraIngredientByIngredientId(long id){
        try {
            Ingredients valueOfAnExtraIngredient = ingredientsRepository.getOne(id);
            return valueOfAnExtraIngredient.getValue();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
