package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class EmployeeControlerTests {
    

	@Autowired
	EmployeeControler eControler;
   
    @Test
    void testFindAll() {
        List<Employee> employees = eControler.findAll();
        assertNotNull(employees);
    }

    @Test
    void testFindById() {
        List<Employee> employees = eControler.findAll();
        Employee found = eControler.findById(employees.get(0).getId());
        assertEquals(employees.get(0), found);
    }

    @Test
    void testSave() {
		Employee testEmployee = new Employee();
        testEmployee.setName("TEST");
        testEmployee.setPosition("Test");
        int listsize = eControler.findAll().size();
        //testsave
		testEmployee = eControler.save(testEmployee);
        assertEquals(listsize+1, eControler.findAll().size());
        eControler.delete(testEmployee.getId());
    }

    @Test
    void testUpdate() {
        Employee testEmployee = new Employee();
        testEmployee.setName("TEST");
        testEmployee.setPosition("Test");
		testEmployee = eControler.save(testEmployee);
        testEmployee.setPosition("updatetest");
        //testupdate
        eControler.update(testEmployee);
        Employee found = eControler.findById(testEmployee.getId());
        assertEquals("updatetest", found.getPosition());
         eControler.delete(testEmployee.getId());
    }
    @Test
    void testDelete() {
        Employee testEmployee = new Employee();
        testEmployee.setName("TEST");
        testEmployee.setPosition("Test");
		testEmployee = eControler.save(testEmployee);
        int listsize = eControler.findAll().size();
        //test
        eControler.delete(testEmployee.getId());
        int listsizeafter = eControler.findAll().size();
        assertEquals(listsize -1 , listsizeafter);
    }
    @Test
    void testNull(){
    }
    @Test
    void testEdge(){

    }
}
