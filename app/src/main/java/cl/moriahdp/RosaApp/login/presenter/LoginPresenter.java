package cl.moriahdp.RosaApp.login.presenter;

import com.squareup.otto.Subscribe;

import cl.moriahdp.RosaApp.baseclasses.BasePresenter;
import cl.moriahdp.RosaApp.login.model.LoginModel;
import cl.moriahdp.RosaApp.login.view.LoginView;

public class LoginPresenter extends BasePresenter<LoginModel, LoginView>{

    public LoginPresenter(LoginModel model, LoginView view) {
        super(model, view);
    }

    public void onResumed() {
        view.onResumed();
    }

    @Subscribe
    public void onLoginOnClickEvent (LoginView.LoginOnClickEvent event) {
//        model.loginUser(event.getLoginModelObject());
        view.loginSuccess();

    }

    @Subscribe
    public void onLoginSuccess (LoginModel.LoginSuccess event) {
        view.loginSuccess();
    }


    @Subscribe
    public void onLoginFailure (LoginModel.LoginFailure event) {
        view.loginFailure();
    }

    @Subscribe
    public void onResetPassword(LoginView.ResetPasswordEvent event) {
        model.resetPassword(event.getEmail());
    }

    @Subscribe
    public void onResetPassword(LoginModel.ResetPasswordSuccess event) {
        view.resetSuccess();
    }

    @Subscribe
    public void onResetPassword(LoginModel.ResetPasswordFailure event) {
        view.resetFailure();
    }
}