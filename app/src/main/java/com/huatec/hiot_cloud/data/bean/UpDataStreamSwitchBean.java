package com.huatec.hiot_cloud.data.bean;

import java.io.Serializable;

/**
 * 开关通道对象
 */
public class UpDataStreamSwitchBean implements Serializable {

    /**
     * 上行通道id
     */
    private String updataStreamId;

    /**
     * 操作时间
     */
    private String timing;

    /**
     * 状态
     */
    private int status;

    public String getUpdataStreamId() {
        return updataStreamId;
    }

    public void setUpdataStreamId(String updataStreamId) {
        this.updataStreamId = updataStreamId;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
