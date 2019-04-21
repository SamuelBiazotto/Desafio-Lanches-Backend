package br.com.application.startUp.demo.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class OrdersServiceTest {

    @Autowired
    OrdersService ordersService;

    @Test
    public void saveSandwichesOrderedsAndSaveOrder() {
    }

    private void testIfLightPromotionReturnTheCorrectValue (){
        Double value = 11.50;
        ordersService.lightPromotion();
    }
}