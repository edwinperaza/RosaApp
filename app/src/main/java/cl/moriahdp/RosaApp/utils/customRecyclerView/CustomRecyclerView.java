package cl.moriahdp.RosaApp.utils.customRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class CustomRecyclerView extends RecyclerView {

    public CustomRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        super.setAdapter(adapter);
        if (getAdapter() instanceof HeaderItemListener) {
            setStickyItemDecoration();
        }
    }

    private void setStickyItemDecoration() {
        HeaderItemDecoration itemDecoration = new HeaderItemDecoration((HeaderItemListener) getAdapter());
        this.addItemDecoration(itemDecoration);
    }
}
