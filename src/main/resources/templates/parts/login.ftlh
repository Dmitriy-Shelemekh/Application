<#macro login path isRegisterForm>
    <form action="${path}" method="post" xmlns="http://www.w3.org/1999/html">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name :</label>
            <div class="col-sm-5">
                <input class="form-control" type="text" name="username" placeholder="User name"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-5">
                <input class="form-control" type="password" name="password" placeholder="Password"/>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> E-mail: </label>
                <div class="col-sm-5">
                    <input class="form-control" type="email" name="email" placeholder="example@mail.com"/>
                </div>
            </div>
        </#if>

        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>

        <#if !isRegisterForm>
            <a href="/registration" class="btn btn-primary">Registration</a>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>

<#macro logout name>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit"><#if name == 'unknown'>Sign In<#else>Sign Out</#if></button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</#macro>