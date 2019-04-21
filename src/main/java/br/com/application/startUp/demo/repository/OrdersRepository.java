package br.com.application.startUp.demo.repository;

import br.com.application.startUp.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * A interface who defines the repository utilized by Order class.
 *
 * @author Samuel Biazotto de Oliveira.
 **/
@Repository
public interface OrdersRepository extends JpaRepository<Order, Long>, CrudRepository<Order, Long> {
}
