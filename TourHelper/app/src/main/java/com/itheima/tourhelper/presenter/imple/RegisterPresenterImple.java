package com.itheima.tourhelper.presenter.imple;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.itheima.tourhelper.presenter.RegisterPresenter;
import com.itheima.tourhelper.ui.activity.RegisterView;

/**
 * Created by Administrator on 2017/1/15.
 */
public class RegisterPresenterImple implements RegisterPresenter {
    private RegisterView mRegisterView ;

    public RegisterPresenterImple(RegisterView mRegisterView) {
        this.mRegisterView = mRegisterView;
    }

    @Override
    public void onRegister(final String username, final String pwd) {
        AVUser user = new AVUser();// 新建 AVUser 对象实例
        user.setUsername(username);// 设置用户名
        user.setPassword(pwd);// 设置密码
        //user.setEmail("tom@leancloud.cn");// 设置邮箱
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    // 注册成功
                    mRegisterView.onRegister(username,pwd,true,null);
                } else {
                    // 失败的原因可能有多种，常见的是用户名已经存在。
                    mRegisterView.onRegister(username,pwd,false,e.getMessage());
                }
            }
        });
    }
}
