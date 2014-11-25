package cat.ppicas.cleanarch.text;

public class NumberFormat {

    private static final java.text.NumberFormat DECIMAL_FORMATTER;

    static {
        DECIMAL_FORMATTER = java.text.NumberFormat.getInstance();
        DECIMAL_FORMATTER.setMaximumFractionDigits(2);
    }

    public static String formatDecimal(double num) {
        return DECIMAL_FORMATTER.format(num);
    }

    private NumberFormat() {
        throw new RuntimeException("Instances are not allowed for this class");
    }
}
