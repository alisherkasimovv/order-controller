package uz.orders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uz.orders.db.dao.interfaces.ProductDAO;
import uz.orders.db.entities.Product;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Product>>getAllProducts(){
        return new ResponseEntity<>(productDAO.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Product>getProduct(@PathVariable int id){
        return new ResponseEntity<>(productDAO.getById(id),HttpStatus.OK);
    }
    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable int id){
        productDAO.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<List<Product>>saveProduct(@Valid @RequestBody Product product){
        productDAO.saveProduct(product);
        return new ResponseEntity<>(productDAO.get(),HttpStatus.OK);
    }

}
