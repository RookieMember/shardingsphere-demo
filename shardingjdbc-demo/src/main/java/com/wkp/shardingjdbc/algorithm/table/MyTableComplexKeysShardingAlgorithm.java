package com.wkp.shardingjdbc.algorithm.table;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;

/**
 * @author wangkp
 * @description TODO
 * @create 2021-04-30 20:03
 */
public class MyTableComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, ComplexKeysShardingValue shardingValue) {
        return null;
    }
}
