<html>
   <#include  "../common/header.ftl">
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <#--container-fluid表示流动布局-->
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <#--紧凑-->
                    <table class="table table-bordered table-condensed">
                    <#--表头-->
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                        <#--操作占了两列-->
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                    <td>${orderDTO.orderId}</td>
                    <td>${orderDTO.buyerName}</td>
                    <td>${orderDTO.buyerPhone}</td>
                    <td>${orderDTO.buyerAddress}</td>
                    <td>${orderDTO.orderAmount}</td>
                <#--获取订单和支付状态-->
                    <td>${orderDTO.orderStatusEnum.getMessage()}</td>
                    <td>${orderDTO.payStatusEnum.getMessage()}</td>
                    <td>${orderDTO.createTime}</td>
                    <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                    <td>
                        <#if orderDTO.getOrderStatusEnum().message == "新订单">
                            <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
            <#--分页-->
                <div class="col-md-12 column">
                <#--pull-right浮动到右边-->
                    <ul class="pagination pull-right">
                    <#--小于等于第一页，则不能点-->
                <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                </#if>
                    <#--0..表示0到n页,还可以0..<-->
                <#list 1..orderDTOPage.getTotalPages() as index>
                <#--disabled使其不可点-->
                    <#if currentPage==index>
                        <li class="disabled"><a href="#">${index}</a></li>
                    <#else >
                        <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                    </#if>
                </#list>
                    <#--当前页等于大于总数-->
                <#if currentPage gte orderDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                <#else>
                        <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</head>
</html>