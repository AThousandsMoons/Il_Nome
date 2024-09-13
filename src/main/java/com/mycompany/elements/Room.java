package com.mycompany.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Room rappresenta una stanza nel gioco. 
 * Ogni stanza ha un identificatore univoco, un nome, una descrizione, e può essere collegata 
 * ad altre stanze nelle quattro direzioni (nord, sud, est, ovest). Le stanze possono contenere oggetti 
 * e presentare caratteristiche particolari, come essere una stanza "morte" o avere indovinelli.
 * 
 * @author simone
 */
public class Room {
    // Identificatore univoco della stanza
    private final int id;

    // Nome della stanza
    private String name;

    // Descrizione della stanza
    private String description;

    // Nuova descrizione (potrebbe cambiare nel corso del gioco)
    private String newDescription;

    // Indica le direzioni in cui si trovano le altre stanze
    private String look;

    // Indica se la stanza è visibile al giocatore
    private boolean visible = true;

    // Stanze adiacenti (sud, nord, est, ovest)
    private Room sud = null;
    private Room nord = null;
    private Room est = null;
    private Room ovest = null;

    // Indica se la stanza è una stanza "morte" (dove il gioco termina)
    private boolean deathRoom = false;

    // Indica se la stanza contiene un indovinello
    private boolean indovinello = false;

    // Indica se il primo dialogo nella stanza è stato già mostrato (es. per il bagno)
    private boolean primoDialogo = false;

    // Stringa temporanea per memorizzare alcune informazioni della stanza
    private String memorizzaStringa;

    // Lista di oggetti presenti nella stanza
    private final List<ObjectProperty> objects = new ArrayList<>();

    /**
     * Costruttore che inizializza la stanza con un identificatore.
     * 
     * @param id L'identificatore univoco della stanza.
     */
    public Room(int id) {
        this.id = id;
    }

    /**
     * Costruttore che inizializza la stanza con un identificatore, un nome e una descrizione.
     * 
     * @param id L'identificatore univoco della stanza.
     * @param name Il nome della stanza.
     * @param description La descrizione della stanza.
     */
    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getter e setter per le proprietà della stanza

    /**
     * Restituisce l'identificatore univoco della stanza.
     * 
     * @return L'identificatore della stanza.
     */
    public int getId() {
        return id;
    }

    /**
     * Restituisce il nome della stanza.
     * 
     * @return Il nome della stanza.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome della stanza.
     * 
     * @param name Il nuovo nome della stanza.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce la descrizione della stanza.
     * 
     * @return La descrizione della stanza.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione della stanza.
     * 
     * @param description La nuova descrizione della stanza.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Restituisce la nuova descrizione della stanza.
     * 
     * @return La nuova descrizione della stanza.
     */
    public String getNewDescription() {
        return newDescription;
    }

    /**
     * Imposta la nuova descrizione della stanza.
     * 
     * @param newDescription La nuova descrizione da assegnare alla stanza.
     */
    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    /**
     * Indica se la stanza è visibile al giocatore.
     * 
     * @return true se la stanza è visibile, altrimenti false.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Imposta se la stanza è visibile o meno.
     * 
     * @param visible true per rendere la stanza visibile, altrimenti false.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
    * Restituisce la stanza a sud della stanza corrente.
    * 
    * @return La stanza a sud, o null se non esiste.
    */
    public Room getSud() {
        return sud;
    }

    /**
    * Imposta la stanza a sud della stanza corrente.
    * 
    * @param sud La stanza da impostare a sud.
    */
    public void setSud(Room sud) {
        this.sud = sud;
    }

    /**
    * Restituisce la stanza a nord della stanza corrente.
    * 
    * @return La stanza a nord, o null se non esiste.
    */
    public Room getNord() {
        return nord;
    }

    /**
    * Imposta la stanza a nord della stanza corrente.
    * 
    * @param nord La stanza da impostare a nord.
    */
    public void setNord(Room nord) {
        this.nord = nord;
    }

    /**
    * Restituisce la stanza a est della stanza corrente.
    * 
    * @return La stanza a est, o null se non esiste.
    */
    public Room getEst() {
        return est;
    }

    /**
    * Imposta la stanza a est della stanza corrente.
    * 
    * @param est La stanza da impostare a est.
    */
    public void setEst(Room est) {
        this.est = est;
    }

    /**
    * Restituisce la stanza a ovest della stanza corrente.
    * 
    * @return La stanza a ovest, o null se non esiste.
    */
    public Room getOvest() {
        return ovest;
    }

