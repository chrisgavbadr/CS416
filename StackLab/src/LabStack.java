import java.util.ArrayList;

public class LabStack {
    public static ArrayList<Integer> popN(Stack st, int n) {
        ArrayList<Integer> popped = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            popped.add(st.pop());
        }
        return popped;
    }

    public static ArrayList<Integer> popAll(Stack st) {
        return popN(st, st.size());
    }

    public static int[] reverse(int[] arr) {
        Stack placeholder = new Stack();
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            placeholder.push(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = placeholder.pop();
        }
        return reversed;
    }
}
