import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class UI {
    private final ParkingLot lot;
    private JTextField vehicleInput;
    private JTextArea outputArea;

    public UI(ParkingLot lot) {
        this.lot = lot;
        createUI();
    }

    private void createUI() {
        JFrame frame = new JFrame("Vehicle Parking System");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Vehicle Parking Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 45));
        title.setForeground(new Color(25, 25, 112));
        panel.add(title, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(240, 248, 255));
        JLabel label = new JLabel("Enter Vehicle Number:");
        label.setFont(new Font("Arial", Font.BOLD, 25));
        vehicleInput = new JTextField(15);
        vehicleInput.setPreferredSize(new Dimension(200, 25));
        vehicleInput.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(label);
        inputPanel.add(vehicleInput);
        panel.add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));

        JButton addButton = new JButton("Park Vehicle");
        JButton removeButton = new JButton("Exit Vehicle");
        JButton viewButton = new JButton("View Vehicles");

        Font buttonFont = new Font("Segoe UI", Font.PLAIN, 14);
        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        viewButton.setFont(buttonFont);

        addButton.setBackground(new Color(144, 238, 144));
        removeButton.setBackground(new Color(255, 160, 122));
        viewButton.setBackground(new Color(173, 216, 230));

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Output Text Area
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.EAST);

        // Action Listeners
        addButton.addActionListener(e -> {
            String num = vehicleInput.getText().trim();
            if (!num.isEmpty()) {
                outputArea.setText(lot.addVehicle(num)
                        ? " Vehicle " + num + " parked."
                        : " Slot full or vehicle already parked.");
                vehicleInput.setText("");
            } else {
                outputArea.setText(" Please enter a vehicle number.");
            }
        });

        removeButton.addActionListener(e -> {
            String num = vehicleInput.getText().trim();
            if (!num.isEmpty()) {
                outputArea.setText(lot.removeVehicle(num));
                vehicleInput.setText("");
            } else {
                outputArea.setText(" Please enter a vehicle number to exit.");
            }
        });

        viewButton.addActionListener(e -> {
            String vehicleList = lot.getParkedVehicles().isEmpty()
                ? "No vehicles parked currently."
                : lot.getParkedVehicles().entrySet().stream()
                    .map(entry -> entry.getKey() + " | Entered: " + entry.getValue().getFormattedEntryTime())
                    .collect(Collectors.joining("\n"));

            outputArea.setText(
                " Parked Vehicles:\n" + vehicleList +
                "\n\n Available slots: " + lot.getAvailableSlots()
            );
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
