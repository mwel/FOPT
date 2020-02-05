package CarValues;

public class ProducerConsumer {

    public static void main(String[] args) {

        Buffer buffer = new Buffer();
        Consumer consumer = new Consumer(buffer);
        Producer producer = new Producer(buffer, 1);
        consumer.start();
        producer.start();
    }
}
