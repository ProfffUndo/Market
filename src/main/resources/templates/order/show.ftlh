<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>
    <div class="child" style="width: 85%; float: left">
        <table class="table table-bordered" style="width: 90%">
            <thead>
            <tr>
                <th scope="col">Название товара</th>
                <th scope="col">Цена</th>
                <th scope="col">Количество</th>
                <th scope="col">Общая сумма</th>
            </tr>
            </thead>
            <tbody>
            <#list order.orderItems as item>
                <tr>
                    <td><a href="/products/${item.product.id}">${item.product.name} </a></td>
                    <td>${item.product.price}</td>
                    <td>${item.count}</td>
                    <td>${item.getSum()}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>