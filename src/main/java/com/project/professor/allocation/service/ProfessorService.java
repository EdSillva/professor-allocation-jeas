package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;
    private DepartmentService departmentService;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
    public Professor findById(Long id)
    {
        Optional<Professor> ProfessorOptional = professorRepository.findById(id);
        Professor Professor = ProfessorOptional.orElse(null);
        return Professor;
    }

    public List<Professor> findAll()
    {
        List<Professor> professors = professorRepository.findAll();
        return professors;
    }

    public Professor create(Professor professor)
    {
        professor.setId(null);
        Professor professorNew = saveInternal(professor);
        return professorNew;
    }

    public Professor update(Professor professor)
    {
        Long id = professor.getId();

        if (id != null && professorRepository.existsById(id))
        {
            Professor professorNew = saveInternal(professor);
            return professorNew;
        }
        else
        {
            return null;
        }
    }

    public void deleteById(Long id)
    {
        if (id != null && professorRepository.existsById(id))
        {
            professorRepository.deleteById(id);
        }
    }

    public void deleteAll()
    {
        professorRepository.deleteAll();
    }

    private Professor saveInternal(Professor professor) {
        professor = professorRepository.save(professor);

        Department department = departmentService.findById(professor.getDepartmentId());
        professor.setDepartment(department);

        return professor;
    }
}