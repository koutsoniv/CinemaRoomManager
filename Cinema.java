package cinema;
import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static int rows = 0;
    static int seatsPerRow = 0;
    static String[][] arr;
    static int numTickets = 0;
    static int currentIncome = 0;
    static int totalSeats = 0;
    private static final Scanner scanner = new Scanner(System.in);


    public static void initialize() {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();
        createArray(rows, seatsPerRow);
        totalSeats = rows * seatsPerRow;
    }

    public static void createArray(int rows, int seatsPerRow) {
        arr = new String[rows][seatsPerRow];
        for (String[] strings : arr) {
            Arrays.fill(strings, "S");
        }
    }
    public static void printArray(String[][] array, int rows, int seatsPerRow) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(" " + arr[i][j]);
            }
            System.out.println();
        }
    }
    private static void showMenu() {
        int choice;

        do {
            // Display the menu options
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            // Get user input
            choice = scanner.nextInt();

            switch(choice) {
                case 1: printArray(arr, rows, seatsPerRow); break;
                case 2: buyATicket(); break;
                case 3: showStatistics(); break;
                case 0: break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
    private static void buyATicket() {
        while (true) {
            System.out.println("Enter a row number:");
            int ticketRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatTicket = scanner.nextInt();
            if(ticketRow > rows || seatTicket > seatsPerRow) {
                System.out.println("Wrong Input!");
                continue;
            }
            if(arr[ticketRow -1 ][seatTicket -1].equals("B")) {
                System.out.println("That ticket has already been purchased!");
                continue;
            }
            if( rows * seatsPerRow <= 60) {
                currentIncome += 10;
                System.out.println("Ticket price: $10");
            } else if( ticketRow <= Math.floor(rows / 2.0)){
                currentIncome += 10;
                System.out.println("Ticket price: $10");
            } else {
                currentIncome += 8;
                System.out.println("Ticket price: $8");
            }
            arr[ticketRow - 1][seatTicket - 1] = "B";
            numTickets++;
            break;
        }
    }

    private static void showStatistics() {
        double percentage = (double) (numTickets * 100) / totalSeats;
        double totalIncome = Math.floor((double) rows / 2)* seatsPerRow * 10 +
                (totalSeats - (double) (rows / 2) * seatsPerRow) * 8;
        System.out.println("Number of purchased tickets: " + numTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.printf("Total income: $%.0f\n", totalIncome );
    }

    public static void main(String[] args) {
        // Write your code here
        initialize();
        showMenu();
        }
    }