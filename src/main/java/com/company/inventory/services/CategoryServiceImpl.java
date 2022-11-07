package com.company.inventory.services;

import com.company.inventory.daos.ICategoryDao;
import com.company.inventory.models.Category;
import com.company.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category>list= new ArrayList<>();

        try {
            Optional<Category>category = iCategoryDao.findById(id);
            if (category.isPresent()){
                list.add(category.get());
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("respuesta nok","00","categoria encontrada");
            }else {
                response.setMetadata("respuesta nok","-1","error al consultar en la BBDD");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("respuesta nok","-1","error al consultar en la BBDD");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
           return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category>list= new ArrayList<>();

        try {
            Category categorySave = iCategoryDao.save(category);
            if (categorySave != null){
                list.add(categorySave);
                response.getCategoryResponse().setCategory(list);
                response.setMetadata("respuesta ok","00","categoria guardada");
            }else {
                response.setMetadata("respuesta nok","-1","categoria no guardada");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMetadata("respuesta nok","-1","error al guardar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
           return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> uptade(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category>list= new ArrayList<>();

        try {
            Optional<Category> categorySearch = iCategoryDao.findById(id);
            if (categorySearch.isPresent()){
                categorySearch.get().setName(category.getName());
                categorySearch.get().setDescription(category.getDescription());
                Category categoryUpdate = iCategoryDao.save(categorySearch.get());
                if(categoryUpdate != null){
                    list.add(categoryUpdate);
                    response.getCategoryResponse().setCategory(list);
                    response.setMetadata("respuesta ok","00","categoria actualizada");
                }else {
                    response.setMetadata("respuesta nok","-1","categoria no actualizada");
                    return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.BAD_REQUEST);
                }
            }else {
                response.setMetadata("respuesta nok","-1","categoria no actualizada");
                return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetadata("respuesta nok","-1","error al actualizar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();

        try {
            iCategoryDao.deleteById(id);
            response.setMetadata("respuesta ok", "00", "categoria eliminada");

        } catch (Exception e) {
            response.setMetadata("respuesta nok","-1","error al eliminar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

}
