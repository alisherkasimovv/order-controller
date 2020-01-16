package uz.orders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uz.orders.collections.ItemCollection;
import uz.orders.collections.WarehouseWithProduct;
import uz.orders.db.dao.interfaces.ProductDAO;
import uz.orders.db.dao.interfaces.WarehouseDAO;
import uz.orders.db.dao.interfaces.registrars.ItemDAO;
import uz.orders.db.entities.Product;
import uz.orders.db.entities.Warehouse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {
    private ProductDAO productDAO;
    private WarehouseDAO warehouseDAO;

    @Autowired
    public ProductController(ProductDAO productDAO, WarehouseDAO warehouseDAO) {
        this.productDAO = productDAO;
        this.warehouseDAO = warehouseDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<WarehouseWithProduct>>getAllProducts(){
        return new ResponseEntity<>(productDAO.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Product>getProduct(@PathVariable int id){
        return new ResponseEntity<>(productDAO.getById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/get/order-quantities")
    public ResponseEntity<List<ItemCollection>> getSummedItems() {
        return new ResponseEntity<>(productDAO.sumUpAllOrderQuantities(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/warehouse")
    public ResponseEntity<List<Warehouse>> getWarehouseProducts() {
        return new ResponseEntity<>(warehouseDAO.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable int id){
        productDAO.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<List<WarehouseWithProduct>>saveProduct(@Valid @RequestBody Product product){
        productDAO.saveProduct(product);
        return new ResponseEntity<>(productDAO.get(),HttpStatus.OK);
    }

}
