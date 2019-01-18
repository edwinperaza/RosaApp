package cl.moriahdp.RosaApp.home.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;
import cl.moriahdp.RosaApp.utils.customRecyclerView.HeaderItemListener;
import cl.moriahdp.RosaApp.utils.recyclerListener.RecyclerOnItemClickListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements HeaderItemListener {

    private List<HomeModelObject> homeModelObjectList = new ArrayList<>();
    private RecyclerOnItemClickListener<HomeModelObject> listener;

    public HomeAdapter() {

    }

    public void setHomeModelObjectList(List<HomeModelObject> homeModelObjectList) {
        this.homeModelObjectList = homeModelObjectList;
        notifyDataSetChanged();
    }

    public void setListener(RecyclerOnItemClickListener<HomeModelObject> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == 1) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diner_header, parent, false);
            return new ItemDinerHeaderHolder(itemView, listener);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diner, parent, false);
            return new ItemDinerHolder(itemView, listener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeModelObject homeItem = homeModelObjectList.get(position);
        if (getItemViewType(position) == 1) {
            ( (ItemDinerHeaderHolder) holder).title.setText(homeItem.getTitle());
        } else {
            ((ItemDinerHolder) holder).title.setText(homeItem.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return homeModelObjectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return homeModelObjectList.get(position).getType();
    }

    @Override
    public Integer getHeaderPositionForItem(Integer itemPosition) {
        Integer headerPosition = 0;
        for (Integer i = itemPosition;i > 0 ;i--){
            if (isHeader(i)){
                headerPosition = i;
                return headerPosition;
            }
        }
        return headerPosition;
    }

    @Override
    public Integer getHeaderLayout(Integer headerPosition) {
        return R.layout.item_diner_header;
    }

    @Override
    public void bindHeaderData(View header, Integer headerPosition) {
        TextView tv = header.findViewById(R.id.tv_item_diner_header_title);
        tv.setText(homeModelObjectList.get(headerPosition).getTitle());
    }

    @Override
    public Boolean isHeader(Integer itemPosition) {
        //TODO implement IntDef
        return homeModelObjectList.get(itemPosition).getType() == 1;
    }

    public class ItemDinerHolder extends RecyclerView.ViewHolder {

        ConstraintLayout itemContainer;
        CircleImageView profileImage;
        TextView title;
        ImageView indicator;
        TextView foodType;

        public ItemDinerHolder(View itemView, final RecyclerOnItemClickListener<HomeModelObject> listener) {
            super(itemView);

            itemContainer = itemView.findViewById(R.id.cl_item_diner_container);
            profileImage = itemView.findViewById(R.id.civ_item_diner_profile);
            title = itemView.findViewById(R.id.tv_item_diner_title);
            indicator = itemView.findViewById(R.id.iv_item_diner_indicator);
            foodType = itemView.findViewById(R.id.tv_item_diner_type);

            itemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(homeModelObjectList.get(getLayoutPosition()));
                }
            });
        }
    }

    public class ItemDinerHeaderHolder extends RecyclerView.ViewHolder {

        TextView title;

        public ItemDinerHeaderHolder(View itemView, final RecyclerOnItemClickListener<HomeModelObject> listener) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_item_diner_header_title);
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(homeModelObjectList.get(getLayoutPosition()));
                }
            });
        }
    }
}
