import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.lang.Integer;

public class NumberGuesserHW {
	private int level = 1;
	private int strikes = 0;
	private int maxStrikes = 5;
	private int number = 0;
	private boolean isRunning = false;
	private static String saveFile = "numberGuesserSave.txt";

	/***
	 * Gets a random number between 1 and level.
	 * 
	 * @param level (level to use as upper bounds)
	 * @return number between bounds
	 */
	public static int getNumber(int level) {
		int range = 9 + ((level - 1) * 5);
		System.out.println("I picked a random number between 1-" + (range + 1) + ", let's see if you can guess.");
		return new Random().nextInt(range) + 1;
	}

	private void win() {
      if (number == 69){
         System.out.println("nice");
      }
      else{
		   System.out.println("That's right!");
      }
		level++;// level up!
		saveLevel();
		strikes = 0;
      if (level == 69){
         System.out.println("You made it to level 69! Nice.");
         number = 69;
      }
      else if (level == 420){ //prints a weed leaf at level 420, because I am funny and also original.
       System.out.println("        /\\ ");
       System.out.println(" |\\    /  \\    /| ");
       System.out.println(" | \\   \\  /   / | ");
       System.out.println(" |  |  \\  /  |  | ");
       System.out.println("  \\  \\ \\  / /  /  ");
       System.out.println("|\\__\\ \\\\  // /__/|");
       System.out.println(" \\___--    --___/ ");
       System.out.println("     /_/||\\_\\     ");
       System.out.println("        ||        ");
      }
      else{
		   number = getNumber(level);
      }
      System.out.println("Welcome to level " + level);
      saveLevel();
	}

	private void lose() {
		System.out.println("Uh oh, looks like you need to get some more practice.");
		System.out.println("The correct number was " + number);
		strikes = 0;
		level--;
		if (level < 1) {
			level = 1;
		}
      number = getNumber(level);
		saveLevel();
	}

	private void processCommands(String message) {
		if (message.equalsIgnoreCase("quit")) {
         saveLevel();
			System.out.println("Tired of playing? No problem, see you next time.");
			isRunning = false;
      }
      else if (message.equalsIgnoreCase("up up down down left right left right b a start")){
         maxStrikes += 100;
         System.out.println("Imanok grants you 100 more strikes");
      }
      else if (message.equalsIgnoreCase("cheats off")){
      maxStrikes = 5;
      strikes = 0;
      System.out.println("cheats have been disabled, Strikes reset.");
		}
	}

	private void processGuess(int guess) {
		if (guess < 0) {
			return;
		}
		System.out.println("You guessed " + guess);
		if (guess == number) {
			win();
		} else {
			System.out.println("That's wrong");
			strikes++;
			if (strikes >= maxStrikes) {
				lose();
			} else {
				int remainder = maxStrikes - strikes;
				System.out.println("You have " + remainder + "/" + maxStrikes + " attempts remaining");
				if (guess > number) {
					System.out.println("Lower");
				} else if (guess < number) {
					System.out.println("Higher");
				}
			}
		}
	}

	private int getGuess(String message) {
		int guess = -1;
		try {
			guess = Integer.parseInt(message);
		} catch (NumberFormatException e) {
			System.out.println("You didn't enter a number, please try again");

		}
		return guess;
	}

	private void saveLevel() {
		try (FileWriter fw = new FileWriter(saveFile)) {
			fw.write("" + level + "\n" + number + "\n" + strikes + "\n" + maxStrikes);// here we need to convert it to a String to record correctly
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean loadLevel() {
		File file = new File(saveFile);
		if (!file.exists()) {
			return false;
		}
		try (Scanner reader = new Scanner(file)) { 
			//while (reader.hasNextLine()) {
				int _level = reader.nextInt();
            int _number = reader.nextInt();
            int _strikes = reader.nextInt();
            int _maxStrikes = reader.nextInt();
				if (_level > 1) {
					level = _level;
               number = _number;
               strikes = _strikes;
               maxStrikes = _maxStrikes;
					//break;
				//}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
		return level > 1;
	}

	void run() {
		try (Scanner input = new Scanner(System.in);) {
			System.out.println("Welcome to Number Guesser 4.0!");
			System.out.println("I'll ask you to guess a number between a range, and you'll have " + maxStrikes
					+ " attempts to guess.");
			if (loadLevel()) {
				System.out.println("Successfully loaded level " + level + " let's continue then");
			}
			else number = getNumber(level); 
			isRunning = true;
			while (input.hasNext()) {
				String message = input.nextLine();
				processCommands(message);
				if (!isRunning) {
					break;
				}
				int guess = getGuess(message);
				processGuess(guess);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
      Scanner nameyBoi = new Scanner(System.in);
      System.out.println("What is your name? ");
      String saveName = nameyBoi.nextLine();
      saveFile = "NGS_" + saveName + ".txt";
      NumberGuesserHW guesser = new NumberGuesserHW();
		guesser.run();
	}
}