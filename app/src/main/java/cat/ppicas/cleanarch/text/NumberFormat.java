package cat.ppicas.cleanarch.text;

public class NumberFormat {

    private static final java.text.NumberFormat DECIMAL_FORMATTER
            = java.text.NumberFormat.getInstance();

    static {
        DECIMAL_FORMATTER.setMaximumFractionDigits(2);
    }

    public static String formatDecimal(double num) {
        return DECIMAL_FORMATTER.format(num);
    }

    public static String formatTemperature(double temp) {
        return DECIMAL_FORMATTER.format(temp) + "º";
    }

    public static String formatHumidity(double humidity) {
        return Math.round(humidity) + "%";
    }

    public static String formatWindSpeed(double windSpeed) {
        return DECIMAL_FORMATTER.format(windSpeed) + " m/s";
    }

    private NumberFormat() {
        throw new RuntimeException("Instances are not allowed for this class");
    }
}
