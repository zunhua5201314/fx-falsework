package com.epri.fx.server.msg;

/**
 *
 * @Description:
 *
 * @param:
 * @return:
 * @auther: liwen
 * @date: 2020/8/2 10:57 上午
 */
public class BaseResponse {
    private int status = 200;
    private String message;

    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
