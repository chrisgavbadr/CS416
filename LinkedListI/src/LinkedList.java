public class LinkedList {
    public class Node {
        String key;
        int value;
        Node next;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void addHead(String key, int val) {
        Node n = new Node(key, val);

        if (head == null) {
            head = n;
            tail = n;
        } else {
            n.next = head;
            head = n;
        }
    }

    public void addTail(String key, int val) {
        Node n = new Node(key, val);

        if (tail == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
    }

    public String toString() {
        String ret = "";

        Node curr = head;

        while (curr != null) {
            if (curr.next != null)
                ret += curr.key + ":" + curr.value + ", ";
            else
                ret += curr.key + ":" + curr.value;

            curr = curr.next;
        }

        return ret;
    }

    private Node find(String key) {
        Node curr = head;

        while (curr != null) {
            if (curr.key.equals(key))
                return curr;

            curr = curr.next;
        }

        return null;
    }


    //////////////////////// YOUR CODE HERE ////////////////////////

    public static double average(LinkedList list) {
        Node front = list.head;
        double avg = 0;
        double count = 0;
        while (front != null) {
            avg += front.value;
            count++;
            front = front.next;
        }
        avg /= count;
        return avg;
    }

    public static boolean ordered(LinkedList list) {
        Node front = list.head;
        while (front.next != null) {
            if (front.key.compareTo(front.next.key) > 0) {
                return false;
            }
            front = front.next;
        }
        return true;
    }

    public static LinkedList reversed(LinkedList list) {
        LinkedList newList = new LinkedList();
        Node front = list.head;
        while (front != null) {
            newList.addHead(front.key, front.value);
            front = front.next;
        }
        return newList;
    }

    public static LinkedList compressList(LinkedList list) {
        LinkedList newList = new LinkedList();
        Node front = list.head;
        while (front != null) {
            if (newList.find(front.key) == null) {
                newList.addTail(front.key, front.value);
            } else {
                newList.find(front.key).value += front.value;
            }
            front = front.next;
        }
        return newList;
    }

    ///////////////////////////////////////////////////////////////

}
