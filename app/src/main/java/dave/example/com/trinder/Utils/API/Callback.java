package dave.example.com.trinder.Utils.API;

/**
 * Created by npaters on 20/02/15.
 */

public interface Callback<T> {
    void execute(T type);
}