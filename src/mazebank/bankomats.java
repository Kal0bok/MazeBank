package mazebank;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class bankomats {

	private static final String[] bankas = {"Swedbank", "SEB", "Citadele"};

    public static void apkalpot(ArrayList<Object> konti) {
        if (konti.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav neviena konta", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }
	
}
}