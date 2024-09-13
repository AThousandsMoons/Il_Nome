package com.mycompany.elements;

/**
 * L'enum CommandType definisce i diversi tipi di comandi disponibili nel gioco.
 * Ogni valore rappresenta un'azione che il giocatore può eseguire, come spostarsi
 * in una direzione, interagire con oggetti o terminare il gioco.
 * 
 * @author simone
 */
public enum CommandType {
    // Termina il gioco
    FINE, 
    
    // Mostra l'inventario del giocatore
    INVENTARIO, 
    
    // Muove il giocatore verso nord
    NORD, 
    
    // Muove il giocatore verso sud
    SUD, 
    
    // Muove il giocatore verso est
    EST, 
    
    // Muove il giocatore verso ovest
    OVEST, 
    
    // Esegue un'azione per premere un oggetto, come un pulsante
    PREMI, 
    
    // Permette di raccogliere un oggetto
    RACCOGLI, 
    
    // Usa un oggetto nell'inventario o nella stanza
    USA, 
    
    // Osserva o guarda una stanza 
    GUARDA, 
    
    // Accende un oggetto, ad esempio una torcia
    ACCENDI;
}

