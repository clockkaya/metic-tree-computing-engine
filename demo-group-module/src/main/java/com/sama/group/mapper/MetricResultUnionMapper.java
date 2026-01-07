package com.sama.analytic.mapper;

import com.core4ct.base.BaseMapper;
import com.sama.api.ledger.bean.MetricResultUnionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: huxh
 * @description:
 * @datetime: 2025/6/30 10:43
 */
public interface MetricResultUnionMapper extends BaseMapper<MetricResultUnionDO> {

    /**
     *  在指定的指标类型和时间范围内，获取每个组织的最新记录（每个组织只取一条最新的记录）
     *
     *  sql 解释：
     *  1. SELECT * FROM ( - 外层查询，从内层查询结果中选择数据
     *  2. 内层查询部分：
     *  SELECT *, ROW_NUMBER() OVER (PARTITION BY org_code, metric_type ORDER BY id DESC) as rn - 为每条记录添加行号
     *      使用窗口函数 ROW_NUMBER()
     *      按 org_code（组织代码）和 metric_type（指标类型）分组
     *      每组内按 id 字段降序排列（最新的记录排在前面）
     *      为每组内的记录分配行号，最新记录行号为1
     *  其他筛选条件
     * 3.) ranked - 给内层查询结果起别名为ranked
     * 4. WHERE rn = 1 - 只选择行号为1的记录（即每个组织代码和指标类型组合中的最新记录）
     * 5. ORDER BY id DESC - 最终结果按 id 降序排列
     */
    List<MetricResultUnionDO> selectAlignedRecords(@Param("cm") MetricResultUnionDO queryDO);

}
