public class clock01 extends Thread {

    public synchronized void tick() {

        notifyAll();
    }

    public synchronized void waitFor (int ticks) {

        int count = 0;
        while (count <= ticks){

            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            count++;
        }
    }
}