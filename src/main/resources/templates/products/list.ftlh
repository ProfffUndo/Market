<#include "../components/security.ftlh">
<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<nav id="navbar-example3" class="navbar navbar-light bg-light" style="position: fixed; height: 100%">
    <nav class="nav nav-pills flex-column" style="">
        <form method="post" action="/products/find/find/find">
            <label for="nameInput">Название товара</label>
            <input id="nameInput" type="text"
                   class="form-control"
                   value="<#if dto.name??>${dto.name}</#if>"
                   placeholder="Название" name="name">
            </br>
            <label for="priceFrom">Цена от:</label>
            <input id="priceFrom" type="number" min="0" max="1000000000"
                   class="form-control ${(priceError??)?string('is-invalid','')}"
                   value="<#if dto.priceFrom??>${dto.priceFrom}</#if>"
                   placeholder="От" name="priceFrom">
            <#if priceError??>
                <div class="invalid-feedback">
                    ${priceError}
                </div>
            </#if>
            </br>
            <label for="priceTo">Цена до:</label>
            <input id="priceTo" type="number" min="0" max="1000000000"
                   class="form-control"
                   value="<#if dto.priceTo??>${dto.priceTo}</#if>"
                   placeholder="До" name="priceTo">
            </br>
            <div class="form-group">
                <label for="categorySelect">Категория товара</label>
                <select class="form-control" id="categorySelect" name="category">
                    <option></option>
                    <#if dto.category??><option selected><#if dto.category??>${dto.category}</#if></option></#if>
                    <#list categories as category>
                        <option>${category.name}</option>
                    </#list>
                </select>
            </div>
            <input type="submit" class="btn btn-primary" value="Поиск">
        </form>
    </nav>
</nav>

<@c.page>

    <div style="margin-left: 20%">

    <#if user?? && isManager>
        <div>
            <form method="get" style="display: inline-block">
                    <a href="/products/new" class="btn btn-primary">Создать товар</a>
            </form>
            <form method="get" style="display: inline-block">
                <a href="/category/new" class="btn btn-primary">Создать категорию</a>
            </form>
        </div>
    </#if>
    <br/>
    <@p.pager url productsPage/>

    <div class="dropdown">
        <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Сортировка
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
            <a class="dropdown-item" href="${urlForSort}&sort=priceup">По возрастанию цены</a>
            <a class="dropdown-item" href="${urlForSort}&sort=pricedown">По убыванию цены</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="${urlForSort}&sort=popularityup">По возрастанию популярности</a>
            <a class="dropdown-item" href="${urlForSort}&sort=popularitydown">По убыванию популярности</a>
        </div>
    </div>

    </br>
    <#list productsPage.content as product>
        <div class="card w-100">
            <div class="card-body">
                <div class="parent">

                        <div class="child" style="width: 20%; float: left; border: 1px solid lightgrey;">
                            <img class="card-img-top img-fluid" src="${product.imagePath}" alt="Пикча">
                        </div>

                        <div class="child" style="width: 55%; float: left; margin-left: 5%">
                            <a href="/products/${product.id}">
                                <div class="card-title" style="font-size: 20px; font-weight: bold;">${product.name}</div>
                            </a>
                            <div class="card-text" style="font-weight: bold">Кол-во товара на складе: ${product.amount} шт.</div>
                            <div class="card-text">${product.description}</div>
                        </div>
                        <div class="child" style="width: 20%; float: right; display: flex; justify-content: center; align-items: center; flex-direction: column ">
                            <div style="font-weight: bold" >${product.price} руб.</div>
                            <form method="post" action="/basket/add">
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
        </div>
    </div>
</@c.page>