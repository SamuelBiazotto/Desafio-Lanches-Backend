package br.com.application.startUp.demo.model;


import br.com.application.startUp.demo.model.common.Common;
import org.hibernate.annotations.Fetch;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name = "sandwiches_ordered")
public class SandwichesOrdered extends Common {

    public SandwichesOrdered() {}

    public SandwichesOrdered(Long sandwichesId, br.com.application.startUp.demo.model.Sandwiches sandwiches, Set<ExtraIngredients> extraIngredients, double value) {
        this.sandwichesId = sandwichesId;
        Sandwiches = sandwiches;
        this.extraIngredients = extraIngredients;
        this.value = value;
    }

    @Column(name = "sandwiches_id")
    private Long sandwichesId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sandwich_id", updatable = false)
    private Sandwiches Sandwiches;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    @JoinTable(
            name = "extra_ingredients_sandwiches_ordered",
            joinColumns = @JoinColumn(name = "sandwiches_ordered"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    private Set<ExtraIngredients> extraIngredients;

    private double value;

    public Set<ExtraIngredients> getExtraIngredients() {
        return extraIngredients;
    }

    public void setExtraIngredients(Set<ExtraIngredients> extraIngredients) {
        this.extraIngredients = extraIngredients;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public br.com.application.startUp.demo.model.Sandwiches getSandwiches() {
        return Sandwiches;
    }

    public void setSandwiches(br.com.application.startUp.demo.model.Sandwiches sandwiches) {
        Sandwiches = sandwiches;
    }

    public Long getSandwichesId() {
        return sandwichesId;
    }

    public void setSandwichesId(Long sandwichesId) {
        this.sandwichesId = sandwichesId;
    }


}
