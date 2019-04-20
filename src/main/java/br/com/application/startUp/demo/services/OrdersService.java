package br.com.application.startUp.demo.services;

import br.com.application.startUp.demo.model.ExtraIngredients;
import br.com.application.startUp.demo.model.Ingredients;
import br.com.application.startUp.demo.model.Order;
import br.com.application.startUp.demo.model.SandwichesOrdered;
import br.com.application.startUp.demo.repository.ExtraIngredientsRepository;
import br.com.application.startUp.demo.repository.OrdersRepository;
import br.com.application.startUp.demo.repository.SandwichesOrderedRepository;
import br.com.application.startUp.demo.utils.ExtraIngredientHelperEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrdersService {

    private ExtraIngredientsRepository extraIngredientsRepository;
    private SandwichesOrderedRepository sandwichesOrderedRepository;
    private IngredientsService ingredientsService;
    private OrdersRepository ordersRepository;

    private Boolean sandwichOrderedBacon = Boolean.FALSE;
    private Boolean sandwichOrderedLetuce = Boolean.FALSE;
    private Integer sandwichOrderedMeatQuantity = 0;
    private Integer sandwichOrderedCheeseQuantity = 0;
    private Double sandwichOrderedValue = 0.0;

    @Autowired
    public OrdersService(ExtraIngredientsRepository extraIngredientsRepository, SandwichesOrderedRepository sandwichesOrderedRepository, IngredientsService ingredientsService, OrdersRepository ordersRepository) {
        this.extraIngredientsRepository = extraIngredientsRepository;
        this.sandwichesOrderedRepository = sandwichesOrderedRepository;
        this.ingredientsService = ingredientsService;
        this.ordersRepository = ordersRepository;
    }

    public OrdersService() { }


    public Order saveSandwichesOrderedsAndSaveOrder(Set<SandwichesOrdered> sandwichesOrdereds){
        Set<ExtraIngredients> extraIngredientsSaved;
        Set<SandwichesOrdered> sandwichesOrderedSaved = new HashSet<>();
        Order order = new Order();


        for (SandwichesOrdered sandwichesOrdered : sandwichesOrdereds) {

            Set<ExtraIngredients> sandwichExtraIngredients = sandwichesOrdered.getExtraIngredients();

            if(sandwichExtraIngredients != null  && sandwichExtraIngredients.size() > 0) {
                extraIngredientsSaved =   new HashSet<>(extraIngredientsRepository.saveAll(sandwichExtraIngredients));
            } else {
                extraIngredientsSaved = new HashSet<>();
            }
            sandwichesOrdered.setExtraIngredients(extraIngredientsSaved);

            Set<Ingredients> sandwichIngredients = sandwichesOrdered.getSandwiches().getIngredients();

            sandwichExtraIngredients = sandwichesOrdered.getExtraIngredients();

            verifyIfHasBaconAndLetuceAndcomputeDefaulSandwichtValue(sandwichIngredients);

            verifyIfHasBaconAndLetuceAndExtraQuantityOfCheeseAndMeat(sandwichExtraIngredients);

            addExtraIngredientsValuestoFinalValueIfNotCheeseAndMeat(sandwichExtraIngredients);

            computePromotes();

            sandwichesOrdered.setSandwichesId(sandwichesOrdered.getSandwiches().getId());
            sandwichesOrdered.setValue(sandwichOrderedValue);

            sandwichesOrderedSaved.add(sandwichesOrderedRepository.save(sandwichesOrdered));

            setVariablesToDefaultValue();

        }

        for (SandwichesOrdered sandwichesOrdered : sandwichesOrderedSaved) {
            order.setTotalValue(order.getTotalValue() + sandwichesOrdered.getValue());
        }
        order.setSandwichesOrdereds(sandwichesOrderedSaved);

        return ordersRepository.save(order);

    }


    private void verifyIfHasBaconAndLetuceAndcomputeDefaulSandwichtValue(Set<Ingredients> ingredients){
        for (Ingredients ingredient : ingredients) {
            sandwichOrderedValue += ingredient.getValue();
            if(!sandwichOrderedBacon)
                sandwichOrderedBacon = verifyIfHasBacon(ingredient.getName());
        }
    }

    private void verifyIfHasBaconAndLetuceAndExtraQuantityOfCheeseAndMeat(Set<ExtraIngredients> extraIngredients){
        for (ExtraIngredients extraIngredient: extraIngredients) {
            if(!sandwichOrderedBacon)
                sandwichOrderedBacon = verifyIfHasBacon(extraIngredient.getName());
            if(!sandwichOrderedLetuce)
                sandwichOrderedLetuce = verifyIfHasLetuce(extraIngredient.getName());
            hasExtraCheese(extraIngredient);
            hasExtraMeat(extraIngredient);
        }
    }

    private Boolean verifyIfHasBacon(String name){
        return name.equalsIgnoreCase(ExtraIngredientHelperEnum.Bacon.getDescricao());
    }


    private Boolean verifyIfHasLetuce(String name){
        return name.equalsIgnoreCase(ExtraIngredientHelperEnum.Letuce.getDescricao());
    }

    private void hasExtraCheese(ExtraIngredients extraIngredient){
        if(extraIngredient.getName().equalsIgnoreCase(ExtraIngredientHelperEnum.Cheese.getDescricao())){
            sandwichOrderedCheeseQuantity = extraIngredient.getQuantity();
        }
    }
    private void hasExtraMeat(ExtraIngredients extraIngredient){
        if(extraIngredient.getName().equalsIgnoreCase(ExtraIngredientHelperEnum.Meat.getDescricao())){
            sandwichOrderedMeatQuantity = extraIngredient.getQuantity();
        }
    }

    private void computePromotes(){

        if(verifiIfHasPromotionToMuchCheese()){
            toMuchCheesePromotion();
        }else if (sandwichOrderedCheeseQuantity == 1){
            addValueOfAUniqueExtraCheese();
        }

        if(verifiIfHasPromotionToMuchMeat()){
            toMuchMeatPromotion();
        }else if (sandwichOrderedMeatQuantity == 1){
            addValueOfAUniqueExtraMeat();
        }

        if(verifiIfHasPromotionLight()){
            ligthPromotion();
        }
    }

    private Boolean verifiIfHasPromotionToMuchCheese(){
        return sandwichOrderedCheeseQuantity > 1;
    }

    private Boolean verifiIfHasPromotionToMuchMeat(){
        return sandwichOrderedMeatQuantity > 1;
    }

    private Boolean verifiIfHasPromotionLight(){
        return (!sandwichOrderedBacon && sandwichOrderedLetuce);
    }

    private void ligthPromotion(){
        sandwichOrderedValue -= (sandwichOrderedValue*10)/100;
    }

    private void toMuchCheesePromotion(){
        Double extraIngredientValue = ingredientsService.returnValueOfAnExtraIngredientByIngredientId(5L);
        Integer quantityOfToMuchCheesePromotion = sandwichOrderedCheeseQuantity / 3;
        Integer quantityOfCheese = sandwichOrderedCheeseQuantity- quantityOfToMuchCheesePromotion;
        sandwichOrderedValue += quantityOfCheese * extraIngredientValue;
    }

    private void toMuchMeatPromotion(){
        Double extraIngredientValue = ingredientsService.returnValueOfAnExtraIngredientByIngredientId(3L);
        Integer quantityOfToMuchCheesePromotion = sandwichOrderedMeatQuantity / 3;
        Integer quantityOfMeat = sandwichOrderedMeatQuantity - quantityOfToMuchCheesePromotion;
        sandwichOrderedValue += quantityOfMeat * extraIngredientValue;
    }

    private void addExtraIngredientsValuestoFinalValueIfNotCheeseAndMeat(Set<ExtraIngredients> extraIngredients){
        for (ExtraIngredients extraIngredient : extraIngredients) {
            if (!extraIngredient.getName().equalsIgnoreCase(ExtraIngredientHelperEnum.Cheese.getDescricao()) && !extraIngredient.getName().equalsIgnoreCase(ExtraIngredientHelperEnum.Meat.getDescricao())){
                Double extraIngredientValue = extraIngredient.getQuantity() * extraIngredient.getValue();
                sandwichOrderedValue += extraIngredientValue;
            }
        }
    }

    private void addValueOfAUniqueExtraCheese(){
        sandwichOrderedValue += ingredientsService.returnValueOfAnExtraIngredientByIngredientId(5L);
    }

    private void addValueOfAUniqueExtraMeat(){
        sandwichOrderedValue += ingredientsService.returnValueOfAnExtraIngredientByIngredientId(3L);
    }

    private void setVariablesToDefaultValue(){
        sandwichOrderedBacon = Boolean.FALSE;
        sandwichOrderedLetuce = Boolean.FALSE;
        sandwichOrderedMeatQuantity = 0;
        sandwichOrderedCheeseQuantity = 0;
        sandwichOrderedValue = 0.0;
    }

}
