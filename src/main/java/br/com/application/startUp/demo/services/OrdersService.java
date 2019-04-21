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

/**
 * A class that contains all the logic to save sandwiches ordered with or whitout extra ingredients,
 * save extra ingredient of each sandwiches and save the order.
 *
 * @author Samuel Biazotto de Oliveira.
 **/

@Service
public class OrdersService {

    private ExtraIngredientsRepository extraIngredientsRepository;
    private SandwichesOrderedRepository sandwichesOrderedRepository;
    private IngredientsService ingredientsService;
    private OrdersRepository ordersRepository;

    private Boolean sandwichOrderedBacon = Boolean.FALSE;
    private Boolean sandwichOrderedLettuce = Boolean.FALSE;
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

    /**
     * Method used to save sandwiches ordereds with or whitout extra ingredients, save extra ingredients of each
     * sandwich and save the order with that sandwiches.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param sandwichesOrdereds a set of sandwiches ordereds with or without a list of extra ingredients.
     * @return a new order, with sandwiches ordereds with or without extra ingredients.
     **/
    public Order saveSandwichesOrderedsSaveExtraIngredientsAndSaveOrder(Set<SandwichesOrdered> sandwichesOrdereds){
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

            verifyIfHasBaconAndLettuceAndcomputeDefaulSandwichtValue(sandwichIngredients);

            verifyIfHasBaconAndLettuceAndExtraQuantityOfCheeseAndMeat(sandwichExtraIngredients);

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

    /**
     * Method which verify if a sandwich has bacon by default ingredients, and set the default value of
     * the sandwich with their default ingredients.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param ingredients a set of ingredients, this ingredients are default of each sandwich.
     * @return nothing.
     **/
    protected void verifyIfHasBaconAndLettuceAndcomputeDefaulSandwichtValue(Set<Ingredients> ingredients){
        for (Ingredients ingredient : ingredients) {
            sandwichOrderedValue += ingredient.getValue();
            if(!sandwichOrderedBacon)
                sandwichOrderedBacon = verifyIfHasBacon(ingredient.getName());
        }
    }

    /**
     * Method which verify if a sandwich has bacon and lettuce and set the variables sandwichOrderedBacon and
     * sandwichOrderedLettuce to true. Check the quantity of meat and cheese. All this in a set of extra ingredients.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param extraIngredients a set of extra ingredients.
     * @return nothing.
     **/
    protected void verifyIfHasBaconAndLettuceAndExtraQuantityOfCheeseAndMeat(Set<ExtraIngredients> extraIngredients){
        for (ExtraIngredients extraIngredient: extraIngredients) {
            if(!sandwichOrderedBacon)
                sandwichOrderedBacon = verifyIfHasBacon(extraIngredient.getName());
            if(!sandwichOrderedLettuce)
                sandwichOrderedLettuce = verifyIfHasLettuce(extraIngredient.getName());
            hasExtraCheese(extraIngredient);
            hasExtraMeat(extraIngredient);
        }
    }

    /**
     * Method which verify if a sandwich has bacon or not. The method compara the name of the extra ingredient
     * with a enumerator and return true if the name is bacon.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param name a string with the name of the extra ingredient.
     * @return nothing.
     **/
    protected Boolean verifyIfHasBacon(String name){
        return name.equalsIgnoreCase(ExtraIngredientHelperEnum.Bacon.getDescricao());
    }

    /**
     * Method which verify if a sandwich has lettuce or not. The mothod compare the name of the extra ingredient
     * with a enumerator and return true if the name is lettuce.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param name a string with the name of the extra ingredient.
     * @return True if name is lettuce.
     **/
    protected Boolean verifyIfHasLettuce(String name){
        return name.equalsIgnoreCase(ExtraIngredientHelperEnum.Lettuce.getDescricao());
    }

    /**
     * Method wich obtains the quantity of extra cheese present in a sandwich. For each extra ingredient, this method
     * vefiry if this extra ingredient is cheese and get his quantity to set it in the sandwichOrderedCheeseQuantity
     * varible.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param extraIngredient a set of extra ingredients inserted in a sandwich.
     * @return nothing.
     **/
    protected void hasExtraCheese(ExtraIngredients extraIngredient){
        if(extraIngredient.getName().equalsIgnoreCase(ExtraIngredientHelperEnum.Cheese.getDescricao())){
            sandwichOrderedCheeseQuantity = extraIngredient.getQuantity();
        }
    }

    /**
     * Method wich obtains the quantity of extra meat present in a sandwich. For each extra ingredient this method verify
     * if this extra ingredient is meat and get his quantity and set it to the sandwichOrderedMeatQuantity variable.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param extraIngredient a set of extra ingredients inserted in a sandwich.
     * @return nothing.
     **/
    protected void hasExtraMeat(ExtraIngredients extraIngredient){
        if(extraIngredient.getName().equalsIgnoreCase(ExtraIngredientHelperEnum.Meat.getDescricao())){
            sandwichOrderedMeatQuantity = extraIngredient.getQuantity();
        }
    }

    /**
     * Method who computes if promotes must be applied or not to each sandwich ordered.
     *
     * @author Samuel Biazotto de Oliveira.
     * @return nothing.
     **/
    protected void computePromotes(){

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
            lightPromotion();
        }
    }

