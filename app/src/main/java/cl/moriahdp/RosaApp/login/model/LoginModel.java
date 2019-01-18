package cl.moriahdp.RosaApp.login.model;

import com.squareup.otto.Bus;

import cl.moriahdp.RosaApp.baseclasses.BaseModel;
import cl.moriahdp.RosaApp.repository.DataRepository;
import cl.moriahdp.RosaApp.login.modelObject.LoginModelObject;
import cl.moriahdp.RosaApp.registry.modelObject.ResetPasswordModelObject;
import cl.moriahdp.RosaApp.utils.data.APIService;
import cl.moriahdp.RosaApp.utils.data.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginModel extends BaseModel<DataRepository> {

    private final static String TAG = LoginModel.class.getSimpleName();

    public LoginModel(DataRepository repository, Bus bus) {
        super(repository, bus);
    }

    public void loginUser(LoginModelObject loginModelObject) {
    }

    public void resetPassword(String email) {
        APIService apiService = ApiUtils.getAPIService();
        if (!email.isEmpty()) {
            Call<ResetPasswordModelObject> call = apiService.resetPassword(new ResetPasswordModelObject(email));

            call.enqueue(new Callback<ResetPasswordModelObject>() {
                @Override
                public void onResponse(Call<ResetPasswordModelObject> call, Response<ResetPasswordModelObject> response) {
                    if (response.isSuccessful()) {
                        bus.post(new ResetPasswordSuccess());
                    } else {
                        bus.post(new ResetPasswordFailure());
                    }
                }

                @Override
                public void onFailure(Call<ResetPasswordModelObject> call, Throwable t) {
                    bus.post(new ResetPasswordFailure());
                }
            });
        }
    }

    /**
     * Classes for Bus
     */
    public class LoginSuccess {
        private boolean mustChangeLanguage;
        private String language;

        public LoginSuccess(boolean mustChangeLanguage, String language) {
            this.mustChangeLanguage = mustChangeLanguage;
            this.language = language;
        }

        public boolean isMustChangeLanguage() {
            return mustChangeLanguage;
        }

        public String getLanguage() {
            return language;
        }
    }

    public class LoginFailure {
    }

    public class RequestObjectiveDone {
        private boolean mustChangeLanguage;
        private String language;

        public RequestObjectiveDone(boolean mustChangeLanguage, String language) {
            this.mustChangeLanguage = mustChangeLanguage;
            this.language = language;
        }

        public boolean isMustChangeLanguage() {
            return mustChangeLanguage;
        }

        public String getLanguage() {
            return language;
        }
    }

    public class ResetPasswordSuccess {
    }

    public class ResetPasswordFailure {
    }
}
