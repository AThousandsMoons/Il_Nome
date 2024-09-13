package com.mycompany.parser;

import com.mycompany.elements.Command;
import com.mycompany.elements.ObjectProperty;
import com.mycompany.elements.Room;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Classe Parser che si occupa di analizzare i comandi dati dall'utente
 * e restituire le azioni corrispondenti da eseguire in base agli oggetti
 * e alle stanze coinvolte.
 * 
 * @author simone
 */
public class Parser {
    public Queue<ParserOutput> parse(String command, List<Command> commands, List<ObjectProperty> inventory, Room room) {
        // Coda che terrà traccia delle azioni da eseguire in base ai comandi dati
        Queue<ParserOutput> movements = new LinkedList<>();
        
        // Oggetti presenti nella stanza corrente
        List<ObjectProperty> objects = room.getObjects();
        
        // Converte il comando in minuscolo e rimuove gli spazi extra
        String cmd = command.toLowerCase().trim();
        String[] tokens = cmd.split("\\s+"); // Divide il comando in parole singole (token)
        int length = tokens.length;  // Numero di parole nel comando
        int lengthVer = tokens.length - 1;  // Lunghezza usata per verifiche di posizione
        boolean verify = false;  // Flag per verificare se il comando è valido
        int objectType;  // Indica se è stato trovato un oggetto nella stanza
        int inventoryIndex;  // Indica se è stato trovato un oggetto nell'inventario
        
        // Scorre ogni parola (token) nel comando
        for (int i = 0; i < length; i++) {
            objectType = -1;
            inventoryIndex = -1;
            
            // Cerca se il token corrente è un comando valido
            int commandType = searchCommand(tokens[i], commands);
            
            if (commandType > -1) {  // Se viene trovato un comando
                if (lengthVer > -1) {  // Verifica se ci sono parole dopo il comando
                    // Controlla se il comando è di movimento e aggiorna la stanza
                    if (commandType == 0 && room.getNord() != null) {
                        objects = room.getNord().getObjects();
                        room = room.getNord();
                    }
                    if (commandType == 2 && room.getSud() != null) {
                        objects = room.getSud().getObjects();
                        room = room.getSud();
                    }
                    if (commandType == 3 && room.getEst() != null) {
                        objects = room.getEst().getObjects();
                        room = room.getEst();
                    }
                    if (commandType == 4 && room.getOvest() != null) {
                        objects = room.getOvest().getObjects();
                        room = room.getOvest();
                    }
                    
                    // Trova la posizione del prossimo comando nella stringa (se esiste)
                    int posNextCommand = positionNextCommand(tokens, i + 1, commands);
                    if (posNextCommand > -1) {  // Se c'è un altro comando successivo
                        // Scorri i token tra i due comandi e cerca oggetti nella stanza o inventario
                        for (int k = i + 1; k < posNextCommand; k++) {
                            if (objectType < 0) {
                                objectType = searchObject(tokens[k], objects);
                            }
                            if (inventoryIndex < 0) {
                                inventoryIndex = searchObject(tokens[k], inventory);
                            }
                        }
                        i = posNextCommand - 1;  // Salta alla posizione del prossimo comando
                    } else {  // Se non c'è un altro comando
                        // Cerca oggetti nei token rimanenti
                        for (int k = i + 1; k < length; k++) {
                            if (objectType < 0) {
                                objectType = searchObject(tokens[k], objects);
                            }
                            if (inventoryIndex < 0) {
                                inventoryIndex = searchObject(tokens[k], inventory);
                            }
                        }
                        i = length;  // Finisce la scansione
                    }
                    verify = true;
                    
                    // Aggiunge il comando alla coda di movimenti, con gli oggetti coinvolti
                    if (objectType > -1 && inventoryIndex > -1) {  // Se l'utente interagisce sia con un oggetto nella stanza che nell'inventario
                        movements.add(new ParserOutput(commands.get(commandType), objects.get(objectType), inventory.get(inventoryIndex)));
                    } else if (objectType > -1 || inventoryIndex > -1) {  // Se interagisce solo con uno dei due (stanza o inventario)
                        if (objectType > -1) {
                            movements.add(new ParserOutput(commands.get(commandType), objects.get(objectType), null));
                        }
                        if (inventoryIndex > -1) {
                            movements.add(new ParserOutput(commands.get(commandType), null, inventory.get(inventoryIndex)));
                        }
                    } else {  // Se il comando non riguarda alcun oggetto
                        movements.add(new ParserOutput(commands.get(commandType), null, null));
                    }
                } else {  // Se il comando non ha parole successive
                    verify = true;
                    movements.add(new ParserOutput(commands.get(commandType), null, null));
                }
            }
        }
        
        // Se nessun comando è stato riconosciuto, aggiunge un comando nullo alla coda
        if (!verify) {
            movements.add(new ParserOutput(null, null));
        }
        
        return movements;  // Restituisce la coda con le azioni da eseguire
    }

    /**
     * Cerca la posizione del prossimo comando nella stringa di input.
     * @param tokensList La lista dei token (parole) del comando.
     * @param actualPos La posizione corrente da cui partire a cercare.
     * @param commands La lista dei comandi validi.
     * @return La posizione del prossimo comando, o -1 se non viene trovato.
     */
    private int positionNextCommand(String[] tokensList, int actualPos, List<Command> commands) {
        int position = -1;
        int length = tokensList.length;
        // Scorre i token successivi e cerca un comando valido
        for (int k = actualPos; k < length; k++) {
            position = searchCommand(tokensList[k], commands);
            if (position > -1) {
                position = k;
                break;
            }
        }
        return position;
    }

    /**
     * Cerca se un token corrisponde a un comando valido.
     * @param token La parola da cercare.
     * @param commands La lista dei comandi validi.
     * @return L'indice del comando trovato, o -1 se non viene trovato.
     */
    private int searchCommand(String token, List<Command> commands) {
        // Confronta il token con il nome o gli alias di ciascun comando
        for(int i = 0; i < commands.size(); i++) {
            Command cmd = commands.get(i);
            // Verifica se l'alias non è nullo prima di chiamare contains
            if(cmd.getName().equals(token) || (cmd.getAlias() != null && cmd.getAlias().contains(token))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Cerca se un token corrisponde a un oggetto presente in una lista.
     * @param token La parola da cercare.
     * @param objects La lista degli oggetti.
     * @return L'indice dell'oggetto trovato, o -1 se non viene trovato.
     */
    private int searchObject(String token, List<ObjectProperty> objects) {
        // Controlla se il token corrisponde al nome o agli alias di un oggetto
        for (int i = 0; i < objects.size(); i++) {
            ObjectProperty obj = objects.get(i);
            if (obj.getName().equalsIgnoreCase(token) || obj.getAlias().stream().anyMatch(alias -> alias.equalsIgnoreCase(token))) {
                return i;
            }
        }
        return -1;  // Oggetto non trovato
    }
}
