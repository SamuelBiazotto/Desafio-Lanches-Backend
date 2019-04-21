package br.com.application.startUp.demo.endpoint;

import br.com.application.startUp.demo.model.Order;
import br.com.application.startUp.demo.model.SandwichesOrdered;
import br.com.application.startUp.demo.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


/**
 * A class used to be an end point of Orders.
 *
 * @author Samuel Biazotto de Oliveira.
 **/


@Controller
@RestController
@RequestMapping("orders")
public class OrdersEndPoint {


    @Autowired
    private OrdersService ordersService;


    /**
     * A default constructor of OrdersEndPoint.
     *
     * @author Samuel Biazotto de Oliveira.
     **/
    public OrdersEndPoint() { }

    /**
     *  Return an order, where must contain sandwiches ordereds with or without an list of extra ingredients
     *  and a default sandwiches. which sandwich must contains his final value, and the order must contain the total value
     *  of the order.
     *
     * @author Samuel Biazotto de Oliveira.
     * @param sandwichesOrdered an set of sandwiches ordereds.
     * @return A new order with at least one default sandwich.
     **/
    @PostMapping
    public Order saveOrder(@RequestBody Set<SandwichesOrdered> sandwichesOrdered) throws IllegalArgumentException {
        if(sandwichesOrdered != null){

            try {
                return ordersService.saveSandwichesOrderedsSaveExtraIngredientsAndSaveOrder(sandwichesOrdered);
            }catch ( Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }
}
