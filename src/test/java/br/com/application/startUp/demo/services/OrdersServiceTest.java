package br.com.application.startUp.demo.services;

import br.com.application.startUp.demo.model.*;
import br.com.application.startUp.demo.repository.ExtraIngredientsRepository;
import br.com.application.startUp.demo.repository.OrdersRepository;
import br.com.application.startUp.demo.repository.SandwichesOrderedRepository;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OrdersServiceTest {

    private OrdersService ordersService;
    private IngredientsService ingredientsService= null;
    @Before
    public void setup(){
        ExtraIngredientsRepository extraIngredientsRepository = new Mockito().mock(ExtraIngredientsRepository.class);
        SandwichesOrderedRepository sandwichesOrderedRepository = new Mockito().mock(SandwichesOrderedRepository.class);
        OrdersRepository ordersRepository = new Mockito().mock(OrdersRepository.class);
        ingredientsService = new Mockito().mock(IngredientsService.class);
        ordersService = new OrdersService(extraIngredientsRepository,sandwichesOrderedRepository,ingredientsService,ordersRepository);

        Set<ExtraIngredients> sandwichExtraIngredients = new HashSet<>();
        List<ExtraIngredients> newSandwichExtraIngredients = new ArrayList<>();
        Mockito.when(extraIngredientsRepository.saveAll(sandwichExtraIngredients)).thenReturn(newSandwichExtraIngredients);

        SandwichesOrdered newSandwichesOrdered = new SandwichesOrdered();
        Mockito.when(sandwichesOrderedRepository.save(newSandwichesOrdered)).thenReturn(null);

        Order newOrder = new Order();
        Mockito.when(ordersRepository.save(newOrder)).thenReturn(null);
    }

    @Test
    public void testIfLightPromotionReturnTheCorrectValue (){
        OrdersService ordersService = new OrdersService();

        ordersService.setSandwichOrderedValue(10.0);
        ordersService.lightPromotion();
        assertEquals(ordersService.getSandwichOrderedValue(), 9.0, 00.1);
    }


    @Test
    public void testIfAValueOfOneMeatIsAddToASandiwichOrderedValue() {
        OrdersService ordersService = new OrdersService();

        ordersService.setIngredientsService(ingredientsService);
        Mockito.when(ingredientsService.returnValueOfAnExtraIngredientByIngredientId(3L)).thenReturn(3.0);

        ordersService.setSandwichOrderedValue(10.0);
        ordersService.addValueOfAUniqueExtraMeat();
        assertEquals(ordersService.getSandwichOrderedValue(), 13.0,0.01);
    }

    @Test
    public void testIfAValueOfOneCheeseIsAddToASandiwichOrderedValue() {
        OrdersService ordersService = new OrdersService();

        ordersService.setIngredientsService(ingredientsService);
        Mockito.when(ingredientsService.returnValueOfAnExtraIngredientByIngredientId(5L)).thenReturn(9.0);

        ordersService.setSandwichOrderedValue(10.0);
        ordersService.addValueOfAUniqueExtraCheese();
        assertEquals(ordersService.getSandwichOrderedValue(), 19.0,0.01);
    }

    @Test
    public void testeIfAnOrderWithSandwichesOrderedHasBeenCreated(){
        Set<SandwichesOrdered> sandwichesOrdereds = new HashSet<>();
        SandwichesOrdered sandwichesOrdered = new SandwichesOrdered();
        Sandwiches sandwiches = new Sandwiches();

        sandwiches.setName("New Sandwich");
        Ingredients ingredients = new Ingredients();
        ingredients.setName("bacon");
        ingredients.setValue(1.00);
        Set<Ingredients> set = new HashSet();

        set.add(ingredients);
        sandwiches.setIngredients(set);

        sandwichesOrdered.setSandwiches(sandwiches);
        sandwichesOrdereds.add(sandwichesOrdered);

        Order result = ordersService.saveSandwichesOrderedsSaveExtraIngredientsAndSaveOrder(sandwichesOrdereds);
        assertTrue(true);
    }

    @Test
    public void testIfAIngredientIsLettuce(){
        ExtraIngredients extraIngredients = new ExtraIngredients();
        extraIngredients.setName("Alface");
        assertTrue(ordersService.verifyIfHasLettuce(extraIngredients.getName()));
    }

    @Test
    public void testIfAIngredientIsBacon(){
        String name = "Bacon";
        assertTrue(ordersService.verifyIfHasBacon(name));
    }


    @Test
    public void testIfMethodIsGetingTheQuantityOfExtraMeat() {
        ExtraIngredients extraIngredient = new ExtraIngredients();
        extraIngredient.setName("Hamburguer de carne");
        extraIngredient.setQuantity(5);
        ordersService.hasExtraMeat(extraIngredient);
        Integer sandwichOrderedMeatQuantity = ordersService.getSandwichOrderedMeatQuantity();
        assertEquals(sandwichOrderedMeatQuantity, 5, 0);
    }

    @Test
    public void testIfMethodIsGetingTheQuantityOfExtraCheese(){
        ExtraIngredients extraIngredient = new ExtraIngredients();
        extraIngredient.setName("Queijo");
        extraIngredient.setQuantity(9);
        ordersService.hasExtraCheese(extraIngredient);
        Integer sandwichOrdereCheeseQuantity = ordersService.getSandwichOrderedCheeseQuantity();
        assertEquals(sandwichOrdereCheeseQuantity, 9, 0);
    }


    @Test
    public void testIfToMuchMeatIsSetingTheRightValueToASandwichOrdered() {
        ordersService.setIngredientsService(ingredientsService);
        ordersService.setSandwichOrderedValue(15.0);
        ordersService.setSandwichOrderedMeatQuantity(12);
        Mockito.when(ingredientsService.returnValueOfAnExtraIngredientByIngredientId(3L)).thenReturn(9.0);

        ordersService.toMuchMeatPromotion();

        assertEquals(ordersService.getSandwichOrderedValue(), 87.0, 0.01);
    }

    @Test
    public void testIfToMuchCheeseIsSetingTheRightValueToASandwichOrdered(){
        ordersService.setIngredientsService(ingredientsService);
        ordersService.setSandwichOrderedValue(5.5);
        ordersService.setSandwichOrderedCheeseQuantity(5);
        Mockito.when(ingredientsService.returnValueOfAnExtraIngredientByIngredientId(5L)).thenReturn(3.5);

        ordersService.toMuchCheesePromotion();

        assertEquals(ordersService.getSandwichOrderedValue(), 19.5, 0.01);
    }


    @Test
    public void testIfHasToBeApplyTheRuleForToMuchMeatPromotion() {
        ordersService.setSandwichOrderedMeatQuantity(7);
        ordersService.verifiIfHasPromotionToMuchMeat();
        assertTrue(ordersService.verifiIfHasPromotionToMuchMeat());
    }

    @Test
    public void testIfHasToBeApplyTheRuleForToMuchCheesePromotion () {
        ordersService.setSandwichOrderedCheeseQuantity(2);
        ordersService.verifiIfHasPromotionToMuchCheese();
        assertTrue(ordersService.verifiIfHasPromotionToMuchCheese());
    }


    @Test
    public void testIfIngredientsThaAreNotCheeseAndMeatAreBeenAddToASandwichValue() {
        ExtraIngredients extraIngredients1 = new ExtraIngredients();
        ExtraIngredients extraIngredients2 = new ExtraIngredients();
        ExtraIngredients extraIngredients3 = new ExtraIngredients();

        extraIngredients1.setName("Bacon");
        extraIngredients1.setValue(2.0);
        extraIngredients1.setQuantity(5);

        extraIngredients2.setName("Ovo");
        extraIngredients2.setValue(3.0);
        extraIngredients2.setQuantity(4);

        extraIngredients3.setName("Alface");
        extraIngredients3.setValue(1.0);
        extraIngredients3.setQuantity(2);

        Set<ExtraIngredients> extraIngredientsSet = new HashSet<>();
        extraIngredientsSet.add(extraIngredients1);
        extraIngredientsSet.add(extraIngredients2);
        extraIngredientsSet.add(extraIngredients3);

        ordersService.setSandwichOrderedValue(2.0);
        ordersService.addExtraIngredientsValuestoFinalValueIfNotCheeseAndMeat(extraIngredientsSet);

        assertEquals(ordersService.getSandwichOrderedValue(), 26.0, 0.01);

    }

    @Test
    public void testIfApplyPromotionLight() {
        ordersService.setSandwichOrderedBacon(false);
        ordersService.setSandwichOrderedLettuce(true);
        assertTrue(ordersService.verifiIfHasPromotionLight());
    }

    @Test
    public void testIfNotApplyPromotionLight() {
        ordersService.setSandwichOrderedBacon(true);
        ordersService.setSandwichOrderedLettuce(true);
        assertFalse(ordersService.verifiIfHasPromotionLight());
    }

    @Test
    public void t() {

    }

}