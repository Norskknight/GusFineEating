package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IngredientControlerTests {

    @Autowired
	IngredientControler iControler;

    private Ingredient creatIngredient(){
        Ingredient testIngredient = new Ingredient(0,"Test",1,1);
        return testIngredient;
    }

    @Test
    void testDelete() {
        Ingredient testIngredient = creatIngredient();
        testIngredient = iControler.save(testIngredient);
         List<Ingredient> all = iControler.findAll();
        //test delete
        iControler.delete(testIngredient.getId());
         List<Ingredient> allafter = iControler.findAll();
         assertEquals(all.size()-1,allafter.size());
    }

    @Test
    void testFindAll() {
       List<Ingredient> all = iControler.findAll();
       assertNotNull(all);
    }

    @Test
    void testFindById() {
        Ingredient testIngredient = creatIngredient();
        testIngredient = iControler.save(testIngredient);
        //test find by id
        Ingredient foundIngredient = iControler.findById(testIngredient.getId());
        assertEquals(testIngredient, foundIngredient);
        iControler.delete(testIngredient.getId());
    }

    @Test
    void testSave() {
        Ingredient testIngredient = creatIngredient();
        int listsize = iControler.findAll().size();
        //testsave
		testIngredient = iControler.save(testIngredient);
        assertEquals(listsize+1, iControler.findAll().size());
        iControler.delete(testIngredient.getId());
    }

    @Test
    void testUpdate() {
        Ingredient testIngredient = creatIngredient();
        testIngredient = iControler.save(testIngredient);
        testIngredient.setName("testupdate");
        iControler.update(testIngredient);
        Ingredient found = iControler.findById(testIngredient.getId());
        assertEquals(testIngredient.getName(), found.getName());
        iControler.delete(testIngredient.getId());
    }

    @Test
    void testNull(){
    }

    @Test
    void testEdge(){


    }

}
