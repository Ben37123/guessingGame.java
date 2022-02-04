import java.util.Scanner;
import java.util.Random;

class Main {
  public static void main(String[] args) {

    //initializing variables
    int randNum, limit, maxGuesses, lowerLimit;
    int numGuesses = 0;
    int guess;
    String result = "";
    boolean isInLimit = false;
    boolean playAgain = true;

    //main while loop of the game getting the user limit, a random number, and the maximum number of guesses
    System.out.println("░██████╗░██╗░░░██╗███████╗░██████╗░██████╗██╗███╗░░██╗░██████╗░  ░██████╗░░█████╗░███╗░░░███╗███████");
    System.out.println("██╔════╝░██║░░░██║██╔════╝██╔════╝██╔════╝██║████╗░██║██╔════╝░  ██╔════╝░██╔══██╗████╗░████║██╔════╝");
    System.out.println("██║░░██╗░██║░░░██║█████╗░░╚█████╗░╚█████╗░██║██╔██╗██║██║░░██╗░  ██║░░██╗░███████║██╔████╔██║█████╗░░");
    System.out.println("██║░░╚██╗██║░░░██║██╔══╝░░░╚═══██╗░╚═══██╗██║██║╚████║██║░░╚██╗  ██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░");
    System.out.println("╚██████╔╝╚██████╔╝███████╗██████╔╝██████╔╝██║██║░╚███║╚██████╔╝  ╚██████╔╝██║░░██║██║░╚═╝░██║███████╗");
    System.out.println("░╚═════╝░░╚═════╝░╚══════╝╚═════╝░╚═════╝░╚═╝╚═╝░░╚══╝░╚═════╝░  ░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝");
    while (playAgain == true) {
      int again;
      result = "";
      numGuesses = 0;
      lowerLimit = get_lower_limit();
      limit = get_limit(lowerLimit);
      randNum = get_random_number(limit, lowerLimit);
      maxGuesses = get_max_guesses(limit, lowerLimit);
      System.out.println("Please guess a number between "+ lowerLimit +" and " + limit);
      System.out.println("You have " + maxGuesses + " guesses.");

      // while loop that gets the user guesses and checks them
      while (result != "You got it!!!" && numGuesses < maxGuesses) {
        if (numGuesses > 0) {
          System.out.println("Guess again");
        }
        guess = get_guess();
        isInLimit = is_in_limit(guess, limit, lowerLimit);
        if (isInLimit == true) {
          numGuesses += 1;
          result = check_guess(guess, randNum, maxGuesses, numGuesses);
          System.out.println(result);
        }
      }

      // asking user if they want to play again
      Scanner input = new Scanner(System.in);
      System.out.println("\n");
      System.out.println("Enter 1 or 2:");
      System.out.println("1 - play again");
      System.out.println("2 - quit game");
      again = input.nextInt();
      if (again != 1) {
        playAgain = false;
      }
    }
  }

  public static int get_random_number(int userLimit, int lowerLimit) {
    // uses Random to find a random number inside the chosen parameters
    int parameter;
    parameter = userLimit - lowerLimit;
    int randNum;
    Random rand = new Random();
    randNum = rand.nextInt(parameter);
    randNum += 1;
    randNum += lowerLimit;
    return randNum;
  }

  public static int get_guess() {
    // gets a number as a guess from the user and checks if its an integer
    Scanner userGuess = new Scanner(System.in);
    while(!userGuess.hasNextInt()) {
      System.out.println("Please enter a number");
      userGuess.next();
    }
    int input = userGuess.nextInt();
    return input;
  }

  public static String check_guess(int userGuess, int randNum, int maxGuesses, int numGuesses) {
    //checks the guess of the user to see if they are correct, too high, too low, or out of guesses and returns it
    if (numGuesses == maxGuesses && userGuess != randNum) {
      return "You are out of guesses!!!!!";
    }
    
    else {
      if (userGuess > randNum) {
        return "Lower";
      }

      if (userGuess < randNum) {
        return "Higher";
      }

      else {
        return "You got it!!!";
      }
    }
  }

  public static int get_lower_limit() {
    // gets the number that the user wants to be their parameters
    int limit;
    Scanner userLimit = new Scanner(System.in);
    System.out.println("What is the lowest limit you want to guess a number in? ??? and ???");
    limit = userLimit.nextInt();
    return limit;
  }

  public static int get_limit(int lowerLimit) {
    // gets the number that the user wants to be their parameters
    int limit;
    Scanner userLimit = new Scanner(System.in);
    System.out.println("What do you want it to pick a number between? "+ lowerLimit +" and ???");
    limit = userLimit.nextInt();
    return limit;
  }

  public static int get_max_guesses(int userLimit, int lowerLimit) {
    // deteremines how many guesses the user should be allowed
    int parameter;
    parameter = userLimit - lowerLimit;
    int maxNumGuesses;
    maxNumGuesses = (int) (Math.log(parameter)/Math.log(2)) + 1;
    return maxNumGuesses;
  }

  public static boolean is_in_limit(int userGuess, int userLimit, int lowerLimit) {
    // deeremines if the users guess is in the chosen range that they chose
    if (userGuess > userLimit || userGuess < lowerLimit) {
      System.out.println("Please enter a number within your chosen range!!");
      return false;
    }
    else {
      return true;
    }
  }
}

