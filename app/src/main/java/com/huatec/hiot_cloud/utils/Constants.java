package com.huatec.hiot_cloud.utils;

/**
 * 常量类
 */
public class Constants {

    public static final int MAIN_FRAGMENT_COUNT = 4;

    /**
     * 主页viewpager的消息fragment片段序号
     */
    public static final int MAIN_VIEWPAGER_INDEX_MESSAGE = 0;
    public static final int MAIN_VIEWPAGER_INDEX_EQUIPMENT = 1;
    public static final int MAIN_VIEWPAGER_INDEX_SCENE = 2;
    public static final int MAIN_VIEWPAGER_INDEX_MINE = 3;


    public static final String LOGIN_CODE_APP = "app";

    public static final String REGISTER_TYPE_NORMAL = "1";

    /**
     * 服务端消息返回状态成功
     */
    public static final int MSG_STATUS_SUCCESS = 1;

    /**
     * form-data类型
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    /**
     * token失效状态
     */
    public static final int MSG_STATUS_TOKEN_OUT = -100;

    /**
     * 设备已绑定状态
     */
    public static final int DEVICE_STATUS_BINDED = 1;
    /**
     * 设备未绑定状态
     */
    public static final int DEVICE_STATUS_UNBINDED = 0;

    /**
     * 设备id
     */
    public static final String INTENT_EXTRA_DEVICE_ID = "DEVICE_ID";

    /**
     * 已激活
     */
    public static final String DEVICE_STATUS_ACTIVITY = "1";

    /**
     * 未激活
     */
    public static final String DEVICE_STATUS_UN_ACTIVITY = "0";

    /**
     * 数值类型
     */
    public static final String DATA_STREAM_TYPE_VALUE = "1";
    /**
     * 开关类型
     */
    public static final String DATA_STREAM_TYPE_SWITCH = "2";
    /**
     * GPS类型
     */
    public static final String DATA_STREAM_TYPE_GPS = "3";
    /**
     * 文本类型
     */
    public static final String DATA_STREAM_TYPE_TEXT = "4";

    /**
     * 开关开启状态
     */
    public static final int SWITCH_STATUS_ON = 1;

    /**
     * 开关关闭状态
     */
    public static final int SWITCH_STATUS_OFF = 0;

    /**
     * 通道id参数名称
     */
    public static final String INTENT_EXTRA_UP_DATASTREAM_ID = "INTENT_EXTRA_UP_DATASTREAM_ID";

    /**
     * 默认每页返回数量
     */
    public static final int DEFAULT_DATASTREAM_LIMIT = 10;
}
