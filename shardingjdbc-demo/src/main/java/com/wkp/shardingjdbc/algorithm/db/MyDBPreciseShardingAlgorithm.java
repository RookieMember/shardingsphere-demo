package com.wkp.shardingjdbc.algorithm.db;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author wangkp
 * @description StandardShardingStrategy策略的精确分片算法
 * @create 2021-04-30 18:09
 */
public class MyDBPreciseShardingAlgorithm implements PreciseShardingAlgorithm {

    /**
     * Sharding.
     *
     * @param availableTargetNames 所有分库或者分表的集合
     * @param shardingValue  分片值,PreciseShardingValue类有三个属性
     *                      logicTableName 逻辑表名;
     *                      columnName 分片键(字段);
     *                      value 从SQL中解析出的分片健的值;
     *
     * @return 分片结果:分片库或者表的名称
     */
    @Override
    public String doSharding(Collection availableTargetNames, PreciseShardingValue shardingValue) {
        for(Object each:availableTargetNames){
            int remainder = (int)shardingValue.getValue() % 2;
            if(each.toString().endsWith(remainder+"")){
                return each.toString();
            }
        }
        throw new UnsupportedOperationException("未找到分片数据节点");
    }

}
