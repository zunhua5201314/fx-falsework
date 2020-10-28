package com.epri.fx.server.msg;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-14 22:40
 */
public class TableResultResponse<T> extends BaseResponse {

    private long total;
    private List<T> datas;

    public TableResultResponse(long total, List<T> rows) {
        this.total = total;
        this.datas = rows;
    }

    public TableResultResponse() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
