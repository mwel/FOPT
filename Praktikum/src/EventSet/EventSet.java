package EventSet;

public class EventSet {

    boolean[] events;
    boolean AND;
    boolean OR;

    public EventSet(int size) {

        events = new boolean[size];
    }

    public synchronized void set(int index, boolean value) {

        events[index] = value;

        if (value) {
            OR();
            AND();
            notifyAll();
        }
    }

    public synchronized void waitAND() {
        while (!AND) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void waitOR() {
        while (!OR) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void OR() { // wäre sie private, müsste ich kein synchronized setzen.
        OR = false;
        for (boolean event : events) {
            if (event)
                OR = event;
        }
    }

    public synchronized void AND() { // wäre sie private, müsste ich kein synchronized setzen.
        AND = true;
        for (boolean event : events) {
            if (!event)
                AND = event;
        }
    }
}
