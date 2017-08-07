package com.gavel.core.db.impl;


import com.gavel.core.db.IUserDao;
import com.gavel.db.entity.User;
import com.gavel.db.generated.DaoSession;
import com.gavel.db.generated.UserDao;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jiahua on 17-2-24.
 * Description：
 */

public class UserDaoImpl implements IUserDao
{
    private UserDao mDao;

    @Inject
    public UserDaoImpl(DaoSession session)
    {
        mDao = session.getUserDao();
    }


    @Override
    public void saveUserLists(final List<User> list)
    {
        if (list == null || list.isEmpty())
        {
            return;
        }
        mDao.getSession().runInTx(() ->
        {
            for (User user : list)
            {
                mDao.insertOrReplace(user);
            }
        });
    }

    @Override
    public User insertOrReplace(final User user)
    {
        if (mDao.insertOrReplace(user) > 0)
        {
            return user;
        }
        return null;
    }

    @Override
    public List<User> loadAll()
    {
        return mDao.loadAll();
    }

    @Override
    public void deleteAll()
    {
        mDao.deleteAll();
    }


}
