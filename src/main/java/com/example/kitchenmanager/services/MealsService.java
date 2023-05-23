package com.example.kitchenmanager.services;

import com.example.kitchenmanager.dtos.EmployeeDTO;
import com.example.kitchenmanager.dtos.MealDTO;
import com.example.kitchenmanager.dtos.requests.CreateMealRequest;
import com.example.kitchenmanager.models.Meals;
import com.example.kitchenmanager.repository.MealsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealsService {
    @Autowired
    private MealsRepository repository;
    public void addMeal(CreateMealRequest meal){
        repository.save(Meals.builder().name(meal.getName()).build());
    }
    public void removeMeal(Long id) throws  ChangeSetPersister.NotFoundException{
        var opt = repository.findById(id);
        if (opt.isPresent()) {
            repository.deleteById(id);
            return;
        }
        throw new ChangeSetPersister.NotFoundException();
    }
    public List<MealDTO> getMenu(){
        return  repository.findAll().stream().map(MealDTO::new).toList();

    }
    public boolean mealExists(CreateMealRequest meal){
        return repository.findMealsByName(meal.getName()).isPresent();
    }
}
