package mazebank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class code {
    private static JLabel displayLabel; 
    private static StringBuilder displayText = new StringBuilder(); 
    private static String action; 
    private static String result; 
    private static JFrame frame; 

    public static String bankomatsKods(String actionType) {
        action = actionType;
        result = null;
        displayText.setLength(0);

        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JLabel headerLabel = new JLabel((action.equals("PIN") ? "Ievadi PIN" : "Ievadi summu"), SwingConstants.LEFT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4; 
        gbc.weightx = 1.0;
        frame.add(headerLabel, gbc);

        displayLabel = new JLabel(" ", SwingConstants.CENTER);
        displayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        displayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        displayLabel.setPreferredSize(new Dimension(320, 60)); 
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 4; 
        gbc.weightx = 1.0;
        frame.add(displayLabel, gbc);

        Dimension buttonSize = new Dimension(80, 80); 

        // First column: 7, 4, 1, *
        gbc.gridx = 0; gbc.gridy = 2; frame.add(createButton("7", buttonSize), gbc);
        gbc.gridx = 0; gbc.gridy = 3; frame.add(createButton("4", buttonSize), gbc);
        gbc.gridx = 0; gbc.gridy = 4; frame.add(createButton("1", buttonSize), gbc);
        gbc.gridx = 0; gbc.gridy = 5; frame.add(createButton("*", buttonSize), gbc);

        // Second column: 8, 5, 2, 0
        gbc.gridx = 1; gbc.gridy = 2; frame.add(createButton("8", buttonSize), gbc);
        gbc.gridx = 1; gbc.gridy = 3; frame.add(createButton("5", buttonSize), gbc);
        gbc.gridx = 1; gbc.gridy = 4; frame.add(createButton("2", buttonSize), gbc);
        gbc.gridx = 1; gbc.gridy = 5; frame.add(createButton("0", buttonSize), gbc);

        // Third column: 9, 6, 3, #
        gbc.gridx = 2; gbc.gridy = 2; frame.add(createButton("9", buttonSize), gbc);
        gbc.gridx = 2; gbc.gridy = 3; frame.add(createButton("6", buttonSize), gbc);
        gbc.gridx = 2; gbc.gridy = 4; frame.add(createButton("3", buttonSize), gbc);
        gbc.gridx = 2; gbc.gridy = 5; frame.add(createButton("#", buttonSize), gbc);

        // Fourth column: ↑, ↓, Enter, Cancel
        gbc.gridx = 3; gbc.gridy = 2; frame.add(createButton("↑", buttonSize), gbc);
        gbc.gridx = 3; gbc.gridy = 3; frame.add(createButton("↓", buttonSize), gbc);
        gbc.gridx = 3; gbc.gridy = 4; frame.add(createButton("Enter", buttonSize), gbc);
        gbc.gridx = 3; gbc.gridy = 5; frame.add(createButton("Cancel", buttonSize), gbc);

        // Window settings
        frame.pack(); // Dynamically adjust size to fit all components
        frame.setMinimumSize(new Dimension(400, 500)); // Ensure minimum size
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);

        // Wait for result or frame close
        while (result == null && frame.isDisplayable()) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

    private static JButton createButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.matches("[0-9]")) {
                    if (displayText.length() < (action.equals("PIN") ? 4 : 10)) {
                        displayText.append(text);
                        displayLabel.setText(displayText.toString());
                    }
                } else if (text.equals("Cancel")) {
                    displayText.setLength(0);
                    displayLabel.setText(" ");
                } else if (text.equals("Enter")) {
                    result = displayText.toString();
                    frame.dispose(); 
                }
            }
        });
        return button;
    }
}