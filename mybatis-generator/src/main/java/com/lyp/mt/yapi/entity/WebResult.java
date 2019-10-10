package com.lyp.mt.yapi.entity;

import com.lyp.mt.yapi.entity.yapientity.Field;
import com.lyp.mt.yapi.entity.yapientity.Page;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 21:22
 */
public class WebResult {
    private Field code;
    private Field message;
    private Page page;
    private Object result;

    public Field getCode() {
        return code;
    }

    public void setCode(Field code) {
        this.code = code;
    }

    public Field getMessage() {
        return message;
    }

    public void setMessage(Field message) {
        this.message = message;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


}
