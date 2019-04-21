package br.com.application.startUp.demo.endpoint;

import br.com.application.startUp.demo.model.Ingredients;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IngredientsEndPointTest {

    @Autowired
    private IngredientsEndPoint ingredientsEndPoint;

    @Test
    public void testGetAllIngredientsNotNull() throws IllegalAccessException {
        List<Ingredients> allIngredients = ingredientsEndPoint.getAllIngredients();
        MatcherAssert.assertThat(allIngredients, Matchers.notNullValue());
    }

    @Test
    public void testGetAllIngredientsGreatThanZero() throws IllegalAccessException {
        List<Ingredients> allIngredients = ingredientsEndPoint.getAllIngredients();
        MatcherAssert.assertThat(allIngredients.size(), Matchers.greaterThan(0));
    }
}