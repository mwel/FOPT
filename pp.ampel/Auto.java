package pp.ampel;

final class Auto extends Thread
{
    private final Ampel[] ampeln;

    private final int startPosition;

    private final String name;

    Auto(final Ampel[] ampeln, final int startPosition, final String name)
    {
        super(name);
        this.startPosition = startPosition;
        this.ampeln = ampeln;
        this.name = name;
    }

    Auto(final Ampel[] ampeln)
    {
        this(ampeln, 0, "Auto?");
    }

    @Override
    public void run()
    {
        int i = this.startPosition;
        try
        {
            while (!this.isInterrupted())
            {
                System.err.printf("%s will %s passieren%n", this, this.ampeln[i]);
                // Hier muss die Referenz auf Ampeln bezogen werden und nicht
                // auf eine der Ampelarten.
                //
                this.ampeln[i].passieren();
                i = (i + 1) % this.ampeln.length;
                Thread.sleep(500);
            }
        }
        catch (final InterruptedException e)
        {

        }
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}