package com.test.practiceProject.Service;

import com.test.practiceProject.DTO.InstructorDTO;
import com.test.practiceProject.Entity.InstructorDetail;
import com.test.practiceProject.Entity.InstructorEntity;
import com.test.practiceProject.Error.BadRequestException;
import com.test.practiceProject.Repository.InstructorDetailRepository;
import com.test.practiceProject.Repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private InstructorDetailRepository instructorDetailRepository;

    public void createNewInstructor(InstructorDTO instructorDTO) {
        // create a new instructor detail
        InstructorDetail instructorDetail = InstructorDetail.builder()
                .hobby(instructorDTO.getHobby())
                .youtubeChannel(instructorDTO.getYoutubeChannel())
                .build();

        System.out.println(instructorDTO);
        // create a new instructor
        InstructorEntity instructor = InstructorEntity.builder()
                .firstName(instructorDTO.getFirstName())
                .lastName(instructorDTO.getLastName())
                .email(instructorDTO.getEmail())
                .instructorDetail(instructorDetail)
                .build();

        // save the instructor (instructorDetail will be saved automatically due to CascadeType.ALL)
        instructorRepository.save(instructor);
    }

    public void updateInstructor(InstructorDTO instructorDTO) {
        InstructorEntity instructor = instructorRepository.findById(instructorDTO.getId()).orElse(null);
        if (instructor == null) throw new BadRequestException("Instructor not found!");
    }

    public InstructorEntity getInstructorById(int id) {
        return instructorRepository.findById(id).orElse(null);
    }

    public void deleteInstructorById(int id) {
        instructorRepository.deleteById(id);
    }
}
