package br.com.application.startUp.demo.model;


import br.com.application.startUp.demo.model.common.Common;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "extra_ingredients")
public class ExtraIngredients extends Common {

    public ExtraIngredients() { }

    public ExtraIngredients(String name, Double value, int quantity) {
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }

    private String name;

    private Double value;

    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
