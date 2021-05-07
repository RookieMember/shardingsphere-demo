package com.wkp.shardingjdbc.mapper;

import com.wkp.shardingjdbc.entity.Address;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @description mybatis注解版本实现
 * @author wangkp
 * @create 2021/5/7 10:06
 */
@Mapper
public interface AddressMapperAnnotation {

    @Options(useGeneratedKeys = true,keyProperty = "addressId",keyColumn = "address_id")
    @Insert("INSERT INTO t_address (address_id, address_name) VALUES (#{addressId,jdbcType=BIGINT}, #{addressName,jdbcType=VARCHAR})")
    long insert(Address address) throws SQLException;

    @Delete("DELETE FROM t_address WHERE address_id = #{addressId}")
    void delete(long addressId) throws SQLException;

    @Select("SELECT address_id,address_name FROM t_address")
//    @Select("SELECT * FROM t_address")
    List<Address> selectAll() throws SQLException;

    @Select("SELECT * FROM t_address WHERE address_id BETWEEN #{start} AND #{end}")
    List<Address> selectRange(@Param("start") long start,@Param("end") long end) throws SQLException;

    @Select("SELECT * FROM t_address order by address_id limit #{offset},#{size}")
    List<Address> selectPageList(@Param("offset") long offset,@Param("size") long size) throws SQLException;

    @Update("update t_address set address_name = #{addressName} where address_id = #{addressId}")
    int update(@Param("addressId") long addressId, @Param("addressName") String addressName) throws SQLException;
}
