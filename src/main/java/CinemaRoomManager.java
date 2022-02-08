import java.util.Arrays;
import java.util.Scanner;

public class CinemaRoomManager {
    private static final Scanner scanner = new Scanner(System.in);
    static String s = " S";
    static String b = " B";
    private static int countRows;
    private static int countSeats;
    static String[][] arr = new String[countRows][countSeats];
    private static final String SPACE = "  ";
    private static int rowNumber;
    private static int seatNumber;
    static String[][] temp;
    static int countByTicket = 0;
    static float hundred = 100;
    static float percentageSeatOccupancy;
    static int sumCurrentIncome = 0;

    public static void main(String[] args) {
        initMap(arr);
        bySeveralTicket();
    }

    public static void bySeveralTicket() {
        while (true) {
            System.out.println("\n1. Show the seats \n2. Buy a ticket\n3. Statistics\n0. Exit");
            int command = scanner.nextInt();
            System.out.println();
            switch (command) {
                case 1 -> occupiedPlace();
                case 2 -> choosePlace();
                case 3 -> statistics();
                case 0 -> {
                    System.exit(0);
                }
            }
        }
    }

    public static void initMap(String[][] ARR) {
        System.out.println("Enter the number of rows:");
        countRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        countSeats = scanner.nextInt();
        totalIncomeMethod();
    }

    public static void creatureMap(int rowNumber, int seatNumber) {
        if (temp == arr) {
            return;
        }
        arr = new String[countRows][countSeats];
        for (String[] strings : arr) {
            Arrays.fill(strings, s);
        }
        temp = arr;
    }

    public static void choosePlace() {
        System.out.println("Enter a row number:");
        rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        seatNumber = scanner.nextInt();
        checkingFreeSpace(rowNumber, seatNumber);
        creatureMap(rowNumber, seatNumber);
        checkingAmountSpace(countRows, rowNumber, countSeats, seatNumber);
        ++countByTicket;
        percentageSeatOccupancy = (countByTicket * hundred) / (countSeats * countRows);
        if (rowNumber != 0 && seatNumber != 0) {
            arr[rowNumber - 1][seatNumber - 1] = b;
            priceTicket(countRows, countSeats, rowNumber);
        }
    }

    public static void table() {
        System.out.println("Cinema:");
        for (int i = 0; i <= countSeats; i++) {
            String tableContents = i == 0 ? SPACE : i + " ";
            System.out.print(tableContents);
        }
        System.out.println();
    }

    public static void occupiedPlace() {
        creatureMap(rowNumber, seatNumber);
        table();
        for (int i = 0; i < temp.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < temp[i].length; j++) {
                if (i == rowNumber - 1 && j == seatNumber - 1) {
                    arr[rowNumber - 1][seatNumber - 1] = b;
                    System.out.print(arr[i][j]);
                } else {
                    System.out.print(arr[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void statistics() {
        System.out.printf("Number of purchased tickets: %d\n" +
                "Percentage: %.2f%%\n" +
                "Current income: $%d\n" +
                "Total income: $%d\n", countByTicket, percentageSeatOccupancy, sumCurrentIncome, totalIncomeMethod());
    }

    public static int totalIncomeMethod() {
        return countRows * countSeats < 60 ? (countRows * countSeats) * 10 : (((countRows / 2) * countSeats) * 10) + ((countRows - (countRows / 2)) * countSeats * 8);
    }

    private static void checkingAmountSpace(int countRows, int rowNumber, int countSeats, int seatNumber) {
        if (countRows < rowNumber || countSeats < seatNumber) {
            System.out.println("Wrong input!\n");
            choosePlace();
        }
    }

    public static void checkingFreeSpace(int rowNumber, int seatNumber) {
        try {
            if (" B".equals(temp[rowNumber - 1][seatNumber - 1])) {
                System.out.println("\nThat ticket has already been purchased!\n");
                choosePlace();
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    public static void priceTicket(int countRows, int countSeats, int rowNumber) {
        int setPlace = countRows * countSeats;
        int halfRows = countRows / 2;
        int currentIncome = rowNumber <= halfRows || setPlace < 60 ? 10 : 8;
        sumCurrentIncome += currentIncome;
        String priceTicket = "Ticket price: $" + currentIncome;
        System.out.println(priceTicket);
        bySeveralTicket();

    }
}
