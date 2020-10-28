package com.epri.fx.server.msg.auth;


import com.epri.fx.server.constant.RestCodeConstants;
import com.epri.fx.server.msg.BaseResponse;

/**
 *
 * @Description:
 *
 * @param:
 * @return:
 * @auther: liwen
 * @date: 2020/8/2 10:56 上午
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
