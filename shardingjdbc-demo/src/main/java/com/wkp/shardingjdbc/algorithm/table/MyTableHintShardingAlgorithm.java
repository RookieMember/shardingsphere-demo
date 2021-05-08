package com.wkp.shardingjdbc.algorithm.table;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangkp
 * @description HintShardingStrategy策略的hint算法
 * @create 2021-04-30 19:57
 */
@Slf4j
public class MyTableHintShardingAlgorithm implements HintShardingAlgorithm {
    /**
     * 举个例子，如果我们希望订单表t_order用 user_id 做分片健进行分库分表，
     * 但是 t_order 表中却没有 user_id 这个字段，
     * 这时可以通过 Hint API 在外部手动指定分片健或分片库。
     */
    public void test(){
        HintManager hint = HintManager.getInstance();
//        //直接指定对应具体的数据库
//        hint.setDatabaseShardingValue("ds0");
//        //读写分离的时候强制走主库
//        hint.setMasterRouteOnly();
        String userId="128991273";
        hint.addDatabaseShardingValue("ds",userId);
        hint.addTableShardingValue("t_order",userId);
    }

    /**
     * Sharding.
     *
     * <p>sharding value injected by hint, not in SQL.</p>
     *
     * @param availableTargetNames 所有分库或者分表的集合
     *                             在分库时值为所有分片库的集合 databaseNames  [ds0,ds1]
     *                             分表时为对应分片库中所有分片表的集合 tablesNames [t_order_0,t_order_1]
     * @param shardingValue  分片值,PreciseShardingValue类有三个属性
     *                      logicTableName 逻辑表名;
     *                      columnName 分片键(字段);
     *                      values HintManager设置的值集合;
     *
     * @return 分片结果:分片库或者表的名称集合
     */
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, HintShardingValue shardingValue) {
        List<String> shardingList = new ArrayList<>();
        for (Object each : availableTargetNames){
            for (Object val : shardingValue.getValues()){
                int remainder = (int)val % 2;
                if(each.toString().endsWith(remainder+"")){
                    shardingList.add(each.toString());
                }
                log.info("availableTargetNames:"+each+":shardingValue:"+val);
            }
        }
        return shardingList;
    }
}
