<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>
    <#if comment ??>
        <h3>${comment}</h3>
    </#if>
    <#if order.orderItems ??>
        <div class="parent">
            <div class="child" style="width: 85%; float: left">
                <table class="table table-bordered" style="width: 90%">
                    <thead>
                    <tr>
                        <th scope="col">Название товара</th>
                        <th scope="col">Цена</th>
                        <th scope="col">Количество</th>
                        <th scope="col">Общая сумма</th>
                        <th scope="col">Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list order.orderItems as item>
                        <tr>
                            <td><a href="/products/${item.product.id}">${item.product.name} </a></td>
                            <td>${item.product.price}</td>
                            <td>
                                <div style="display: flex; justify-content: center; align-items: center">
                                    <form method="post" action="/basket/delete" style="display: inline-block">
                                        <input type="submit" class="btn btn-primary btn-sm" value="-">
                                        <input type="hidden" name="product_id" value="${item.product.id}"/>
                                    </form>
                                    <div  style="display: inline-block; margin-left: 8px; margin-right: 8px">${item.count}</div>
                                    <form method="post" action="/basket/add" style="display: inline-block">
                                        <input type="submit" class="btn btn-primary btn-sm" value="+">
                                        <input type="hidden" name="product_id" value="${item.product.id}"/>
                                    </form>
                                </div>
                            </td>
                            <td>${item.getSum()}</td>
                            <td>
                                <form method="post" action="/basket/remove">
                                    <input type="submit" class="btn btn-primary" value="Удалить">
                                    <input type="hidden" name="product_id" value="${item.product.id}"/>
                                </form>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>

            <div class="child" style="width: 15%; float: right;">
                <#if amountError ??>
                <h3>${amountError}</h3>
                <#else>
                <form method="post" action="/basket/pay/${order.id}">
                    <input type="submit" value="Оформить заказ" class="btn btn-primary"/>
                </form>
                <br/>
                <div style="font-size: 16px; margin-top: 5px; font-weight: bold">Всего ${calculatedSum} руб.</div>
                </#if>
            </div>
        </div>
    </#if>
</@c.page>