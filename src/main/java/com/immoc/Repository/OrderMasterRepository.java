package com.immoc.Repository;

import com.immoc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// 订单DAO
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    //按照买家的OpenId查询订单
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
