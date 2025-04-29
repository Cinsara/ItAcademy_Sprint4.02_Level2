package cat.itacademy.s04.t02.n02.services;

import cat.itacademy.s04.t02.n02.model.Fruit;
import cat.itacademy.s04.t02.n02.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitService {
    @Autowired
    FruitRepository fruitRepository;

    public List<Fruit> getAll(){
        return fruitRepository.findAll();
    }

    public Optional<Fruit> getOne(Integer id){
        return fruitRepository.findById(id);
    }

    public Fruit save(Fruit fruit){
        return fruitRepository.save(fruit);
    }

    public void delete(Integer id){
        fruitRepository.deleteById(id);
    }

    public List<Fruit> findByName(String name){
        return fruitRepository.findByName(name);
    }
}
