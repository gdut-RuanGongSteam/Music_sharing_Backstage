package com.zhuanye.music_system.util;

import java.util.List;

/**
 * @auther:sabot
 * @date:2020/05/07
 * @description:返回结果包装类
 */
public class ResultMessage {

    //结果
    private boolean flag = false;
    //信息
    private List<String> msg;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<String> getMsg() {
        return msg;
    }

    public void setMsg(List<String> msg) {
        this.msg = msg;
    }
}
