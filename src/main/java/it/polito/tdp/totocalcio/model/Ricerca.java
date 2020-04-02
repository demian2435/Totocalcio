package it.polito.tdp.totocalcio.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	private Pronostico pronostico;
	private int N;
	private List<Risultato> soluzione;

	public List<Risultato> cerca(Pronostico pronostico) {
		this.pronostico = pronostico;
		this.N = pronostico.size();

		List<RisultatoPartita> parziale = new ArrayList<RisultatoPartita>();
		int livello = 0;

		this.soluzione = new ArrayList<Risultato>();
		ricorsiva(parziale, livello);
		return soluzione;
	}

	private void ricorsiva(List<RisultatoPartita> parziale, int livello) {

		// if -> CASO TERMINALE // else -> CASO GENERALE
		if (livello == N) {
			// QUESTA SOLUZIONE PARZIALE E' COMPLETA
			// System.out.println(parziale);
			soluzione.add(new Risultato(parziale));
		} else {
			// AVREMO UNA LISTA PARI AI RISULTATI PRECEDENTI
			// L'ATTUALE LIVELLO DA RISOLVERE
			// ED IL BAGAGLIO DA MANDARE IN RICORSIONE
			// [parziale da 0 a livelo-1] [*livello*] [livello+1 a N]

			PronosticoPartita pp = pronostico.get(livello);
			// pp contiene i sotto-problemi da provare -> 2X al livello 0

			// PER OGNI SEGNO DIAMO I RISULTATI ALTERNATIVI DEL LIVELLO
			// E INVIAMO LE RESTANTI PARTITE ALLA PROSSIMA PARTITA
			for (RisultatoPartita rp : pp.getRisultati()) {
				// COSTRUZIONE SOLUZIONE PARZIALE
				parziale.add(rp);
				// CHIAMATA RICORSIVA
				ricorsiva(parziale, livello + 1);
				// BACKTRACKING
				parziale.remove(parziale.size() - 1);
			}
		}
	}
}

// ESEMPIO FUNZIONE RICORSIVA INTERNAMENTE
//
// LIVELLO = NUMERO DI PARTITE CONSIDERATE
// LA PARTITA DI LIVELLO LA DEVO SCEGLIERE IO
// LE PARTITE DI LIVELLO+1 LE DECIDE LA RICORSIONE
//
// SOLUZIONE PARZIALE: un elenco di RiusltatoPartita di lunghezza pari al livello
// SOLUZIONE TOTALE: ho N risultati
// CONDIZIONE DI TERMINAZIONE: livello == N

// 2x	-	1	-	1x2	-	12		BASE
// 
// [x]	-	[1	-	1x2	-	12]		LIVELLO 0 a
// 			[1] -	[1x2-	12]		LIVELLO 1 a
//					[x] -	[12]	LIVELLO 2 a
//							[1] 	LIVELLO 3 a
//							[2] 	LIVELLO 3 b
//    				[1] -	[12]	LIVELLO 2 b 
//							[1]		LIVELLO 3 a
//							[2] 	LIVELLO 3 b
//   				[2] -	[12]	LIVELLO 2 c
// 							[1] 	LIVELLO 3 a
//							[2] 	LIVELLO 3 b
// [2]	-	[1	-	1x2	-	12]		LIVELLO 0 b
//			[1] -	[1x2-	12]		LIVELLO 1 a
//					[x] -	[12]	LIVELLO 2 a
//							[1] 	LIVELLO 3 a
//							[2] 	LIVELLO 3 b
//					[1] -	[12]	LIVELLO 2 b 
//							[1]		LIVELLO 3 a
//							[2] 	LIVELLO 3 b
//					[2] -	[12]	LIVELLO 2 c
//							[1] 	LIVELLO 3 a
//							[2] 	LIVELLO 3 b
