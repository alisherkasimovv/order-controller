package uz.orders.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.orders.collections.Filter;
import uz.orders.collections.components.OrderWithItems;
import uz.orders.db.dao.interfaces.registrars.OrderDAO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<OrderWithItems>> getAll() {
        return new ResponseEntity<>(orderDAO.getAll(null), HttpStatus.OK);
    }

    @PostMapping(value = "/get/filtered")
    public ResponseEntity<List<OrderWithItems>> getFilteredData(@Valid @RequestBody Filter filter) {
        return new ResponseEntity<>(orderDAO.getAll(filter), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<OrderWithItems> getById(@PathVariable int id) {
        return new ResponseEntity<>(orderDAO.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public HttpStatus getById(@Valid @RequestBody OrderWithItems order) {
        orderDAO.saveOrder(order);
        return HttpStatus.OK;
    }

}
