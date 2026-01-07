package com.sama.maint.object.dto.ledger;

import com.sama.api.ledger.bean.ComprehensiveProtectionExtendedDO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/7/28 16:55
 */
public class ComprehensiveProtectionOnlineBO extends ComprehensiveProtectionExtendedDO {

    @Schema(description = "当前页")
    private Integer current;

    @Schema(description = "页面大小")
    private Integer size;

    @Schema(description = "序号")
    private String infoId;

    @Schema(description = "单元格锁定信息，key: 列属性, value: 用户ID")
    private Map<String, Long> locks = new HashMap<>();

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public Map<String, Long> getLocks() {
        return locks;
    }

    public void setLocks(Map<String, Long> locks) {
        this.locks = locks;
    }

    @Override
    public String toString() {
        return "ComprehensiveProtectionOnlineBO{" +
                "current=" + current +
                ", size=" + size +
                ", infoId='" + infoId + '\'' +
                ", locks=" + locks +
                "} " + super.toString();
    }
}
