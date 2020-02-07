package rmi_serializableList;

import java.io.Serializable;

public class ListItem implements Serializable {

    private int value;
    private ListItem next;

    public ListItem(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public ListItem getNext() {
        return getNext();
    }

    public void setNext(ListItem nextListItem) {
        next = nextListItem;
    }

}
