import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RestaurantOrderSystem extends JFrame implements ActionListener {

    JCheckBox item1, item2, item3, item4;
    JTextField qty1, qty2, qty3, qty4;
    JButton billBtn, clearBtn;
    JTextArea billArea;

    public RestaurantOrderSystem() {
        setTitle("Restaurant Ordering System");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Menu Items
        item1 = new JCheckBox("Burger - ₹100");
        item1.setBounds(30, 30, 200, 30);
        add(item1);
        qty1 = new JTextField("0");
        qty1.setBounds(250, 30, 50, 30);
        add(qty1);

        item2 = new JCheckBox("Pizza - ₹200");
        item2.setBounds(30, 70, 200, 30);
        add(item2);
        qty2 = new JTextField("0");
        qty2.setBounds(250, 70, 50, 30);
        add(qty2);

        item3 = new JCheckBox("Fries - ₹70");
        item3.setBounds(30, 110, 200, 30);
        add(item3);
        qty3 = new JTextField("0");
        qty3.setBounds(250, 110, 50, 30);
        add(qty3);

        item4 = new JCheckBox("Coke - ₹50");
        item4.setBounds(30, 150, 200, 30);
        add(item4);
        qty4 = new JTextField("0");
        qty4.setBounds(250, 150, 50, 30);
        add(qty4);

        // Buttons
        billBtn = new JButton("Calculate Bill");
        billBtn.setBounds(50, 200, 150, 30);
        billBtn.addActionListener(this);
        add(billBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(220, 200, 100, 30);
        clearBtn.addActionListener(this);
        add(clearBtn);

        // Bill Display Area
        billArea = new JTextArea();
        billArea.setBounds(30, 250, 420, 180);
        billArea.setEditable(false);
        add(billArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == billBtn) {
            double total = 0;
            StringBuilder bill = new StringBuilder();
            bill.append("===== Your Order =====\n");

            if (item1.isSelected()) {
                int qty = Integer.parseInt(qty1.getText());
                double price = 100 * qty;
                total += price;
                bill.append("Burger x ").append(qty).append(" = ₹").append(price).append("\n");
            }

            if (item2.isSelected()) {
                int qty = Integer.parseInt(qty2.getText());
                double price = 200 * qty;
                total += price;
                bill.append("Pizza  x ").append(qty).append(" = ₹").append(price).append("\n");
            }

            if (item3.isSelected()) {
                int qty = Integer.parseInt(qty3.getText());
                double price = 70 * qty;
                total += price;
                bill.append("Fries  x ").append(qty).append(" = ₹").append(price).append("\n");
            }

            if (item4.isSelected()) {
                int qty = Integer.parseInt(qty4.getText());
                double price = 50 * qty;
                total += price;
                bill.append("Coke   x ").append(qty).append(" = ₹").append(price).append("\n");
            }

            double tax = total * 0.05;
            double grandTotal = total + tax;

            bill.append("---------------------------\n");
            bill.append("Subtotal: ₹").append(total).append("\n");
            bill.append("GST (5%): ₹").append(tax).append("\n");
            bill.append("Total Bill: ₹").append(grandTotal).append("\n");
            bill.append("===========================\n");

            billArea.setText(bill.toString());
        }

        if (e.getSource() == clearBtn) {
            item1.setSelected(false);
            item2.setSelected(false);
            item3.setSelected(false);
            item4.setSelected(false);

            qty1.setText("0");
            qty2.setText("0");
            qty3.setText("0");
            qty4.setText("0");

            billArea.setText("");
        }
    }

    public static void main(String[] args) {
        new RestaurantOrderSystem();
    }
}
