package com.gavel.auth.ui.activity.welcome;

import com.gavel.auth.base.mvp.ActivityView;
import com.gavel.auth.base.mvp.Moudle;
import com.gavel.basic.auth.dto.UserDTO;
import com.gavel.http.entity.CommonResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author jiahua
 * @Description: $description
 * @date 2017/08/08
 */
public interface WelComeContract
{
    interface IMoudle extends Moudle
    {

        Observable<CommonResponse<String>> testEmpty();

        Observable<CommonResponse<List<UserDTO>>> testEmptyList();

        Observable<CommonResponse<UserDTO>> testObject();

        Observable<CommonResponse<UserDTO>> testObjectNull();

        Observable<CommonResponse<List<UserDTO>>> testList();

        Observable<CommonResponse<List<UserDTO>>> requsetListNull();

    }

    interface IView extends ActivityView
    {
    }

    interface IPresenter
    {

        void requsetEmpty();

        void requsetEmptyList();

        void requsetObject();

        void requsetObjectNull();

        void requsetList();

        void requsetListNull();

    }
}
