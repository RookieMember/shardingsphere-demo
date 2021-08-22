package com.wkp.shardingjdbc.controller;

import com.wkp.shardingjdbc.dto.OrderInfoDto;
import com.wkp.shardingjdbc.service.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "分库分表相关接口", tags = {"分库分表相关接口"})
public class ShardingController {

    @Autowired
    OrderServiceImpl orderService;

    @GetMapping("/confirm_order")
    @ApiOperation(value = "创建订单")
    public String confirmOrder(int sequenceId){
        long id = orderService.confirmOrder(sequenceId);
        return "创建订单成功:订单ID = " + id;
    }

    @GetMapping("/order_histroy_list")
    @ApiOperation(value = "查询所有订单")
    public OrderInfoDto orderHistoryList(){
        return orderService.selectAll();
    }

    /**
     * 删除历史订单
     * @param orderId
     * @return
     */
    @GetMapping("/delete_histroy_order")
    @ApiOperation(value = "删除订单")
    public String deleteHistroyOrder(long orderId){
        return orderService.deleteData(orderId);
    }

    /**
     * 更改历史订单状态
     * @param orderId
     * @param status
     * @return
     */
    @GetMapping("/update_histroy_order")
    @ApiOperation(value = "修改订单")
    public int updateHistoryOrderStatus(long orderId,String status){
        return orderService.updateOrder(orderId,status);
    }

    /**
     * range orderid {200000000000000000 - 400000000000000000}
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/order_range_list")
    @ApiOperation(value = "订单范围查询")
    public OrderInfoDto orderRangeList(long start,long end){
        return orderService.selectOrderRange(start,end);
    }

    /**
     * range userid {1-20}
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/item_range_list")
    @ApiOperation(value = "订单商品【分片键】范围查询")
    public OrderInfoDto orderItemRangeList(int start,int end){
        return orderService.selectOrderItemRange(start,end);
    }

    /**
     * 笛卡尔积测试
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/item_range_in_list")
    @ApiOperation(value = "关联表查询笛卡尔积")
    public OrderInfoDto orderItemRangeInList(long start,long end){
        return orderService.selectOrderItemWithIn(start,end);
    }

    @GetMapping("/item_page_list")
    @ApiOperation(value = "订单分页查询")
    public OrderInfoDto orderPageList(long offset,long size){
        return orderService.selectOrderPageList(offset,size);
    }
}
