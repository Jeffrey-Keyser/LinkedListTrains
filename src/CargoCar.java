/** 
 * This class represents a single train car.  It has a single cargo product
 * which is identified by the name.  The weight of the cargo and its 
 * destination are also tracked.
 * DO NOT EDIT OR HAND IN THIS CLASS
 */
public class CargoCar {
	
	/** The name of cargo carried by this car */
	private String name;
	
	/** The weight in pounds of the cargo carried by this car */
	private int weight;
	
	/** The destination that this cargo car is supposed to reach */
	private String destination;
	
	/**
	 * Construct an instance of CargoCar with name, weight and destination param.
	 * 
	 * @param cargo name for what product is in this cargo car
	 * @param weight of the cargo product
	 * @param destination city for this cargo car
	 */
	public CargoCar(String name, int weight, String destination) {
		this.name = name;
		this.weight = weight;
		this.destination = destination;
	}
	
	/**
	 * Get the cargo load name of this cargo car.
	 * 
	 * @return cargo name of the product in this car
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set a new cargo load name for this cargo car.  Allows a car to be
	 * reused for a different type of cargo.  Renaming cargo cars should not 
	 * be necessary in this program.
	 * 
	 * @param The new cargo name
	 */
	public void setCargoName(String name){
		this.name = name;
	}
	
	/**
	 * Get the weight of this cargo car.
	 * 
	 * @return the weight of the cargo in this car
	 */
	public int getWeight(){
		return this.weight;
	}
	
	/**
	 * Edit or change the weight of this cargo car
	 * 
	 * @param weight cargo weight
	 */
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	/**
	 * Get the destination of this cargo car
	 * 
	 * @return the destination of the cargo in this car
	 */
	public String getDestination(){
		return this.destination;
	}
	
	/**
	 * Edit/change the destination for this cargo car
	 * 
	 * @param the new destination of this cargo car
	 */
	public void setDestination(String destination){
		this.destination = destination;
	}
}
