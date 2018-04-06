package com.product.nguyencongduc.bookticketapplication.intereface;

/**
 * Created by nguyencongduc on 4/2/18.
 */

public interface onFragmentCallback {
    void onFgCallback(Object object, int fromPage, int toPage);
    void onMenuCallback(int action);
    void onHeaderCalback(int action);
}