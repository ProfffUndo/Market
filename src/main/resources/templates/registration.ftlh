<#import "components/common.ftlh" as c>
<#import "components/login.ftlh" as l>

<!-- Страница регистрации -->
<@c.page>
    <div class="mb-3"><h2>Add new user</h2></div>
    ${message!""}
    <@l.login "/registration" true />
</@c.page>