package database;

import java.util.List;

import org.springframework.stereotype.Repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
public class EmployeeControler {
    
    @PersistenceContext private EntityManager entityManager;
    //create
    @Transactional
    public Employee save(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    //readByID
    public Employee findById(int id) {
    return entityManager.find(Employee.class, id);
    }

    //readAll
    public List<Employee> findAll() {
        String jpql = "SELECT c FROM Employee c";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        return query.getResultList();
    }

    //update
    @Transactional
    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }

    //Delete 
    @Transactional
    public void delete(int id) {
    Employee employee = entityManager.find(Employee.class, id);    
    entityManager.remove(employee);
}
}
