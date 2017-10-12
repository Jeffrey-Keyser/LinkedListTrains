import java.util.Scanner;

/**
 * This class is the main class for this project.
 * The main method will play a role to interact between users and TrainHub object.
 * 
 * DO NOT EDIT OR HAND IN THIS CLASS
 * 
 * @see TrainHub
 */
public class TrainHubMain {

	/**
	 * [DO NOT MODIFY THIS METHOD]
	 * 
	 * The main method of this project assignment.
	 * 1. Initialize TrainHub object.
	 * 2. Parse arguments. If there are arguments with the right format, load trains from files
	 * 3. Display menu and call appropriate methods from console inputs.
	 */
	public static void main(String[] args) {
		TrainHub hub = new TrainHub();
		
		if(args.length != 0){
			if(args.length < 2){
				System.out.println(Config.ERROR_USAGE);
				System.exit(1);
			}
			
			if(args[0].equalsIgnoreCase("-f")){
				for(int i=1;i<args.length;i++){
					Train trainRead = TrainGenerator.getIncomingTrainFromFile(args[i]);
					if(trainRead == null){
						System.out.println(Config.ERROR_FILE_READ + args[i]);
						System.exit(1);
					}
					
					hub.processIncomingTrain(trainRead);
				}
			} else {
				System.out.println(Config.ERROR_USAGE);
				System.exit(1);
			}
		}
		
		System.out.println(Config.PROMPT_WELCOME_MSG);
		Scanner stdinScn = new Scanner(System.in);
		
		boolean isContinued = true;
		//// Main Loop ////
		while(isContinued){
			// See the displaying menu in Config.PROMPT_MENU.
			System.out.println(Config.PROMPT_MENU);
			
			// Get user input
			System.out.print("Command : ");
			String input = stdinScn.nextLine().trim();
			
			// Process input
			switch(input){
			case "1":{	// Handle a new train arrival
				hub.processIncomingTrain(TrainGenerator.getIncomingTrain());
			}
				break;
			case "2":{ // Remove a Cargo car from a departing Train
				String dest, name;
				System.out.print(Config.PROMPT_INPUT_DEST);
				dest = stdinScn.nextLine().trim();

				System.out.print(Config.PROMPT_INPUT_CARGO);
				name = stdinScn.nextLine().trim();
				
				if(hub.removeCargo(dest, name) == null){
					System.out.println(Config.ERROR_DEST_OR_CARGO_NOT_FOUND + 
									"("+ name + "," + dest + ")");
				}
			}
				break;
			case "3":{ // Get the Weight of a Cargo product from all the departing train
				int output = 0;
				System.out.print(Config.PROMPT_INPUT_CARGO);
				input = stdinScn.nextLine().trim();
				output = hub.getWeight(input);
				
				if(output == 0){
					System.out.println(Config.ERROR_CARGO_NOT_FOUND  + 
									"(" + input + ")");
				} else {
					System.out.println(Config.PROMPT_OUTPUT_WEIGHT + ": " + 
									input + " = " + output);
				}
			}
				break;
			case "4":{ // Depart a Train
				System.out.print(Config.PROMPT_INPUT_DEST);
				input = stdinScn.nextLine().trim();
				if(!hub.departTrain(input)){
					System.out.println(Config.ERROR_DEST_NOT_FOUND + "(" + input + ")");
				}
			}
				break;
			case "5":{ // Depart All the Trains in Hub
				if(!hub.departAllTrains()){
					System.out.println(Config.ERROR_NO_TRAIN_IN_HUB);
				}
			}
				break;
			case "6":{ // Display a departing Train
				System.out.print(Config.PROMPT_INPUT_DEST);
				input = stdinScn.nextLine().trim();
				if(!hub.displayTrain(input)){
					System.out.println(Config.ERROR_DEST_NOT_FOUND + "(" + input + ")");
				}
			}
				break;
			case "7":{ // Display All departing Trains in Hub
				if(!hub.displayAllTrains()) {
					System.out.println(Config.ERROR_NO_TRAIN_IN_HUB);
				}
			}
				break;
			case "8":{ // Move matching cargo from src chain to dst chain
				System.out.print(Config.PROMPT_INPUT_MOVE_SRC);
				input = stdinScn.nextLine().trim();
				Train srcTrain = hub.findTrain(input);
				if(srcTrain == null){
					System.out.println(Config.ERROR_DEST_NOT_FOUND + "(" + input + ")");
					break;
				}

				System.out.print(Config.PROMPT_INPUT_MOVE_DST);
				input = stdinScn.nextLine().trim();
				Train dstTrain = hub.findTrain(input);
				if(dstTrain == null){
					System.out.println(Config.ERROR_DEST_NOT_FOUND + "(" + input + ")");
					break;
				}

				System.out.print(Config.PROMPT_INPUT_CARGO);
				input = stdinScn.nextLine().trim();
				TrainHub.moveMultipleCargoCars(srcTrain, dstTrain, input);
			}
				break;
			case "9":{ // Quit
				isContinued = false;
			}
				break;
			default :
				System.out.println(Config.ERROR_WRONG_COMMAND);
			}
		}
		//// Main Loop ////
		
		if(stdinScn != null) stdinScn.close();
	}

}
