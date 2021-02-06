package game;
/**
 * @author Shlomi Rosh.
 */
public class Counter {
    private int counter;
    /**
     * Constructor Method.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * Add number to current count.
     * @param number after addition.
     */
    public void increase(int number) {
        this.counter += number;
    }
    /**
     * Subtract number from the current count.
     * @param number to be subtracted.
     */
    public void decrease(int number) {
        this.counter -= number;
    }
    /**
     * Gets te current Count.
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}
