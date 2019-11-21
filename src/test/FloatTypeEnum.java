package test;

/**
 * @author wangql
 * @since 2019/11/18 7:59 下午
 */
public enum FloatTypeEnum {

    /**
     * 直播飘屏
     */
    LIVE_FLOAT(1),
    /**
     * 娱乐厅飘屏
     */
    HALL_FLOAT(5);

    private Integer appId;

    FloatTypeEnum(Integer appId) {
        this.appId = appId;
    }

    public Integer getAppId() {
        return appId;
    }
}
