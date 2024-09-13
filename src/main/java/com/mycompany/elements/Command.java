package com.mycompany.elements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * La classe Command rappresenta un comando eseguibile nel gioco.
 * Ogni comando ha un tipo (definito dall'enum CommandType), un nome, e può avere una serie di alias.
 * I comandi possono essere utilizzati per interagire con stanze, oggetti e altre entità di gioco.
 * 
 * @author simone
 */
public class Command {
    // Tipo del comando, definito dall'enum CommandType
    private final CommandType type;

    // Nome del comando
    private final String name;

    // Alias associati al comando, se presenti (alias alternativi per il comando)
    private Set<String> alias;

    /**
     * Costruttore che inizializza un comando con un tipo e un nome.
     * 
     * @param type Il tipo del comando (ad es. NORD, SUD, INVENTARIO, ecc.).
     * @param name Il nome del comando.
     */
    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Costruttore che inizializza un comando con un tipo, un nome e un insieme di alias.
     * 
     * @param type Il tipo del comando.
     * @param name Il nome del comando.
     * @param alias Gli alias associati al comando.
     */
    public Command(CommandType type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    /**
     * Restituisce il nome del comando.
     * 
     * @return Il nome del comando.
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce l'insieme degli alias del comando.
     * 
     * @return Gli alias del comando.
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta gli alias per il comando.
     * 
     * @param alias L'insieme di alias da assegnare al comando.
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     * Imposta gli alias per il comando utilizzando un array di stringhe.
     * 
     * @param alias Un array di alias da assegnare al comando.
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * Restituisce il tipo del comando.
     * 
     * @return Il tipo del comando.
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Metodo hashCode per calcolare il codice hash del comando.
     * 
     * @return Il codice hash basato sul tipo del comando.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.type);
        return hash;
    }

    /**
     * Confronta due oggetti Command per verificarne l'uguaglianza.
     * 
     * @param obj L'oggetto da confrontare.
     * @return true se i due oggetti sono uguali, altrimenti false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Command other = (Command) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
}

