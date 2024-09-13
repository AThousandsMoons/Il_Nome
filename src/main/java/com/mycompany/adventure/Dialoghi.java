package com.mycompany.adventure;

/**
 * La classe principale del gioco, che gestisce l'inizializzazione e la logica principale del gioco di avventura.
 *
 * @author simone
 */
public class Dialoghi {
     protected void startGame() throws InterruptedException {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("????: Ciao! Ti stavo aspettando! Potevo attacarti prima, ma non volevo espormi. E cosi ti ho aspettato qui.");
        System.out.println("???: Lui e il fantasma che mi ha sottratto nome e corpo...");
        System.out.println("????: Bando alle ciance! Hai un unico modo per sconfiggermi: dire il mio nome. Dillo e restituiro cio che e tuo!");
        System.out.println("???: Non conosco il suo nome, penso che qualcosa trovero nel castello alle sue spalle." +
                                    "Non resta che scappare nel castello e barricarmi dentro...");
        Thread.sleep(2000);
        System.out.println("????: Ehi ! Che fai scappi ? Vabbe, lo aspettero qui. Dovra passare di qua prima o poi!");
        Thread.sleep(4000);
        System.out.println("???: Per fortuna sono scappato dalla sua imboscata, ora dovrei essere al sicuro per un po. Devo recuperare la mia identità al piu presto!");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    protected void indovinelloP() throws InterruptedException {
        System.out.println("???: Raccogliendo il martello mi sono avvicinato molto alla statua.");
        Thread.sleep(1000);
        System.out.println("\tE una strana statua, d'altronde chi e che metterebbe una statua a forma di P nel suo salone!");
        Thread.sleep(1000);
        System.out.println("\tPero aspetta... Qui c'e una scritta! Vediamo cosa dice!");
        Thread.sleep(1000);
        System.out.println("\tIo sono come il prezzemolo: sono sempre in mezzo, piu o meno!");
        Thread.sleep(1000);
        System.out.println("\tHo capito! Questo e un indizio per il nome del fantasma! Si tratta sicuramente della lettera P, chissa dove devo inserirla...");
    }
    
    protected void indovinelloE() throws InterruptedException {
        System.out.println("[Click]");
        Thread.sleep(2000);
        System.out.println("Bowser: EHI QUI LA LETTERA E NON C'E PIU! ORA E APPARSO UN TESTO!");
        Thread.sleep(2000);
        System.out.println("???: Cosa dice?");
        Thread.sleep(1000);
        System.out.println("Bowser: C'e scritto: Mi conoscono come una congiunzione, infatti qui collego due lettere!");
        System.out.println("\tA cosa si riferisce?");
        Thread.sleep(1000);
        System.out.println("???: Grazie Bowser! A presto!");
        Thread.sleep(1000);
        System.out.println("Bowser: EHI DOVE VAI? E COME FAI A CONOSCERE IL MIO NOME?");
    }
    
    protected void indovinelloM() throws InterruptedException {
        System.out.println("???: Questo letto a baldacchino e veramente comodo! Quasi quasi ci faccio una dormita!");
        Thread.sleep(3000);
        System.out.println("\tMa sul soffitto c'e un altro indizio! A questo giro si tratta della lettera M!");
        Thread.sleep(1000);
        System.out.println("\tOvviamente c'e anche un indovinello, vediamo...");
        Thread.sleep(1000);
        System.out.println("\tIo sono la terza lettera!");
        Thread.sleep(1000);
        System.out.println("\tMeglio andare via, non mi vorrei addormentare...");
    }
    
    protected void indovinelloR() throws InterruptedException {
        System.out.println("[Sfrrr.....Thud]");
        Thread.sleep(2000);
        System.out.println("???: Qualcosa si e mosso nell'altra stanza, devo controllare le strade bloccate.");
        Thread.sleep(3000);
        System.out.println("\tMa il blocco con il punto interrogativo si è trasformato in una statua a forma di lettera R!");
        Thread.sleep(1000);
        System.out.println("\tQui c'e un altro indovinello: Non ci sono altre lettere alla mia sinistra!");
        Thread.sleep(1000);
        System.out.println("\tPerfetto, posso tornare indietro.");
    }
    
    protected void indovinelloL() throws InterruptedException {
        System.out.println("???: La chiave ha un portachiavi su cui c'e scritto BALCONE, chissa dove devo utilizzarla!");
        Thread.sleep(1000);
        System.out.println("\tSu questo scrittoio, oltre alla chiave, c'e un foglio con su scritta una L. Immagino che dietro ci sia l'indizio.");
        Thread.sleep(1000);
        System.out.println("\tCome pensavo. L'indizio recita: Con questa lettera di solito iniziano le parole, qui beh...");
        Thread.sleep(1000);
        System.out.println("\tChe indizio strano!");
    }
    
    protected void indovinelloA() throws InterruptedException {
        System.out.println("???: La chiave sul cornicione eh... Un soffio di vento e cade giu nel recinto degli animali e poi addio chiave!");
        Thread.sleep(1000);
        System.out.println("\tAttento...");
        Thread.sleep(2000);
        System.out.println("\tAttento...");
        Thread.sleep(1000);
        System.out.println("\tPresa!");
        Thread.sleep(1000);
        System.out.println("\tEh? Davanti a me, scolpita nel giardino c'e una grande lettera A. E l'indizio?");
        Thread.sleep(2000);
        System.out.println("\tAh, era qua dietro! Allora l'indizio recita:");
        System.out.println("\tSe guardi nel recinto riuscirai a vedere un solo animale. Questa lettera si trova nella stessa posizione in cui si trova nel nome di questo animale!");
        Thread.sleep(1000);
        System.out.println("\tL'unico animale che vedo nel recinto è una capra.");
        Thread.sleep(1000);
    }
    
    protected void nonUsareLetto() {
        System.out.println("???: Non cadro in tentazione.");
    }
    
    protected void disturbaBowser() throws InterruptedException {
        System.out.println("??: EHI! Il bagno e occupato! Sto pianificando come sconfiggere Mario! Aspetta un po!");
        Thread.sleep(1000);
        System.out.println("???: (Questa e la voce di Bowser! Perche dovrebbe essere qui? Vabbe potrebbe tornarmi utile)");
        System.out.println("???: Chiedo scusa, ma mi domandavo se per caso intorno a te vedi qualcosa di simile ad una lettera!");
        Thread.sleep(1000);
        System.out.println("Bowser: Una lettera dici? Mmmmmh....");
        Thread.sleep(2000);
        System.out.println("\t(Niente, neanche in bagno posso rimanere in pace...)");
        Thread.sleep(1000);
        System.out.println("Bowser: Si, vedo una lettera E! C'e scritto che devi premere un pulsante che si trova nella camera da letto.");
        System.out.println("???: Ok, do un'occhiata in camera da letto! Non andare via!");
        System.out.println("Bowser: E dove vuoi che vada!");
        Thread.sleep(3000);
        System.out.println("???: Trovato! Ora devo solo premerlo!");
    }
    
    protected void continuaDisturbaBowser() throws InterruptedException{
        System.out.println("Bowser: SEI TORNATO EH? VUOI DIRMI CHI SEI?");
        Thread.sleep(1000);
        System.out.println("???: Meglio andare via...");
        Thread.sleep(1000);
    }
    
    protected void bowserInfuriato() throws InterruptedException{
        System.out.println("Bowser: Perche stai provando ad entrare! Qui non e successo niente!");
        Thread.sleep(1000);
        System.out.println("???: Scusami, ho sbagliato!");
        Thread.sleep(1000);
        System.out.println("Bowser: MA CHE DIAVOLO TI SALTA IN MENTE! VEDI DI NON PERDERE TEMPO!");
    }
    
    protected void recapIndovinelli() throws InterruptedException {
        System.out.println("???: Prima di tornare dal fantasma, fammi fare un recap mentale degli indovinelli: ");
        Thread.sleep(1000);
        System.out.println("\tLettera P -> Io sono come il prezzemolo: sono sempre in mezzo, piu o meno!");
        Thread.sleep(1000);
        System.out.println("\tLettera R -> Non ci sono altre lettere alla mia sinistra!");
        Thread.sleep(1000);
        System.out.println("\tLettera L -> Con questa lettera di solito iniziano le parole, qui beh...");
        Thread.sleep(1000);
        System.out.println("\tLettera A -> Si trova nella stessa posizione in cui si trova nella parola 'Capra'");
        Thread.sleep(1000);
        System.out.println("\tLettera M -> Io sono la terza lettera!");
        Thread.sleep(1000);
        System.out.println("\tLettera E -> Mi conoscono come una congiunzione, infatti qui collego due lettere!");
        Thread.sleep(1000);
        System.out.println("\tBene, dovrei sapere la risposta.");
    }
    
    protected void badEnding() throws InterruptedException {
        System.out.println("????: AHAHAHAHAHAHAHAH! Hai sbagliato: non e quello il mio nome!");
        System.out.println("\tBeh mi spiace, ma non tornerai mai piu indietro!");
        Thread.sleep(4000);
        System.out.println("Game over, Mario.");
        System.exit(0);
    }
    
    protected void goodEnding() throws InterruptedException {
        System.out.println("Rampel: Hai scoperto il mio nome! E impossibile...");
        Thread.sleep(1000);
        System.out.println("Rampel: Uh...");
        Thread.sleep(1000);
        System.out.println("Rampel: Mi...");
        Thread.sleep(1000);
        System.out.println("Rampel: Gira...");
        Thread.sleep(1000);
        System.out.println("Rampel: La...");
        Thread.sleep(1000);
        System.out.println("Rampel: Testa...");
        Thread.sleep(1000);
        System.out.println("Rampel: Me la pagherai Mario! UAAAAAAAAAAAAAAH!");
        Thread.sleep(1000);
        System.out.println("Mario: Bene! Ora che sono tornato in possesso del mio nome e del mio corpo, posso tornare a casa!");
        System.exit(0);
    }
}
