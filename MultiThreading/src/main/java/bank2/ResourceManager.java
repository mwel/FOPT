package bank2;

public interface ResourceManager {

    public void acquire(int[] resources);

    public void release(int[] resources);
}
