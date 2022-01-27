package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.AllocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AllocationService {

    private AllocationRepository allocationRepository;

    public AllocationService(AllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

     public List<Allocation> findByProfessorId(Long professorId)
    {
        if (professorId != null)
        {
            List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);
            return allocations;
        }
        else
        {
            return null;
        }
    }

    public List<Allocation> findByCourseId(Long courseId)
    {
        if (courseId != null)
        {
            List<Allocation> allocations = allocationRepository.findByCourseId(courseId);
            return allocations;
        }
        else
        {
            return null;
        }
    }

    public Allocation findById(Long id)
    {
        if (id != null)
        {
            Optional<Allocation> allocationOptional = allocationRepository.findById(id);
            Allocation allocation = allocationOptional.orElse(null);
            return allocation;
        }
        else
        {
            return null;
        }
    }

    public List<Allocation> findAll()
    {
        List<Allocation> allocations = allocationRepository.findAll();
        return allocations;
    }

    public Allocation create(Allocation allocation)
    {
        allocation.setId(null);
        Allocation allocationNew = allocationRepository.save(allocation);
        return allocationNew;
    }

    public Allocation update(Allocation allocation)
    {
        Long id = allocation.getId();

        if (id != null && allocationRepository.existsById(id))
        {
            Allocation allocationNew = allocationRepository.save(allocation);
            return allocationNew;
        }
        else
        {
            return null;
        }
    }

    public void deleteById(Long id)
    {
        if (id != null && allocationRepository.existsById(id))
        {
            allocationRepository.deleteById(id);
        }
    }

    public void deleteAll()
    {
        allocationRepository.deleteAll();
    }
}
