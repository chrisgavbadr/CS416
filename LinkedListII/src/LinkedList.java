public class LinkedList {
    public static class Node {
        String key;
        int value;
        Node next;
        Node prev;

        public Node(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

    private Node head;
    private Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void addHead(String key, int val) {
        Node n = new Node(key, val);

        if (head == null) {
            head = n;
            tail = n;
        } else {
            head.prev = n;
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
            n.prev = tail;
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

    public Node find(String key) {
        Node curr = head;

        while (curr != null) {
            if (curr.key.equals(key))
                return curr;

            curr = curr.next;
        }

        return null;
    }


    //////////////////////// YOUR CODE HERE ////////////////////////

    public void unlinkNode(Node n) {
        if (n != head && n != tail) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        } else if (n == head && n != tail) {
            head = n.next;
            head.prev = null;
        } else if (n != head) {
            tail = n.prev;
            tail.next = null;
        }
        n.prev = null;
        n.next = null;
    }

    public void addAfter(Node n, Node before) {
        n.prev = before;
        n.next = before.next;
        if (before != tail) {
            before.next.prev = n;
        } else {
            tail = n;
        }
        before.next = n;
    }

    public boolean swapIfNeeded(Node n) {
        boolean swap = n != tail && n.key.compareTo(n.next.key) > 0;
        if (swap) {
            Node before = n.next;
            unlinkNode(n);
            addAfter(n, before);
        }
        return swap;
    }

    public void sort() {
        boolean sorted;
        do {
            Node current = head;
            sorted = true;
            do {
                if (swapIfNeeded(current)) {
                    sorted = false;
                }
                current = current.next;
            } while (current != null);
        } while (!sorted);
    }

    ///////////////////////////////////////////////////////////////

}
