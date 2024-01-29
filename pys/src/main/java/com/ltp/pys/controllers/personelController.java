package com.ltp.pys.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ltp.pys.entity.personel;
import com.ltp.pys.service.personService;

@RestController
@RequestMapping ("/personeller")
public class personelController {
    @Autowired
    private final personService personService;

    public personelController(personService personService)
    {
    this.personService = personService;
    }

@GetMapping
    public List<personel> gettAllPersonel()
    {
        return personService.gettAllPersonel();
    }

@PostMapping
    public ResponseEntity<?> createPersonel(@Valid @RequestBody personel newPersonel, BindingResult bindingResult) 
    {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } else {
            return ResponseEntity.ok(personService.saveOnePersonel(newPersonel));
        }
    }
    
@GetMapping ("/{personelId}")
    public personel getOnePersonel(@PathVariable Long personelId)
    {
        return personService.getOnePersonel(personelId);
    }

@DeleteMapping ("/{personelId}")
    public void deleteOnePersonel(@PathVariable Long personelId)
    {
        personService.deleteById(personelId);
    }

@PutMapping("/{personelId}")
    public personel updateOnePersonel (@PathVariable Long personelId, @RequestBody personel newPersonel){
        return personService.updateOnePersonel(personelId,newPersonel);
    }
}
