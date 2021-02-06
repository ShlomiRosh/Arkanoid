package assix;

/**
 * Interface that will control the tasks.
 * @param <T> the task to be done.
 */
public interface Task<T> {
    /**
     * Run the T type.
     * @return the running.
     */
    T run();
}
