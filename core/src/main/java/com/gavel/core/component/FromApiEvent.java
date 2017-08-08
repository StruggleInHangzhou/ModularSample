package com.gavel.core.component;

import com.gavel.http.constant.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by jhhuang on 2017/8/8.
 * QQ:781913268
 * Description：xxx
 */
@Data
@AllArgsConstructor
public class FromApiEvent
{
    private ResponseCode codeEnum;
}
