import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillingProject extends JFrame implements ActionListener {
    private JTextField itemField, priceField, quantityField, gstField, discountField;
    private JTextArea billArea;
    private JButton addButton, calculateButton, terminateButton;
    private double totalBill;

    public BillingProject() {
        setTitle("APNA VYAPAR");
        setSize(550, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY); // Set the panel background color

        // Create components
        itemField = new JTextField(15);
        priceField = new JTextField(8);
        quantityField = new JTextField(8);
        gstField = new JTextField(10);
        discountField = new JTextField(10);

        billArea = new JTextArea(30, 65);
        billArea.setEditable(false);
        billArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        addButton = new JButton("Add Item");
        calculateButton = new JButton("Calculate Total");
        terminateButton = new JButton("Terminate");

        // Customize button appearance
        addButton.setBackground(Color.BLUE); // Set button background color
        addButton.setForeground(Color.WHITE); // Set button text color
        calculateButton.setBackground(Color.BLACK);
        calculateButton.setForeground(Color.WHITE);
        terminateButton.setBackground(Color.RED);
        terminateButton.setForeground(Color.WHITE);

        // Add components to the frame
        add(new JLabel("Item Name:"));
        add(itemField);
        add(new JLabel("Price:"));
        add(priceField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("GST (%):"));
        add(gstField);
        add(new JLabel("Discount (%):"));
        add(discountField);

        add(addButton);
        add(calculateButton);
        add(new JScrollPane(billArea));
        add(terminateButton);

        // Add action listeners
        addButton.addActionListener(this);
        calculateButton.addActionListener(this);
        terminateButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            // Get input values
            String item = itemField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            double total = price * quantity;
            double gstPercentage = Double.parseDouble(gstField.getText().replace("%", ""));

            double gstAmount = total * (gstPercentage / 100);
            double discountAmount = 0.0; // Initialize discount amount to 0
            if (!discountField.getText().isEmpty()) {
                double discountPercentage = Double.parseDouble(discountField.getText().replace("%", ""));
                discountAmount = total * (discountPercentage / 100);
            }
            double finalTotal = total + gstAmount - discountAmount;

            String billItem = item + " - " + price + " x " + quantity + " = " + total;
            billArea.append(billItem + "\n");

            totalBill += finalTotal;

            // Clear data fields
            itemField.setText("");
            priceField.setText("");
            quantityField.setText("");
            gstField.setText("");
            discountField.setText("");
        } else if (e.getSource() == calculateButton) {
            billArea.append("--------------------------" + "\n" + "Total Bill: \u20B9 " + totalBill + "\n"); // Display the Rupee symbol
        } else if (e.getSource() == terminateButton) {
            JOptionPane.showMessageDialog(this, "Billing Terminated. Total Bill: \u20B9 " + totalBill); // Display the Rupee symbol
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new BillingProject();
    }
}