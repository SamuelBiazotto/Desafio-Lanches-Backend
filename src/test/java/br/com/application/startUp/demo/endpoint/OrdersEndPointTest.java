package br.com.application.startUp.demo.endpoint;

import br.com.application.startUp.demo.model.Sandwiches;
import br.com.application.startUp.demo.model.SandwichesOrdered;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class OrdersEndPointTest {

    @Autowired
    private OrdersEndPoint ordersEndPoint;

//    @Test
//    public void testSaveOrder() {
//        Set<SandwichesOrdered> sandwichesOrdered = new HashSet<>();
//        ordersEndPoint.saveOrder(sandwichesOrdered);
//    }
}