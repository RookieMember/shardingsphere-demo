package com.wkp.shardingjdbc.algorithm.db;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
 * @author wangkp
 * @description ComplexShardingStrategy策略的复合分片算法
 * @create 2021-04-30 20:03
 */
public class MyDBComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    /**
     * Sharding.
     *
     * @param availableTargetNames 所有分库或者分表的集合
     *                             在分库时值为所有分片库的集合 databaseNames  [ds0,ds1]
     *                             分表时为对应分片库中所有分片表的集合 tablesNames [t_order_0,t_order_1]
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        //等值查询分片键及值的map
        Map columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        //范围查询分片键及值的map
        Map columnNameAndRangeValuesMap = shardingValue.getColumnNameAndRangeValuesMap();
        Collection<Long> userIdList = getShardingValue(shardingValue, "user_id");
        Collection<Long> orderIdList = getShardingValue(shardingValue, "order_id");

        List<String> shardingList = new ArrayList<>();
        // user_id，order_id分片键进行分表
        for (Long userId : userIdList) {
            for (Long orderId : orderIdList) {
                Long suffix = (userId+orderId) % 2;
                for (Object each : availableTargetNames) {
                    if (each.toString().endsWith(suffix.toString())) {
                        shardingList.add(each.toString());
                    }
                }
            }
        }

        return shardingList;
    }

    private Collection<Long> getShardingValue(ComplexKeysShardingValue<Long> shardingValues, final String key) {
        Collection<Long> valueSet = new ArrayList<>();
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = shardingValues.getColumnNameAndShardingValuesMap();
        if (columnNameAndShardingValuesMap.containsKey(key)) {
            valueSet.addAll(columnNameAndShardingValuesMap.get(key));
        }
        return valueSet;
    }
}
