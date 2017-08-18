package com.gavel.basic.auth.dto;

import com.gavel.basic.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jhhuang on 2017/8/13.
 * QQ:781913268
 * Description：xxx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO
{
    private String mobile;
    private String password;
}
