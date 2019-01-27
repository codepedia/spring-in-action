package tacos.data;

import org.springframework.data.repository.CrudRepository;

import org.springframework.beans.factory.annotation.Autowired;

import tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
