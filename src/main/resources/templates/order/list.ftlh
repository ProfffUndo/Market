<#include "../components/security.ftlh">
<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>

<#--    <@p.pager url usersPage/>-->
    <table class="table table-bordered" style="width: 90%">
        <thead>
            <tr>
                <th scope="col">id заказа</th>
                <th scope="col">Адрес доставки</th>
                <th scope="col">Дата заказа</th>
                <th scope="col">Комментарий</th>
                <th scope="col">Статус</th>
                <th scope="col">Действия</th>
            </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr>
                <td>${order.id}</td>
                <td><#if order.deliveryAddress ??> ${order.deliveryAddress} <#else></#if> </td>
                <td><#if order.orderDate ??> ${order.orderDate} <#else></#if> </td>
                <td><#if order.comment ??> ${order.comment} <#else></#if> </td>
                <td>
                    <#if isManager>
                        <div class="dropdown">
                            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                               ${order.status}
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                                <#list statuses as status>
                                    <a class="dropdown-item" href="/orders/set_status/${order.id}?status=${status}">${status}</a>
                                </#list>
                            </div>
                        </div>
                    <#else>
                        ${order.status}
                    </#if>
                </td>
                <td><a href="/orders/get_order/${order.id}">Просмотреть заказ</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
<#--    <@p.pager url productsPage/>-->

</@c.page>