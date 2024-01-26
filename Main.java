import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean runCinema = true;

        System.out.println("Enter the number of rows:");
        int totalRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int totalSeatsPerRow = scanner.nextInt();
        char[][] cinemaSeatingSetup = createCinemaSeats(totalRows, totalSeatsPerRow);

        System.out.println();
        printMenu();

        while(runCinema) {
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    printCinemaSeats(cinemaSeatingSetup);
                    printMenu();
                    break;
                case 2:
                    buyTicket(cinemaSeatingSetup);
                    printMenu();
                    break;
                case 3:
                    calculateAndPrintStatistics(cinemaSeatingSetup);
                    printMenu();
                    break;
                case 0:
                    runCinema = false;
                    break;
           }
        }
    }

    public static void printMenu() {

        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit""");
    }

    public static char[][] createCinemaSeats(int totalRowsInCinema, int totalSeatsPerRow) {

        char[][] seats = new char[totalRowsInCinema][totalSeatsPerRow];
        for(int i=0; i<seats.length; i++){
            for(int j=0; j<seats[i].length; j++){
                seats[i][j] = 'S';
            }
        }
        return seats;
    }

    public static void printCinemaSeats(char[][] cinemaSeatingSetup) {

        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        int seatNumberCounter = 1;
        for(int i=0; i<cinemaSeatingSetup[0].length; i++) {
            System.out.printf("%d%s", seatNumberCounter++, (i<cinemaSeatingSetup[0].length -1) ? " " : "");
        }
        System.out.println();

        for(int i=1; i<=cinemaSeatingSetup.length; i++){
            System.out.print(i + " ");
            for(int j=0; j<cinemaSeatingSetup[i-1].length; j++){
                System.out.printf("%c%s", cinemaSeatingSetup[i-1][j],(j < cinemaSeatingSetup[i-1].length) ? " " : "" );
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void calculateAndPrintStatistics(char[][] currentSeatingSetup) {

        int totalRowsInCinema = currentSeatingSetup.length;
        int totalSeatsPerRow = currentSeatingSetup[0].length;
        int totalSeatsInCinema = totalRowsInCinema * totalSeatsPerRow;
        int frontHalf = totalRowsInCinema / 2;
        int totalIncome = 0;
        int totalSeatsSold = 0;
        int currentIncome = 0;

        for(int i=0; i<currentSeatingSetup.length; i++){
            for(int j=0; j<currentSeatingSetup[i].length; j++){
                if(totalSeatsInCinema < 61) {
                    totalIncome +=10;
                } else {

                    if(i < frontHalf ) {
                        totalIncome +=10;
                    } else {
                        totalIncome +=8;
                    }
                }
                if(currentSeatingSetup[i][j] == 'B') {
                    currentIncome += totalSeatsInCinema < 61 ? 10 : (i<frontHalf ? 10 : 8);
                    totalSeatsSold++;
                }
            }
        }
        double percentageSold = (double) totalSeatsSold/ totalSeatsInCinema * 100;
        String formattedPercentageSold = String.format("%.2f", percentageSold);

        System.out.println();
        System.out.println("Number of purchased tickets: " + totalSeatsSold);
        System.out.println("Percentage: " + formattedPercentageSold +"%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static void buyTicket(char[][] currentSeatingStatus) {

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int selectedRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int selectedSeatNumber = scanner.nextInt();

        int totalRowsInCinema = currentSeatingStatus.length;
        int totalSeatsPerRow = currentSeatingStatus[0].length;
        int totalSeatsInCinema = totalRowsInCinema * totalSeatsPerRow;
        int frontHalf = totalRowsInCinema / 2;
        int backHalf = totalRowsInCinema - frontHalf;
        int ticketPrice = 0;


        if(selectedRow > currentSeatingStatus.length || selectedSeatNumber > currentSeatingStatus[0].length
        || selectedRow < 1 || selectedSeatNumber < 1) {
            System.out.println();
            System.out.println("Wrong input!");
            buyTicket(currentSeatingStatus);
        } else if (currentSeatingStatus[selectedRow-1][selectedSeatNumber-1] == 'B') {
            System.out.println();
            System.out.println("That ticket has already been purchased");
            buyTicket(currentSeatingStatus);
        } else {
            if(totalSeatsInCinema < 61) {
                ticketPrice = 10;
            } else {
                if(selectedRow <= frontHalf) {
                    ticketPrice = 10;
                } else {
                    ticketPrice = 8;
                }
            }

            printTicketPrice(ticketPrice);
            updateCinemaSeats(currentSeatingStatus, selectedRow, selectedSeatNumber);
        }
    }

    private static void printTicketPrice(int ticketPrice) {
        System.out.println();
        System.out.println("Ticket Price: $" + ticketPrice);
        System.out.println();
    }

    private static void updateCinemaSeats(char[][] currentSeatingStatus, int selectedRow, int selectedSeatNumber) {
        currentSeatingStatus[selectedRow-1][selectedSeatNumber-1] = 'B';
    }

}

