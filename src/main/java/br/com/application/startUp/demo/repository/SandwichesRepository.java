package br.com.application.startUp.demo.repository;

import br.com.application.startUp.demo.model.Sandwiches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * A interface who defines the repository utilized by Sandwiches class.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
@Repository
public interface SandwichesRepository extends CrudRepository<Sandwiches, Long>, JpaRepository<Sandwiches, Long> {



}
