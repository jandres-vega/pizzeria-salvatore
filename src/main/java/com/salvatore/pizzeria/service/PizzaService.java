package com.salvatore.pizzeria.service;

import com.salvatore.pizzeria.persistence.entity.PizzaEntity;
import com.salvatore.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.salvatore.pizzeria.persistence.repository.PizzaRepository;
import com.salvatore.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elemnts){
        Pageable pageRequest = PageRequest.of(page, elemnts);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }
    public Page<PizzaEntity> getAvailable(int page, int elemnts, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elemnts, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }
    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("La piza no existe"));
    }
    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public List<PizzaEntity> getWith(String ingredient){
        return  this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }
    public List<PizzaEntity> getWithOut(String ingredient){
        return  this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public PizzaEntity get(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }
    public PizzaEntity savePizza(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }
    public boolean exists(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
    public void delete(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }
    @Transactional
    public void updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto);
    }
}
