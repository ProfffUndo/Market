<#import '../components/common.ftlh' as c>

<@c.page>
    ${product}
    <form action="/products/${product.id}/edit" method="get">
        <input type="submit" class="btn btn-primary" value="Изменить товар">
    </form>
    <form action="/products/${product.id}/delete" method="POST">
        <input type="submit" class="btn btn-primary" value="Удалить товар">
    </form>
</@c.page>