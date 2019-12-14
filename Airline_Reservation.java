import java.util.Arrays;
import java.util.Scanner;

class Main {

    static int airplaneSeatTotal = 10; // the total number of seats on the airplane
    static int availableAirplaneSeats = airplaneSeatTotal; // anytime a seat is selected by an user subtract by 1
    static int currentFirstClassSeat = -1; // this is the array index of the First Class seat
    static int currentEconomySeat = 5; // this is the array index of the Economy seat

    static boolean isFirstClassSeatAvailable = true;
    static boolean isEconomySeatAvailable = true;
    static boolean isUserAssignedSeat = false;
    static boolean[] airplaneSeats = new boolean[airplaneSeatTotal]; // an array the size of the airplane seat total 
    static boolean isLookingForAirplaneSeats = true; //set this to false to stop console app

    static String addBorderLine = "*******************************************"; /// this is used to display a border
                                                                                 /// line style in the console

    public static void main(String[] args) {
        // initialize all the elements in the array to false
        for (int i = 0; i < airplaneSeatTotal; i++) {
            airplaneSeats[i] = false;
        }

        System.out.println(addBorderLine); // border line style
        System.out.println("Welcome to the Airline Reservation System");

        /// continue to look for airplane seats, until the user quits or there are no
        /// seats left to assign
        while (isLookingForAirplaneSeats) {

            /// ask the user which section of the airplane they like to sit
            System.out.println(addBorderLine); // border line console style
            System.out.println("Search for available airplane seat");

            if (isFirstClassSeatAvailable) {
                System.out.println("Please type 1 for First Class");
            }

            if (isEconomySeatAvailable) {
                System.out.println("Please type 2 for Economy");
            }

            System.out.println("Please type 3 to stop looking for airplane seats");
            System.out.println(addBorderLine); // border line console style

            //// save the user choice in variable
            Scanner userInput = new Scanner(System.in);
            int userChoice = userInput.nextInt();

            if (userChoice == 1 || userChoice == 2) {
                assignAirplaneSeat(userChoice);
            } else if (userChoice == 3) { // quit searching for tickets
                isLookingForAirplaneSeats = false;
                System.out.println("Please come back in the future, bye!!!");
            } else { // if an user choice is not 1 or 2 ,then the choice is invalid
                System.out.println("Your choice was invalid, Please choose a valid number");
            }
        }
    }

    //// method to assign available seats
    public static void assignAirplaneSeat(int userChoice) {

        if (availableAirplaneSeats == 0) {// check if all seats are assigned and quit
            isUserAssignedSeat = true; // break out loop because there are no more available seats
            isLookingForAirplaneSeats = false; // set to false indicating their are no more seats left
            System.out.println("Sorry,there are no more available seats on this flight.");
            System.out.println("Please come back in the future, bye!!!");

        } else if (userChoice == 1) {
            assignFirstClassSeat();
        } else {
            assignEconomySeat();
        }
    }

    //// method to assign First Class seats
    public static void assignFirstClassSeat() {
        // only loop thru first 5 seats i < 5, because seats 1 - 5 are First Class
        for (int i = currentFirstClassSeat + 1; i <= 5 && !isUserAssignedSeat; currentFirstClassSeat++) {
            // ask the user if they would like to find seat in Economy, because First Class in unavailable
            if (airplaneSeats[4] == true) {
                isFirstClassSeatAvailable = false;
                System.out.println("Sorry,there are no more available First Class seats on this flight.");
                System.out.println("Would you like to find an available Economy seat on this flight");
                System.out.println("Please enter 1 for yes or enter 2 for no");
                Scanner userInput = new Scanner(System.in);
                int userChoice = userInput.nextInt();

                // if the user selects (1)yes find an empty Economy seat
                if (userChoice == 1) {
                    assignEconomySeat();
                    isUserAssignedSeat = true; // break out loop because user was assign seat
                } else if (userChoice == 2) {
                    isLookingForAirplaneSeats = false; // indicates user doesn't want to continue looking for seats 
                    isUserAssignedSeat = true; // break out loop because there are no more available seats
                    System.out.println("next flight leaves in 3 hours.");
                    System.out.println("Thank you for visiting, bye!!!!!!");
                } else { // if an user choice is not 1 or 2 ,then the choice is invalid
                    System.out.println("Your choice was invalid, Please choose a valid number 1 for yes or 2 for no");
                }
            } else { // else there are available Economy seats
                airplaneSeats[i] = true; // set current seat to true to indicate seat is not available
                isUserAssignedSeat = true; // break out loop because there are no more available seats
                availableAirplaneSeats--; // subtract 1 from available airplane seats total
                printBoardingPass((currentFirstClassSeat + 1), "First Class");
            }
        }
        isUserAssignedSeat = false; // break out loop because there are no more available seats
    }

    //// method to assign Economy seats
    public static void assignEconomySeat() {
        // assign Economy seat
        // loop thru last 5 seats start at index array[5]
        for (int i = currentEconomySeat; i <= airplaneSeatTotal && !isUserAssignedSeat; currentEconomySeat++) {

            // check if there are no available seats in Economy
            // ask the user if they would like to find seat in First Class
            if (airplaneSeats[9] == true) { // checks if last element in array is true, indicating there are no Economy
                                            // seats
                isEconomySeatAvailable = false;
                System.out.println("Sorry,there are no more available Economy seats on this flight.");
                System.out.println("Would you like to find any available First Class seats on this flight");
                System.out.println("Please enter 1 for yes or enter 2 for no");
                Scanner userInput = new Scanner(System.in);
                int userChoice = userInput.nextInt();

                // if the user selects yes find an empty First Class seat
                if (userChoice == 1) {
                    assignFirstClassSeat();
                    isUserAssignedSeat = true; // break out loop because user was assign seat
                } else if (userChoice == 2) {
                    isLookingForAirplaneSeats = false; // this indicates user does not want to continue looking for
                                                       // airplane seats
                    isUserAssignedSeat = true; // break out loop because there are no more available seats
                    System.out.println("Next flight leaves in 3 hours.");
                    System.out.println("Thank you for visiting, bye!!!!!!");
                } else { // if an user choice is not 1 or 2 ,then the choice is invalid, ask the user
                         // again to select a valid number
                    System.out.println("Your choice was invalid, Please choose a valid number 1 for yes or 2 for no");
                }
            } else { // else there are available Economy seats

                airplaneSeats[i] = true; // set current seat to true to indicate seat is not available
                isUserAssignedSeat = true; // break out loop because user assigned seat
                availableAirplaneSeats--; // subtract 1 from available airplane seats total

                if (airplaneSeats[currentEconomySeat] == true) { // only print boarding pass when seats are assigned
                    printBoardingPass((currentEconomySeat), "Economy");
                }
            }
        }
        isUserAssignedSeat = false; // break out loop because there are no more available seats
    }

    //// method to print Boarding Pass
    static void printBoardingPass(int seatNumber, String sectionType) {
        int adjustedSeatNumber = seatNumber + 1;// add 1 to current seatNumber because the array index starts at 0
        String template = "Seat Number: %s, Section: %s";
        String boardingPass = String.format(template, adjustedSeatNumber, sectionType);

        System.out.println(addBorderLine); // border line console style
        System.out.println("BOARDING PASS");
        System.out.println(boardingPass);
        System.out.println(addBorderLine); // border line console style
    }
}
