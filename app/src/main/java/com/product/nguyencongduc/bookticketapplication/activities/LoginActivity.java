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
    protected ButtonDefault btnLogin;

    @BindView(R.id.edt_id)
    protected EditTextDefault edtId;

    @BindView(R.id.edt_password)
    protected EditTextDefault edtPassword;

    @BindView(R.id.cb_storage_user)
    protected CheckBox mCheckBox;

    @OnClick(R.id.btn_login)
    protected void onBtnLoginAction() {
        mLocalStorage.setLoginSetting(mCheckBox.isChecked(), edtId.getText().toString(),  edtPassword.getText().toString()).commit();
        mActivityManager.getMainPageActivity().build().start();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultData();
        onValidate();
        mActivityManager = new ActivityManager(this);
    }

    private void setDefaultData() {
        mLocalStorage = new LocalStorage(this );
        mCheckBox.setChecked(mLocalStorage.getIsSaveLogin());
        if (mLocalStorage.getIsSaveLogin()) {
            this.edtId.setText(mLocalStorage.getUserName());
            this.edtPassword.setText(mLocalStorage.getPassword());
        }
    }

    private void onValidate() {
        btnLogin.setEnabled(!edtId.getText().toString().equals("") && !edtPassword.getText().toString().equals(""));
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                btnLogin.setEnabled(!edtId.getText().toString().equals("") && !edtPassword.getText().toString().equals(""));
            }
        };
        edtId.addTextChangedListener(textWatcher);
        edtPassword.addTextChangedListener(textWatcher);
    }
}