    /**
     * Method wich verify and return if a sandwich belongs to a lots of cheeser promotion. The method vefiry a variable
     * who contains the quantity of extra cheese present in a sandwich. If this quantity is more than one the promotion
     * is applicable to this sandwich.
     *
     * @author Samuel Biazotto de Oliveira.
     * @return True if the quantity of extra cheese present in the sandwich is more than one.
     **/
    protected Boolean verifiIfHasPromotionToMuchCheese(){
        return sandwichOrderedCheeseQuantity > 1;
    }

    /**
     * Method wich verify and return if a sandwich belongs to lots of meat promotion. The method verify a variable who contains
     * the quantity of extra meat present in a sandwich. If this quantity is more than one than the promotion is applicable
     * to this sandwich.
     *
     * @author Samuel Biazotto de Oliveira.
     * @return return true if the quantity of extra meat present in the sandwich is more than one.
     **/
    protected Boolean verifiIfHasPromotionToMuchMeat(){
        return sandwichOrderedMeatQuantity > 1;
    }

    /**
     * Method which verify and return if a sandwich belongs to light promotion or not. The method verify two variables
     * to see if bacon and lettuce are present in a sandwich, if bacon is present the promotion is not applicable, otherwise
     * if bacon is not present but lettuce is present the promotion is applicable.
     * 
     * @author Samuel Biazotto de Oliveira.
     * @return a boolean true if bacon is not present but luttuce is.
     **/
    protected Boolean verifiIfHasPromotionLight(){
        return (!sandwichOrderedBacon && sandwichOrderedLettuce);
    }

    /**
     * Method who applies the rule of promotion ligh, which if the sandwich does not have bacon and have
     * lettuce, win ten percent of discount on the value of that sandwich.
     * 
     * @author Samuel Biazotto de Oliveira.
     * @return nothing
     **/
    protected void lightPromotion(){
        sandwichOrderedValue -= (sandwichOrderedValue*10)/100;
    }


    /**
     * Methodo who applies a rule if a promotion of a lots of cheese is contempled.  Which every three cheeses
     * pays only two cheeses, and this value is added to the value of a sandwich.
     * 
     * @author Samuel Biazotto de Oliveira.
     * @return nothing
     **/
    protected void toMuchCheesePromotion(){
        Double extraIngredientValue = ingredientsService.returnValueOfAnExtraIngredientByIngredientId(5L);
        Integer quantityOfToMuchCheesePromotion = sandwichOrderedCheeseQuantity / 3;
        Integer quantityOfCheese = sandwichOrderedCheeseQuantity- quantityOfToMuchCheesePromotion;
        sandwichOrderedValue += quantityOfCheese * extraIngredientValue;
    }

    /**
     * Methodo who applies a rule if a promotion of a lots of meat is contempled. Which every three meats
     * pays only two meats, and this value is added to the value of a sandwich.
     * 
     * @author Samuel Biazotto de Oliveira.
     * @return nothing.
     **/
    protected void toMuchMeatPromotion(){
        Double extraIngredientValue = ingredientsService.returnValueOfAnExtraIngredientByIngredientId(3L);
        Integer quantityOfToMuchCheesePromotion = sandwichOrderedMeatQuantity / 3;
        Integer quantityOfMeat = sandwichOrderedMeatQuantity - quantityOfToMuchCheesePromotion;
        sandwichOrderedValue += quantityOfMeat * extraIngredientValue;
    }

    /**
     * Method used to add to a value of sandwich the values of all extra ingredients, which are not meat and cheese.
     * 
     * @author Samuel Biazotto de Oliveira.
     * @param extraIngredients a set of extra ingredients that will be included in a sandwich.
     **/
    protected void addExtraIngredientsValuestoFinalValueIfNotCheeseAndMeat(Set<ExtraIngredients> extraIngredients){
        for (ExtraIngredients extraIngredient : extraIngredients) {
            if (!extraIngredient.getName().equalsIgnoreCase(ExtraIngredientHelperEnum.Cheese.getDescricao()) && !extraIngredient.getName().equalsIgnoreCase(ExtraIngredientHelperEnum.Meat.getDescricao())){
                Double extraIngredientValue = extraIngredient.getQuantity() * extraIngredient.getValue();
                sandwichOrderedValue += extraIngredientValue;
            }
        }
    }

    /**
     * Method used to add to a value of a sandwich a value of only one extra cheese.
     * 
     * @author Samuel Biazotto de Oliveira.
     * @return nothing.
     **/
    protected void addValueOfAUniqueExtraCheese(){
        sandwichOrderedValue += ingredientsService.returnValueOfAnExtraIngredientByIngredientId(5L);
    }


    /**
     * Method used to add to a value of a sandwich a value of only one extra meat.
     * 
     * @author Samuel Biazotto de Oliveira.
     * @return nothing.
     **/
    protected void addValueOfAUniqueExtraMeat(){
        sandwichOrderedValue += ingredientsService.returnValueOfAnExtraIngredientByIngredientId(3L);
    }

    /**
     * Method used for reset the variables to their initial state for each sandwich who will be saved.
     * 
     * @author Samuel Biazotto de Oliveira.
     * @return nothing.
     **/
    protected void setVariablesToDefaultValue(){
        sandwichOrderedBacon = Boolean.FALSE;
        sandwichOrderedLettuce = Boolean.FALSE;
        sandwichOrderedMeatQuantity = 0;
        sandwichOrderedCheeseQuantity = 0;
        sandwichOrderedValue = 0.0;
    }

}
