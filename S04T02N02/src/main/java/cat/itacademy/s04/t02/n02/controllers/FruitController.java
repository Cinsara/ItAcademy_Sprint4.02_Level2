package cat.itacademy.s04.t02.n02.controllers;


import cat.itacademy.s04.t02.n02.exception.ResourceNotFoundException;
import cat.itacademy.s04.t02.n02.model.Fruit;
import cat.itacademy.s04.t02.n02.services.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Autowired
    FruitService fruitService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Fruit>> getAllFruits(@RequestParam(required = false) String name){
        List<Fruit> fruits = new ArrayList<>();

        if (name == null) {
            fruitService.getAll().forEach(f -> fruits.add((Fruit) f));
        } else {
            fruits.addAll(fruitService.findByName(name));
        }
        if (fruits.isEmpty()) {
            throw new ResourceNotFoundException("Fruit not found");
        }
            return ResponseEntity.ok(fruits);
    }

    @GetMapping("getOne/{id}")
    public ResponseEntity<Fruit> getOneId(@PathVariable("id") Integer id){
        Optional<Fruit> fruitData = fruitService.getOne(id);

        if(fruitData.isPresent()){
            return ResponseEntity.ok(fruitData.get());
            //return new ResponseEntity<>(fruitData.get(),HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Fruit not found");
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Fruit> add(@RequestBody Fruit fruit){
        fruit.setId(null);

        Fruit savedFruit = (Fruit) fruitService
                .save(new Fruit(fruit.getName(), fruit.getQuantity()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/../getOne/{id}")
                .buildAndExpand(savedFruit.getId())
                .normalize()
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedFruit);
        //return new ResponseEntity<>(fruit_,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fruit> update(@PathVariable("id") Integer id, @RequestBody Fruit fruit){
        Optional<Fruit> fruitData = fruitService.getOne(id);

        if(fruitData.isEmpty()){
            throw new ResourceNotFoundException("Fruit not found");
        }

        Fruit fruitFound = fruitData.get();
        fruitFound.setName(fruit.getName());
        fruitFound.setQuantity(fruit.getQuantity());
        Fruit updatedFruit = fruitService.save(fruitFound);
        return ResponseEntity.ok(updatedFruit);
        //return new ResponseEntity<>(fruitService.save(fruitFound), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Optional<Fruit> deletedFruitId = fruitService.getOne(id);

        if(deletedFruitId.isEmpty()){
            throw new ResourceNotFoundException("Fruit not found");
        }
        fruitService.delete(id);
        return ResponseEntity.noContent().build();
            //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}