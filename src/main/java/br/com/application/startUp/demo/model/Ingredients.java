package br.com.application.startUp.demo.model;

import br.com.application.startUp.demo.model.common.Common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * A class who defines the entity Ingredients.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
@Entity
@Table(name = "ingredients")
public class Ingredients extends Common {

    public Ingredients() {}

    public Ingredients(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    private String name;

    private Double value;

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
}
