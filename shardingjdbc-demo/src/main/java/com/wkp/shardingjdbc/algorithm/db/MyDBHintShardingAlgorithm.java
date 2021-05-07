package com.wkp.shardingjdbc.algorithm.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangkp
 * @description HintShardingStrategy策略的hint算法
 * @create 2021-04-30 19:57
 */
@Slf4j
public class MyDBHintShardingAlgorithm implements HintShardingAlgorithm {
    /**
     * Sharding.
     *
     * <p>sharding value injected by hint, not in SQL.</p>
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, HintShardingValue shardingValue) {
        List<String> shardingList = new LinkedList<>();
        for (Object each : availableTargetNames){
            for (Object val : shardingValue.getValues()){
                if(each.toString().endsWith(val + "")){
                    shardingList.add(each.toString());
                }
                log.info("availableTargetNames:"+each+":shardingValue:"+val);
            }
        }
        return shardingList;
    }
}
