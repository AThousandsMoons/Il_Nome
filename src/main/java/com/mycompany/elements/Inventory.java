package com.mycompany.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che rappresenta l'inventario del giocatore. 
 * Contiene una lista di oggetti che il giocatore può raccogliere e utilizzare.
 * 
 * @author simone
 */
public class Inventory {
    // Lista degli oggetti presenti nell'inventario
    private List<ObjectProperty> list = new ArrayList<>();
    
    /**
     * Restituisce la lista di oggetti presenti nell'inventario.
     * 
     * @return La lista di oggetti dell'inventario.
     */
    public List<ObjectProperty> getList() {
        return list;
    }
    
    /**
     * Imposta la lista di oggetti nell'inventario.
     * 
     * @param list La nuova lista di oggetti da assegnare all'inventario.
     */
    public void setList(List<ObjectProperty> list) {
        this.list = list;
    }
    
    /**
     * Aggiunge un oggetto all'inventario del giocatore.
     * 
     * @param o L'oggetto da aggiungere.
     */
    public void add(ObjectProperty o) {
        list.add(o);
    }
    
    /**
     * Rimuove un oggetto dall'inventario del giocatore.
     * 
     * @param o L'oggetto da rimuovere.
     */
    public void remove(ObjectProperty o) {
        list.remove(o);
    }
}
