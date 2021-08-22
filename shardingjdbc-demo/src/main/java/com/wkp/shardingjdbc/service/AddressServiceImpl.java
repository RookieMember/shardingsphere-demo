package com.wkp.shardingjdbc.service;

import com.wkp.shardingjdbc.entity.Address;
import com.wkp.shardingjdbc.mapper.AddressMapper;
import com.wkp.shardingjdbc.mapper.AddressMapperAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
/**
 * @description 广播表插入，更新，删除都是全库执行
 *              广播表查询随机选择一个库执行
 *              4.1.1版本广播表查询有bug,select * 而不是select 具体字段的话,返回的数据没有映射到都是null，master分支已修复
 * @author wangkp
 * @create 2021/8/22 17:08
 */
@Slf4j
@Service
public class AddressServiceImpl {

    @Resource
    AddressMapper addressMapper;
    @Resource
    AddressMapperAnnotation addressMapperAnnotation;

    @Transactional
    public long addAddress(long sequenceId){
        //创建订单
        Address address = new Address();
        address.setAddressId(sequenceId);
        address.setAddressName("地址");
        try {
            addressMapperAnnotation.insert(address);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
            throw new RuntimeException("SQLException",e.getCause());
        }

        /*
        ******************测试事务回滚************************
        try {
            addressMapperAnnotation.delete(23974927498L);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
            throw new RuntimeException("SQLException",e.getCause());
        }

        if(true){
            throw new RuntimeException("事务回滚");
        }*/
        return address.getAddressId();
    }

    @Transactional
    public String deleteAddress(long addressId){
        try {
            addressMapperAnnotation.delete(addressId);
            return "delete data success";
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return "failure";
    }

    @Transactional
    public int updateAddress(long addressId,String addressName) {
        try {
            return addressMapperAnnotation.update(addressId,addressName);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
            throw new RuntimeException("exception is happenning, tx will be rollback",e.getCause());
        }
    }

    public List<Address> selectAll() throws SQLException {
        //1.xml实现方式
//        List<Address> list = addressMapper.selectAll();

        //2.注解实现方式
        List<Address> list = addressMapperAnnotation.selectAll();
        return list;
    }

    public List<Address> selectAddressRange(long start, long end){
        try {
            return addressMapperAnnotation.selectRange(start,end);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return null;
    }

    public List<Address> selectAddressPage(long offset,long size){
        try {
            return addressMapperAnnotation.selectPageList(offset,size);
        } catch (SQLException e) {
            log.info(e.getMessage(),e.getCause());
        }
        return null;
    }

}
