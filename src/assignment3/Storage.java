package assignment3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;


/**
 * Class represent a storage facility 
 * The storage can only hold a specific number of items, weight and volume at one time 
 * @author Anton
 *
 */
public class Storage {
	private double weightCapacity, volumeCapacity, currentWeight, currentVolume;
	private FoodItem takenItem;
	private Semaphore occupiedSpaces, emptySpaces;
	private Object LOCK;
	private LinkedList<FoodItem> buffer;
	private GUIFoodSupply GUI;
	private boolean storageStatus;

	public Storage(double weightCapacity, double volumeCapacity, GUIFoodSupply GUI) {
		this.GUI = GUI;
		this.weightCapacity = weightCapacity;
		this.volumeCapacity = volumeCapacity;
		buffer = new LinkedList<FoodItem>();
		occupiedSpaces = new Semaphore(0);
		emptySpaces = new Semaphore(100);
		LOCK = new Object();
	}

	/**
	 * Adds a food item to the storage and updates the GUI 
	
	 */
	public void enqueue(FoodItem foodItem) throws InterruptedException {

		if (addToStorage(foodItem) == true) {
			emptySpaces.acquire();

			synchronized (LOCK) {
				currentVolume = currentVolume + foodItem.getVolume();
				currentWeight = currentWeight + foodItem.getWeight();
				buffer.addLast(foodItem);
			}

			GUI.setBufferStatus(buffer.size());
			occupiedSpaces.release();
		}

	}
/**
 * Removes and returns a food item from the storage and updates the GUI 
 
 */
	public FoodItem dequeue() throws InterruptedException {
		occupiedSpaces.acquire();

		synchronized (LOCK) {
			takenItem = buffer.removeFirst();
			removeFromStorage(takenItem);

		}
		GUI.setBufferStatus(buffer.size());
		emptySpaces.release();
		return takenItem;

	}
/**
 * Method checks if there is enough available volume and weightspace to add a specific item 
 * 
 * @return Returns true if ok to add and false if the space is not available 
 */
	private boolean addToStorage(FoodItem foodItem) {
		storageStatus = false;

		if (currentVolume + foodItem.getVolume() <= volumeCapacity
				&& currentWeight + foodItem.getWeight() <= weightCapacity) {

			storageStatus = true;

		}
		return storageStatus;

	}

	/**
	 * Removes the weight and volume from total current weight/volume of storage when an item is removed
	 * @param takenItem the item to be removed 
	 */
	public void removeFromStorage(FoodItem takenItem) {
		currentWeight = (currentWeight - takenItem.getWeight());
		currentVolume = (currentVolume - takenItem.getVolume());

	}

}
