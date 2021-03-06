package uz.orders.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.orders.collections.Filter;
import uz.orders.collections.ItemCollection;
import uz.orders.collections.components.IncomeWithItems;
import uz.orders.db.dao.interfaces.ProductDAO;
import uz.orders.db.dao.interfaces.registrars.IncomeDAO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/incomes")
@CrossOrigin("http://localhost:3000")
public class IncomeController {
    
    private IncomeDAO incomeDAO;
    private ProductDAO productDAO;

    public IncomeController(IncomeDAO incomeDAO, ProductDAO productDAO) {
        this.incomeDAO = incomeDAO;
        this.productDAO = productDAO;
    }
    
    @GetMapping(value = "/get")
    public ResponseEntity<List<IncomeWithItems>> getAll() {
        return new ResponseEntity<>(incomeDAO.getAll(null), HttpStatus.OK);
    }

    @PostMapping(value = "/get/filtered")
    public ResponseEntity<List<IncomeWithItems>> getFilteredData(@Valid @RequestBody Filter filter) {
        return new ResponseEntity<>(incomeDAO.getAll(filter), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<IncomeWithItems> getById(@PathVariable int id) {
        return new ResponseEntity<>(incomeDAO.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<List<ItemCollection>> getById(@Valid @RequestBody IncomeWithItems income) {
        incomeDAO.saveIncome(income);
        return new ResponseEntity<>(productDAO.sumUpAllOrderQuantities(), HttpStatus.OK);
    }
}
