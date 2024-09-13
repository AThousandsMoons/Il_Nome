package com.mycompany.elements;

/**
 *
 * @author simone
 */
public class Timer {
    // memorizza il numero di millisecondi che sono trascorsi
    private long contatore;
    // momento dell'ultimo avvio del timer
    private long avvio;
    // variabile di stato che indica se il tempo scorre
    private boolean tempo;
    
    /**
     * con l'invocazione del costruttore si resetta il timer ma non lo
     * si avvia.
     */
    public Timer() {
        azzera();
    }
    
    /**
     * metodo utilzzato per azzerare il timer
     */
    public void azzera() {
        synchronized(this) {
            contatore = 0;
            tempo = false;
        }
    }
    
    /**
     * metodo utilizzato per far partire e ripartire (in base ai casi) il timer.
     */
    public void avanza() {
        avvio = System.currentTimeMillis();
        tempo = true;
    }
    
    /**
     * metodo che interrompe l'avanzamento del timer.
     */
    public void stop() {
        synchronized(this) {
            contatore += System.currentTimeMillis() - avvio;
            tempo = false;
        }
    }
    
    /**
     * metodo con cui si azzera il timer e lo si fa ripartire
     */
    public void ricomincia() {
        azzera();
        avanza();
    }
    
    /**
     * metodo con cui si legge il tempo misurato dal timare.
     * @return   numero di millisecondi contati
     */
    public long leggiTempo() {
        synchronized(this) {
            return tempo ? contatore + System.currentTimeMillis() - avvio : contatore;
        }
    }
    
    /**
     * metodo utilizzato per convertire in stringa il tempo misurato mediante 
     * il metodo leggiTempo()
     * @return    stringa rappresentante il numero di millisecondi contati
     */
    public String secondToString() {
        return "" + leggiTempo() / 1000;
    }
}
