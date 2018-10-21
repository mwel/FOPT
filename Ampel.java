package pp.ampel;
// Schnittstelle <Ampel>

public interface Ampel
{
    // 1. Parameterlose void Methode <Schaltung auf ROT>
    public void schalteRot();

    // 2. Parameterlose void Methode <Schaltung auf GRUEN>
    public void schalteGruen();

    // 3. Parameterlose void Methode <passieren>
    public void passieren() throws InterruptedException;

    // 4. Parameterlose void Methode <wartende Fahrzeuge>
    public int wartendeFahrzeuge();

    // Umschalten, weil sonst Error in Ampelsteuerung
    public void umschalten();

}