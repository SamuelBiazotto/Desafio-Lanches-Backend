package br.com.application.startUp.demo.repository;

import br.com.application.startUp.demo.model.SandwichesOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * A interface who defines the repository utilized by SandwichesOrdered class.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
@Repository
public interface SandwichesOrderedRepository extends JpaRepository<SandwichesOrdered, Long>, CrudRepository<SandwichesOrdered, Long> {
}
