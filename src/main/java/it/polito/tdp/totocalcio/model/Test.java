package it.polito.tdp.totocalcio.model;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		// DATO UN PRONOSTICO TROVIAMO TUTTI I RISULTATI
		// ["2X", "1", "1X2", "12"]
		
		Pronostico pronostico = new Pronostico();
		pronostico.add(new PronosticoPartita("2X"));
		pronostico.add(new PronosticoPartita("1"));
		pronostico.add(new PronosticoPartita("1X2"));
		pronostico.add(new PronosticoPartita("12"));

		System.out.println(pronostico);
		
		Ricerca r = new Ricerca();
		
		List<Risultato> risultati = r.cerca(pronostico);
		
		System.out.println(risultati);
	}

}
