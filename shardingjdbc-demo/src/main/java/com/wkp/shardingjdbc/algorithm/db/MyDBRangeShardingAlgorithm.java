package com.wkp.shardingjdbc.algorithm.db;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangkp
 * @description StandardShardingStrategy策略的范围分片算法
 * @create 2021-04-30 19:15
 */
public class MyDBRangeShardingAlgorithm implements RangeShardingAlgorithm {
    /**
     * Sharding.
     *
     * @param availableTargetNames 所有分库或者分表的集合
     *                             在分库时值为所有分片库的集合 databaseNames  [ds0,ds1]
     *                             分表时为对应分片库中所有分片表的集合 tablesNames [t_order_0,t_order_1]
     * @param shardingValue  分片值,PreciseShardingValue类有三个属性
     *                      logicTableName 逻辑表名;
     *                      columnName 分片键(字段);
     *                      valueRange 里面有SQL语句范围查询的起始值;
     * @return 分片结果:分片库或者表的名称集合
     */
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
        // sql语句中范围查询between and 的起始值
        Long lower= (Long) shardingValue.getValueRange().lowerEndpoint();
        Long upper = (Long) shardingValue.getValueRange().upperEndpoint();
        //是否有查询的下界限，上界限
        boolean hasLowerBound = shardingValue.getValueRange().hasLowerBound();
        boolean hasUpperBound = shardingValue.getValueRange().hasUpperBound();

        List<String> shardingList = new ArrayList<>();
        //TODO 自定义范围分片逻辑
        return shardingList;
    }
}
