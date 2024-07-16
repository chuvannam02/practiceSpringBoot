package com.test.practiceProject.Service;

import com.test.practiceProject.Entity.AppParams;
import com.test.practiceProject.Repository.AppParamsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppParamsService {
    private final AppParamsRepository appParamsRepository;

    public AppParamsService(AppParamsRepository appParamsRepository) {
        this.appParamsRepository = appParamsRepository;
    }

    public AppParams update(Long id, String param_value, String param_name) {
        Optional<AppParams> oldOptional = appParamsRepository.findById(id);
        if (oldOptional.isPresent()) {
            AppParams old = oldOptional.get();
            old.setParamValue(param_value);
            old.setParamName(param_name);
            return appParamsRepository.save(old);
        }
        // Handle the case when the AppParams with the given id is not found
        throw new EntityNotFoundException("AppParams with id " + id + " not found");
    }
    public List<AppParams> getAll() {
        return appParamsRepository.findAll();
    }
}
