package com.itheima.tourhelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itheima.tourhelper.MainActivity;
import com.itheima.tourhelper.R;
import com.itheima.tourhelper.presenter.RegisterPresenter;
import com.itheima.tourhelper.presenter.imple.RegisterPresenterImple;
import com.itheima.tourhelper.utils.StringUtils;
import com.itheima.tourhelper.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mRegisterPresenter =new RegisterPresenterImple(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        register();
    }

    private void register() {
        String username = etUsername.getText().toString().trim();
        String pwd =etPwd.getText().toString().trim();
        if (!StringUtils.checkUsername(username)) {
            tilUsername.setErrorEnabled(true);
            tilUsername.setError("用户名不合法");
            etUsername.requestFocus(View.FOCUS_RIGHT);
            return;
        }else {
            tilUsername.setErrorEnabled(false);
        }
        if (!StringUtils.checkPsw(pwd)) {
            tilPwd.setErrorEnabled(true);
            tilPwd.setError("密码不合法");
            tilPwd.requestFocus(View.FOCUS_RIGHT);
        }else {
            tilPwd.setErrorEnabled(false);
        }
        showDialog("正在注册");
        mRegisterPresenter.onRegister(username,pwd);
    }

    @Override
    public void onRegister(String username, String pwd, boolean isSuccess, String msg) {
        if (isSuccess) {
            Intent intent =new Intent(this,LoggingActivity.class);
            intent.putExtra("isFromRegister",true);
            startActivity(intent,true);
            dismissDialog();
        }else {
            ToastUtils.showToast(this,msg);
            dismissDialog();
        }
    }
}
