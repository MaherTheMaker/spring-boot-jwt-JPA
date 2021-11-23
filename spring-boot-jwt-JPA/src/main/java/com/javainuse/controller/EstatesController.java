package com.javainuse.controller;

import com.javainuse.dao.EstateRepo;
import com.javainuse.model.Estate;
import com.javainuse.model.UserDTO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EstatesController {

    @Autowired
    EstateRepo repo;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> saveEstate(@RequestBody Estate estate) throws Exception {
        return ResponseEntity.ok(repo.save(estate));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEstate(@RequestBody Estate estate, @PathVariable Integer id) throws Exception {
        Optional<Estate> byId = repo.findById(id);
        if (byId.isPresent()) {
            byId.get().setName(estate.getName());
            byId.get().setOriginalPrice(estate.getOriginalPrice());
            byId.get().setShares(estate.getShares());
            repo.save(byId.get());
            return ResponseEntity.ok(byId.get());
        }
        JSONObject jo = new JSONObject();
        jo.put("Message", "Estate id not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEstate(@PathVariable Integer id) throws Exception {
        Optional<Estate> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            JSONObject jo = new JSONObject();
            jo.put("Message", " Estate " + id + " deleted");
            return ResponseEntity.status(HttpStatus.OK).body(jo);

        }
        JSONObject jo = new JSONObject();
        jo.put("Message", "Estate id not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo);
    }

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> buyEstate(@RequestBody Estate estate, @PathVariable Integer id) throws Exception {
        Optional<Estate> byId = repo.findById(id);
        if (byId.isPresent()) {
            byId.get().Buy(estate.getSalePrice(), estate.getDOS(), estate.getBuyerName());
            repo.save(byId.get());
            return ResponseEntity.ok(byId.get());
        }
        JSONObject jo = new JSONObject();
        jo.put("Message", "Estate id not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo);
    }

    @RequestMapping(value = "/display" ,method = RequestMethod.GET)
    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.ok( repo.findAllByIsSold(false));


    }

}
