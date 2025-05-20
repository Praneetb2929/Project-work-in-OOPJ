import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    boolean playerXTurn = true;
    JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe Game");
        setSize(400, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Status Label
        statusLabel = new JLabel("Player X's Turn");
        statusLabel.setBounds(120, 20, 200, 30);
        add(statusLabel);

        // Game Buttons (3x3 Grid)
        int x = 50, y = 60;
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 30));
            buttons[i].setBounds(x, y, 80, 80);
            buttons[i].addActionListener(this);
            add(buttons[i]);

            x += 90;
            if ((i + 1) % 3 == 0) {
                x = 50;
                y += 90;
            }
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) {
            return; // Ignore if already clicked
        }

        if (playerXTurn) {
            clicked.setText("X");
            statusLabel.setText("Player O's Turn");
        } else {
            clicked.setText("O");
            statusLabel.setText("Player X's Turn");
        }

        if (checkWinner()) {
            String winner = playerXTurn ? "Player X" : "Player O";
            JOptionPane.showMessageDialog(this, winner + " wins!");
            resetGame();
            return;
        }

        if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            resetGame();
            return;
        }

        playerXTurn = !playerXTurn;
    }

    boolean checkWinner() {
        String[][] grid = new String[3][3];
        for (int i = 0; i < 9; i++) {
            grid[i / 3][i % 3] = buttons[i].getText();
        }

        // Check rows, columns, diagonals
        for (int i = 0; i < 3; i++) {
            if (!grid[i][0].equals("") && grid[i][0].equals(grid[i][1]) && grid[i][1].equals(grid[i][2]))
                return true;
            if (!grid[0][i].equals("") && grid[0][i].equals(grid[1][i]) && grid[1][i].equals(grid[2][i]))
                return true;
        }

        if (!grid[0][0].equals("") && grid[0][0].equals(grid[1][1]) && grid[1][1].equals(grid[2][2]))
            return true;
        if (!grid[0][2].equals("") && grid[0][2].equals(grid[1][1]) && grid[1][1].equals(grid[2][0]))
            return true;

        return false;
    }

    boolean isBoardFull() {
        for (JButton btn : buttons) {
            if (btn.getText().equals("")) return false;
        }
        return true;
    }

    void resetGame() {
        for (JButton btn : buttons) {
            btn.setText("");
        }
        playerXTurn = true;
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
