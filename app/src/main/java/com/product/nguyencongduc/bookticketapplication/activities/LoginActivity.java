package com.product.nguyencongduc.bookticketapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;

import com.product.nguyencongduc.bookticketapplication.R;
import com.product.nguyencongduc.bookticketapplication.utils.ActivityManager;
import com.product.nguyencongduc.bookticketapplication.utils.LocalStorage;
import com.product.nguyencongduc.uiticketlibrary.ButtonDefault;
import com.product.nguyencongduc.uiticketlibrary.EditTextDefault;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private ActivityManager mActivityManager;
    private LocalStorage mLocalStorage;

    @BindView(R.id.btn_login)
    protected ButtonDefault mBtnLogin;

    @BindView(R.id.edt_id)
    protected EditTextDefault mEdtId;

    @BindView(R.id.edt_password)
    protected EditTextDefault mEdtPassword;

    @BindView(R.id.cb_storage_user)
    protected CheckBox mCheckBox;

    @OnClick(R.id.btn_login)
    protected void onBtnLoginAction() {
        mLocalStorage.setLoginSetting(mCheckBox.isChecked(), mEdtId.getText().toString(), "token").commit();
        mActivityManager.getMainPageActivity().keepParentActivity(false).build().start();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityManager = new ActivityManager(this);
        setDefaultData();
        onValidate();
    }

    private void setDefaultData() {
        mLocalStorage = new LocalStorage(this );
        mCheckBox.setChecked(mLocalStorage.getIsSaveLogin());
        if (mLocalStorage.getIsSaveLogin()) {
            if (mLocalStorage.getKeyToken().toString().equals("")) {
                this.mEdtId.setText(mLocalStorage.getUserName());
            } else {
                mActivityManager.getMainPageActivity().keepParentActivity(false).build().start();
            }
        }
    }

    private void onValidate() {
        mBtnLogin.setEnabled(!mEdtId.getText().toString().equals("") && !mEdtPassword.getText().toString().equals(""));
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mBtnLogin.setEnabled(!mEdtId.getText().toString().equals("") && !mEdtPassword.getText().toString().equals(""));
            }
        };
        mEdtId.addTextChangedListener(textWatcher);
        mEdtPassword.addTextChangedListener(textWatcher);
    }
}
