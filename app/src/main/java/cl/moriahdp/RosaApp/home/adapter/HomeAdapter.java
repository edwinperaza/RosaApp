package cl.moriahdp.RosaApp.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;
import cl.moriahdp.RosaApp.utils.recyclerListener.RecyclerOnItemClickListener;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sermonView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sermons, parent, false);
        return new ViewHolder(sermonView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModelObject homeItem = homeModelObjectList.get(position);
        holder.title.setText(homeItem.getTitle());
        holder.date.setText(homeItem.getDate());
        Picasso.get().load(homeItem.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return homeModelObjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        ImageView image;

        public ViewHolder(View itemView, final RecyclerOnItemClickListener<HomeModelObject> listener) {
            super(itemView);

            image = itemView.findViewById(R.id.iv_home_image);
            title = itemView.findViewById(R.id.tv_home_title);
            date = itemView.findViewById(R.id.tv_home_date);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickListener(homeModelObjectList.get(getLayoutPosition()));
                }
            });
        }
    }
}
