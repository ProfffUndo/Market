<#include "../components/security.ftlh">
<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>
    <form method="get">
        <#if user?? && isManager>
            <a href="/products/new" class="btn btn-primary">Создать товар</a>
        </#if>
    </form>
    <br/>
    <@p.pager url productsPage/>
    <#list productsPage.content as product>
        <div class="card w-100">
            <div class="card-body">
                <div class="parent">

                    <div class="child" style="width: 20%; float: left; border: 1px solid lightgrey;">
                        <img class="card-img-top img-fluid" src="${product.imagePath}" alt="Пикча">
                    </div>

                    <div class="child" style="width: 70%; float: left">
                        <a href="/products/${product.id}">
                            <div class="card-title" style="font-size: 20px; font-weight: bold;">${product.name}</div>
                        </a>
                        <div class="card-text">${product.description}</div>
                    </div>
                    <div class="child" style="width: 10%; float: right;">
                        <form method="post" action="/basket">
                            <input type="submit" class="btn btn-primary" value="В корзину">
                            <input type="hidden" name="product_id" value="${product.id}"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </#list>
    <@p.pager url productsPage/>
</@c.page>