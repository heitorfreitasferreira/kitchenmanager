package com.example.kitchenmanager.services;

import com.example.kitchenmanager.dtos.EmployeeDTO;
import com.example.kitchenmanager.dtos.MealDTO;
import com.example.kitchenmanager.dtos.SnackDTO;
import com.example.kitchenmanager.dtos.requests.CreateMealRequest;
import com.example.kitchenmanager.dtos.requests.CreateSnackRequest;
import com.example.kitchenmanager.models.Meals;
import com.example.kitchenmanager.models.Snacks;
import com.example.kitchenmanager.repository.MealsRepository;
import com.example.kitchenmanager.repository.SnacksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SnackService {
    @Autowired
    private SnacksRepository repository;
    public void add(CreateSnackRequest snack){
        repository.save(Snacks.builder().name(snack.getName()).build());
    }
    public void remove(Long id) throws  ResponseStatusException{
        var opt = repository.findById(id);
        if (opt.isPresent()) {
            repository.deleteById(id);
            return;
        }
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
    }
    public List<SnackDTO> getMenu(){
        return repository.findAll().stream().map(SnackDTO::new).toList();
    }
    public boolean exists(CreateSnackRequest snack){
        return repository.findByName(snack.getName()).isPresent();
    }
}
