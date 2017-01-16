package controller;

 /**
  * Enumeration for game turn.
  *
  */
public enum Turn {
    /**
     * Name of Player.
     */
    PLAYER("Player"),
    /**
     * Name of Computer.
     */
    COMPUTER("Computer");

    private final String actualName;

    Turn(final String actualName) {
        this.actualName = actualName;
    }

    /**
     * ToString of enumeration.
     * @return
     *          the name of enumeration
     */
    public String toString() {
        return this.actualName;
    }
}
