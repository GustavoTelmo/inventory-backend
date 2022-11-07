package com.company.inventory.services;

import com.company.inventory.daos.ICategoryDao;
import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryDao iCategoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {

        CategoryResponseRest response = new CategoryResponseRest();

        try {
            List<Category> category = (List<Category>) iCategoryDao.findAll();
            response.getCategoryResponse().setCategory(category);
            response.setMetadata("respuesta ok", "00", "respuesta exitosa");

        } catch (Exception e) {
           response.setMetadata("respuesta nok","-1","error al consultar en la BBDD");
           e.getStackTrace();
           return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
           return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
