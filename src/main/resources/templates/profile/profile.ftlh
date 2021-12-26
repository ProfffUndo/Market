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

    <#if user?? && isAdmin>
        <form action="/profile/set_roles/${currentUser.id}" method="post">
            <div>
                <div style="display: inline-block">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" <#if managerCheck??>checked</#if> id="setManager"
                               value="yes" name="manager">
                        <label class="form-check-label" for="setManager">Менеджер</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" <#if adminCheck??>checked</#if> id="setAdmin"
                               value="yes" name="admin">
                        <label class="form-check-label" for="setAdmin">Администратор</label>
                    </div>
                </div>
                <div style="display: inline-block">
                    <input type="submit" class="btn btn-primary btn-sm" value="Применить роли">
                </div>
            </div>
        </form>

        <br/>
    </#if>

    <#if currentUser.id == user.id || isManager>
        <a href="/orders/${currentUser.id}">Список заказов</a>
    </#if>

    <br/>
    <br/>

    <#if currentUser.id == user.id>
        <form method="get">
            <a href="/logout" class="btn btn-primary">Выйти</a>
        </form>
    </#if>

</@c.page>