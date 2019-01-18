package cl.moriahdp.RosaApp.db;

public interface ResponseListener<T> {
    void onResponse(T body);
}
