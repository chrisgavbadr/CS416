import java.util.ArrayList;

public class BubbleSort extends Sort {

    public BubbleSort(String fileName) {
        super(fileName);
    }

    @Override
    public void sort() {
        for (int i = 0; i < samples.size() - 1; i++) {
            for (int j = 0; j < samples.size() - i - 1; j++) {
                if (samples.get(j).measurement < samples.get(j + 1).measurement) {
                    Sample temp = samples.get(j);
                    samples.set(j, samples.get(j + 1));
                    samples.set(j + 1, temp);
                }
            }
        }
    }
}
