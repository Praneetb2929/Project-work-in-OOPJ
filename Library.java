import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Book {
    String title;
    String author;
    boolean isIssued;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String toString() {
        return title + " by " + author + " | Status: " + (isIssued ? "Issued" : "Available");
    }
}

public class LibrarySystem extends JFrame implements ActionListener {

    ArrayList<Book> books;
    JTextArea displayArea;
    JButton addBtn, viewBtn, issueBtn, returnBtn, exitBtn;

    public LibrarySystem() {
        // Initialize book list
        books = new ArrayList<>();

        // Frame settings
        setTitle("Library Management System");
        setLayout(null);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Text area to display info
        displayArea = new JTextArea();
        displayArea.setBounds(20, 20, 450, 250);
        displayArea.setEditable(false);
        add(displayArea);

        // Buttons
        addBtn = new JButton("Add Book");
        addBtn.setBounds(50, 300, 120, 30);
        addBtn.addActionListener(this);
        add(addBtn);

        viewBtn = new JButton("View Books");
        viewBtn.setBounds(200, 300, 120, 30);
        viewBtn.addActionListener(this);
        add(viewBtn);

        issueBtn = new JButton("Issue Book");
        issueBtn.setBounds(50, 350, 120, 30);
        issueBtn.addActionListener(this);
        add(issueBtn);

        returnBtn = new JButton("Return Book");
        returnBtn.setBounds(200, 350, 120, 30);
        returnBtn.addActionListener(this);
        add(returnBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(130, 400, 120, 30);
        exitBtn.addActionListener(this);
        add(exitBtn);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            String title = JOptionPane.showInputDialog(this, "Enter Book Title:");
            String author = JOptionPane.showInputDialog(this, "Enter Author Name:");
            if (title != null && author != null) {
                books.add(new Book(title, author));
                JOptionPane.showMessageDialog(this, "Book added successfully!");
            }
        }

        else if (e.getSource() == viewBtn) {
            displayArea.setText("");
            if (books.isEmpty()) {
                displayArea.append("No books available.\n");
            } else {
                for (Book b : books) {
                    displayArea.append(b.toString() + "\n");
                }
            }
        }

        else if (e.getSource() == issueBtn) {
            String title = JOptionPane.showInputDialog(this, "Enter Book Title to Issue:");
            boolean found = false;
            for (Book b : books) {
                if (b.title.equalsIgnoreCase(title)) {
                    found = true;
                    if (!b.isIssued) {
                        b.isIssued = true;
                        JOptionPane.showMessageDialog(this, "Book issued successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Book already issued.");
                    }
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Book not found.");
            }
        }

        else if (e.getSource() == returnBtn) {
            String title = JOptionPane.showInputDialog(this, "Enter Book Title to Return:");
            boolean found = false;
            for (Book b : books) {
                if (b.title.equalsIgnoreCase(title)) {
                    found = true;
                    if (b.isIssued) {
                        b.isIssued = false;
                        JOptionPane.showMessageDialog(this, "Book returned successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Book was not issued.");
                    }
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Book not found.");
            }
        }

        else if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new LibrarySystem();
    }
}
