<#include "../components/security.ftlh">
<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>

<#--    <@p.pager url usersPage/>-->
    <#list users as user>
        <div class="card w-100">
            <div class="card-body">
                <div class="child" style="width: 70%; float: left">
                    <a href="/profile/${user.id}">
                        <div class="card-title" style="font-size: 20px; font-weight: bold;">${user.name}</div>
                    </a>
                    <div class="card-text">${user.email}</div>
                </div>
            </div>
        </div>
        <br/>
    </#list>
<#--    <@p.pager url productsPage/>-->

</@c.page>