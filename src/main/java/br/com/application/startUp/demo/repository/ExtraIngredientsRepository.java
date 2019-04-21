package br.com.application.startUp.demo.repository;

import br.com.application.startUp.demo.model.ExtraIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



/**
 * A interface who defines the repository utilized by ExtraIngredients class.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
@Repository
public interface ExtraIngredientsRepository extends CrudRepository<ExtraIngredients, Long>, JpaRepository<ExtraIngredients, Long> {


}
