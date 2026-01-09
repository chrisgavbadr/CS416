/**
 * @author chrisgavbadr
 * @version 1.0
 */
public class Color {
    static final Color BLACK = new Color(0, 0, 0);
    static final Color RED = new Color(255, 0, 0);
    static final Color GREEN = new Color(0, 255, 0);
    static final Color YELLOW = new Color(255, 255, 0);
    static final Color BLUE = new Color(0, 0, 255);
    static final Color MAGENTA = new Color(202, 31, 123);
    static final Color CYAN = new Color(0, 183, 235);
    static final Color WHITE = new Color(255, 255, 255);

    private static final int OFFSET = 32;
    private static final double FACTOR = 0.2;

    private int red;
    private int green;
    private int blue;

    /**
     * @param red [int] Red value in RGB
     * @param green [int] Green value in RGB
     * @param blue [int] Blue value in RGB
     */
    public Color(int red, int green, int blue) {
        this.red = validateColorValue(red);
        this.green = validateColorValue(green);
        this.blue = validateColorValue(blue);
    }

    /**
     * @param value [int] Arbitrary RGB color value
     * @return [int] Valid RGB color value
     */
    private int validateColorValue(int value) {
        if (value < 0) {
            return 0;
        } else if (value > 255) {
            return 255;
        }
        return value;
    }

    /**
     * @param addend [Color] Color object to be added
     * @return [Color] Sum of this color and addend
     */
    public Color add(Color addend) {
        red = validateColorValue(this.red + addend.getRed());
        green = validateColorValue(this.green + addend.getGreen());
        blue = validateColorValue(this.blue + addend.getBlue());
        return new Color(red, green, blue);
    }

    /**
     * @param minuend [Color] Color object to be subtracted
     * @return [Color] Difference of this color and minuend
     */
    public Color sub(Color minuend) {
        red = validateColorValue(this.red - minuend.getRed());
        green = validateColorValue(this.green - minuend.getGreen());
        blue = validateColorValue(this.blue - minuend.getBlue());
        return new Color(red, green, blue);
    }

    /**
     * @return [Color] A lightened version of this color
     */
    public Color lighten() {
        return new Color(validateColorValue(this.red + OFFSET),
                validateColorValue(this.green + OFFSET),
                validateColorValue(this.blue + OFFSET));
    }

    /**
     * @return [Color] A darkened version of this color
     */
    public Color darken() {
        return new Color(validateColorValue(this.red - OFFSET),
                validateColorValue(this.green - OFFSET),
                validateColorValue(this.blue - OFFSET));
    }

    /**
     * @return [Color] A brightened version of this color
     */
    public Color brighten() {
        return new Color(validateColorValue((int) ((1 + FACTOR) * this.red)),
                validateColorValue((int) ((1 + FACTOR) * this.green)),
                validateColorValue((int) ((1 + FACTOR) * this.blue)));
    }

    /**
     * @return [Color] A dimmed version of this color
     */
    public Color dim() {
        return new Color((int) ((1 - FACTOR) * this.red),
                (int) ((1 - FACTOR) * this.green),
                (int) ((1 - FACTOR) * this.blue));
    }

    /**
     * @param color [Color] A color object to be compared with
     * @return [boolean] Whether or not the colors are the same (same RGB values)
     */
    public boolean equals(Color color) {
        return this.red == color.getRed() && this.green == color.getGreen() && this.blue == color.getBlue();
    }

    /**
     * @return [int] This color's red value
     */
    public int getRed() {
        return red;
    }

    /**
     * @param red [int] A new red value
     */
    public void setRed(int red) {
        this.red = validateColorValue(red);
    }

    /**
     * @return [int] This color's green value
     */
    public int getGreen() {
        return green;
    }

    /**
     * @param green [int] A new green value
     */
    public void setGreen(int green) {
        this.green = validateColorValue(green);
    }

    /**
     * @return [int] This color's blue value
     */
    public int getBlue() {
        return blue;
    }

    /**
     * @param blue [int] A new blue value
     */
    public void setBlue(int blue) {
        this.blue = validateColorValue(blue);
    }

    /**
     * @return [String] This color in hexadecimal
     */
    public String toString() {
        String[] rgb = {Integer.toHexString(this.red), Integer.toHexString(this.green), Integer.toHexString(this.blue)};
        for (int i = 0; i < rgb.length; i++) {
            if (rgb[i].length() == 1) {
                rgb[i] = "0" + rgb[i];
            }
        }
        return "#" + rgb[0] + rgb[1] + rgb[2];
    }
}
