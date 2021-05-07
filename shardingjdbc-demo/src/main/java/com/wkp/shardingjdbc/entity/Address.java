package com.wkp.shardingjdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 地址
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address  implements Serializable {
    
    private static final long serialVersionUID = 6614347019506702131L;
    
    private long addressId;
    
    private String addressName;

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", addressName='" + addressName + '\'' +
                '}';
    }
}
