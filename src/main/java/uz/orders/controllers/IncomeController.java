package uz.orders.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.orders.collections.Filter;
import uz.orders.collections.IncomeWithItems;
import uz.orders.db.dao.interfaces.IncomeDAO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    private IncomeDAO incomeDAO;

    public IncomeController(IncomeDAO incomeDAO) {
        this.incomeDAO = incomeDAO;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<IncomeWithItems>> getAllIncomes() {
        return new ResponseEntity<>(incomeDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/filter", method = RequestMethod.GET)
    public ResponseEntity<List<IncomeWithItems>> getFilteredByDate(@Valid @RequestBody Filter filter) {
        return new ResponseEntity<>(incomeDAO.getFilteredByDates(filter), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public HttpStatus saveIncome(@Valid @RequestBody IncomeWithItems income) {
        incomeDAO.saveIncome(income);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public HttpStatus deleteIncome(@PathVariable int id) {
        incomeDAO.deleteIncome(id);
        return HttpStatus.OK;
    }

}
