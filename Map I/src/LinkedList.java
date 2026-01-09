import java.util.Scanner;

/**
 * @param <T> Generic type
 * @author Christian Baduria
 * @version 1.0
 */
public class LinkedList<T> extends List<T> {

    private Node head;
    private Node tail;

    /**
     * Appends the specified value to the end of this list.
     *
     * @param value T The value to add
     * @return boolean True if the value is inserted, false otherwise
     */
    @Override
    boolean add(T value) {
        if (!contains(value)) {
            Node newNode = new Node(value);
            if (head != null) {
                tail.next = newNode;
                newNode.prev = tail;
            } else {
                head = newNode;
            }
            tail = newNode;
            return true;
        }
        return false;
    }

    /**
     * Inserts the specified value at the specified position in this list.
     *
     * @param index Integer The index at which to insert
     * @param value T The value to insert
     */
    @Override
    void add(int index, T value) {
        if (!contains(value)) {
            Node newNode = new Node(value);
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            if (current == null) {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else if (current != head) {
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        }
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    void clear() {
        head = null;
        tail = null;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param o Object The element to check if present in the list
     * @return boolean
     */
    @Override
    boolean contains(Object o) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index Integer The index at which to insert
     * @return T
     */
    @Override
    T get(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                return null;
            }
            current = current.next;
        }
        if (current == null) {
            return null;
        }
        return current.value;
    }

    /**
     * Get the list entry corresponding to the value provided in the parameter.
     *
     * @param o to search for
     * @return T matching data in the list
     */
    @Override
    T get(Object o) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(o)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes the element at the specified position in this list.
     * Returns the element from the list or null if index is invalid.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position or null
     */
    @Override
    T remove(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                return null;
            }
            current = current.next;
        }
        remove(current);
        return current.value;
    }

    /**
     * Removes the first occurrence of the specified element from this
     * list, if it is present.
     * If this list does not contain the element, it is unchanged.
     * Returns true if this list contained the specified element
     * (or equivalently, if this list changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    boolean remove(Object o) {
        Node current = head;
        while (current != null) {
            if (current.value.equals(o)) {
                remove(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Performs remove operation on Node in LinkedList.
     *
     * @param current [Node] Node to be removed from LinkedList
     */
    private void remove(Node current) {
        if (current != head && current != tail) {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        } else if (current == head) {
            head = current.next;
            if (head != null) {
                head.prev = null;
            }
        } else {
            tail = current.prev;
            tail.next = null;
        }
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return int
     */
    @Override
    int size() {
        int size = 0;
        Node current = head;
        while (current != null) {
            current = current.next;
            size++;
        }
        return size;
    }

    /**
     * Returns first Node (the head) of LinkedList.
     *
     * @return [Node] First Node of LinkedList
     */
    public Node getHead() {
        return head;
    }

    /**
     * Returns last Node (the tail) of LinkedList.
     *
     * @return [Node] Last Node of LinkedList
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Returns LinkedList expressed as string.
     *
     * @return [String] LinkedList as string
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        Node current = head;
        if (current != null) {
            string.append(current.value);
            current = current.next;
        }
        while (current != null) {
            string.append(", ").append(current.value);
            current = current.next;
        }
        string.append("]");
        return string.toString();
    }

    /**
     * Main function that controls/tests LinkedList class.
     *
     * @param args [String[]] args parameter of main function
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<String> strings = new LinkedList<>();
        boolean exit = false;
        do {
            String value;
            int index;
            String action = scan.next();
            switch (action) {
                case "a":
                    value = scan.next();
                    strings.add(value);
                    System.out.println(strings.toString());
                    break;
                case "A":
                    index = scan.nextInt();
                    value = scan.next();
                    strings.add(index, value);
                    System.out.println(strings.toString());
                    break;
                case "c":
                    value = scan.next();
                    System.out.println(strings.contains(value));
                    break;
                case "C":
                    strings.clear();
                    System.out.println(strings.toString());
                    break;
                case "g":
                    index = scan.nextInt();
                    System.out.println(strings.get(index));
                    break;
                case "s":
                    System.out.println(strings.size());
                    break;
                case "e":
                    System.out.println(strings.isEmpty());
                    break;
                case "r":
                    value = scan.next();
                    strings.remove(value);
                    System.out.println(strings.toString());
                    break;
                case "R":
                    index = scan.nextInt();
                    strings.remove(index);
                    System.out.println(strings.toString());
                    break;
                case "x":
                    exit = true;
                    break;
            }
            if (!exit) {
                System.out.println("------------------");
            }
        } while (!exit);
    }
}
