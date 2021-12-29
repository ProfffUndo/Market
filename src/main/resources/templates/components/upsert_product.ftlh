<#macro upsert>
    <label for="nameInput">Введите название товара</label>
    <input id="nameInput" type="text"
           class="form-control ${(nameError??)?string('is-invalid','')} ${(productNameError??)?string('is-invalid','')}"
           value="<#if product??>${product.name}</#if>"
           placeholder="Название" name="name">
    <#if nameError??>
        <div class="invalid-feedback">
            ${nameError}
        </div>
    </#if>
    <#if productNameError??>
        <div class="invalid-feedback">
            ${productNameError}
        </div>
    </#if>
    <br/>
    <label for="descInput">Введите описание товара</label>
    <input id="descInput" type="text"
           class="form-control ${(descriptionError??)?string('is-invalid','')}"
           value="<#if product??>${product.description}</#if>"
           placeholder="Описание" name="description">
    <#if descriptionError??>
        <div class="invalid-feedback">
            ${descriptionError}
        </div>
    </#if>
    <br/>
    <label for="priceInput">Введите цену товара</label>
    <input id="priceInput" type="text" pattern="[1-9]\d*(\.\d{1,2})?"
           class="form-control ${(priceError??)?string('is-invalid','')}"
           value="<#if priceOld??>${priceOld}</#if>" placeholder="Цена" name="price">
    <#if priceError??>
        <div class="invalid-feedback">
            ${priceError}
        </div>
    </#if>
    <br/>
    <label for="imgInput">Введите ссылку на картинку</label>
    <input id="imgInput" type="text"
           class="form-control ${(imagePathError??)?string('is-invalid','')}"
           value="<#if product??>${product.imagePath}</#if>"
           placeholder="Картинка" name="imagePath">
    <#if imagePathError??>
        <div class="invalid-feedback">
            ${imagePathError}
        </div>
    </#if>
    <br/>
    <div class="form-group">
        <label for="categorySelect">Выберите категорию товара</label>
        <select class="form-control ${(categoryError??)?string('is-invalid','')}" id="categorySelect" name="categoryName">
            <option selected><#if product??><#if categoryOld??>${categoryOld}</#if></#if></option>
            <#list categories as category>
                <option>${category.name}</option>
            </#list>
        </select>
        <#if categoryError??>
            <div class="invalid-feedback">
                ${categoryError}
            </div>
        </#if>
    </div>
    <label for="numberImput">Введите количество товара на складе</label>
    <br/>
    <input id="numberImput" type="number" min="0" max="10000"
           class="form-control ${(amountError??)?string('is-invalid','')}"
           value="<#if product??>${product.amount}</#if>"
           placeholder="Количество товара" name="amount">
    <#if amountError??>
        <div class="invalid-feedback">
            ${amountError}
        </div>
    </#if>
    <br/>
</#macro>