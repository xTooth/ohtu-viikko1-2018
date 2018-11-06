package ohtu.ohtuvarasto;

public class Varasto {

    // --- piilotettu tietorakenteen toteutus: ---
    private double tilavuus;  // paljonko varastoon mahtuu,  > 0
    private double saldo;     // paljonko varastossa on nyt, >= 0
    private double minimi;
    // --- konstruktorit: ---
    public Varasto(double tilavuus) {  // tilavuus on annettava
        this.minimi = 0.0;
        if (tilavuus > minimi) {
            this.tilavuus = tilavuus;
        } else {         // virheellinen, nollataan
            this.tilavuus = minimi;  // => käyttökelvoton varasto
        }
        saldo = minimi;     // oletus: varasto on tyhjä
    }

    public Varasto(double tilavuus, double alkuSaldo) { // kuormitetaan
        minimi = 0.0;
        if (tilavuus > minimi) {
            this.tilavuus = tilavuus;
        } else { // virheellinen, nollataan       
            this.tilavuus = minimi;  // => käyttökelvoton varasto
            this.saldo = minimi;
            return;
        }
        this.saldo=minimi;
        lisaaVarastoon(alkuSaldo);
    }
    // --- ottavat aksessorit eli getterit: ---
    public double getSaldo() {
        return saldo;
    }

    public double getTilavuus() {
        return tilavuus;
    }

    public double paljonkoMahtuu() {  // huom: ominaisuus voidaan myös laskea
        return tilavuus - saldo;        //  ei tarvita erillistä kenttää vielaTilaa tms.
    }

    // --- asettavat aksessorit eli setterit: ---
    public void lisaaVarastoon(double maara) {
        if (maara < 0) {// virhetilanteessa voidaan tehdä         
            return;       // tällainen pikapoistuminenkin!
        }
        if (maara <= paljonkoMahtuu()){ // omia aksessoreita voi kutsua
            saldo = saldo + maara;          // ihan suoraan sellaisinaan
        } else {
            saldo = tilavuus;  // täyteen ja ylimäärä hukkaan!
        }
    }

    public double otaVarastosta(double maara) {
        if (maara < minimi) {// virhetilanteessa voidaan tehdä 
        
            return minimi;   // tällainen pikapoistuminenkin!
        }
        if (maara > saldo) {          // annetaan mitä voidaan
            double kaikkiMitaVoidaan = saldo;
            saldo = minimi;               // ja tyhjäksi menee
            return kaikkiMitaVoidaan;  // poistutaan saman tien
        }
        // jos tänne päästään, kaikki pyydetty voidaan antaa
        saldo = saldo - maara;  // vähennetään annettava saldosta
        return maara;
    }

    // --- Merkkijonoesitys Varasto-oliolle: ----
    public String toString() {
        return ("saldo = " + saldo + ", vielä tilaa " + paljonkoMahtuu());
    }
}