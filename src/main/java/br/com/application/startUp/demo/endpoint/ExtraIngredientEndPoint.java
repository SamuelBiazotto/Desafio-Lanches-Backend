package br.com.application.startUp.demo.endpoint;


import br.com.application.startUp.demo.model.ExtraIngredients;
import br.com.application.startUp.demo.repository.ExtraIngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("extra_ ingredients")
public class ExtraIngredientEndPoint {

    private final ExtraIngredientsRepository extraIngredientsRepository;

    @Autowired
    public ExtraIngredientEndPoint(ExtraIngredientsRepository extraIngredientsRepository) {
        this.extraIngredientsRepository = extraIngredientsRepository;
    }

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
