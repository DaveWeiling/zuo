package com.itheima.tourhelper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itheima.tourhelper.MainActivity;
import com.itheima.tourhelper.R;
import com.itheima.tourhelper.presenter.LogingPresenter;
import com.itheima.tourhelper.presenter.imple.LoggingPresentImple;
import com.itheima.tourhelper.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoggingActivity extends BaseActivity implements TextView.OnEditorActionListener,LogingView {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView((R.id.til_pwd))
    TextInputLayout tilPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_newuser)
    TextView tvNewuser;
    @BindView(R.id.tv_trial)
    TextView tvTrial;
    private boolean isRegister =false;
    private LogingPresenter mLogingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        ButterKnife.bind(this);
        mLogingPresenter =new LoggingPresentImple(this);
        etPwd.setOnEditorActionListener(this);
        //数据回显
        dataResee();

    }

    private void dataResee() {
        etUsername.setText(getUsername());
        etPwd.setText(getPwd());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        isRegister = intent.getBooleanExtra("isFromRegister",false);
        if (isRegister) {
            Intent intent1 =new Intent(this,MainActivity.class);
            startActivity(intent1,true);
        }
       // Log.i("dssddd", "onNewIntent: ");
        dataResee();
    }


    @OnClick({R.id.btn_login, R.id.tv_newuser, R.id.tv_trial})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                logging();
                break;
            case R.id.tv_newuser:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent, false);
                break;
            case R.id.tv_trial:
                Intent intent1 =new Intent(this,MainActivity.class);
                startActivity(intent1,true);
                break;
        }
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v ==etPwd) {
            logging();
            return true;
        }
        return false;
    }
    private void logging() {
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
            return;
        }else {
            tilPwd.setErrorEnabled(false);
        }
        showDialog("玩命登录中....");
        mLogingPresenter.logining(username, pwd);
    }

    @Override
    public void onLoging(String username,String pwd, boolean isSuccess, String msg) {
        dismissDialog();
        if (isSuccess) {
            saveUser(username,pwd);
            Intent intent =new Intent(this,MainActivity.class);
            startActivity(intent,true);
        }else {
            showToast("登录失败"+msg);
        }
    }
}
