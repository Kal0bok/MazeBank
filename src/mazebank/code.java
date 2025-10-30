package mazebank;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class code {
    private static JLabel displayLabel;
    private static StringBuilder displayText = new StringBuilder();
    private static String action;
    private static String result;
    private static JFrame frame;

    public static String bankomatsKods(String actionType, ArrayList<norkarte> konti, int izv) {
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
        gbc.gridx = 0; gbc.gridy = 2; frame.add(createButton("7", buttonSize, konti, izv), gbc);
        gbc.gridx = 0; gbc.gridy = 3; frame.add(createButton("4", buttonSize, konti, izv), gbc);
        gbc.gridx = 0; gbc.gridy = 4; frame.add(createButton("1", buttonSize, konti, izv), gbc);
        gbc.gridx = 0; gbc.gridy = 5; frame.add(createButton("*", buttonSize, konti, izv), gbc);

        gbc.gridx = 1; gbc.gridy = 2; frame.add(createButton("8", buttonSize, konti, izv), gbc);
        gbc.gridx = 1; gbc.gridy = 3; frame.add(createButton("5", buttonSize, konti, izv), gbc);
        gbc.gridx = 1; gbc.gridy = 4; frame.add(createButton("2", buttonSize, konti, izv), gbc);
        gbc.gridx = 1; gbc.gridy = 5; frame.add(createButton("0", buttonSize, konti, izv), gbc);

        gbc.gridx = 2; gbc.gridy = 2; frame.add(createButton("9", buttonSize, konti, izv), gbc);
        gbc.gridx = 2; gbc.gridy = 3; frame.add(createButton("6", buttonSize, konti, izv), gbc);
        gbc.gridx = 2; gbc.gridy = 4; frame.add(createButton("3", buttonSize, konti, izv), gbc);
        gbc.gridx = 2; gbc.gridy = 5; frame.add(createButton("#", buttonSize, konti, izv), gbc);

        gbc.gridx = 3; gbc.gridy = 2; frame.add(createButton("Enter", buttonSize, konti, izv), gbc);
        gbc.gridx = 3; gbc.gridy = 3; frame.add(createButton("Dzēst", buttonSize, konti, izv), gbc);
        gbc.gridx = 3; gbc.gridy = 4; frame.add(createButton("Nespied", buttonSize, konti, izv), gbc);
        gbc.gridx = 3; gbc.gridy = 5; frame.add(createButton("Cancel", buttonSize, konti, izv), gbc);

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
    //1
    public static void pressButton() {
        try {
            File soundFile = new File("sound" + File.separator + "kras.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Kļūda atskaņojot skaņu: " + e.getMessage(), "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
    //2
    public static void pressButtonspec() {
        try {
            File soundFile = new File("sound" + File.separator + "puk.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Kļūda atskaņojot skaņu: " + e.getMessage(), "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
    //3
    public static void pressButtonspeci() {
        try {
            File soundFile = new File("sound" + File.separator + "knopka.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Kļūda atskaņojot skaņu: " + e.getMessage(), "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
    //4
    public static void pressButtonsp() {
        try {
            File soundFile = new File("sound" + File.separator + "pukpuk.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Kļūda atskaņojot skaņu: " + e.getMessage(), "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }
    //5
    public static void pressButtonspecc() {
        try {
            File soundFile = new File("sound" + File.separator + "canc.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.addLineListener(event -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close();
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(null, "Kļūda atskaņojot skaņu: " + e.getMessage(), "Kļūda", JOptionPane.ERROR_MESSAGE);
        }
    }


    private static JButton createButton(String text, Dimension size, ArrayList<norkarte> konti, int izv) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.matches("[0-9]")) {
                	pressButton();
                    if (displayText.length() < (action.equals("PIN") ? 4 : 10)) {
                        displayText.append(text);
                        displayLabel.setText(displayText.toString());
                    }
                } else if (text.equals("Enter")) {
                    result = displayText.toString();
                    frame.dispose();
                    pressButtonspeci();
                } else if (text.equals("Dzēst")) {
                    if (displayText.length() > 0) {
                        displayText.deleteCharAt(displayText.length() - 1);
                        displayLabel.setText(displayText.toString());
                        pressButtonsp();
                    }
                } else if (text.equals("Nespied")) {
                    JOptionPane.showMessageDialog(null, "Es tev teicu, lai nespiež. Malacis, tev vairs nav kartes)", "Bankomāts", JOptionPane.INFORMATION_MESSAGE);
                    konti.remove(izv);
                    result = null;
                    frame.dispose();
                    pressButtonspec();
                } else if (text.equals("Cancel")) {
                    JOptionPane.showMessageDialog(null, "Neaizmirstiet savu karti", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    result = null;
                    displayText.setLength(0);
                    frame.dispose();
                    pressButtonspecc();
                }
            }
        });
        return button;
    }
}