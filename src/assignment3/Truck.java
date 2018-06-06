package assignment3;

/**
 * Class represent a consumer truck that loads food items from the storage
 * 
 * 
 * @author Anton
 *
 */

public class Truck extends Thread {
	private double weightCapacity, volumeCapacity, currentWeight, currentVolume;
	private int itemsAdded;
	private Storage storage;
	private FoodItem[] load;
	private GUIFoodSupply GUI;
	public boolean spaceExist, active, threadAlive;
	private String company;

	/**
	 * 
	 * @param weightCapacity represents weight capacity of this truck 
	 * @param volumeCapacity  represents volume capacity of this truck 
	 * @param itemCapacity represent max items that can be loaded 
	 * @param storage shared storage 
	 * @param GUI for updating GUI 
	 * @param company the name of the company this object represents 
	 */
	public Truck(double weightCapacity, double volumeCapacity, int itemCapacity, Storage storage, GUIFoodSupply GUI,
			String company) {
		this.GUI = GUI;
		this.weightCapacity = weightCapacity;
		this.volumeCapacity = volumeCapacity;
		load = new FoodItem[itemCapacity];
		spaceExist = true;
		active = true;
		this.storage = storage;
		this.company = company;
		start();

	}

	/**
	 * Method keeps loading items IF space exist and boolean active is true IF
	 * boolean active is changed by the GUI by pushing stop the thread goes to
	 * sleep
	 */
	public void run() {
		threadAlive = true;
		FoodItem currentItem;

		while (spaceExist) {

			while (active == false) {
				synchronized (this) {
					try {
						GUI.setTruckStatus(company, "stopped");
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
			GUI.setTruckStatus(company, "loading");

			try {
				Thread.sleep(500);
				currentItem = storage.dequeue();
				spaceExist = addToLoad(currentItem);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}
		// This happens just before thread finishes
		GUI.setTruckStatus(company, "Fully Loaded!");
		threadAlive = false;

	}
/**
 * This method checks if an item can actually be loaded onto the truck. 
 * If this method returns true the truck is full and the thread finishes and dies 
 * 
 * @return returns true if space exist to add the item, else false 
 */
	private boolean addToLoad(FoodItem foodItem) {
		spaceExist = false;

		if (itemsAdded < load.length) {
			currentVolume = currentVolume + foodItem.getVolume();
			currentWeight = currentWeight + foodItem.getWeight();

			if (currentVolume <= volumeCapacity && currentWeight <= weightCapacity) {
				load[itemsAdded] = foodItem;

				GUI.setCargoList(company, foodItem);
				GUI.updatePackageLimits(company, (volumeCapacity - currentVolume), (weightCapacity - currentWeight),
						(load.length - itemsAdded));

				spaceExist = true;
				itemsAdded++;
				
			}

		}
		return spaceExist;

	}
/**
 * changes the status of loading by altering the boolean active used in the run method 
 */
	public void pauseActive() {
		active = false;

	}
	/**
	 * changes the staus of the boolean active and notifies 
	 * a sleeping thread that the truck should resume loading 
	 */
	public synchronized void resumeActive() {
		active = true;
		notify();
	}
/**
 * Method returns the status of the objects thread thread. If the thread has 
 * finished execution and died this boolean returns false 
 * @return
 */
	public boolean threadAlive() {
		return threadAlive;

	}
}
