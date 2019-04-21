package br.com.application.startUp.demo.endpoint;

import br.com.application.startUp.demo.model.ExtraIngredients;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtraIngredientEndPointTest {

    @Autowired
    private ExtraIngredientEndPoint extraIngredientEndPoint;

    @Test
    public void testGetAllExtraIngredientsNotNull() throws IllegalAccessException {
        List<ExtraIngredients> allExtraIngredients = extraIngredientEndPoint.getAllExtraIngredients();
        MatcherAssert.assertThat(allExtraIngredients, Matchers.notNullValue());
    }

    @Test
    public void testGetAllExtraIngredientsGreaterThanZero() throws IllegalAccessException {
        List<ExtraIngredients> allExtraIngredients = extraIngredientEndPoint.getAllExtraIngredients();
        MatcherAssert.assertThat(allExtraIngredients.size(), Matchers.greaterThan(0));
    }
}