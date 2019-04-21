package br.com.application.startUp.demo.model;

import br.com.application.startUp.demo.model.common.Common;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * A class who defines the entity Sandwiches.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
@Entity
@Table(name = "sandwiches")
public class Sandwiches extends Common {

    public Sandwiches() {}

    public Sandwiches(String name, Set<Ingredients> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "ingredients_sandwiches",
            joinColumns = @JoinColumn(name = "sandwiches_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    private Set<Ingredients> ingredients;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
