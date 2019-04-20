package br.com.application.startUp.demo.utils;

public enum ExtraIngredientHelperEnum {

    Meat("Hamburguer de carne"),

    Cheese("Queijo"),

    Bacon("Bacon"),

    Letuce("Alface");

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