    /**
    * Imposta la stanza a ovest della stanza corrente.
    * 
    * @param ovest La stanza da impostare a ovest.
    */
    public void setOvest(Room ovest) {
        this.ovest = ovest;
    }   

    /**
    * Restituisce le direzioni in cui è possibile proseguire nelle stanze
    * 
    * @return La descrizione visiva della stanza.
    */
    public String getLook() {
        return look;
    }

    /**
    * Imposta le direzioni in cui è possibile proseguire nelle stanze
    * 
    * @param look La nuova descrizione visiva da assegnare alla stanza.
    */
    public void setLook(String look) {
        this.look = look;
    }

    /**
    * Verifica se la stanza è una "stanza morte", dove il giocatore potrebbe morire o fallire.
    * 
    * @return true se la stanza è una stanza morte, altrimenti false.
    */
    public boolean isDeathRoom() {
        return deathRoom;
    }

    /**
    * Imposta se la stanza è una "stanza morte" o meno.
    * 
    * @param deathRoom true per impostare la stanza come stanza morte, altrimenti false.
    */ 
    public void setDeathRoom(boolean deathRoom) {
        this.deathRoom = deathRoom;
    }

    /**
    * Imposta il valore del campo booleano 'indovinello'.
    * 
    * @param indovinello il valore booleano da assegnare al campo 'indovinello'.
    */
    public void setIndovinello(boolean indovinello) {
        this.indovinello = indovinello;
    }

    /**
    * Restituisce il valore corrente del campo booleano 'indovinello'.
    * 
    * @return il valore booleano del campo 'indovinello'.
    */
    public boolean getIndovinello() {
        return indovinello;
    }

    /**
    * Imposta il valore del campo booleano 'primoDialogo'.
    * 
    * @param dialogo il valore booleano da assegnare al campo 'primoDialogo'.
    */
    public void setPrimoDialogo(boolean dialogo) {
        this.primoDialogo = dialogo;
    }

    /**
    * Restituisce il valore corrente del campo booleano 'primoDialogo'.
    * 
    * @return il valore booleano del campo 'primoDialogo'.
    */
    public boolean getPrimoDialogo() {
        return primoDialogo;
    }

    /**
    * Imposta la descrizione memorizzata nel campo 'memorizzaStringa'.
    * 
    * @param descrizione la stringa da assegnare al campo 'memorizzaStringa'.
    */
    public void setMemorizzaDescrizione(String descrizione) {
        this.memorizzaStringa = descrizione;
    }

    /**
    * Restituisce la descrizione memorizzata nel campo 'memorizzaStringa'.
    * 
    * @return la stringa memorizzata nel campo 'memorizzaStringa'.
    */
    public String getMemorizzaDescrizione() {
        return memorizzaStringa;
    }

    /**
    * Restituisce la lista degli oggetti di tipo 'ObjectProperty' associati a questa istanza.
    * 
    * @return una lista di oggetti 'ObjectProperty'.
    */
    public List<ObjectProperty> getObjects() {
        return objects;
    }

    /**
    * Override del metodo hashCode per fornire un codice hash unico per ogni istanza di 'Room'.
    * Utilizza il campo 'id' per calcolare il codice hash.
    * 
    * @return un intero che rappresenta il codice hash dell'istanza.
    */
    @Override
    public int hashCode() {
        int hash = 5; // Valore iniziale del codice hash
        hash = 89 * hash + this.id; // Calcolo del codice hash basato sul campo 'id'
        return hash;
    }

    /**
    * Override del metodo equals per confrontare l'uguaglianza tra due oggetti 'Room'.
    * 
    * @param obj l'oggetto da confrontare con l'istanza corrente.
    * @return true se l'oggetto è uguale all'istanza corrente, false altrimenti.
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Gli oggetti sono uguali se sono lo stesso riferimento
        }
        if (obj == null) {
            return false; // Gli oggetti non sono uguali se l'altro oggetto è nullo
        }
        if (getClass() != obj.getClass()) {
            return false; // Gli oggetti non sono uguali se non appartengono alla stessa classe
        }
        final Room other = (Room) obj; // Cast dell'oggetto a 'Room'
        if(this.id != other.id) {
            return false; // Gli oggetti non sono uguali se gli ID sono diversi
        }
        return true; // Gli oggetti sono uguali se tutti i test precedenti sono passati
    }
}
