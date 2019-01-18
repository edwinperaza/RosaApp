package cl.moriahdp.RosaApp.baseclasses;

public class BasePresenter<M extends BaseModel, V> {

    protected M model;
    protected V view;

    public BasePresenter(M model, V view) {
        this.model = model;
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
