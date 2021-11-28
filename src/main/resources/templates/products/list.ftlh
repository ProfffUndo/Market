<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>
    <form method="get">
        <a href="/products/new" class="btn btn-primary">Создать товар</a>
    </form>
    <br/>
    <@p.pager url productsPage/>
    <#list productsPage.content as product>
        <div class="card w-100">
            <div class="card-body">
                <div class="parent">
                    <div class="child" style="width: 20%; float: left; border: 1px solid lightgrey;">
                        <img class="card-img-top" src="..." alt="Здесь когда-нибудь будет пикча">
                    </div>
                    <div class="child" style="width: 70%; float: left">
                        <a href="/products/${product.id}">
                            <div class="card-title" style="font-size: 20px; font-weight: bold;">${product.name}</div>
                        </a>
                        <div class="card-text">${product.description}</div>
                    </div>
                    <div class="child" style="width: 10%; float: right;">
                        <div style="position:absolute; right: 0">
                            <div class="card-text">${product.price}</div>
                            <a href="#" class="btn btn-primary">В корзину</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </#list>
    <@p.pager url productsPage/>
</@c.page>