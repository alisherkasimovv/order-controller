package uz.orders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.orders.db.dao.interfaces.MarketDAO;
import uz.orders.db.entities.Market;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/markets")
public class MarketController {
    private MarketDAO marketDAO;

    @Autowired
    public MarketController(MarketDAO marketDAO) {
        this.marketDAO = marketDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Market>> getAllMarkets() {
        return new ResponseEntity<>(marketDAO.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Market> getMarket(@PathVariable int id) {
        return new ResponseEntity<>(marketDAO.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteMarket(@PathVariable int id) {
        marketDAO.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<List<Market>> saveMarket(@Valid @RequestBody Market market) {
        marketDAO.saveMarket(market);
        return new ResponseEntity<>(marketDAO.get(), HttpStatus.OK);
    }


}
