package assignment3;

/**
 * TODO class gets an item to "produce" from the productionlist and sends it to
 * the factory(buffer).
 * 
 * @author Anton
 *
 */

public class Producer extends Thread {
	private ProductionList productionList;
	private Storage storage;
	private GUIFoodSupply GUI;
	private FoodItem currentItem;
	private Boolean running;
	private String company;

	public Producer(Storage storage, GUIFoodSupply GUI, String company) {

		this.GUI = GUI;
		this.storage = storage;
		productionList = new ProductionList();
		productionList.initiateFoodItems();
		this.company = company;
		running = true;
		start();
	}

	
	/**
	 * The run method takes a food item from a productionList 
	 * instance and calls enqueue method of the storage instance 
	 */
	public void run() {
		GUI.setProducerStatus("Running", company);
		while (running) {
			currentItem = productionList.getItem();
			try {
				Thread.sleep(500);
				storage.enqueue(currentItem);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}

	}

	/**
	 * Stops the producer thread and updates the producer GUI
	 */
	public void stopThread() {
		GUI.setProducerStatus("Stopped", company);
		running = false;

	}

}
