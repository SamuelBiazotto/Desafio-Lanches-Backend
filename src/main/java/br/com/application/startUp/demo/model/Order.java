package br.com.application.startUp.demo.model;

import br.com.application.startUp.demo.model.common.Common;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * A class who defines the entity Order.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
@Component
@Entity
@Table(name = "Orders")
public class Order extends Common {

    public Order() {}

    public Order(Set<SandwichesOrdered> sandwichesOrdereds, double totalValue) {
        this.sandwichesOrdereds = sandwichesOrdereds;
        this.totalValue = totalValue;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinTable(
            name = "orders_sandwiches_ordered",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "sandwiches_ordered_id")
    )
    private Set<SandwichesOrdered> sandwichesOrdereds = new HashSet<>();

    private double totalValue;

    public Set<SandwichesOrdered> getSandwichesOrdereds() {
        return sandwichesOrdereds;
    }

    public void setSandwichesOrdereds(Set<SandwichesOrdered> sandwichesOrdereds) {
        this.sandwichesOrdereds = sandwichesOrdereds;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

}
