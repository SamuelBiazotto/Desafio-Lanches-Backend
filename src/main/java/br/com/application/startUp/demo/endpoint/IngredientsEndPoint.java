package br.com.application.startUp.demo.endpoint;

import br.com.application.startUp.demo.model.Ingredients;
import br.com.application.startUp.demo.repository.IngredientsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("ingredients")
public class IngredientsEndPoint {

    private final IngredientsRepository ingredientsRepository;

    public IngredientsEndPoint(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @GetMapping
    public List<Ingredients> getAllIngredients() throws IllegalAccessException {
        try {
            return ingredientsRepository.findAll();
        }catch (Exception e){
            throw new IllegalAccessException();
        }
    }
}
