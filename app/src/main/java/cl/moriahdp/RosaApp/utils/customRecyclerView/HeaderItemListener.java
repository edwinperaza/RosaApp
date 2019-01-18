package cl.moriahdp.RosaApp.utils.customRecyclerView;

import android.view.View;

public interface HeaderItemListener {
    Integer getHeaderPositionForItem(Integer itemPosition);

    Integer getHeaderLayout(Integer headerPosition);

    void bindHeaderData(View header, Integer headerPosition);

    Boolean isHeader(Integer itemPosition);
}
