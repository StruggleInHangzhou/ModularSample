package com.gavel.basic.auth.query;

import com.gavel.basic.BaseQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jhhuang on 2017/8/13.
 * QQ:781913268
 * Description：xxx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserQuery extends BaseQuery
{
    private String mobile;
    private String password;
}
