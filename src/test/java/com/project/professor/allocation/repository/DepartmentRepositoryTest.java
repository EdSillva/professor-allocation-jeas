package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class DepartmentRepositoryTest {

    @Autowired
    public DepartmentRepository departmentRepository;

    @Test
    public void buscarTudo(){
        List<Department> departments = departmentRepository.findAll();
        System.out.println(departments);
    }

    @Test
    public void buscarId(){
        Long id = 1L;
        Department departments = departmentRepository.findById(id).orElse(null);
        System.out.println(departments);
    }

    @Test
    public void create(){
        Department dep = new Department();
        dep.setName("Contabilidade");
        dep = departmentRepository.save(dep);
        System.out.println(dep);
    }

    @Test
    public void update() throws ParseException {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("Filosofia");

        // Act
        department =departmentRepository.save(department);

        // Print
        System.out.println(department);
    }

    @Test
    public void delete(){
        Long id = 5L;
        departmentRepository.deleteById(id);
        System.out.println(id);
    }

    @Test
    public void deleteAll() {
        // Act
        departmentRepository.deleteAllInBatch();
    }
}
