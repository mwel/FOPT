package pp.ampel;

import java.util.Random;

final class AmpelSteuerung extends Thread
{

    // Referenz von Ampel
    private final Ampel[] ampeln;

    private final long intervall;

    // Konstruktor pp.ampel.Ampel[]
    public AmpelSteuerung(final Ampel[] ampeln, final long intervall)
    {
        super("Steuerung");
        this.intervall = intervall;
        this.ampeln = ampeln;
    }

    @Override
    public void run()
    {
        final Random random = new Random();
        try
        {
            while (!this.isInterrupted())
            {
                this.ampeln[random.nextInt(this.ampeln.length)].umschalten();
                Thread.sleep(this.intervall);
            }
        }
        catch (final InterruptedException e)
        {

        }
    }

    public static void main(final String[] args) throws InterruptedException
    {
        final Random random = new Random();
        final Ampel[] ampeln = new Ampel[3];
        final Auto[] autos = new Auto[3];

        System.err.printf("### Ampeln%n");
        for (int i = 0; i < ampeln.length; i++)
        {
            ampeln[i] = new ItalienischeAmpel("Ampel" + i);
            ampeln[i] = new DeutscheAmpel("Ampel" + i);

        }

        System.err.printf("\n\n### Auto%n");
        for (int i = 0; i < autos.length; i++)
        {
            autos[i] = new Auto(ampeln, random.nextInt(ampeln.length), "Auto" + i);
            autos[i].start();
        }
        Thread.sleep(2 * 1000);

        System.err.printf("\n\n### Steuerung%n");
        final AmpelSteuerung steuerung = new AmpelSteuerung(ampeln, 1000);
        steuerung.start();
        Thread.sleep(20 * 1000);

        System.err.printf("\n\n### Abbruch%n");
        steuerung.interrupt();
        steuerung.join();
        for (final Auto auto : autos)
        {
            auto.interrupt();
            auto.join();
        }
    }
}