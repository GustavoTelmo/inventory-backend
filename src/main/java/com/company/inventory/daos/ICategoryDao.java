package com.company.inventory.daos;

import com.company.inventory.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category,Long> {

}
