package uz.orders.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.orders.collections.Filter;
import uz.orders.collections.ItemCollection;
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
        return new ResponseEntity<>(orderDAO.getAll(null, true), HttpStatus.OK);
    }

    @GetMapping(value = "/get/with-unprovided")
    public ResponseEntity<List<OrderWithItems>> getAllWithUnprovided() {
        return new ResponseEntity<>(orderDAO.getAll(null, false), HttpStatus.OK);
    }

    @PostMapping(value = "/get/filtered")
    public ResponseEntity<List<OrderWithItems>> getFilteredData(@Valid @RequestBody Filter filter) {
        return new ResponseEntity<>(orderDAO.getAll(filter, true), HttpStatus.OK);
    }

    @PostMapping(value = "/get/with-unprovided/filtered")
    public ResponseEntity<List<OrderWithItems>> getFilteredDataWithUnprovided(@Valid @RequestBody Filter filter) {
        return new ResponseEntity<>(orderDAO.getAll(filter, false), HttpStatus.OK);
    }

    @PostMapping(value = "/get/items")
    public ResponseEntity<List<ItemCollection>> getSummedItems() {
        return new ResponseEntity<>(orderDAO.sumUpAllOrderItems(), HttpStatus.OK);
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
