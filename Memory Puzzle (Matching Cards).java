import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MemoryPuzzleGame extends JFrame implements ActionListener {
    private JButton[] buttons;
    private String[] cardValues;
    private JButton firstButton, secondButton;
    private int pairsFound = 0;
    private Timer timer;

    public MemoryPuzzleGame() {
        setTitle("🧠 Memory Puzzle Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4)); // 4x4 grid

        // Create 8 pairs of letters (A-H)
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        cardValues = new String[16];
        for (int i = 0; i < 8; i++) {
            cardValues[2 * i] = letters[i];
            cardValues[2 * i + 1] = letters[i];
        }

        // Shuffle the cards
        List<String> cardList = Arrays.asList(cardValues);
        Collections.shuffle(cardList);
        cardValues = cardList.toArray(new String[0]);

        // Create buttons for cards
        buttons = new JButton[16];
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton("?");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 24));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Ignore already matched cards
        if (!clickedButton.getText().equals("?")) return;

        int index = Arrays.asList(buttons).indexOf(clickedButton);
        clickedButton.setText(cardValues[index]);

        if (firstButton == null) {
            firstButton = clickedButton;
        } else if (secondButton == null && clickedButton != firstButton) {
            secondButton = clickedButton;

            // Disable clicks during check
            for (JButton btn : buttons) btn.setEnabled(false);

            timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    checkForMatch();
                    for (JButton btn : buttons) btn.setEnabled(true);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void checkForMatch() {
        if (firstButton.getText().equals(secondButton.getText())) {
            firstButton.setEnabled(false);
            secondButton.setEnabled(false);
            pairsFound++;
            if (pairsFound == 8) {
                JOptionPane.showMessageDialog(this, "🎉 You matched all pairs! Game Over!");
            }
        } else {
            firstButton.setText("?");
            secondButton.setText("?");
        }

        firstButton = null;
        secondButton = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MemoryPuzzleGame());
    }
}
