package org.med.youhospital.serverside.service.impl;

import org.med.youhospital.serverside.exception.ResourceNotFoundException;
import org.med.youhospital.serverside.model.entity.Patient;
import org.med.youhospital.serverside.model.entity.Recipe;
import org.med.youhospital.serverside.model.entity.Staff;
import org.med.youhospital.serverside.model.request.RecipeReq;
import org.med.youhospital.serverside.model.response.RecipeRes;
import org.med.youhospital.serverside.repository.PatientRepository;
import org.med.youhospital.serverside.repository.RecipeRepository;
import org.med.youhospital.serverside.repository.StaffRepository;
import org.med.youhospital.serverside.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public RecipeRes save(RecipeReq recipeReq) {
        Patient patient = patientRepository.findById(recipeReq.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("patient Not found"));
        Staff staff = staffRepository.findById(recipeReq.getStaffId()).orElseThrow(() -> new ResourceNotFoundException("staff Not found"));
        Recipe recipe = modelMapper.map(recipeReq, Recipe.class);
        recipe.setPatient(patient);
        recipe.setStaff(staff);
        return modelMapper.map(recipeRepository.save(recipe), RecipeRes.class);
    }

    @Override
    public List<RecipeRes> findAll() {
        return recipeRepository.findAll().stream().map(recipe -> {
            RecipeRes recipeRes = modelMapper.map(recipe, RecipeRes.class);
            recipeRes.setStaffId(recipe.getStaff().getId());
            recipeRes.setPatientId(recipe.getPatient().getId());
            return recipeRes;
        }).collect(Collectors.toList());
    }

    @Override
    public RecipeRes findOne(UUID id) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    RecipeRes recipeRes = modelMapper.map(recipe, RecipeRes.class);
                    recipeRes.setStaffId(recipe.getStaff().getId());
                    recipeRes.setPatientId(recipe.getPatient().getId());
                    return recipeRes;
                }).orElseThrow(() -> new ResourceNotFoundException("Recipe Not found with this: " + id));
    }

    @Override
    public RecipeRes update(UUID id, RecipeReq recipeReq) {
        Recipe existingRecipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe Not found with this: " + id));
        Patient patient = patientRepository.findById(recipeReq.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("patient Not found"));
        Staff staff = staffRepository.findById(recipeReq.getStaffId()).orElseThrow(() -> new ResourceNotFoundException("staff Not found"));
        existingRecipe.setId(id);
        existingRecipe.setPatient(patient);
        existingRecipe.setStaff(staff);
        return modelMapper.map(recipeRepository.save(existingRecipe), RecipeRes.class);
    }

    @Override
    public void delete(UUID id) {
        recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe Not found with this: " + id));
        recipeRepository.deleteById(id);
    }

}
