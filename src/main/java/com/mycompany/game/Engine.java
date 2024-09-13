package com.mycompany.game;

import com.mycompany.adventure.Adventure;
import com.mycompany.elements.CommandType;
import com.mycompany.parser.Parser;
import com.mycompany.parser.ParserOutput;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;

/**
 * La classe Engine è responsabile dell'esecuzione del gioco, inclusa l'inizializzazione, 
 * la gestione degli input dell'utente e la gestione del tempo di gioco.
 * 
 * @author simone
 */
public class Engine {

    private final GameBasics game; // Oggetto che gestisce le basi del gioco
    private final Parser parser;    // Oggetto che gestisce l'analisi dei comandi dell'utente

    /**
     * Costruisce un'istanza di Engine con il gioco specificato e inizializza il gioco.
     * 
     * @param game l'oggetto GameBasics che gestisce il gioco.
     */
    public Engine(GameBasics game) {
        this.game = game;
        try {
            this.game.init(); // Inizializzazione del gioco
        } catch (Exception ex) {
            System.err.println(ex); // Stampa dell'errore in caso di eccezione durante l'inizializzazione
        }
        parser = new Parser(); // Inizializzazione del parser
    }

    /**
     * Restituisce l'oggetto GameBasics associato a questo Engine.
     * 
     * @return l'oggetto GameBasics.
     */
    public GameBasics getGame() {
        return game;
    }

    /**
     * Restituisce l'oggetto Parser associato a questo Engine.
     * 
     * @return l'oggetto Parser.
     */
    public Parser getParser() {
        return parser;
    }

    /**
     * Avvia il ciclo principale del gioco, gestendo l'input dell'utente e le azioni di gioco.
     * Gestisce anche il tempo di gioco e le condizioni di termine.
     */
    public void run() {
        if (game.isTimerOn()) {
            game.getCronometro().avanza(); // Avanza il cronometro se il timer è attivo
        }
        System.out.println(game.getCurrentRoom().getName()); // Stampa il nome della stanza corrente
        System.out.println("> " + game.getCurrentRoom().getDescription()); // Stampa la descrizione della stanza corrente
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        Scanner scanner = new Scanner(System.in); // Scanner per leggere l'input dell'utente
        while (scanner.hasNextLine()) {
            if (game.isTimerOn() && game.getCronometro().leggiTempo() > game.getTimer()) {
                System.out.println("Tempo esaurito!"); // Messaggio quando il tempo è scaduto
                break;
            } else if (game.isTimerOn()) {
                System.out.println("[Ti rimangono " + ((game.getTimer() - game.getCronometro().leggiTempo()) / 1000) + " secondi]"); // Tempo rimanente
            }

            String command = scanner.nextLine(); // Legge il comando dell'utente
            listParser: {
                Queue<ParserOutput> movements = parser.parse(command, game.getCommands(), game.getInventory(), game.getCurrentRoom());
                for (Iterator<ParserOutput> iter = movements.iterator(); iter.hasNext();) {
                    ParserOutput p = iter.next();
                    try {
                        if (p.getCommand() != null && p.getCommand().getType() == CommandType.FINE) {
                            System.out.println("Hai abbandonato il gioco!"); // Messaggio quando l'utente abbandona il gioco
                            System.exit(0); // Termina il programma
                            break;
                        } else {
                            game.nextMove(p, System.out); // Esegue la mossa successiva
                            iter.remove(); // Rimuove l'elemento dalla coda
                            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        }
                    } catch (Exception e) {
                        System.out.println("Restart da: " + game.getCurrentRoom().getName()); // Messaggio di errore e ripristino della stanza corrente
                        System.out.println("> " + game.getCurrentRoom().getDescription());
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        break listParser; // Esce dal ciclo di parsing in caso di eccezione
                    }
                }
            }
        }
    }

    /**
     * Metodo principale per avviare l'applicazione di gioco.
     * Crea un'istanza di Engine e avvia il ciclo di gioco.
     * 
     * @param args argomenti della riga di comando (non utilizzati in questo caso).
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new Adventure()); // Crea un'istanza di Engine con un oggetto Adventure
        engine.run(); // Avvia il ciclo di gioco
    }
}
