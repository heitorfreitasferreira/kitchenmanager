package com.example.kitchenmanager.services;

import com.example.kitchenmanager.dtos.EmployeeDTO;
import com.example.kitchenmanager.dtos.LunchRoomDTO;
import com.example.kitchenmanager.models.Employee;
import com.example.kitchenmanager.models.LunchRoom;
import com.example.kitchenmanager.repository.EmployeeRepository;
import com.example.kitchenmanager.repository.LunchRoomRepository;
import com.example.kitchenmanager.repository.MealsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LunchRoomService {
    @Autowired
    private LunchRoomRepository lunchRoomRepository;
    @Autowired
    private MealsRepository mealsRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public void createLunch(
            Long mealId,
            LocalDate date
    )throws ResponseStatusException{
        var today = LocalDate.now();
        if (date.isEqual(today) || date.isBefore(today)) throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);

        var mealOpt = mealsRepository.findById(mealId);
        if(!mealOpt.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        var meal = mealOpt.get();
        var lunchRoom = LunchRoom.builder()
                .lunchDay(date)
                .clients(new HashSet<Employee>())
                .meal(meal)
                .build();
        lunchRoomRepository.save(lunchRoom);
    }
    public List<LunchRoomDTO> getAllLunchRooms(){
        return lunchRoomRepository.findAll().stream().map(LunchRoomDTO::new).toList();
    }
    public void addEmployee(LocalDate date, String employeeEmail){
        // Verificar se a data dada é antes de hoje
        var today = LocalDate.now();
        if (date.isEqual(today) || date.isBefore(today)) throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
        // Verificar se já existe almoço
        var lunchRoomOpt = lunchRoomRepository.findByLunchDay(date);
        if(!lunchRoomOpt.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // Verificar se existe o funcionario
        var employeeOpt = employeeRepository.findByEmail(employeeEmail);
        if (!employeeOpt.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // ADICIONAR o funcionário na lista "clients" do almoço
        var lunchroom = lunchRoomOpt.get();
        var employee = employeeOpt.get();

        lunchroom.getClients().add(employee);
        lunchRoomRepository.save(lunchroom);
    }
    public List<EmployeeDTO> getClients(LocalDate date){
        // Verificar se existe o almoço nessa data
        var lunchRoomOpt = lunchRoomRepository.findByLunchDay(date);
        if(!lunchRoomOpt.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // Caso exista, retornar a lista clients do almoço
        return lunchRoomOpt.get().getClients().stream().map(EmployeeDTO::new).toList();
    }
    public void removeEmployee(LocalDate date, String employeeEmail){
        // Verificar se a data dada é antes de hoje
        var today = LocalDate.now();
        if (date.isEqual(today) || date.isBefore(today)) throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
        // Verificar se já existe almoço
        var lunchRoomOpt = lunchRoomRepository.findByLunchDay(date);
        if(!lunchRoomOpt.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // Verificar se existe o funcionario
        var employeeOpt = employeeRepository.findByEmail(employeeEmail);
        if (!employeeOpt.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // REMOVER o funcionário na lista "clients" do almoço
        var lunchRoom = lunchRoomOpt.get();
        var userWasInLunch = lunchRoom.getClients().remove(employeeOpt.get());
        if(!userWasInLunch) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        lunchRoomRepository.save(lunchRoom);
    }
}
