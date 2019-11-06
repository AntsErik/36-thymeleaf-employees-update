package ee.praktika.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ee.praktika.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //no need to write any code, CRUD methods will be received for free.

    //add a method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();
}
