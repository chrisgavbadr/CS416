import java.util.ArrayList;

public class InsertionSort extends Sort {

    public InsertionSort(String fileName) {
        super(fileName);
    }

    @Override
    public void sort() {
        for (int i = 1; i < samples.size(); i++) {
            Sample key = samples.get(i);
            int j = i - 1;
            while (j >= 0 && samples.get(j).measurement < key.measurement) {
                samples.set(j + 1, samples.get(j));
                j--;
            }
            samples.set(j + 1, key);
        }
    }
}
