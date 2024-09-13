package com.mycompany.adventure;

import com.mycompany.elements.Command;
import com.mycompany.elements.CommandType;
import com.mycompany.elements.ObjectProperty;
import com.mycompany.elements.Room;
import com.mycompany.game.GameBasics;
import com.mycompany.parser.ParserOutput;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe Adventure estende GameBasics e rappresenta un'implementazione concreta del gioco.
 * 
 * Questa classe può includere la logica specifica per un'avventura, come la gestione delle stanze,
 * degli oggetti e delle azioni del giocatore.
 * 
 * @author simone
 */
public class Adventure extends GameBasics {
    private Dialoghi dialoghi = new Dialoghi();

    @Override
    public void init() throws Exception {
        dialoghi.startGame();
        //420000 -> 7 minuti
        setTimer(420000);
        tryConnection();
        var url = "jdbc:h2:./resources/db/IlNome";
        Connection conn = DriverManager.getConnection(url, "sa", "");
        Room atrio;
        // Comandi
        try(Statement stm = conn.createStatement()) {
            try(ResultSet rs1 = stm.executeQuery("SELECT * FROM comandi;")) {
                rs1.next();
                Command nord = new Command(CommandType.NORD, rs1.getString("nome"));
                nord.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(nord);
                rs1.next();
                Command sud = new Command(CommandType.SUD, rs1.getString("nome"));
                sud.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(sud);
                rs1.next();
                Command est = new Command(CommandType.EST, rs1.getString("nome"));
                est.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(est);
                rs1.next();
                Command ovest = new Command(CommandType.OVEST, rs1.getString("nome"));
                ovest.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(ovest);
                rs1.next();
                Command inventario = new Command(CommandType.INVENTARIO, rs1.getString("nome"));
                inventario.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(inventario);
                rs1.next();
                Command guarda = new Command(CommandType.GUARDA, rs1.getString("nome"));
                guarda.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(guarda);
                rs1.next();
                Command raccogli = new Command(CommandType.RACCOGLI, rs1.getString("nome"));
                raccogli.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(raccogli);
                rs1.next();
                Command usa = new Command(CommandType.USA, rs1.getString("nome"));
                usa.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(usa);
                rs1.next();
                Command accendi = new Command(CommandType.ACCENDI, rs1.getString("nome"));
                accendi.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(accendi);
                rs1.next();
                Command premi = new Command(CommandType.PREMI, rs1.getString("nome"));
                premi.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(premi);
                rs1.next();
                Command fine = new Command(CommandType.FINE, rs1.getString("nome"));
                fine.setAlias(tokenAlias(rs1.getString("alias")));
                getCommands().add(fine);
                rs1.close();
            }
            // Mappa
            Room ingresso;
            Room balcone;
            Room biblioteca;
            Room cameraDaLetto;
            Room cortile;
            Room bagno;
            // Stanze
            try(ResultSet rs2 = stm.executeQuery("SELECT * FROM stanze;")) {
                rs2.next();
                atrio = new Room(rs2.getInt("ID"), rs2.getString("nome"), rs2.getString("descrizione"));
                atrio.setLook(rs2.getString("setlook"));
                rs2.next();
                ingresso = new Room(rs2.getInt("ID"), rs2.getString("nome"), rs2.getString("descrizione"));
                ingresso.setLook(rs2.getString("setlook"));
                ingresso.setVisible(true);
                // Potrei cancellare la proprieta deathroom
                ingresso.setDeathRoom(true);;
                rs2.next();
                balcone = new Room(rs2.getInt("ID"), rs2.getString("nome"), rs2.getString("descrizione"));
                balcone.setLook(rs2.getString("setlook"));
                balcone.setVisible(false);
                rs2.next();
                biblioteca = new Room(rs2.getInt("ID"), rs2.getString("nome"), rs2.getString("descrizione"));
                biblioteca.setNewDescription(rs2.getString("hideDescription"));
                biblioteca.setDescription(rs2.getString("descrizione"));
                biblioteca.setLook(rs2.getString("setlook"));
                biblioteca.setVisible(false);
                rs2.next();
                cameraDaLetto = new Room(rs2.getInt("ID"), rs2.getString("nome"), rs2.getString("descrizione"));
                cameraDaLetto.setLook(rs2.getString("setlook"));
                cameraDaLetto.setVisible(false);
                rs2.next();
                cortile = new Room(rs2.getInt("ID"), rs2.getString("nome"), rs2.getString("descrizione"));
                cortile.setLook(rs2.getString("setlook"));
                rs2.next();
                bagno = new Room(rs2.getInt("ID"), rs2.getString("nome"), rs2.getString("descrizione"));
                bagno.setLook(rs2.getString("setlook"));
                rs2.close();
            }
            atrio.setNord(balcone);
            atrio.setEst(cortile);
            atrio.setSud(ingresso);
            atrio.setOvest(biblioteca);
            cortile.setOvest(atrio);
            balcone.setSud(atrio);
            biblioteca.setEst(atrio);
            biblioteca.setOvest(cameraDaLetto);
            cameraDaLetto.setEst(biblioteca);
            cameraDaLetto.setNord(bagno);
            bagno.setSud(cameraDaLetto);
            
            // Oggetti
            try(ResultSet rs3 = stm.executeQuery("SELECT * FROM oggetti;")) {
                // codice 1: martello
                rs3.next();
                ObjectProperty martello = new ObjectProperty(rs3.getInt("ID"), rs3.getString("nome"), rs3.getString("descrizione"));
                martello.setAlias(tokenAlias(rs3.getString("alias")));
                atrio.getObjects().add(martello);
                martello.setPickupable(true);
                martello.setUsable(false);
                // codice 2: ingranaggio per l'apertura del balcone
                rs3.next();
                ObjectProperty ingranaggio = new ObjectProperty(rs3.getInt("ID"), rs3.getString("nome"), rs3.getString("descrizione"));
                ingranaggio.setAlias(tokenAlias(rs3.getString("alias")));
                biblioteca.getObjects().add(ingranaggio);
                ingranaggio.setPickupable(true);
                ingranaggio.setUsable(false);
                // codice 3: chiave della camera da letto
                rs3.next();
                ObjectProperty chiaveCameraDaLetto = new ObjectProperty(rs3.getInt("ID"), rs3.getString("nome"), rs3.getString("descrizione"));
                chiaveCameraDaLetto.setAlias(tokenAlias(rs3.getString("alias")));
                balcone.getObjects().add(chiaveCameraDaLetto);
                chiaveCameraDaLetto.setPickupable(true);
                chiaveCameraDaLetto.setUsable(false);
                // codice 4: torcia
                rs3.next();
                ObjectProperty torcia = new ObjectProperty(rs3.getInt("ID"), rs3.getString("nome"), rs3.getString("descrizione"));
                torcia.setAlias(tokenAlias(rs3.getString("alias")));
                cortile.getObjects().add(torcia);
                torcia.setPickupable(true);
                torcia.setIgnitable(false);
                torcia.setUsable(false);
                // codice 5: pulsante
                rs3.next();
                ObjectProperty pulsante = new ObjectProperty(rs3.getInt("ID"), rs3.getString("nome"), rs3.getString("descrizione"));
                pulsante.setAlias(tokenAlias(rs3.getString("alias")));
                cameraDaLetto.getObjects().add(pulsante);
                pulsante.setPushable(true);
                // codice 6: letto
                rs3.next();
                ObjectProperty letto = new ObjectProperty(rs3.getInt("ID"), rs3.getString("nome"), rs3.getString("descrizione"));
                letto.setAlias(tokenAlias(rs3.getString("alias")));
                cameraDaLetto.getObjects().add(letto);
                letto.setUsable(true);
            }
            stm.close();
        }
        // Stanza iniziale
        setCurrentRoom(atrio); 
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if(p.getCommand() == null) {
            System.out.println("Non ho capito cosa devo fare! prova con un altro comando.");
        } else {
            Room currentRoom = getCurrentRoom();
            if(p.getCommand().getType() != null) {
                // Controllo del tipo di comando ricevuto
                switch(p.getCommand().getType()) {
                    case NORD:
                        // Se esiste una stanza a nord della stanza attuale
                        if(currentRoom.getNord() != null && currentRoom.getNord().isVisible() == true) {
                            // se sei nella camera da letto e vuoi accedere al bagno
                            if(currentRoom.getName().equals("Camera da letto") == true) {
                                // se l'evento in bagno non è ancora iniziato
                                if(currentRoom.getNord().getPrimoDialogo() == false){
                                    try {
                                        dialoghi.disturbaBowser();
                                        currentRoom.getNord().setPrimoDialogo(true);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                // se l'evento in bagno è terminato con la rivelazione dell'indovinello    
                                } else if(currentRoom.getNord().getPrimoDialogo() == true && currentRoom.getNord().getIndovinello() == true) {
                                    try {
                                        dialoghi.continuaDisturbaBowser();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                // se l'evento in bagno non è terminato
                                } else if(currentRoom.getNord().getPrimoDialogo() == true && currentRoom.getNord().getIndovinello() == false) {
                                    try {
                                        dialoghi.bowserInfuriato();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } 
                            } else {
                                setCurrentRoom(currentRoom.getNord());
                                System.out.println("Ti sei spostato.");
                                System.out.println(getCurrentRoom().getName());
                            }
                        } else {
                            System.out.println("Non puoi andare a nord.");
                        }
                    break;
                
                    case SUD:
                        // Se esiste una stanza a sud della stanza attuale
                        if(currentRoom.getSud() != null && currentRoom.getSud().isVisible() == true) {
                            // Controllo utilizzato per verificare se è possibile accedere al finale
                            if(currentRoom.getName().equals("Atrio") == true &&
                                    currentRoom.getIndovinello() == true &&
                                    currentRoom.getEst().getIndovinello() == true &&
                                    currentRoom.getNord().getIndovinello() == true &&
                                    currentRoom.getOvest().getIndovinello() == true &&
                                    currentRoom.getOvest().getOvest().getIndovinello() == true &&
                                    currentRoom.getOvest().getOvest().getNord().getIndovinello() == true) {
                                setCurrentRoom(currentRoom.getSud());
                                System.out.println("Ti sei spostato.");
                                System.out.println(getCurrentRoom().getName());
                                try {
                                    // Finale
                                    dialoghi.recapIndovinelli();
                                    ending();
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if(currentRoom.getName().equals("Atrio") == false){
                                setCurrentRoom(currentRoom.getSud());
                                System.out.println("Ti sei spostato.");
                                System.out.println(getCurrentRoom().getName());
                            } else {
                                System.out.println("???: Non e il momento di tornare dal fantasma.");
                            }
                        } else {
                            System.out.println("Non puoi andare a sud.");
                        }
                    break;
                
                    case EST:
                        // variabile utilizzata per controllare se la torcia risulta accesa
                        ObjectProperty torciaEst = getInventory().stream().filter(obj->obj.getName().equals("Torcia")).findFirst().orElse(null);
                
                        // Se esiste una stanza ad est della stanza attuale
                        if(currentRoom.getEst() != null && currentRoom.getEst().isVisible() == true) {
                            setCurrentRoom(currentRoom.getEst());
                            System.out.println("Ti sei spostato.");
                            System.out.println(getCurrentRoom().getName());
                            // Controllo torcia accesa
                            if(torciaEst != null) {
                                torciaEst.setIgnitable(false);
                            }
                        } else {
                            System.out.println("Non puoi andare ad est.");
                        }
                    break;
                
                    case OVEST:
                        // variabile utilizzata per controllare se la torcia risulta accesa
                        ObjectProperty torciaOvest = getInventory().stream().filter(obj->obj.getName().equals("Torcia")).findFirst().orElse(null);
              
                        // Se esiste una stanza ad ovest della stanza attuale
                        if(currentRoom.getOvest() != null && currentRoom.getOvest().isVisible() == true) {
                            setCurrentRoom(currentRoom.getOvest());
                            System.out.println("Ti sei spostato.");
                            System.out.println(getCurrentRoom().getName());
                            // Controllo torcia accesa
                            if(torciaOvest != null) {
                                torciaOvest.setIgnitable(false);
                            }
                        } else if (currentRoom.getOvest().isVisible() == false && torciaOvest.isIgnitable() == true) {
                            System.out.println("Non puoi andare ad ovest.");
                        } else {
                            System.out.println("Non puoi andare ad ovest."); 
                        }
                    break;
                
                    case GUARDA:
                        // variabile utilizzata per controllare se la torcia risulta accesa
                        ObjectProperty torcia = getInventory().stream().filter(obj->obj.getName().equals("Torcia")).findFirst().orElse(null);
                
                        // stampa una diversa descrizione della biblioteca quando la torcia è accesa o spenta
                        if(torcia != null && torcia.isIgnitable()) {
                            if(currentRoom.getName().equals("Biblioteca")) {
                                System.out.println(currentRoom.getNewDescription());
                                System.out.println(currentRoom.getLook());
                            }
                        } else {
                            // Stampa la descrizione della stanza attuale
                            System.out.println(currentRoom.getDescription());
                            System.out.println(currentRoom.getLook());
                        }
                    break;
                
                    case INVENTARIO:
                        // Mostra gli oggetti nell'inventario
                        if(getInventory().isEmpty()) {
                            System.out.println("Il tuo inventario è vuoto.");
                        } else {
                            System.out.println("Nel tuo inventario hai:");
                            for(ObjectProperty obj : getInventory()) {
                                System.out.println("- " + obj.getName());
                            }
                        }
                    break;
                
                    case RACCOGLI:
                        // variabile utilizzata per controllare se la torcia risulta accesa
                        ObjectProperty torciaRaccogli = getInventory().stream().filter(obj->obj.getName().equals("Torcia")).findFirst().orElse(null);
                        // Se il comando è del tipo "Raccogli torcia"
                        if(p.getObject() != null && p.getObject().isPickupable()) {
                            ObjectProperty object = p.getObject();
                            // Verifica se l'oggetto è già nell'inventario
                            if (getInventory().contains(object)) {
                                System.out.println("Non ci sono oggetti da raccogliere.");
                            } else if (object.isPickupable()) {
                                // Raccolta del martello nell'atrio
                                if (currentRoom.getName().equals("Atrio")) {
                                    try {
                                        // Rimuovi l'oggetto dalla stanza e aggiungilo all'inventario
                                        getInventory().add(object);
                                        currentRoom.getObjects().remove(object);
                                        object.setUsable(true);
                                        System.out.println("Hai raccolto: " + object.getName());
                                        dialoghi.indovinelloP();
                                        currentRoom.setIndovinello(true);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                // Raccolta dell'ingranaggio solo con la torcia accesa
                                } else if (currentRoom.getName().equals("Biblioteca") && torciaRaccogli.isIgnitable() == true) {
                                    try {
                                        // Rimuovi l'oggetto dalla stanza e aggiungilo all'inventario
                                        getInventory().add(object);
                                        currentRoom.getObjects().remove(object);
                                        object.setUsable(true);
                                        System.out.println("Hai raccolto: " + object.getName());
                                        dialoghi.indovinelloL();
                                        currentRoom.setIndovinello(true);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                // Raccolta chiave della camera da letto    
                                } else if (currentRoom.getName().equals("Balcone")) {
                                    try {
                                        // Rimuovi l'oggetto dalla stanza e aggiungilo all'inventario
                                        getInventory().add(object);
                                        currentRoom.getObjects().remove(object);
                                        object.setUsable(true);
                                        dialoghi.indovinelloA();
                                        System.out.println("Hai raccolto: " + object.getName());
                                        currentRoom.setIndovinello(true);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                // Raccolta della torcia in cortile
                                } else if (currentRoom.getName().equals("Cortile")){
                                    // Rimuovi l'oggetto dalla stanza e aggiungilo all'inventario
                                    getInventory().add(object);
                                    currentRoom.getObjects().remove(object);
                                    object.setUsable(true);
                                    System.out.println("Hai raccolto: " + object.getName());
                                } else {
                                    System.out.println("Non puoi raccogliere questo oggetto.");
                                }
                            } else {
                                System.out.println("Non puoi raccogliere questo oggetto.");
                            }
                        } else {
                            System.out.println("Non ci sono oggetti da raccogliere.");
                        }
                    break;
                
                    case USA:
                        // variabile utilizzata per controllare se la torcia risulta accesa
                        ObjectProperty torciaUso = getInventory().stream().filter(obj->obj.getName().equals("Torcia")).findFirst().orElse(null);
                
                        // Se il comando è del tipo "Usa chiave", quindi usare un oggetto dall''inventario
                        if(p.getInvObject() != null) {
                            ObjectProperty objectInv = p.getInvObject();
                            boolean found = getInventory().stream().anyMatch(obj->obj.getName().equals(objectInv.getName()));
                            if(found) {
                                // Utilizzo del martello per rompere il blocco "!" nel cortile
                                if(p.getInvObject().getName().equals("Martello")){
                                    if(objectInv.isUsable()) {
                                        if(currentRoom.getName().equals("Cortile")) {
                                            try {
                                                System.out.println("Hai usato: " + objectInv.getName());
                                                objectInv.setUsable(false);
                                                currentRoom.getOvest().getOvest().setVisible(true);
                                                currentRoom.getOvest().getOvest().setMemorizzaDescrizione(currentRoom.getOvest().getOvest().getDescription());
                                                currentRoom.getOvest().getOvest().setDescription(currentRoom.getOvest().getOvest().getNewDescription());
                                                currentRoom.getOvest().getOvest().setNewDescription(currentRoom.getOvest().getOvest().getMemorizzaDescrizione());
                                                dialoghi.indovinelloR();
                                                currentRoom.setIndovinello(true);
                                            } catch (InterruptedException ex) {
                                                Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        } else {
                                            System.out.println("Non è il momento di usare il martello.");
                                        }
                                    } else {
                                        System.out.println("Non è il momento di usare il martello.");
                                    }
                                    // utilizzo della torcia per fare luce nella biblioteca
                                } else if(p.getInvObject().getName().equals("Torcia")) {
                                    if(objectInv.isUsable() == true && currentRoom.getName().equals("Biblioteca")) {
                                        System.out.println("Hai usato: " + objectInv.getName());
                                        p.getInvObject().setIgnitable(true);
                                    } else {
                                        System.out.println("Non puoi usare questo oggetto qui.");
                                    }
                                    // Utilizzo dell'ingranaggio per l'apertura del balcone
                                } else if(p.getInvObject().getName().equals("Ingranaggio")) {
                                    if(objectInv.isUsable() && currentRoom.getName().equals("Atrio")) {
                                        System.out.println("Hai usato: " + objectInv.getName());
                                        currentRoom.getNord().setVisible(true);
                                        System.out.println("L'ingranaggio si è rotto, non posso piu usarlo");
                                        getInventory().remove(objectInv);
                                    } else {
                                        System.out.println("Non puoi usare l'ingranaggio qua.");
                                    }
                                    // utilizzo della chiave della camera da letto, possibile solo con la luce accesa
                                } else if(p.getInvObject().getName().equals("Chiave")) {
                                    if(objectInv.isUsable() && currentRoom.getName().equals("Biblioteca") && torciaUso.isIgnitable() == true) {
                                        System.out.println("Hai usato: " + objectInv.getName());
                                        currentRoom.getOvest().setVisible(true);
                                        System.out.println("La chiave si è rotta, non posso piu usarla");
                                        getInventory().remove(objectInv);
                                    } else {
                                        System.out.println("Non puoi usare la chiave qua.");
                                    }
                                } else {
                                    System.out.println("Non hai questo oggetto.");
                                }
                            } else {
                                System.out.println("Non possiedi questo oggetto.");
                            }
                        // Se il comando è del tipo "usa letto", quindi usare un oggetto nella stanza
                        } else if(p.getObject() != null && currentRoom.getObjects().contains(p.getObject())){
                            ObjectProperty objectRoom = p.getObject();
                            // utilizzare il letto nella camera da letto
                            if(objectRoom.isUsable() && currentRoom.getName().equals("Camera da letto")) {
                                // controllo utilizzato per non permettere di usare più volte il letto
                                if(currentRoom.getIndovinello() == false) {
                                    try {
                                        dialoghi.indovinelloM();
                                        currentRoom.setIndovinello(true);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    dialoghi.nonUsareLetto();
                                }
                            } else {
                                System.out.println("Non ci sono oggetti da usare.");
                            }
                        } else {
                            System.out.println("Non ci sono oggetti da usare.");
                        }
                    break;
                
                    case ACCENDI:
                        // Se il comando è del tipo "accendi torcia"
                        if(p.getInvObject() != null) {
                            ObjectProperty objectInv = p.getInvObject();
                            boolean found = getInventory().stream().anyMatch(obj->obj.getName().equals(objectInv.getName()));
                            if(found) {
                                // Caso in cui al posto di "usa torcia" si scrive "accendi torcia"
                                if(p.getInvObject().getName().equals("Torcia")) {
                                    if(objectInv.isUsable() == true && currentRoom.getName().equals("Biblioteca")) {
                                        System.out.println("Hai usato: " + objectInv.getName());
                                        p.getInvObject().setIgnitable(true);
                                    } else {
                                        System.out.println("Non puoi usare questo oggetto qui.");
                                    }
                                } else {
                                    System.out.println("Non puoi accendere questo oggetto.");
                                }
                            } else {
                                System.out.println("Oggetto non trovato.");
                            }
                        } else {
                            System.out.println("Oggetto non trovato.");
                        }
                    break;
                    
                    case PREMI:
                        // Se il comando è del tipo "Premi pulsante"
                        if(p.getObject() != null && currentRoom.getObjects().contains(p.getObject())) {
                            ObjectProperty object = p.getObject();
                            // Se l'oggetto non è stato premuto
                            if(object.isPushable() == true) {
                                // Pulsante che sblocca dialogo nel bagno
                                if(currentRoom.getName().equals("Camera da letto") && currentRoom.getNord().getPrimoDialogo() == true) {
                                    object.setPushable(false);
                                    System.out.println("Hai premuto: " + object.getName());
                                    try {
                                        dialoghi.indovinelloE();
                                        currentRoom.getNord().setIndovinello(true);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Adventure.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    System.out.println("Non puoi premere ora questo pulsante.");
                                }
                            } else {
                                System.out.println("Non puoi premere questo oggetto.");
                            }
                        } else {
                            System.out.println("Non ci sono oggetti da premere.");
                        }
                    break;
                
                    case FINE:
                        // Termina la partita prima della fine del gioco
                        System.out.println("Hai deciso di terminare il gioco. Arrivederci!");
                        break;
            
                    default:
                        System.out.println("Comando non riconosciuto");
                    break;
                }
            }
        }
    }
    
    /**
    * Separa ogni alias presente nella stringa fornita e restituisce un array di alias.
    * 
    * Questo metodo elabora una stringa che può contenere più alias separati da delimitatori specifici
    * (ad esempio, virgole o spazi) e restituisce un array di stringhe, ognuna delle quali rappresenta un alias.
    * 
    * @param alias la stringa contenente gli alias separati da delimitatori.
    * @return un array di stringhe, dove ogni stringa è un alias estratto dalla stringa fornita.
    */
    private String[] tokenAlias(String alias) {
        String [] aliasVector = alias.split("-");
        return aliasVector;
    }
    
    // prova di connessione al database
    private void tryConnection() {
        var url = "jdbc:h2:./resources/db/IlNome";
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, "sa", "");
            Statement stm = conn.createStatement();
            stm.executeQuery("SELECT * FROM stanze;");
            stm.close();
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 42102) {
                newDB();
            } else {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    // Creazione delle tabelle del database
    private void newDB() {
        var url = "jdbc:h2:./resources/db/IlNome";
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, "sa", "");
            Statement stm = conn.createStatement();
            String CREATE_TABLE = "CREATE TABLE stanze (id int, nome varchar(255), descrizione varchar(255), setLook varchar(500), hideDescription varchar(255));";
            String FILL_TABLE = "INSERT INTO stanze (id, nome, descrizione, setlook) VALUES (1, 'Atrio', 'In questa stanza vedo solo una statua a forma di P e li vicino il mio martello. Dovrei raccoglierlo', 'a NORD balcone, a EST cortile, a SUD ingresso e ad OVEST biblioteca');"
                + "INSERT INTO stanze (id, nome, descrizione) VALUES (2, 'Ingresso', 'Il luogo dello scontro con il fantasma');"
                + "INSERT INTO stanze (id, nome, descrizione, setlook) VALUES (3, 'Balcone', 'Il posto panoramico del castello! Per fortuna vi è una sedia per ammirare il panorama. Sul cornicione vedo una chiave. Dovrei raccoglierla.', 'a SUD ingresso');"
                + "INSERT INTO stanze (id, nome, descrizione, setlook, hideDescription) VALUES (4, 'Biblioteca', 'Oltre ai libri, vedo una poltrona con accanto un tavolino su cui è posato un ingranaggio. Dovrei raccoglierlo.', 'a EST atrio, ad OVEST camera da letto', 'Una stanza molto buia: forse ho qualcosa con cui illuminare la stanza');"
                + "INSERT INTO stanze (id, nome, descrizione, setlook) VALUES (5, 'Camera da letto', 'Oltre al letto, vedo un pulsante accanto ad una porta, chissà a cosa serve...', 'a EST biblioteca');"
                + "INSERT INTO stanze (id, nome, descrizione, setlook) VALUES (6, 'Cortile', 'Perché al centro del cortile ci sta un blocco con un punto esclamativo sopra? Per il mio martello sarà un gioco da ragazzi! Oh vedo anche una torcia, meglio prenderla: puo sempre servire!', 'a OVEST atrio');"
                + "INSERT INTO stanze (id, nome, descrizione, setlook) VALUES (7, 'Bagno', 'Lo sta utilizzando Bowser', 'a SUD camera da letto');";
            stm.executeUpdate(CREATE_TABLE);
            stm.executeUpdate(FILL_TABLE);
            CREATE_TABLE = "CREATE TABLE oggetti (id int, nome varchar(255), descrizione varchar(255), alias varchar(500));";
            FILL_TABLE = "INSERT INTO oggetti (id, nome, descrizione, alias) VALUES (1, 'Martello', 'Serve per attivare qualcosa', 'martello-MARTELLO');"
                + "INSERT INTO oggetti (id, nome, descrizione, alias) VALUES (2, 'Ingranaggio', 'Serve per aprire la porta a nord in atrio', 'ingranaggio-INGRANAGGIO');"
                + "INSERT INTO oggetti (id, nome, descrizione, alias) VALUES (3, 'Chiave', 'Serve per aprire la porta ad ovest della biblioteca', 'chiave-CHIAVE');"
                + "INSERT INTO oggetti (id, nome, descrizione, alias) VALUES (4, 'Torcia', 'Utilizzata per fare luce in una certa stanza', 'torcia-luce-TORCIA-LUCE');"
                + "INSERT INTO oggetti (id, nome, descrizione, alias) VALUES (5, 'Pulsante', 'Pulsante da premere per sapere se qualcuno è in bagno', 'pulsante-bottone-PULSANTE-BOTTONE');"
                + "INSERT INTO oggetti (id, nome, descrizione, alias) VALUES (6, 'Letto', 'Un normale letto. Di solito lo usi per dormire o rilassarti', 'letto-LETTO');";
            stm.executeUpdate(CREATE_TABLE);
            stm.executeUpdate(FILL_TABLE);
            CREATE_TABLE = "CREATE TABLE comandi (id int, nome varchar(255), alias varchar(500));";
            FILL_TABLE = "INSERT INTO comandi (id, nome, alias) VALUES (1, 'nord', 'nord-NORD');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (2, 'sud', 'sud-SUD');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (3, 'est', 'est-EST');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (4, 'ovest', 'ovest-OVEST');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (5, 'inventario', 'inventario-INVENTARIO-inv-INV');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (6, 'guarda', 'guarda-GUARDA-osserva-OSSERVA-vedi-VEDI-descrivi-DESCRIVI');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (7, 'raccogli', 'raccogli-RACCOGLI-prendi-PRENDI');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (8, 'usa', 'usa-USA-utilizza-UTILIZZA-applica-APPLICA-inserisci-INSERISCI');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (9, 'accendi', 'accendi-ACCENDI');" 
                + "INSERT INTO comandi (id, nome, alias) VALUES (10, 'premi', 'premi-PREMI-spingi-SPINGI');"
                + "INSERT INTO comandi (id, nome, alias) VALUES (11, 'fine', 'fine-FINE-end-END-esci-ESCI-exit-EXIT');";
            stm.executeUpdate(CREATE_TABLE);
            stm.executeUpdate(FILL_TABLE);
            stm.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Gestione del finale di gioco
    private void ending() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("????: Sei tornato, finalmente! Ora non ti lascio scappare! Dai su, hai scoperto il mio nome?");
        System.out.println("???: Penso proprio di si!");
        System.out.println("????: Su prova allora!");
        Scanner scanner = new Scanner(System.in);
        String nameIn = scanner.nextLine().toLowerCase();
        if("rampel".equals(nameIn)) {
            dialoghi.goodEnding();
        } else {
            dialoghi.badEnding();
        }
    }
}
