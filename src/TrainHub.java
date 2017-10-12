/**
 * This class represents a train hub and provides the common operations
 * needed for managing the incoming and outgoing trains.
 * 
 * It has a LinkedList of Train as a member variable and manages it.
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 * 
 * @see LinkedList
 * @see Train
 * @see Config
 */
public class TrainHub {
	
	/** The internal data structure of a hub is a linked list of Trains */
	private LinkedList<Train> trains;

	/**
	 * Constructs and initializes TrainHub object
	 */
	public TrainHub(){
		this.trains = new LinkedList<Train>();
	}
	
	/**
	 * This method processes the incoming train.
	 * It iterates through each of the cargo car of the incoming train.
	 * If there is an outgoing train in the train list going to the 
	 * destination city of the cargo car, then it removes the cargo car 
	 * from the incoming train and adds the cargo car at the correct location 
	 * in the outgoing train.  The correct location is to become the first
	 * of the matching cargo cars, with the cargo cars in alphabetical order 
	 * by their cargo name.
	 * 
	 * If there is no train going to the destination city, 
	 * it creates a new train and adds the cargo to this train.
	 * 
	 * @param train Incoming train (list or cargo cars)
	 */
	public void processIncomingTrain(Train train){
		//TODO: implement this method
	}
	
	/**
	 * This method tries to find the train in the list of trains, departing to the given destination city.
	 * 
	 * @param dest Destination city for which train has to be found.
	 * @return  Pointer to the train if the train going to the given destination exists. Otherwise returns null.
	 */
	public Train findTrain(String dest){
		//TODO: implement this method
	}
	
	/**
	 * This method removes the first cargo car going to the given 
	 * destination city and carrying the given cargo.
	 * 
	 * @param dest Destination city
	 * @param name Cargo name
	 * @return If there is a train going to the the given destination and is carrying the given cargo, 
	 * it returns the cargo car. Else it returns null.
	 */
	public CargoCar removeCargo(String dest, String name){
		//TODO: implement this method
	}
	
	/**
	 * This method iterates through all the trains in the list 
	 * and finds the sum of weights of given cargo in all trains.
	 * 
	 * @param name Name of the cargo
	 * @return Total weight of given cargo in all departing trains.
	 */
	public int getWeight(String name){
		//TODO: implement this method
	}
	
	/**
	 * This method is used to depart the train to the given destination. 
	 * To depart the train, one needs to delete the train from the list. 
	 * 
	 * @param dest Destination city for which corresponding train has to be departed/deleted.
	 * @return True if train to the given destination city exists. If not, then return false. 
	 */
	public boolean departTrain(String dest){
		//TODO: implement this method
	}
	/**
	 * This method deletes all the trains.
	 * 
	 * @return True if there was at least one train to delete. False if there was no train to delete.
	 */
	public boolean departAllTrains(){
		//TODO: implement this method
	}

	/**
	 * Display the specific train for a destination.
	 * 
	 * @param dest Destination city for the train the to be displayed.
	 * @return True if train to the given destination city exists. If not, then return false.
	 */
	public boolean displayTrain(String dest){
		//TODO: implement this method
	}

	/**
	 * This method is used to display all the departing trains in the train hub.
	 * Train should be displayed in the specified format. 
	 * 
	 * @return True if there is at least one train to print. False if there is no train to print.
	 */
	public boolean displayAllTrains(){
		//TODO: implement this method
	}
	
	/**
	 * Move all cargo cars that match the cargo name from a 
	 * source (src) train to a destination (dst) train.  
	 * 
	 * The matching cargo cars are added before the first cargo car
	 * with a name match in the destination train.
	 * 
	 * All matching cargo is to be moved as one chain of nodes and inserted
	 * into the destination train's chain of nodes before the first cargo matched node.
	 * 
	 * PRECONDITION: there is a source train and a destination train,
	 * and the source train of nodes has at least one node with
	 * matching cargo.  We will not test other conditions.
	 * 
	 * NOTE: This method requires direct access to the chain of nodes of a
	 * Train object.  Therefore, the Train class has a method in addition to 
	 * ListADT methods so that you can get direct access to header node 
	 * of the train's chain of nodes.   
	 *
	 * @param src a reference to a Train that contains at least one node with cargo.  
	 * 
	 * @param dst a reference to an existing Train.  The destination is the 
	 * train that will have the cargo added to it.  If the destination chain 
	 * does not have any matching cargo, add the chain at its correct location 
	 * alphabetically.  Otherwise, add the chain of cargo nodes before the
	 * first matching cargo node.
	 * 
	 * @param cargoName The name of cargo to be moved from one chain to another.
	 */
	public static void moveMultipleCargoCars(Train srcTrain, Train dstTrain, String cargoName) {
		// TODO Implement this method last.  It is not needed for other parts of program
		
		// get references to train header nodes
		// get references to train header nodes
		Listnode<CargoCar> srcHeader, dstHeader, prev, curr;
		srcHeader = srcTrain.getHeaderNode();
		dstHeader = dstTrain.getHeaderNode();
		
		Listnode<CargoCar> first_prev = null, first = null, last = null;
		boolean hasFound = false;
		
		// 1. Find references to the node BEFORE the first matching cargo node
		//    and a reference to the last node with matching cargo.

		
		
		
			// NOTE : We know we can find this cargo,
			//        so we are not going to deal with other exceptions here.

		
		
		
		
		// 2. Remove from matching chain of nodes from src Train
		//    by linking node before match to node after matching chain
		
		
		
		
		// 3-1. Find reference to first matching cargo in dst Train
		
		
				// 3-2. If found, insert them before cargo found in dst

		
			// 3-3. If no matching cargo, add them at the end of train
	}
}
