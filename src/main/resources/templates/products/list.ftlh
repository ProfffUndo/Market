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
              <input id="priceFrom" type="text" pattern="[1-9]\d{1, 10}*(\.\d{1,2})?"
                     class="form-control"
                     value="<#if dto.priceFrom??>${dto.priceFrom}</#if>" name="priceFrom">
              </br>
              <label for="priceTo">Цена до:</label>
              <input id="priceTo" type="text" pattern="[1-9]\d{1, 10}*(\.\d{1,2})?"
                     class="form-control"
                     value="<#if dto.priceTo??>${dto.priceTo}</#if>" name="priceTo">
              </br>
              <div class="form-group">
                  <label for="categorySelect">Категория товара</label>
                  <select class="form-control" id="categorySelect" name="category">
                      <option selected><#if dto.category??>${dto.category}</#if></option>
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

    <div style="margin-left: 50px">
        <#if user?? && isManager>
            <form method="get">
                    <a href="/products/new" class="btn btn-primary">Создать товар</a>
            </form>
        </#if>
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
</@c.page>