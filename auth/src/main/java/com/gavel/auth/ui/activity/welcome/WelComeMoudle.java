package com.gavel.auth.ui.activity.welcome;

import com.gavel.basic.auth.dto.UserDTO;
import com.gavel.core.net.ApiService;
import com.gavel.core.utils.HttpUtils;
import com.gavel.http.ClientManager;
import com.gavel.http.entity.CommonResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author jiahua
 * @Description: $description
 * @date 2017/08/08
 */
public class WelComeMoudle implements WelComeContract.IMoudle
{
    @Inject
    public WelComeMoudle()
    {
    }


    @Override
    public Observable<CommonResponse<String>> testEmpty()
    {
        return ClientManager.getClient(HttpUtils.getApiServer()).create(ApiService.class).testEmpty("15180009216");
    }

    @Override
    public Observable<CommonResponse<List<UserDTO>>> testEmptyList()
    {
        return ClientManager.getClient(HttpUtils.getApiServer()).create(ApiService.class).testEmptyList();
    }

    @Override
    public Observable<CommonResponse<UserDTO>> testObject()
    {
        return ClientManager.getClient(HttpUtils.getApiServer()).create(ApiService.class).testObject();
    }

    @Override
    public Observable<CommonResponse<UserDTO>> testObjectNull()
    {
        return ClientManager.getClient(HttpUtils.getApiServer()).create(ApiService.class).testObjectNull();
    }

    @Override
    public Observable<CommonResponse<List<UserDTO>>> testList()
    {
        return ClientManager.getClient(HttpUtils.getApiServer()).create(ApiService.class).testList();
    }

    @Override
    public Observable<CommonResponse<List<UserDTO>>> requsetListNull()
    {
        return ClientManager.getClient(HttpUtils.getApiServer()).create(ApiService.class).requsetListNull();
    }

}
