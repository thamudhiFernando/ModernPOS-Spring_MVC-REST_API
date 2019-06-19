package lk.pos.springmvc.finalapp.repository;

import lk.pos.springmvc.finalapp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, String> {

}
