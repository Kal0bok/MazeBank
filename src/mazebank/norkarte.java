package mazebank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class norkarte implements Comparable<norkarte> {
    private String banka, vards, uzvards, personasKods, 
    smartIdKods, paraksts, kartesNumurs, cvc, pin, kontaNumurs;
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
    
    public norkarte(String banka, String vards, String uzvards, String personasKods, String smartIdKods,
            int derigumaGadi, String paraksts) {
    	this.banka = banka;
    	this.vards = vards;
    	this.uzvards = uzvards;
    	this.personasKods = personasKods;
    	this.smartIdKods = smartIdKods;
    	this.derigumaGadi = derigumaGadi;
    	this.paraksts = paraksts;
    	this.derigumaDatums = izdosanasDatums.plusYears(derigumaGadi);
    	generateRandoms();
    }
    
    private void generateRandoms() {
        Random rand = new Random();
        kartesNumurs = String.format("%04d %04d %04d %04d", rand.nextInt(10000), rand.nextInt(10000),
                rand.nextInt(10000), rand.nextInt(10000));
        cvc = String.format("%03d", rand.nextInt(1000));
        pin = String.format("%04d", rand.nextInt(10000));
        String bankCode = bankuKodi.getOrDefault(banka, "XXXX");
        kontaNumurs = "LV" + String.format("%02d", rand.nextInt(100)) + bankCode +
        		String.format("%013d", rand.nextLong(10000000000000L));
    }
    
    public double noteiktAtlikumu() {
        return atlikums;
    }
    
    public void depozits(double summa) {
        atlikums += summa;
    }
    
    public String getBanka() {
        return banka;
    }
    
    public String izvadit() {
        return "Banka: " + banka +
                "\nVārds: " + vards +
                "\nUzvārds: " + uzvards +
                "\nPersonas kods: " + personasKods +
                "\nSmart ID kods: " + smartIdKods +
                "\nDerīguma termiņš: " + derigumaDatums.format(DateTimeFormatter.ofPattern("MM/yy")) +
                "\nKartes numurs: " + kartesNumurs +
                "\nCVC: " + cvc +
                "\nPIN: " + pin +
                "\nKonta numurs: " + kontaNumurs +
                "\nAtlikums: " + atlikums + " EUR";
    }
    
    public String getPin() {
        return pin;
    }
    
    
    
    
    
    
    
    
	@Override
	public int compareTo(norkarte o) {
		return 0;
	}
}