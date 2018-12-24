package tacos.data;
import tacos.Taco;

/*Saving a taco design requires that you also
save the ingredients associated with that taco to the Taco_Ingredients table.
*/
public interface TacoRepository {
	Taco save(Taco design);
}