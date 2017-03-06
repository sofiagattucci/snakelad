package view.pawn;

/**
 * Enumeration of the available colors for the pawns.
 */
public enum AvailableColor {

    /**
     * Red color.
     */
    RED("color.red"),

    /**
     * Light blue color.
     */
    LIGHTBLUE("color.lightblue"),

    /**
     * Yellow color.
     */
    YELLOW("color.yellow"),

    /**
     * Green color.
     */
    GREEN("color.green"),

    /**
     * Fuchsia color.
     */
    FUCHSIA("color.fuchsia"),

    /**
     * Blue color.
     */
    BLUE("color.blue"),

    /**
     * Brown color.
     */
    BROWN("color.brown"),

    /**
     * Pink color.
     */
    PINK("color.pink"),

    /**
     * Violet color.
     */
    VIOLET("color.violet");

    private final String name;

    AvailableColor(final String name) {
        this.name = name;
    }

    /**
     * ToString of enumeration.
     * @return
     *          the name of enumeration
     */
    public String toString() {
        return this.name;
    }
}
