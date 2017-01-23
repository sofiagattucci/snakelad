package utilities;
/**
 * This class manages a pair of elements.
 * @param <X>
 *     The first element of type X
 * @param <Y>
 *     The second element of type Y
 */
public class Pair<X, Y> {

    private final X first;
    private final Y second;

    /**
     * Constructor of this class.
     * @param f
     *     The first element. It must be of type X.
     * @param s
     *     The second element. It must be of type Y.
     */
    public Pair(final X f, final Y s) {
        this.first = f;
        this.second = s;
    }

    /**
     * Getter of the first element of the pair. 
     * @return
     *     The first element of the pair. Its type is X.
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * Getter of the second element of the pair.
     * @return
     *    The second element of the pair. Its type is Y.
     */
    public Y getSecond() {
        return this.second;
    }

}
