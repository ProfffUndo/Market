<#import '../components/common.ftlh' as c>
<#import '../components/upsert_product.ftlh' as up>

<@c.page>
    <form action="/products/new" method="POST">
        <@up.upsert/>
        <input type="submit" class="btn btn-primary" value="Добавить товар">
    </form>
</@c.page>