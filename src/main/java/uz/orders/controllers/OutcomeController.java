package uz.orders.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.orders.collections.Filter;
import uz.orders.collections.OutcomeWithItems;
import uz.orders.db.dao.interfaces.OutcomeDAO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/outcomes")
public class OutcomeController {

    private OutcomeDAO outcomeDAO;

    public OutcomeController(OutcomeDAO outcomeDAO) {
        this.outcomeDAO = outcomeDAO;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<OutcomeWithItems>> getAllOutcomes() {
        return new ResponseEntity<>(outcomeDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/filter", method = RequestMethod.GET)
    public ResponseEntity<List<OutcomeWithItems>> getFilteredByDate(@Valid @RequestBody Filter filter) {
        return new ResponseEntity<>(outcomeDAO.getFilteredByDates(filter), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public HttpStatus saveOutcome(@Valid @RequestBody OutcomeWithItems outcome) {
        outcomeDAO.saveOutcome(outcome);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public HttpStatus deleteOutcome(@PathVariable int id) {
        outcomeDAO.deleteOutcome(id);
        return HttpStatus.OK;
    }
    
}
