package br.com.application.startUp.demo.utils;


/**
 * A enum that's used to not campare visually strings. Helps in comparison in OrderService.
 *
 * @author Samuel Biazotto de Olivera.
**/

public enum ExtraIngredientHelperEnum {

    Meat("Hamburguer de carne"),

    Cheese("Queijo"),

    Bacon("Bacon"),

    Lettuce("Alface");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    ExtraIngredientHelperEnum(String descricao) {
        this.descricao = descricao;
    }
}
