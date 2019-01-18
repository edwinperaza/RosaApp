package cl.moriahdp.RosaApp.homeDetail.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import cl.moriahdp.RosaApp.R;
import cl.moriahdp.RosaApp.baseclasses.BaseFragmentView;
import cl.moriahdp.RosaApp.home.modelObject.HomeModelObject;
import cl.moriahdp.RosaApp.homeDetail.fragment.HomeDetailFragment;

public class HomeDetailView extends BaseFragmentView {

    private Context context;
    private Button btnHomeDetailWatch;
    private ImageView ivHomeDetailImage;
    private TextView tvHomeDetailTitle;
    private TextView tvHomeDetailDescription;

    public HomeDetailView(HomeDetailFragment fragment, View rootView, Bus bus) {
        super(fragment, rootView, bus);
        this.context = fragment.getContext();
        setUpView(rootView);
    }

    private void setUpView(View rootView) {
        btnHomeDetailWatch = rootView.findViewById(R.id.btn_home_detail_watch);
        ivHomeDetailImage = rootView.findViewById(R.id.iv_home_detail_image);
        tvHomeDetailTitle = rootView.findViewById(R.id.tv_home_detail_title);
        tvHomeDetailDescription = rootView.findViewById(R.id.tv_sermon_detail_description);
    }

    public void setSermonDetail(final HomeModelObject sermon) {

        Picasso.get().load(sermon.getImageUrl()).into(ivHomeDetailImage);
        tvHomeDetailTitle.setText(sermon.getTitle());
        tvHomeDetailDescription.setText(sermon.getDescription());
        btnHomeDetailWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    public void showSermonDetailError() {
        Toast.makeText(context, R.string.sermon_detail_error, Toast.LENGTH_LONG).show();
    }
}
