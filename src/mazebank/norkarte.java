package mazebank;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class norkarte implements Comparable<norkarte> {
    private String banka, vards, uzvards, personasKods, smartIdKods, paraksts, kartesNumurs, cvc, pin, kontaNumurs;
    private int derigumaGadi;
    private double atlikums = 0.0;
    private LocalDate izdosanasDatums = LocalDate.now();
    private LocalDate derigumaDatums;
    private static Map<String, String> bankuKodi = new HashMap<>();

    static {
        bankuKodi.put("Swedbank", "HABA");
        bankuKodi.put("SEB", "UNLA");
        bankuKodi.put("Citadele", "PARX");
    }

	@Override
	public int compareTo(norkarte o) {
		return 0;
	}
}