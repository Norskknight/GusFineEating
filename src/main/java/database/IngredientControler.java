package database;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.NonNull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
public class IngredientControler {
    




    @PersistenceContext private EntityManager entityManager;
    //create
    @Transactional
    
    public Ingredient save(@NonNull Ingredient ingredient) {
        entityManager.persist(ingredient);
        return ingredient;
    }

    //readByID
    public Ingredient findById(int id) {
    return entityManager.find(Ingredient.class, id);
    }

    //readAll
    public List<Ingredient> findAll() {
        String jpql = "SELECT c FROM Ingredient c";
        TypedQuery<Ingredient> query = entityManager.createQuery(jpql, Ingredient.class);
        return query.getResultList();
    }

    //update
    @Transactional
    public Ingredient update(Ingredient ingredient) {
        return entityManager.merge(ingredient);
    }

    //Delete 
    @Transactional
    public void delete(Integer ingredientId) {
        Ingredient ingredient = entityManager.find(Ingredient.class, ingredientId);    
        entityManager.remove(ingredient);
    }
}
