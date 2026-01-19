// Ce code définit ce qu'est une lettre dans ton alphabet. Elle a une valeur (la lettre) et on précise si c'est une action (F, +, -) ou une variable (X, Y).
public class Symbol {
    private final char value;

    public Symbol(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}