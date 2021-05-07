package com.wkp.shardingjdbc.algorithm.table;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * @author wangkp
 * @description StandardShardingStrategy策略的范围分片算法
 * @create 2021-04-30 19:15
 */
public class MyTableRangeShardingAlgorithm implements RangeShardingAlgorithm {
    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
        // between and 的起始值
        Long lower= (Long) shardingValue.getValueRange().lowerEndpoint();
        Long upper = (Long) shardingValue.getValueRange().upperEndpoint();
        return null;
    }
}
