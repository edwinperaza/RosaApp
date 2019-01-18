package cl.moriahdp.RosaApp.baseclasses;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import cl.moriahdp.RosaApp.R;

public abstract class BaseFragment extends Fragment implements IBackPressedCallback {

    private BaseActivity baseActivity;
    protected BasePresenter basePresenter;
    protected ConnectivityManager connectivityManager;
    private View loadingOverlay;
    private View errorConnectionFullScreenView;
    private View errorLoadingDataFullScreenView;
    private View emptyStateFullScreenView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_base, container, false);
        FrameLayout mMainContainer = rootView.findViewById(R.id.main_fragment_container);
        loadingOverlay = rootView.findViewById(R.id.pb_base);
        errorConnectionFullScreenView = rootView.findViewById(R.id.error_connection);
        errorLoadingDataFullScreenView = rootView.findViewById(R.id.error_loading_data);
        emptyStateFullScreenView = rootView.findViewById(R.id.empty_state);
        View content = onCreateEventView(inflater, null, savedInstanceState);
        mMainContainer.addView(content);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        connectivityManager = (ConnectivityManager) baseActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public static boolean isConnectionOff(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        return info == null || !info.isConnectedOrConnecting();
    }

    public void hideEmptyStateLayout() {
        emptyStateFullScreenView.setVisibility(View.GONE);
    }

    public void showNoObjectiveFullScreen(final OnNoObjectiveCallback onNoObjectiveCallback) {
        emptyStateFullScreenView.setVisibility(View.VISIBLE);
        Button retryBtn = emptyStateFullScreenView.findViewById(R.id.btn_privacy_state);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoObjectiveCallback.onNoObjectiveCallback();
            }
        });
    }

    public void showErrorConectionFullScreen(final OnErrorConectionCallback onErrorConectionCallback) {
        errorConnectionFullScreenView.setVisibility(View.VISIBLE);
        RelativeLayout retryBtn = (RelativeLayout) errorConnectionFullScreenView.findViewById(R.id.rl_continue_btn_container);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorConectionCallback.onErrorConectionRetry();
            }
        });
    }

    public void hideErrorConectionFullScreen() {
        errorConnectionFullScreenView.setVisibility(View.GONE);
    }

    public void showErrorLoadingDataFullScreen(final OnErrorLoadingDataCallback onErrorLoadingDataCallback) {
        errorLoadingDataFullScreenView.setVisibility(View.VISIBLE);
        RelativeLayout retryBtn = (RelativeLayout) errorLoadingDataFullScreenView.findViewById(R.id.rl_retry_btn_container);
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorLoadingDataCallback.onErrorLadingDataRetry();
            }
        });
    }

    public void hideErrorLoadingDataFullScreen() {
        errorConnectionFullScreenView.setVisibility(View.GONE);
    }


    public void setBasePresenter(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }

    public void showLoadingOverlay() {
        if (!isLoadingOverlayShowing()) {
            loadingOverlay.bringToFront();
            loadingOverlay.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoadingOverlay() {
        if (isLoadingOverlayShowing()) {
            loadingOverlay.setVisibility(View.GONE);
        }
    }

    private boolean isLoadingOverlayShowing() {
        return loadingOverlay.getVisibility() == View.VISIBLE;
    }

    protected abstract View onCreateEventView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public interface OnErrorConectionCallback {
        void onErrorConectionRetry();
    }

    public interface OnNoObjectiveCallback {
        void onNoObjectiveCallback();
    }

    public interface OnErrorLoadingDataCallback {
        void onErrorLadingDataRetry();
    }

}
