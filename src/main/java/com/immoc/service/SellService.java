package com.immoc.service;

import com.immoc.dataobject.SellerInfo;

//卖家端service
public interface SellService {
    /**通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellInfoByOpenid(String openid);
}
