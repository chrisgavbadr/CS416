import java.text.DecimalFormat;

public class Rock {
    private String name;
    private double numPounds;
    private double volume;

    public Rock(String name) {
        this.name = name;
    }

    public int calculateDensity() {
        return (int) (numPounds / volume);
    }

    public void increasePounds(double increase) {
        numPounds += increase;
    }

    public double decreasePounds(double decrease) {
        numPounds += decrease;
        return numPounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumPounds() {
        return numPounds;
    }

    public void setNumPounds(double numPounds) {
        this.numPounds = numPounds;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat(".###");
        return "Rock a weighs " + df.format(numPounds) + " pounds with a density of " + calculateDensity();
    }
}
