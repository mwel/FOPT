//package bank2;
//
//
//import java.util.concurrent.*;
//
//public class RessourceManagerNaive implements ResourceManager {
//
//    private Semaphore availableResources;
//
//    public void acquire(int[] initialResources) {
//
//        availableResources = new Semaphore(initialResources.length);
//        availableResources.changeValues(initialResources);
//
//    }
//
//    public void release(int[] resources) {
//
//    }
//}
