package lastpencil;

import java.util.InputMismatchException;
import java.util.Scanner;
class ZeroPencilsException extends Exception {
    public ZeroPencilsException(String message) {
        super(message);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first;
        String second;
        int pencils;
        System.out.println("How many pencils would you like to use?");
        do {
            String input = scanner.nextLine();


            try {
                pencils = Integer.parseInt(input);
                if (input.isEmpty()) {
                    throw new InputMismatchException();
                }
                if(pencils == 0) {
                    throw new ZeroPencilsException("The number of pencils should be positive");
                }
                if(pencils < 0) {
                    throw new InputMismatchException();
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
                continue;
            } catch (InputMismatchException | ZeroPencilsException e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;

        } while (true);

        System.out.println("Who will be the first (John, Jack): ");
        while (true) {
            first = scanner.nextLine();
            if (!first.equals("John") && !first.equals("Jack")) {
                System.out.println("Choose between 'John' and 'Jack'");
            } else {
                break;
            }
        }

        if(first.equals("John")) {
            second = "Jack";
        }else{
            second = "John";
        }

        while(true) {
            printPencils(pencils);
            pencils = playerTurn(first, pencils);
            checkWinner(second, pencils);
            printPencils(pencils);
            pencils = playerTurn(second, pencils);
            checkWinner(first, pencils);
        }


    }
    public static void printPencils(int pencils) {
        for(int i = 0; i < pencils; i++) {
            System.out.print("|");
        }
    }
    public static int playerTurn(String player, int pencils) {
        Scanner scanner = new Scanner(System.in);
        int turn;

        if (player.equals("Jack")) {
            if (pencils == 1) {
                turn = 1;
            }
            else if (pencils % 4 == 0) {
                turn = 3;
            }
            else if (pencils % 2 == 0) {
                turn = 1;
            }
            else if (pencils % 4 > 1) {
                turn = 2;
            }
            else {
                turn = (int) (Math.random() * 3) + 1;
            }
            System.out.println("\nJack's turn!\n" + turn);
        } else {
            System.out.println("\n" + player + "'s turn!");
            do {
                String input = scanner.nextLine();
                try {
                    turn = Integer.parseInt(input);
                    if (turn != 1 && turn != 2 && turn != 3) {
                        System.out.println("Possible values: 1, 2 or 3");
                        continue;
                    } else if (turn > pencils) {
                        System.out.println("Too many pencils were taken");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Possible values: 1, 2 or 3");
                    continue;
                }
                break;
            } while (true);
        }
        return pencils - turn;
    }




    public static void checkWinner(String player, int pencils) {
        if(pencils <= 0) {
            System.out.println(player + " won!");
            System.exit(0);
        }

    }
}
