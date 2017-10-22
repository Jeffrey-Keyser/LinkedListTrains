import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class provide methods for generating a Train.
 * 
 * COMPLETE THIS CLASS and HAND IN THIS FILE
 * 
 * @see Config
 */
public class TrainGenerator {
	
	/**
	 * Get a new train generated randomly.
	 * The constant variables for determining how many cargo, 
	 * what cargo and how heavy are in {@link Config}.
	 * 
	 * @return a train generated randomly
	 */
	public static Train getIncomingTrain(){
		Train incomingTrain = new Train("TrainHub");
		
		Random rand = new Random(System.currentTimeMillis());

		// How many freight cars
		int cartNum = rand.nextInt(Config.MAX_CART_NUM - Config.MIN_CART_NUM + 1) + Config.MIN_CART_NUM;

		for(int i=0; i<cartNum;i++){
			// What kind of cargo
			int loadIndex = rand.nextInt(Config.CARGO_ARRAY.length);
			String load = Config.CARGO_ARRAY[loadIndex];

			// How heavy
			int weight = rand.nextInt(Config.MAX_WEIGHT - Config.MIN_WEIGHT + 1) + Config.MIN_WEIGHT;

			// Where to
			int destIndex = rand.nextInt(Config.DEST_ARRAY.length);
			String dest = Config.DEST_ARRAY[destIndex];
			
			incomingTrain.add(new CargoCar(load, weight, dest));
		}
		
		return incomingTrain;
	}
	
	/**
	 * Get a new train generated from a file.
	 * Files for generating a train must have the format as follow
	 * <p>
	 * {destination},{cargo},{weight}<br>
	 * {destination},{cargo},{weight}<br>
	 * ...
	 * <p>
	 * where {destination} is a string among Config.DEST_ARRAY,
	 * {cargo} is a string among Config.CARGO_ARRAY,
	 * and {weight} is a string for any positive integer.
	 * <p>
	 * Ignore the line if it is not matched in this format. 
	 * See the sample in/outputs provided in the assignment description to get more details.
	 * 
	 * @param filename train input file name
	 * @return a train generated from a file
	 */
	public static Train getIncomingTrainFromFile(String filename){
		
		File inFile = new File(filename);
        Scanner input = null;
        Train train = new Train(null);
        String destination;
        String product;
        Integer amount = 0;
        
        // Try loading the given file
        // If an IOException is thrown, display the error
        // and return null
        try
        {
        	input = new Scanner(inFile);
        }
        catch(IOException e){
        	System.out.println(Config.ERROR_FILE_READ);
        	return null;
        }
        	while(input.hasNextLine()){
        		
        		try{
        		String line = input.nextLine();
        		String[] parts = line.split(",");
        		
        		// If there are less or more than 2 intances of ','
        		// Don't try to load that line
        		if(parts.length != 3){
        			continue;
        		}
        		// Trim all the Strings found on the line
        		for(int i = 0; i < parts.length; i++){
        			parts[i] = parts[i].trim();
        		}
        		
        		// Store corresponding data
        		destination = parts[0];
        		product = parts[1];
        		// Make String of type Int
        		amount = Integer.parseInt(parts[2]);
        		
        		// Create a new CargoCar with the given data
        		CargoCar car = new CargoCar(product, amount, destination);
        		train.add(car);
        		
        		} 
        catch(NumberFormatException e){
        	// If the parseInt line isn't of type int
        	// Catch the NumberFormatException here 
        }

        }
        	

        // finally{
    	   if(input != null)input.close();
//	}
        return train;
}
}
