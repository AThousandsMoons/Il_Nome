package com.mycompany.parser;

import com.mycompany.elements.Command;
import com.mycompany.elements.ObjectProperty;

/**
 * Classe ParserOutput rappresenta il risultato dell'interpretazione di un comando dell'utente.
 * Contiene il comando eseguito e gli oggetti coinvolti.
 * nella stanza e/o nell'inventario.
 * 
 * @author simone
 */
public class ParserOutput {

    // Comando che l'utente ha inserito.
    private Command command;

    // Oggetto della stanza su cui viene eseguito il comando.
    private ObjectProperty object;

    // Oggetto dell'inventario su cui viene eseguito il comando. 
    private ObjectProperty invObject;

    /**
     * Costruttore che inizializza la classe con un comando e un oggetto della stanza.
     *
     * @param command Il comando eseguito.
     * @param object L'oggetto della stanza coinvolto nel comando.
     */
    public ParserOutput(Command command, ObjectProperty object) {
        this.command = command;
        this.object = object;
    }

    /**
     * Costruttore che inizializza la classe con un comando,
     * un oggetto della stanza e un oggetto dell'inventario.
     *
     * @param command Il comando eseguito.
     * @param object L'oggetto della stanza coinvolto.
     * @param invObject L'oggetto dell'inventario coinvolto.
     */
    public ParserOutput(Command command, ObjectProperty object, ObjectProperty invObject) {
        this.command = command;
        this.object = object;
        this.invObject = invObject;
    }

    /**
     * Restituisce il comando eseguito dall'utente.
     * 
     * @return Il comando eseguito.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Imposta il comando eseguito dall'utente.
     * 
     * @param command Il comando da assegnare.
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Restituisce l'oggetto della stanza su cui viene eseguito il comando.
     * 
     * @return L'oggetto della stanza coinvolto.
     */
    public ObjectProperty getObject() {
        return object;
    }

    /**
     * Imposta l'oggetto della stanza su cui viene eseguito il comando.
     *
     * @param object L'oggetto da assegnare.
     */
    public void setObject(ObjectProperty object) {
        this.object = object;
    }

    /**
     * Restituisce l'oggetto dell'inventario su cui viene eseguito il comando.
     * 
     * @return L'oggetto dell'inventario coinvolto.
     */
    public ObjectProperty getInvObject() {
        return invObject;
    }

    /**
     * Imposta l'oggetto dell'inventario su cui viene eseguito il comando.
     * 
     * @param invObject L'oggetto dell'inventario da assegnare.
     */
    public void setInvObject(ObjectProperty invObject) {
        this.invObject = invObject;
    }
}