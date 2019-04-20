package br.com.application.startUp.demo.services;

import br.com.application.startUp.demo.model.Ingredients;
import br.com.application.startUp.demo.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    protected double returnValueOfAnExtraIngredientByIngredientId(long id){
        Ingredients valueOfAnExtraIngredient = ingredientsRepository.getOne(id);
        return valueOfAnExtraIngredient.getValue();
    }
}
