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

        frame = new JFrame("Bankomāts");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        JLabel headerLabel = new JLabel((action.equals("PIN") ? "Ievadi PIN" : "Ievadi summu"), SwingConstants.LEFT);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        frame.add(headerLabel, gbc);

        displayLabel = new JLabel(" ", SwingConstants.CENTER);
        displayLabel.setFont(new Font("Arial", Font.BOLD, 18));
        displayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        displayLabel.setPreferredSize(new Dimension(400, 80));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        frame.add(displayLabel, gbc);

        Dimension buttonSize = new Dimension(100, 100); 

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2; frame.add(createButton("7", buttonSize), gbc);
        gbc.gridx = 0; gbc.gridy = 3; frame.add(createButton("4", buttonSize), gbc);
        gbc.gridx = 0; gbc.gridy = 4; frame.add(createButton("1", buttonSize), gbc);
        gbc.gridx = 0; gbc.gridy = 5; frame.add(createButton("*", buttonSize), gbc);

        gbc.gridx = 1; gbc.gridy = 2; frame.add(createButton("8", buttonSize), gbc);
        gbc.gridx = 1; gbc.gridy = 3; frame.add(createButton("5", buttonSize), gbc);
        gbc.gridx = 1; gbc.gridy = 4; frame.add(createButton("2", buttonSize), gbc);
        gbc.gridx = 1; gbc.gridy = 5; frame.add(createButton("0", buttonSize), gbc);

        gbc.gridx = 2; gbc.gridy = 2; frame.add(createButton("9", buttonSize), gbc);
        gbc.gridx = 2; gbc.gridy = 3; frame.add(createButton("6", buttonSize), gbc);
        gbc.gridx = 2; gbc.gridy = 4; frame.add(createButton("3", buttonSize), gbc);
        gbc.gridx = 2; gbc.gridy = 5; frame.add(createButton("#", buttonSize), gbc);

        gbc.gridx = 3; gbc.gridy = 2; frame.add(createButton("Enter", buttonSize), gbc);
        gbc.gridx = 3; gbc.gridy = 3; frame.add(createButton("Dzēst", buttonSize), gbc);
        gbc.gridx = 3; gbc.gridy = 4; frame.add(createButton("Nespied", buttonSize), gbc);
        gbc.gridx = 3; gbc.gridy = 5; frame.add(createButton("Cancel", buttonSize), gbc);

        frame.pack();
        frame.setMinimumSize(new Dimension(600, 600)); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.matches("[0-9]")) {
                    if (displayText.length() < (action.equals("PIN") ? 4 : 10)) {
                        displayText.append(text);
                        displayLabel.setText(displayText.toString());
                    }
                } else if (text.equals("Enter")) {
                    result = displayText.toString();
                    frame.dispose();
                } else if (text.equals("Dzēst")) {
                    if (displayText.length() > 0) {
                        displayText.deleteCharAt(displayText.length() - 1);
                        displayLabel.setText(displayText.toString());
                    }
                } else if (text.equals("Nespied")) {
                    JOptionPane.showMessageDialog(null, "Es tev teicu, lai nespiež. Malacis, tev vairs nav kartes)", "Bankomāts", JOptionPane.INFORMATION_MESSAGE);
                    result = null;
                    frame.dispose();
                } else if (text.equals("Cancel")) {
                    JOptionPane.showMessageDialog(null, "Neaizmirstiet savu karti", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    result = null;
                    displayText.setLength(0);
                    frame.dispose();
                }
            }
        });
        return button;
    }
}