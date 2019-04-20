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

@Controller
@RestController
@RequestMapping("orders")
public class OrdersEndPoint {


    @Autowired
    private OrdersService ordersService;


    public OrdersEndPoint() { }

    @PostMapping
    public Order saveOrder(@RequestBody Set<SandwichesOrdered> sandwichesOrdered) throws IllegalArgumentException {
        if(sandwichesOrdered != null){

            try {
                return ordersService.saveSandwichesOrderedsAndSaveOrder(sandwichesOrdered);
            }catch ( Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }
}
