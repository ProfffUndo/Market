<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>
    ${order}
    <form method="post" action="/basket/pay/${order.id}">
        <input type="submit" value="Оплатить" class="btn btn-primary"/>
    </form>
    <br/>

</@c.page>