package tacos.data;

import tacos.Ingredient;
import tacos.Order;


public interface IngredientRepository {
	Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
	Ingredient findById(String id);
}
