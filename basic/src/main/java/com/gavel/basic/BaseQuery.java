package com.gavel.basic;

import java.util.List;

import lombok.Data;

/**
 * Created by jhhuang on 17-3-7
 * QQ:781913268
 * Description：与服务器接口请求实体类基类
 */
@Data
public abstract class BaseQuery
{
    protected Integer id;     //唯一ID
    protected Integer version = 1;        //数据版本号
    protected String remark;        //备注
    protected Long createAt;         //创建时间
    protected Long stateflag;       //数据状态
    protected String orderDesc = "DESC";       //排序方向
    protected boolean isOrder;      //是否排序
    protected List orderBy;       //排序列表
    protected String key;       //密钥
}
