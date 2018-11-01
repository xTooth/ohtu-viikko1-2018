package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    @Test
    public void ottaminenEiHyvaksyNegatiivistaLukua() {
        varasto.lisaaVarastoon(4);

        double saatuMaara = varasto.otaVarastosta(-2);

        assertEquals(0,saatuMaara,vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void varstoNegatiivisellaKoollaLuodaanOikein() {
         Varasto uusi = new Varasto(-8);
         assertEquals(0,uusi.getTilavuus(),vertailuTarkkuus);
    }
    @Test
    public void varastoLuodaanOikeinJosLisataansisaltoa() {
        Varasto uusi = new Varasto(10,5);
        assertEquals(5,uusi.getSaldo(),vertailuTarkkuus);
    }
    @Test
    public void VarastoLisataanLiikaa() {
        Varasto uusi = new Varasto(10,11);
        assertEquals(10,uusi.getSaldo(),vertailuTarkkuus);
    }
    @Test
    public void VarastoluodaanVaarin() {
        Varasto uusi = new Varasto(-5,-3);
        assertEquals(0,uusi.getTilavuus(),vertailuTarkkuus);
        assertEquals(0,uusi.getSaldo(),vertailuTarkkuus);
    }
    @Test
    public void VarastoonLisaaminenSkulaaNegatiivisillaLuvuilla() {
        double saldo = varasto.getSaldo();
        varasto.lisaaVarastoon(-5);
        assertEquals(saldo,varasto.getSaldo(),vertailuTarkkuus);
    }
    @Test
    public void VarastoonLisataanLiikaaToimiiOikein() {
        varasto.lisaaVarastoon(100);
        assertEquals(10,varasto.getSaldo(),vertailuTarkkuus);
    }
    @Test
    public void VarastoTyhjeneeOikein() {
        double saldoEnnen = varasto.getSaldo();
        double saadut = varasto.otaVarastosta(100);
        assertEquals(0,varasto.getSaldo(),vertailuTarkkuus);
        assertEquals(saldoEnnen,saadut,vertailuTarkkuus);
    }
    @Test
    public void toStringToimii() {
        String oikein = "saldo = "+ varasto.getSaldo() + ", vielä tilaa " + 5 +varasto.paljonkoMahtuu();
        assertEquals(oikein,varasto.toString());
    }

}