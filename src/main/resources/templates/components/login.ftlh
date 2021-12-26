<#include "security.ftlh">

<#macro login path isRegisterForm>

    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Введите логин:</label>
            <div class="col-sm-6">
                <input type="text" name="username"
                       class="form-control ${(usernameError??)?string('is-invalid','')}"
                       value="<#if user??>${user.username}</#if>"
                       placeholder="Логин"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Введите пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid','')}"
                       placeholder="Пароль"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="text" name="password2"
                           class="form-control ${(password2Error??)?string('is-invalid','')}"
                           placeholder="Retype password"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <br>
        <#if !isRegisterForm>
            <div>
                <a class="btn btn-primary mb-3"
                   type="submit"
                   href="/registration">
                    Sign Up
                </a>
            </div>
            <div>
                <button class="btn btn-primary mb-3"
                        type="submit">
                    Sign In
                </button>
            </div>
        <#else>
            <div>
                <button class="btn btn-primary" type="submit">
                    Sign Up
                </button>
            </div>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <div>
            <button class="btn btn-primary" type="submit">
                <#if user??>
                    Sign Out
                <#else>
                    Sign in/Sign up
                </#if>
            </button>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>