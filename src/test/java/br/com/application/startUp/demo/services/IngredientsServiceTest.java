package br.com.application.startUp.demo.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IngredientsServiceTest {

    @Autowired
    private IngredientsService ingredientsService;

    @Test
    public void testReturnValueOfAnExtraIngredientByIngredientIdNotNull() {
        double ingredient = ingredientsService.returnValueOfAnExtraIngredientByIngredientId(1L);
        MatcherAssert.assertThat(ingredient, Matchers.notNullValue());
    }

}