package com.wkp.shardingjdbc.controller;

import com.wkp.shardingjdbc.entity.Address;
import com.wkp.shardingjdbc.service.AddressServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @description 测试广播表操作
 * @author wangkp
 * @create 2021/5/6 15:36
 */
@Slf4j
@RestController
@Api(value = "广播表相关接口", tags = {"广播表相关接口"})
public class AddressController {

    @Autowired
    AddressServiceImpl addressServiceImpl;

    @GetMapping("/confirm_address")
    @ApiOperation(value = "创建地址")
    public String confirmAddress(long sequenceId){
        long id = addressServiceImpl.addAddress(sequenceId);
        return "创建地址成功:地址ID = " + id;
    }

    @GetMapping("/delete_address")
    @ApiOperation(value = "删除地址")
    public String deleteAddress(long sequenceId){
        return addressServiceImpl.deleteAddress(sequenceId);
    }

    @GetMapping("/update_address")
    @ApiOperation(value = "更新地址")
    public int updateAddress(long sequenceId, String addressName){
        return addressServiceImpl.updateAddress(sequenceId,addressName);
    }

    @GetMapping("/select_all_address")
    @ApiOperation(value = "查询所有地址")
    public List<Address> selectAll() throws SQLException {
        return addressServiceImpl.selectAll();
    }

    @GetMapping("/select_range_address")
    @ApiOperation(value = "按照范围查询地址")
    public List<Address> selectRange(int start,int end) throws SQLException {
        return addressServiceImpl.selectAddressRange(start,end);
    }

    @GetMapping("/select_page_address")
    @ApiOperation(value = "分页查询地址")
    public List<Address> selectPage(int offset,int size) throws SQLException {
        return addressServiceImpl.selectAddressPage(offset,size);
    }
}
