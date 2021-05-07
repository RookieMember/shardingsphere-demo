package com.wkp.shardingjdbc.mapper;

import com.wkp.shardingjdbc.entity.Address;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @description mybatis xml版本实现
 * @author wangkp
 * @create 2021/5/7 10:06
 */
@Mapper
public interface AddressMapper {

    List<Address> selectAll() throws SQLException;
}
