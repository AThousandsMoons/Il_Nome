package com.mycompany.game;

import com.mycompany.elements.Command;
import com.mycompany.elements.Inventory;
import com.mycompany.elements.ObjectProperty;
import com.mycompany.elements.Room;
import com.mycompany.elements.Timer;
import com.mycompany.parser.ParserOutput;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe astratta che fornisce la struttura di base per un gioco, gestendo stanze,
 * comandi, inventario e un timer opzionale.
 * Questa classe è progettata per essere estesa.
 * 
 * @author simone
 */
public abstract class GameBasics {

    // Lista delle stanze del gioco
    private final List<Room> stanze = new ArrayList<>();

    // Lista dei comandi disponibili nel gioco
    private final List<Command> commandi = new ArrayList<>();

    // Oggetto che gestisce l'inventario del giocatore
    private final Inventory inventario = new Inventory();

    // Cronometro utilizzato per gestire il tempo di gioco
    private final Timer cronometro = new Timer();

    // La stanza corrente in cui si trova il giocatore
    private Room currentRoom;

    // Valore del timer che viene aggiornato durante il gioco
    private int timer;

    // Flag che indica se il timer è attivo o meno
    private boolean timerOn = false;

    // Costruttore di default
    public GameBasics() {}

    /**
     * Restituisce lo stato del timer (attivo o disattivo).
     * 
     * @return true se il timer è attivo, altrimenti false.
     */
    public boolean isTimerOn() {
        return this.timerOn;
    }

    /**
     * Imposta lo stato del timer.
     * 
     * @param timerOn true per attivare il timer, false per disattivarlo.
     */
    public void setTimerOn(boolean timerOn) {
        this.timerOn = timerOn;
    }

    /**
     * Restituisce il cronometro del gioco.
     * 
     * @return L'oggetto Timer.
     */
    public Timer getCronometro() {
        return this.cronometro;
    }

    /**
     * Restituisce il valore corrente del timer.
     * 
     * @return Il valore del timer.
     */
    public int getTimer() {
        return this.timer;
    }

    /**
     * Imposta il valore del timer e lo attiva.
     * 
     * @param timer Il nuovo valore del timer.
     */
    public void setTimer(int timer) {
        this.timerOn = true;
        this.timer = timer;
    }

    /**
     * Avanza il cronometro di una unità di tempo.
     */
    public void startTimer() {
        this.cronometro.avanza();
    }

    /**
     * Restituisce la lista di stanze del gioco.
     * 
     * @return La lista di stanze.
     */
    public List<Room> getRooms() {
        return this.stanze;
    }

    /**
     * Restituisce la lista dei comandi disponibili nel gioco.
     * 
     * @return La lista di comandi.
     */
    public List<Command> getCommands() {
        return this.commandi;
    }

    /**
     * Restituisce la stanza corrente in cui si trova il giocatore.
     * 
     * @return La stanza corrente.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Imposta la stanza corrente in cui si trova il giocatore.
     * 
     * @param currentRoom La stanza da impostare come corrente.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Restituisce la lista degli oggetti presenti nell'inventario del giocatore.
     * 
     * @return La lista degli oggetti dell'inventario.
     */
    public List<ObjectProperty> getInventory() {
        return this.inventario.getList();
    }

    /**
     * Metodo astratto.
     * Viene utilizzato per inizializzare il gioco.
     *
     * @throws Exception Eccezione generica durante l'inizializzazione.
     */
    public abstract void init() throws Exception;

    /**
     * Metodo astratto.
     * Gestisce la logica per eseguire la prossima mossa in base all'output del parser.
     * 
     * @param var1 L'output del parser che contiene il comando e gli oggetti associati.
     * @param var2 Il flusso di output su cui stampare i risultati dell'azione.
     */
    public abstract void nextMove(ParserOutput var1, PrintStream var2);
}