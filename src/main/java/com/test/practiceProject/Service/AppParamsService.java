package com.test.practiceProject.Service;

import com.test.practiceProject.Entity.AppParams;
import com.test.practiceProject.Entity.LoginEntity;
import com.test.practiceProject.Error.BadRequestException;
import com.test.practiceProject.Repository.AppParamsRepository;
import com.test.practiceProject.Request.AppParamsRequest;
import com.test.practiceProject.Utils.Enums.DayOfWeek;
import com.test.practiceProject.Utils.Enums.Season;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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

    public AppParams createNew(AppParamsRequest app) {
        AppParams existed = checkIsExistedParamName(app.getParamName());
        log.info(String.valueOf(existed));
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (existed != null) throw new BadRequestException("Tên tham số đã tồn tại!", status);

        // Builder Pattern
        AppParams newParam = AppParams.builder()
                .paramName(app.getParamName())
                .paramValue(app.getParamValue())
                .build();

        return appParamsRepository.save(newParam);
    }

    private AppParams checkIsExistedParamName(String paramName) {
        return appParamsRepository.findByParamName(paramName).orElse(null);
    }

    public String getCharacteristicOfSeason(Season season) {
        // Switch Expressions using arrow syntax
        // Using yield keyword to return a value from a switch expression in case of a block
        return switch(season) {
            case SPRING -> "Warm";
            case SUMMER -> {
                LocalDateTime now = LocalDateTime.now();
                // DateTimeFormatter to format the LocalDateTime object
                // Pattern: dd-MM-yyyy hh-mm-ss a B
                // dd: day, MM: month, yyyy: year, hh: hour, mm: minute, ss: second, a: AM/PM, B: era
                // Using in java version 15
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh-mm-ss a B");
                String formattedDateTime = now.format(formatter);
                yield "Hot " + formattedDateTime;
            }
            case AUTUMN -> {
                yield """
                        Text block in Java 15
                        This is a multiline string
                        The season is Autumn
                        It is so cool
                        """;
            }
            case WINTER -> "Cold";
            default -> "Unknown";
        };
    }

    public String getDayOfType(DayOfWeek dayOfWeek) {
        // SE switch expression
        // it allows us to return a value from a switch expression in case of a block
        // Using yield keyword to return a value from a switch expression in case of a block
        // Using arrow syntax
        // Using in java version 15
        // Allow case include multiple values
        return switch(dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
            default -> "Invalid day!";
        };
    }
}
