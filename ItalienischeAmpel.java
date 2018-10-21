package pp.ampel;

public final class ItalienischeAmpel implements Ampel
{
    public static enum AmpelPhase
    {
        ROT, GRUEN;
    }

    private final String name;

    private AmpelPhase phase;

    public ItalienischeAmpel()
    {
        this("ItalienischeAmpel?");
    }

    // Konstruktor
    public ItalienischeAmpel(final String name)
    {
        this.name = name;
        this.phase = AmpelPhase.ROT;
    }

    public synchronized void schalteRot()
    {
        this.schalte(AmpelPhase.ROT);
    }

    public synchronized void schalteGruen()
    {
        this.schalte(AmpelPhase.GRUEN);
        this.notifyAll();
    }

    public synchronized void umschalten()
    {
        switch (this.phase)
        {
        case GRUEN:
            this.schalteRot();
            break;
        case ROT:
            this.schalteGruen();
            break;
        default:
            throw new IllegalStateException();
        }
    }

    private synchronized void schalte(final AmpelPhase newPhase)
    {
        if (this.phase != newPhase)
        {
            this.phase = newPhase;
            System.err.printf("%s schaltet auf %s%n", this.name, this.phase.name());
        }
    }

    public synchronized void passieren() throws InterruptedException
    {
        while (this.phase == AmpelPhase.ROT)
        {
            this.wait();
        }
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    @Override
    public int wartendeFahrzeuge()
    {
        // TODO Auto-generated method stub
        return 0;
    }
}