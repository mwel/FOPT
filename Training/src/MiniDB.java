import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MiniDB implements Serializable {

    private String name;
    private int age;
    private List<String> children;

    public MiniDB(String name, int age) {

        this.name = name;
        this.age = age;
        children = new ArrayList<String>();

        children.add("Max");
        children.add("Alex");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

