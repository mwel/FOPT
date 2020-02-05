import java.io.*;

public class FileReaderWriter {

    public static void main(String[] args) {

        try {

            File file = new File("/Users/mwellenhofer/Desktop/dbFil.dat");

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object rawObj = ois.readObject();

            MiniDB inputDB = (MiniDB) rawObj;
            System.out.println(inputDB.getName() + ", " + inputDB.getAge());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
