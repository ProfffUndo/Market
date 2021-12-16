<#include "../components/security.ftlh">
<#import '../components/common.ftlh' as c>
<#import '../css/inline.css' as css>
<#import '../components/pager.ftl' as p>

<@c.page>

    <label for="nameInput">ФИО</label>
    <input id="nameInput" type="text" class="form-control"
           value="<#if currentUser??>${currentUser.name}</#if>"
           placeholder="ФИО" readonly="readonly">
    <br/>
    <label for="emailInput">Email</label>
    <input id="emailInput" type="text" class="form-control"
           value="<#if currentUser??>${currentUser.email}</#if>"
           placeholder="Email" readonly="readonly">
    <br/>
    <label for="lvInput">Был в сети в последний раз</label>
    <input id="lvInput" type="text" class="form-control"
           value="<#if currentUser??>${currentUser.lastVisit}</#if>"
           placeholder="Был в сети в последний раз" readonly="readonly">
    <br/>

    <#if currentUser.id == user.id>
        <form method="get">
            <a href="/logout" class="btn btn-primary">Выйти</a>
        </form>
    </#if>

</@c.page>