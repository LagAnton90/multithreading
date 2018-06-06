package assignment3;

import java.util.Random;

/**
 * Class Creates an array of food items and has a method 
 * to be called for returning a random foodItem from the array 
 * @author Anton
 *
 */

public class ProductionList {

	private FoodItem[] foodBuffer;
	private Random randomizer;

	public ProductionList() {
		foodBuffer = new FoodItem[10];
		randomizer = new Random();

	}
/**
 * TODO make volume and weight different for different items 
 */
	public void initiateFoodItems() {
		foodBuffer[0] = new FoodItem("Tofu", 1.3, 1.0);
		foodBuffer[1] = new FoodItem("Beans", 1.11, 1.3);
		foodBuffer[2] = new FoodItem("Milk", 1.121, 1.32);
		foodBuffer[3] = new FoodItem("Ice", 1.2, 1.123);
		foodBuffer[4] = new FoodItem("Corn", 0.2, 1.0);
		foodBuffer[5] = new FoodItem("Tomatoes", 1.131, 1.0);
		foodBuffer[6] = new FoodItem("bananas", 1.22, 0.9);
		foodBuffer[7] = new FoodItem("Pasta", 1.3, 1.0);
		foodBuffer[8] = new FoodItem("Potatoes", 1.0, 1.0);
		foodBuffer[9] = new FoodItem("Oranges", 0.9, 0.9);

	}

	public synchronized FoodItem getItem() {

		return foodBuffer[randomizer.nextInt(9)];
	}

}
