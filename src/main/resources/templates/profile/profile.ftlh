<#include "../components/security.ftlh">
<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>
    ${user.username}
    <form method="get">
        <a href="/logout" class="btn btn-primary">Выйти</a>
    </form>
</@c.page>