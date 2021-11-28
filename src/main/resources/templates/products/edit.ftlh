<#import '../components/common.ftlh' as c>
<#import '../components/upsert_product.ftlh' as up>

<@c.page>
    <form action="/products/${product.id}/edit" method="POST">
        <@up.upsert/>
        <input type="submit" class="btn btn-primary" value="Изменить товар">
    </form>
</@c.page>