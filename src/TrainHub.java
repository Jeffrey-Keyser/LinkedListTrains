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
			CargoCar processCar = itr.next();
			added = false;
			counter = 0;
			try
			{
			if (findTrain(processCar.getDestination()) == null)
			{
				CargoCar tempCar = train.removeCargo(processCar.getDestination());
				trains.add(new Train(tempCar.getDestination()));
				findTrain(processCar.getDestination()).add(tempCar);
				added = true;
			}
			else 
			{
				LinkedListIterator<CargoCar> compareIt = findTrain(processCar.getDestination()).iterator();

				compareIt.next();
				while (compareIt.hasNext())
				{				

					CargoCar car = compareIt.next();
					//if the name of the car comes before at it before
					if (processCar.getName().compareTo(car.getName()) < 0)
					{ findTrain(processCar.getDestination()).add(counter, train.removeCargo(processCar.getDestination()));
						added = true;
						break;
					}
					
					//if the names are the same, compare weights and add the lesser weight first
					else if (processCar.getName().compareTo(car.getName()) == 0)
					{				
						// While we are within the number of total CargoCars in the train
						while (findTrain(processCar.getDestination()).numCargoCars() > counter)
						{
							
						try
						{
							if (compareIt.hasNext())
								nextCar = compareIt.next();
						
						// If the car we are adding has weight less then the one we are comparing to, insert before	
						if (processCar.getWeight() <= car.getWeight())
						{
						findTrain(processCar.getDestination()).add(counter , train.removeCargo(processCar.getDestination()));
						added = true;
						break;
						}
						
						// If there is another car and the car's name doesn't equal our to one we are adding, it must be added beforet this other car
						// also, if the nextCar has weight greater then the car we are adding, add the car before it
						else if ( !nextCar.getName().equals(processCar.getName()) || processCar.getWeight() <= nextCar.getWeight() || nextCar.getName() == null)
						{
							findTrain(processCar.getDestination()).add(counter + 1, train.removeCargo(processCar.getDestination()));
							added = true;
							break;
						}	
						
						counter++;
						}
						
						// If a null pointer is caught, assume it is the end of the linked list and create a new CargoCar
						// at the end of the list
						catch (NullPointerException e)
						{
							findTrain(processCar.getDestination()).add(counter + 1, train.removeCargo(processCar.getDestination()));
							added = true;
							break;
						}
						}
						
						break;
												
						
						}
					else if (processCar.getName().compareTo(car.getName()) > 0)
					{
						counter++;
					//	continue;
					}
				//	System.out.println(processCar.getDestination() + " " + processCar.getName() + " " + findTrain(processCar.getDestination()));

					
					//	if (!added)
					//		break;
					}
				//	counter++;
				}
				
			//	counter = 0;

			
			

				if (!added)
				{
					findTrain(processCar.getDestination()).add(train.removeCargo(processCar.getDestination()));
				}
			
			
			System.out.println(processCar.getDestination() + " " + processCar.getName() + " " + findTrain(processCar.getDestination()));
	//		LinkedListIterator<CargoCar> test = findTrain("Milwaukee").iterator();
	//		test.next();
	//		while (test.hasNext())
	//		{
	//			CargoCar thisCar = test.next();
	//			System.out.print(thisCar.getName()  + "  " + thisCar.getWeight() + " - ");
	//		}
	//		System.out.println();
			
			}
			
			catch(NullPointerException e)
			{	}
			
			System.out.println();
			
		
			}
	}

		

	
	
	/**
	 * This method tries to find the train in the list of trains, departing to the given destination city.
	 * 
	 * @param dest Destination city for which train has to be found.
	 * @return  Pointer to the train if the train going to the given destination exists. Otherwise returns null.
	 */
	public Train findTrain(String dest){
		try
		{
	//goes through train list with zero-based indexing so it starts at 1
		for (int i = 1; i < trains.size(); i++)
		{
			if (trains.get(i).getDestination().equals(dest))
			{
				return trains.get(i);
			}
		}
		}
		catch(NullPointerException e)
		{
		return null;
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
		
		
		if (findTrain(dest) == null)
			return null;
		else
		{
			LinkedListIterator<CargoCar> itr = findTrain(dest).iterator();
			CargoCar n = itr.next();
			
			for (int i = 0; i < findTrain(dest).numCargoCars(); i++)
			{
				n = itr.next();
				if (n.getName().equals(name))
				{
					System.out.println(n.getDestination() + " " + n.getName());
					return n;
				}
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
		int totalWeight = 0;
		Train tempTrain = null;
		
		while(itr.hasNext()){
			try
			{
			tempTrain = itr.next();
			
			totalWeight = totalWeight + tempTrain.getWeight(name);
			}
			catch (NullPointerException e)
		{	}
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
		Train tempTrain = null;
		//must keep track of where the iterator is so that position can be removed
		int count = 0;
		
		//iterate through list of trains
		while(itr.hasNext()){
			tempTrain = itr.next();
			
			try 
			{
			//check if current train equals the destination of removal
			if(tempTrain.getDestination().equals(dest)){
				//only set check true if there is a matching train
				check = true;
				trains.remove(count);
			}
			}
			catch(NullPointerException e)
			{	}
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
			// Must remove the index 1 because index 0 is null and will throw a null pointer exception
			}
			catch(NullPointerException e)
			{	}
		
		}
		System.out.println(trains.size());
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
			System.out.print("(" + dest + ")");
			LinkedListIterator<CargoCar> itr = t.iterator();
			itr.next();
			while(itr.hasNext()){
				CargoCar c = itr.next();
				System.out.print("->" + c.getName() + ":" + c.getWeight());
			}
			System.out.println();
			return true;
			
		}catch(NullPointerException e){
		//	e.printStackTrace();
			System.out.print("ERROR: Train for the destination not found(" + dest + ")" );
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
			return false;
		
		itr.next();
		
		while(itr.hasNext())
		{
			try
			{
			Train n = itr.next();
			displayTrain(n.getDestination());
			}
			catch(NullPointerException e)
			{	}
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
		Listnode<CargoCar> srcHeader, dstHeader, prev, curr;
		srcHeader = srcTrain.getHeaderNode();
		dstHeader = dstTrain.getHeaderNode();
		
		Listnode<CargoCar> first_prev = null, first = null, last = null;
		boolean hasFound = false;
		
		// 1. Find references to the node BEFORE the first matching cargo node
		//    and a reference to the last node with matching cargo.
		while(! (srcHeader.getNext() == null)){
			
		}
		
		
		
			// NOTE : We know we can find this cargo,
			//        so we are not going to deal with other exceptions here.

		
		
		
		
		// 2. Remove from matching chain of nodes from src Train
		//    by linking node before match to node after matching chain
		
		
		
		
		// 3-1. Find reference to first matching cargo in dst Train
		
		
				// 3-2. If found, insert them before cargo found in dst

		
			// 3-3. If no matching cargo, add them at the end of train
	}
}
