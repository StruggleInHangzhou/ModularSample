package com.gavel.auth.ui.activity.welcome;

import com.gavel.auth.base.mvp.ActivityPresenter;
import com.gavel.basic.auth.dto.UserDTO;
import com.gavel.http.entity.CommonResponse;
import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

/**
 * @author jiahua
 * @Description: $description
 * @date 2017/08/08
 */
public class WelComePresenter extends ActivityPresenter<WelComeContract.IView, WelComeMoudle> implements WelComeContract.IPresenter
{
    @Inject
    public WelComePresenter()
    {
    }


    @Override
    public void requsetEmpty()
    {
        mMoudle.testEmpty()
                .compose(callbackOnIOThread())
                .map(CommonResponse::getData)
                .compose(verifyOnMainThread())
                .subscribe(new NetSubseriber<String>()
                {
                    @Override
                    public void onNext(String value)
                    {
                        Logger.e("onNext" + value);
                    }
                });
    }

    @Override
    public void requsetEmptyList()
    {
        mMoudle.testEmptyList()
                .compose(callbackOnIOThread())
                .map(CommonResponse::getData)
                .compose(verifyOnMainThread())
                .subscribe(new NetSubseriber<List<UserDTO>>()
                {
                    @Override
                    public void onNext(List<UserDTO> value)
                    {
                        Logger.e("onNext" + value);
                    }
                });
    }

    @Override
    public void requsetObject()
    {
        mMoudle.testObject()
                .compose(callbackOnIOThread())
                .map(CommonResponse::getData)
                .compose(verifyOnMainThread())
                .subscribe(new NetSubseriber<UserDTO>()
                {
                    @Override
                    public void onNext(UserDTO value)
                    {
                        Logger.e("onNext" + value);
                    }
                });
    }

    @Override
    public void requsetObjectNull()
    {
        mMoudle.testObjectNull()
                .compose(callbackOnIOThread())
                .map(CommonResponse::getData)
                .compose(verifyOnMainThread())
                .subscribe(new NetSubseriber<UserDTO>()
                {
                    @Override
                    public void onNext(UserDTO value)
                    {
                        Logger.e("onNext" + value);
                    }
                });
    }

    @Override
    public void requsetList()
    {
        mMoudle.testList()
                .compose(callbackOnIOThread())
                .map(CommonResponse::getData)
                .compose(verifyOnMainThread())
                .subscribe(new NetSubseriber<List<UserDTO>>()
                {
                    @Override
                    public void onNext(List<UserDTO> value)
                    {
                        Logger.e("onNext" + value);
                    }
                });
    }

    @Override
    public void requsetListNull()
    {
        mMoudle.requsetListNull()
                .compose(callbackOnIOThread())
                .map(CommonResponse::getData)
                .compose(verifyOnMainThread())
                .subscribe(new NetSubseriber<List<UserDTO>>()
                {
                    @Override
                    public void onNext(List<UserDTO> value)
                    {
                        Logger.e("onNext" + value);
                    }
                });
    }

}
