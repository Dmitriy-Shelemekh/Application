<#import "parts/common.ftlh" as c>

<@c.page>
    User Editor
    <form action="/user" method="post">
        <div class="form-group">
            <input class="form-control col-sm-5 mt-3" type="text" name="username" value="${user.username}">
            <small class="form-text text-muted mb-3">User name.</small>
            <#list roles as role>
                <div>
                    <label class="form-check-label">
                        <input type="checkbox"
                               name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}
                    </label>
                </div>
            </#list>
        </div>
        <input type="hidden" value="${user.id}" name="user_id">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</@c.page>