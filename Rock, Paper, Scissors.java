import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] choices = {"rock", "paper", "scissors"};
        String userChoice, computerChoice;
        String playAgain = "yes";

        System.out.println("🎮 Welcome to Rock, Paper, Scissors Game!");

        while (playAgain.equalsIgnoreCase("yes")) {
            // Get user's choice
            System.out.print("\nEnter your choice (rock, paper, scissors): ");
            userChoice = sc.next().toLowerCase();

            // Validate input
            if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
                System.out.println("❌ Invalid choice! Please enter rock, paper, or scissors.");
                continue;
            }

            // Computer's random choice
            computerChoice = choices[random.nextInt(3)];
            System.out.println("Computer chose: " + computerChoice);

            // Decide winner
            if (userChoice.equals(computerChoice)) {
                System.out.println("😐 It's a tie!");
            } else if (
                (userChoice.equals("rock") && computerChoice.equals("scissors")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock")) ||
                (userChoice.equals("scissors") && computerChoice.equals("paper"))
            ) {
                System.out.println("🎉 You win!");
            } else {
                System.out.println("💻 Computer wins!");
            }

            // Ask to play again
            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.next();
        }

        System.out.println("\n👋 Thanks for playing!");
        sc.close();
    }
}
