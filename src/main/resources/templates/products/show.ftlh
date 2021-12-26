<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#include "../components/security.ftlh">

<@c.page>
    <div class="parent" style="height: 700px">
        <div class="child" style="width: 48%; float: left; height: 250px;">
            <img src="${product.imagePath}"
                 alt="Здесь когда-нибудь будет пикча" style="border: 1px solid lightgrey;">
        </div>
        <div class="child" style="width: 52%; float: left">
            <div class="title" style="color: blue; font-size: 30px; font-weight: bold">${product.name}</div>
            <div style="font-size: 20px; margin-top: 10px; font-weight: bold">Всего за ${product.price} рублей</div>
            <div style="font-size: 14px; margin-top: 10px; font-weight: bold">На складе: ${product.amount} шт.</div>
            <div style="margin-top: 10px">
                <form method="post" action="/basket/add">
                    <input type="submit" class="btn btn-primary" value="В корзину">
                    <input type="hidden" name="product_id" value="${product.id}"/>
                </form>
                <div class="card-text" style="margin-top: 10px">${product.description}</div>
                <div class="parent" style="margin-top: 5px">
                    <div class="child" style="width: 26%; float:left;">
                        <#if user?? && isManager>
                            <form action="/products/${product.id}/edit" method="get">
                                <input type="submit" class="btn btn-primary" value="Изменить товар">
                            </form>
                        </#if>
                    </div>
                    <div class="child" style="width: 72%; float:left;">
                        <#if user?? && isManager>
                            <form action="/products/${product.id}/delete" method="POST">
                                <input type="submit" class="btn btn-primary" value="Удалить товар">
                            </form>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@c.page>