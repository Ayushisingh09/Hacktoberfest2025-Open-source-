import java.util.Scanner;

public class TicTacToe {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char currentPlayer = 'X';
        boolean gameEnded = false;

        System.out.println("🎮 Welcome to Tic-Tac-Toe!");
        printBoard();

        while (!gameEnded) {
            System.out.println("\nPlayer " + currentPlayer + "'s turn.");
            int row, col;

            while (true) {
                System.out.print("Enter row (0, 1, 2): ");
                row = sc.nextInt();
                System.out.print("Enter column (0, 1, 2): ");
                col = sc.nextInt();

                // Check if move is valid
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                    board[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("❌ Invalid move! Try again.");
                }
            }

            printBoard();

            // Check if the current player has won
            if (hasWon(currentPlayer)) {
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (isBoardFull()) {
                System.out.println("😐 It's a tie!");
                gameEnded = true;
            } else {
                // Switch players
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        sc.close();
        System.out.println("👋 Game over. Thanks for playing!");
    }

    // Print the game board
    public static void printBoard() {
        System.out.println("\nBoard:");
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Check if a player has won
    public static boolean hasWon(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (
                (board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)
            ) {
                return true;
            }
        }

        // Check diagonals
        if (
            (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        ) {
            return true;
        }

        return false;
    }

    // Check if board is full (for draw)
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }
}
