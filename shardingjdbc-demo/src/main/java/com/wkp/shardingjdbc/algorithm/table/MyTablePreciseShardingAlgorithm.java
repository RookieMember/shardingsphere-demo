package com.wkp.shardingjdbc.algorithm.table;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author wangkp
 * @description StandardShardingStrategy策略的精确分片算法
 * @create 2021-04-30 18:09
 */
public class MyTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm {

    /**
     * Sharding.
     *
     * @param availableTargetNames 所有分库或者分表的集合
     *                             在分库时值为所有分片库的集合 databaseNames  [ds0,ds1]
     *                             分表时为对应分片库中所有分片表的集合 tablesNames [t_order_0,t_order_1]
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
        //这里不要抛异常，否则只分库不分表/只分表不分库的情况下回报错
        return null;
    }


}
