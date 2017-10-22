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
		LinkedListIterator<CargoCar> itr = train.iterator();
		CargoCar nextCar = null;
		
		boolean added = false;
		int counter = 0;
		
		// While there is another CargoCar in the loader train
			while (itr.hasNext())
			{
			// Store the car
			CargoCar processCar = itr.next();
			added = false;
			counter = 0;
			try
			{
			// If a train hasn't been created that corresponds to the destination given on the CargoCar
			// Create a new train
			if (findTrain(processCar.getDestination()) == null)
			{
				CargoCar tempCar = train.removeCargo(processCar.getName());
				trains.add(new Train(tempCar.getDestination()));
				findTrain(processCar.getDestination()).add(tempCar);
				added = true;
			}
			
			// Otherwise we know that there 
			// is a train with the given destination
			else 
			{
				// Iterate through the train with the destination
				LinkedListIterator<CargoCar> compareIt = findTrain(processCar.getDestination()).iterator();
				
				// Pass the null pointer
				compareIt.next();
				
				while (compareIt.hasNext())
				{				

					CargoCar car = compareIt.next();
					
					// If the name is before the one we are comparing
					// Remove the CargoCar from the main train and add it to this one
					if (processCar.getName().compareTo(car.getName()) < 0)
					{ findTrain(processCar.getDestination()).add(counter, train.removeCargo(processCar.getName()));
						added = true;
						break;
					}
					
					// If the names of the CargoCars are the same
					// Remove the CargoCar from the main train and add it to this one
					else if (processCar.getName().compareTo(car.getName()) == 0)
					{	
						findTrain(processCar.getDestination()).add(counter , train.removeCargo(processCar.getName()));
						added = true;
						break;	
					}
					// If none of the other conditions are met
					// Increment the counter and continue comparing
					else if (processCar.getName().compareTo(car.getName()) > 0)
					{
						counter++;
					}
					}
				}

				// If the CargoCar couldn't be added before any CargoCars
				// Add it to the end of it's corresponding train
				if (!added)
				{
					findTrain(processCar.getDestination()).add(train.removeCargo(processCar.getName()));
				}			
			}

			catch(NullPointerException e)
			{ // Do nothing, can't add the null
			}
			
			}
			
			}
	

		

	
	
	/**
	 * This method tries to find the train in the list of trains, departing to the given destination city.
	 * 
	 * @param dest Destination city for which train has to be found.
	 * @return  Pointer to the train if the train going to the given destination exists. Otherwise returns null.
	 */
	public Train findTrain(String dest){

	// Goes through LinkedList trains collection of trains
	// finds and returns the train corresponding to dest
		for (int i = 1; i < trains.size(); i++)
		{
			try
			{
			if (trains.get(i).getDestination().toLowerCase().equals(dest.toLowerCase()))
			{
				return trains.get(i);
			}
		}
		
		catch(NullPointerException e)
		{
		return null;
		}
			// If no train is found corresponding to dest
			// return null
		}
		return null;

	}
	
	/**
	 * This method removes the first cargo car going to the given 
	 * destination city and carrying the given cargo.
	 * 
	 * @param dest Destination city
	 * @param name Cargo name
	 * @return If there is a train going to the the given destination and is carrying the given cargo, 
	 * it returns the cargo car. El(se it returns null.
	 */
	public CargoCar removeCargo(String dest, String name){
		
		// Because the program allows lowerCase versions of
		// destination and name, convert these user Strings to lowerCase
		dest = dest.toLowerCase();
		name = name.toLowerCase();
		
		// If there is no corresponding train
		// there will be no way to remove the cargo
		if (findTrain(dest) == null)
		{
			return null;
		}
		else
		{
			
			try
			{
			
			LinkedListIterator<CargoCar> itr = findTrain(dest).iterator();
			CargoCar n = itr.next();
			
			// Iterates though the entire train's Cargo to find the product
			for (int i = 0; i < findTrain(dest).numCargoCars(); i++)
			{
				n = itr.next();
				if (n.getName().toLowerCase().equals(name))
				{
					// Remove the cargo from the train
					findTrain(dest).removeCargo(name);
					return n;
				}
			}
			
			
			}
			catch (Exception e)
			{
				return null;
			}
			
		}
		return null;
	}
	
	/**
	 * This method iterates through all the trains in the list 
	 * and finds the sum of weights of given cargo in all trains.
	 * 
	 * @param name Name of the cargo
	 * @return Total weight of given cargo in all departing trains.
	 */
	public int getWeight(String name){
		LinkedListIterator<Train> itr = trains.iterator();
		
		// Advance the Iterator if there is a next so that the
		// iterator isn't pointing to null
		if (itr.hasNext())
			itr.next();
		
		int totalWeight = 0;
		Train tempTrain = null;
		
		while(itr.hasNext()){
			try
			{
			tempTrain = itr.next();
			
			// Find and store the weight of the product from the current train
			totalWeight = totalWeight + tempTrain.getWeight(name);
			}
			catch (NullPointerException e)
		{	
				// If there is a null, print error message and break out of the loop
				System.out.println(Config.ERROR_CARGO_NOT_FOUND + "(" + name + ")");
				break;
		}
			}
		return totalWeight;
	}
	
	/**
	 * This method is used to depart the train to the given destination. 
	 * To depart the train, one needs to delete the train from the list. 
	 * 
	 * @param dest Destination city for which corresponding train has to be departed/deleted.
	 * @return True if train to the given destination city exists. If not, then return false. 
	 */
	public boolean departTrain(String dest){
		boolean check = false;
		LinkedListIterator<Train> itr = trains.iterator();
		itr.next();
		Train tempTrain = null;
		// Must keep track of where the iterator is so that position can be removed
		int count = 0;
		
		// If there is no next after the null, the train must be empty
		if (!itr.hasNext())
		{
			System.out.println(Config.ERROR_NO_TRAIN_IN_HUB);
			return false;
		}
		
		// Iterate through list of trains
		while(itr.hasNext()){
			tempTrain = itr.next();
			
			try 
			{
			// Check if current train equals the destination of removal
			if(tempTrain.getDestination().equals(dest)){
				
				// Only set check true if there is a matching train
				check = true;
				trains.remove(count);
				return check;
			}
			}
			catch(NullPointerException e)
			{	

			}
			count++;
		}
		return check;
		
		
	}
	/**
	 * This method deletes all the trains.
	 * 
	 * @return True if there was at least one train to delete. False if there was no train to delete.
	 */
	public boolean departAllTrains(){
		
		int trainSize = trains.size();
		// Keeps the initial size of the trains array so that
		// removing trains won't effect the for loop
		
		if (trainSize < 1)
			return false;
		
		for (int i = 1; i <= trainSize; i++)
		{
			try
			{
			System.out.println(departTrain(trains.get(1).getDestination()));
			// Must remove at index 1 because index 0 is null and will throw a null pointer exception
			}
			catch(NullPointerException e)
			{	}
		
		}
		return true;
	}
	/**
	 * Display the specific train for a destination.
	 * 
	 * @param dest Destination city for the train the to be displayed.
	 * @return True if train to the given destination city exists. If not, then return false.
	 */
	public boolean displayTrain(String dest){
		try{
			
			Train t = findTrain(dest);
			LinkedListIterator<CargoCar> itr = t.iterator();
			itr.next();
			
			// Prints the train out in form
			// (DEST)->PRODUCT:WEIGHT->PRODUCT:WEIGHT->
			System.out.print(Config.ENGINE_START + dest + Config.ENGINE_END);
			while(itr.hasNext()){
				CargoCar c = itr.next();
				System.out.print(Config.CARGO_LINK + c.getName() + ":" + c.getWeight());
			}
			return true;
			
		}catch(NullPointerException e){
			return false;
		}
		
	}

	/**
	 * This method is used to display all the departing trains in the train hub.
	 * Train should be displayed in the specified format. 
	 * 
	 * @return True if there is at least one train to print. False if there is no train to print.
	 */
	public boolean displayAllTrains(){
		LinkedListIterator<Train> itr = trains.iterator();
		

		
		if (trains.size() <= 1)
		{
			return false;
		}
		
		itr.next();
		
		// Calls the displayTrain corresponding to how many
		// trains are in the trains linkedList
		while(itr.hasNext())
		{
			Train n = itr.next();
			displayTrain(n.getDestination());
			System.out.println();
		}
		
		return true;
		
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
		//Header's data is null
		Listnode<CargoCar> srcHeader, dstHeader, prev = null, curr;
		srcHeader = srcTrain.getHeaderNode();
		dstHeader = dstTrain.getHeaderNode();
		
		Listnode<CargoCar> first_prev = null, first = null, last = null;
		boolean hasFound = false;
		//LinkedList<CargoCar> movingNode = new LinkedList();
		
		// 1. Find references to the node BEFORE the first matching cargo node
		//    and a reference to the last node with matching cargo.
		//When we go throught the list we don't want to lose it
		curr = srcHeader;
		while(! (curr.getNext() == null)){
			//finding the previous node
			if(curr.getNext().getData().getName().equals(cargoName)){
				
				prev = curr;
				
			//now advance the pointer and the iteration going to see if there are any others
				curr = curr.getNext();
				//last one error code here
				while(!(curr.getNext() == null)){
				if(curr.getNext().getData().getName().equals(cargoName)){
					curr = curr.getNext();
				}
				}
				//curr is now on the last node of that type
				last = curr;	
			}
			
			//if the data wasn't match keep iterating through the LinkedList
			else{
				curr = curr.getNext();
			}
			
			
		}
		
			// NOTE : We know we can find this cargo,
			//        so we are not going to deal with other exceptions here.

		
		
		
		
		// 2. Remove from matching chain of nodes from src Train
		//    by linking node before match to node after matching chain
		//before we do this we much save references
	
		curr = prev;
		//creating a new node to save the data too
		Listnode<CargoCar>movingNode = new Listnode<CargoCar>(curr.getNext().getData());
	
		
		//we use prev later so we must iterator with curr again
		curr = curr.getNext();
		
		//we need this if statement in case the cargo car is the last in the list and nothing follows it
		while(!(curr.getNext() == null)){
		if(curr.getNext().getData().getName().equals(cargoName)){
			curr = curr.getNext();
			CargoCar c = new CargoCar(curr.getData().getName(),curr.getData().getWeight(), curr.getData().getName());
			
			//need another variable to traverse to make sure added at end
			Listnode<CargoCar> trav = movingNode;
			
			while(!(trav.getNext() == null)){
				trav = trav.getNext();
			
			}
			trav.setNext(new Listnode<CargoCar>(c, null));
			
		}
		}
		//now remove the chain for the source train
		prev.setNext(last.getNext());

		
		
	
		// 3-1. Find reference to first matching cargo in dst Train
		curr = dstHeader;
		//System.out.print("got Here");
		//System.out.print(curr.getNext().getData().getName());
		while(!(curr.getNext() == null)){
			
			
			//this while loop finds the node before the desired cargo
			//and then sets prev to the current node
			if(curr.getNext().getData().getName().equals(cargoName)){
				prev = curr;
				hasFound = true;
			}
			curr = curr.getNext();
		}
		
			// 3-2. If found, insert them before cargo found in dst
		//we need to traverse this chain and set the next value of the last node to the new source train
			Listnode<CargoCar> trav = movingNode;
			while(!(trav.getNext() == null)){
				trav = trav.getNext();
				}
			
			if(hasFound){
			trav.setNext(prev.getNext());
			prev.setNext(movingNode);
			}
			// 3-3. If no matching cargo, add them at the end of train
			
			
			if(!hasFound){
				//we need to traverse through the train again to see where we should add the cargo
				curr = dstHeader;
				while(!(curr.getNext() == null)){
					if(curr.getNext().getData().getName().compareTo(cargoName) > 0){
						prev = curr;
						
						//we need to traverse this chain and set the next value of the last node to the new source train
						trav = movingNode;
						System.out.print(trav.getData().getName());
						while(!(trav.getNext() == null)){
							trav = trav.getNext();
							}
						trav.setNext(prev.getNext());
						prev.setNext(movingNode);
						
						
						hasFound = true;
						break;
					}
					
					else{
						curr = curr.getNext();
					}
	
				}
				//special case if it needs to be added at the end of the list
				if(!hasFound){
					curr.setNext(movingNode);
				}
				
				
				
			}
			
	}
}
