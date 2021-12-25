<#import '../components/common.ftlh' as c>
<#import '../components/upsert_product.ftlh' as up>

<@c.page>
    <form action="/category/new/new" method="POST">
        <label for="nameInput">Введите название категории</label>
        <input id="nameInput" type="text"
               class="form-control ${(nameError??)?string('is-invalid','')}"
               value="<#if category??>${category.name}</#if>"
               placeholder="Название" name="name">
        <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
        <br/>
        <input type="submit" class="btn btn-primary" value="Добавить категорию">
    </form>
</@c.page>