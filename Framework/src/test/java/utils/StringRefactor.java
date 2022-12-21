package utils;

public class StringRefactor {
    public Double makeTextToPrice(String text) {
        return Double.parseDouble(text.replace("$", "").replace(",", ""));
    }

    public int takeIntFromCounter(String text) {
        return Integer.parseInt(text);
    }
}
