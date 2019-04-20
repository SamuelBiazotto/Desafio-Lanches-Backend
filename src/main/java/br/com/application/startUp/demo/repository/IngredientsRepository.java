package br.com.application.startUp.demo.repository;

import br.com.application.startUp.demo.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends CrudRepository<Ingredients, Long>, JpaRepository <Ingredients, Long>{
}
