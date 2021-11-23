<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/"><b>TheBestMarket</b></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/products">Товары</a>
            </li>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/profile">Профиль</a>
                </li>
            </#if>
        </ul>

        <a class="nav-link" href="/basket">Корзина</a>
        <#if user??>
            {name}
        <#else>
            Guest
        </#if>
    </div>
</nav>