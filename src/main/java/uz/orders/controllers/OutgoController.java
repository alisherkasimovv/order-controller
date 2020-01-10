package uz.orders.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.orders.collections.components.OrderWithItems;
import uz.orders.collections.components.OutgoWithItems;
import uz.orders.db.dao.interfaces.registrars.OutgoDAO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/outgoes")
public class OutgoController {

    private OutgoDAO outgoDAO;

    public OutgoController(OutgoDAO outgoDAO) {
        this.outgoDAO = outgoDAO;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<OutgoWithItems>> getAll() {
        return new ResponseEntity<>(outgoDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<OutgoWithItems> getOneOutgo(@PathVariable int id) {
        return new ResponseEntity<>(outgoDAO.getById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<List<OutgoWithItems>> saveOutgo(@Valid @RequestBody OrderWithItems outgo) {
        outgoDAO.saveOutgo(outgo);
        return new ResponseEntity<>(outgoDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<OutgoWithItems>> deleteOutgo(@PathVariable int id) {
        outgoDAO.deleteOutgo(id);
        return new ResponseEntity<>(outgoDAO.getAll(), HttpStatus.OK);
    }
}
