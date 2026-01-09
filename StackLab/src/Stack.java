public class Stack {
    private final int CAPACITY = 10;
    private int[] stack;
    private int size;
    private int index;

    public Stack() {
        stack = new int[CAPACITY];
        index = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean push(int value) {
        if (size == CAPACITY) {
            return false;
        }
        stack[size] = value;
        size++;
        return true;
    }

    public int pop() {
        int value = stack[size - 1];
        stack[size - 1] = 0;
        size--;
        return value;
    }

    public int peek() {
        return stack[size - 1];
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = size - 1; i > 0; i--) {
            string.append(stack[i]).append(", ");
        }
        if (size != 0) {
            string.append(stack[0]);
        }
        return string.toString();
    }
}
