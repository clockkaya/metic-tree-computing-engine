package com.sama.api.ledger.bean.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.core4ct.base.BaseModel;

import java.io.Serial;

/**
 * 分页通用项
 * @author: huxh
 * @description: 反向验证了 Mybatis 框架下（？）父类未加注解默认存在
 * @datetime: 2025/10/23 16:36
 */
public class PageBaseModel extends BaseModel {

    @Serial
    private static final long serialVersionUID = 8542619900085394360L;

    @TableField(exist = false)
    private Integer current;

    @TableField(exist = false)
    private Integer size;

    public Integer getCurrent() {
        if (current == null) {
            current = 1;
        }
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        if (size == null) {
            size = 10;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageBaseModel{" +
            "current=" + current +
            ", size=" + size +
            "} " + super.toString();
    }
}
