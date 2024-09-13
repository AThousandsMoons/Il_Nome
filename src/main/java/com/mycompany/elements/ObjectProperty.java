package com.mycompany.elements;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * La classe ObjectProperty rappresenta le proprietà di un oggetto nel gioco.
 * Ogni oggetto ha un identificatore unico (id), un nome, una descrizione e una serie di attributi
 * che ne definiscono il comportamento (ad esempio, se è raccoglibile, utilizzabile o infiammabile).
 * Gli oggetti possono anche avere alias che permettono di riconoscerli con nomi alternativi.
 * 
 * @author simone
 */
public class ObjectProperty {
    // Identificatore univoco dell'oggetto
    private final int id;

    // Nome dell'oggetto
    private String name;

    // Descrizione dell'oggetto
    private String description;

    // Insieme di alias associati all'oggetto
    private Set<String> alias;

    // Indica se l'oggetto è apribile
    private boolean openable = false;

    // Indica se l'oggetto può essere raccolto
    private boolean pickupable = false;

    // Indica se l'oggetto può essere premuto
    private boolean pushable = false;

    // Indica se l'oggetto è aperto
    private boolean open = false;

    // Indica se l'oggetto è stato premuto
    private boolean push = false;

    // Indica se l'oggetto può essere utilizzato
    private boolean use = false;

    // Indica se l'oggetto può essere acceso (infiammabile)
    private boolean ignitable = false;

    /**
     * Costruttore che inizializza un oggetto con un identificatore.
     * 
     * @param id L'identificatore univoco dell'oggetto.
     */
    public ObjectProperty(int id) {
        this.id = id;
    }

    /**
     * Costruttore che inizializza un oggetto con un identificatore e un nome.
     * 
     * @param id L'identificatore univoco dell'oggetto.
     * @param name Il nome dell'oggetto.
     */
    public ObjectProperty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Costruttore che inizializza un oggetto con un identificatore, un nome e una descrizione.
     * 
     * @param id L'identificatore univoco dell'oggetto.
     * @param name Il nome dell'oggetto.
     * @param description La descrizione dell'oggetto.
     */
    public ObjectProperty(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Costruttore che inizializza un oggetto con un identificatore, un nome, una descrizione e un insieme di alias.
     * 
     * @param id L'identificatore univoco dell'oggetto.
     * @param name Il nome dell'oggetto.
     * @param description La descrizione dell'oggetto.
     * @param alias Gli alias associati all'oggetto.
     */
    public ObjectProperty(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    // Getter e setter per le proprietà dell'oggetto

    /**
     * Restituisce l'identificatore univoco dell'oggetto.
     * 
     * @return L'identificatore dell'oggetto.
     */
    public int getId() {
        return id;
    }

    /**
     * Restituisce il nome dell'oggetto.
     * 
     * @return Il nome dell'oggetto.
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome dell'oggetto.
     * 
     * @param name Il nome da assegnare all'oggetto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce la descrizione dell'oggetto.
     * 
     * @return La descrizione dell'oggetto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta la descrizione dell'oggetto.
     * 
     * @param description La descrizione da assegnare all'oggetto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Indica se l'oggetto è apribile.
     * 
     * @return true se l'oggetto è apribile, altrimenti false.
     */
    public boolean isOpenable() {
        return openable;
    }

    /**
     * Imposta se l'oggetto è apribile o meno.
     * 
     * @param openable true per rendere l'oggetto apribile, altrimenti false.
     */
    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    /**
     * Indica se l'oggetto può essere acceso (infiammabile).
     * 
     * @return true se l'oggetto è infiammabile, altrimenti false.
     */
    public boolean isIgnitable() {
        return ignitable;
    }

    /**
     * Imposta se l'oggetto è infiammabile o meno.
     * 
     * @param ignitable true per rendere l'oggetto infiammabile, altrimenti false.
     */
    public void setIgnitable(boolean ignitable) {
        this.ignitable = ignitable;
    }

    /**
     * Indica se l'oggetto può essere raccolto.
     * 
     * @return true se l'oggetto è raccoglibile, altrimenti false.
     */
    public boolean isPickupable() {
        return pickupable;
    }

    /**
     * Imposta se l'oggetto è raccoglibile o meno.
     * 
     * @param pickupable true per rendere l'oggetto raccoglibile, altrimenti false.
     */
    public void setPickupable(boolean pickupable) {
        this.pickupable = pickupable;
    }

    /**
     * Indica se l'oggetto può essere premuto.
     * 
     * @return true se l'oggetto è premibile, altrimenti false.
     */
    public boolean isPushable() {
        return pushable;
    }

    /**
     * Imposta se l'oggetto è premibile o meno.
     * 
     * @param pushable true per rendere l'oggetto premibile, altrimenti false.
     */
    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

    /**
     * Indica se l'oggetto è utilizzabile.
     * 
     * @return true se l'oggetto è utilizzabile, altrimenti false.
     */
    public boolean isUsable() {
        return use;
    }

    /**
     * Imposta se l'oggetto è utilizzabile o meno.
     * 
     * @param use true per rendere l'oggetto utilizzabile, altrimenti false.
     */
    public void setUsable(boolean use) {
        this.use = use;
    }

    /**
     * Indica se l'oggetto è attualmente aperto.
     * 
     * @return true se l'oggetto è aperto, altrimenti false.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Imposta se l'oggetto è aperto o meno.
     * 
     * @param open true per indicare che l'oggetto è aperto, altrimenti false.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Indica se l'oggetto è stato premuto.
     * 
     * @return true se l'oggetto è premuto, altrimenti false.
     */
    public boolean isPush() {
        return push;
    }

    /**
     * Imposta se l'oggetto è stato premuto o meno.
     * 
     * @param push true per indicare che l'oggetto è premuto, altrimenti false.
     */
    public void setPush(boolean push) {
        this.push = push;
    }

    /**
     * Restituisce l'insieme di alias associati all'oggetto.
     * 
     * @return Gli alias dell'oggetto.
     */
    public Set<String> getAlias() {
        return alias;
    }

    /**
     * Imposta l'insieme di alias associati all'oggetto.
     * 
     * @param alias Gli alias da assegnare all'oggetto.
     */
    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    /**
     * Imposta gli alias dell'oggetto utilizzando un array di stringhe.
     * 
     * @param alias Un array di alias da assegnare all'oggetto.
     */
    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    /**
     * Metodo hashCode per calcolare il codice hash dell'oggetto.
     * 
     * @return Il codice hash basato sul tipo del comando.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        return hash;
    }

    /**
     * Confronta due oggetti ObjectProperty per verificarne l'uguaglianza.
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
        final ObjectProperty other = (ObjectProperty) obj;
        if(this.id != other.id) {
            return false;
        }
        return true;
    }
}
