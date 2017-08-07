package com.gavel.core.db;


import com.gavel.db.entity.User;

import java.util.List;

/**
 * Created by jhhuang on 2017/4/11
 * QQ:781913268
 * Description：xxx
 */
public interface IUserDao
{

    void saveUserLists(final List<User> list);

    User insertOrReplace(final User user);

    List<User> loadAll();

    void deleteAll();

}
