<#import "parts/common.ftlh" as c>

<@c.page>
    Users:
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th class="text-center">Name</th>
            <th class="text-center">Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody class="table-hover">
        <#list users as user>
            <tr>
                <td class="text-center">${user.username!}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td>
                    <a href="/user/${user.id}" class="text-center btn btn-primary btn-block">Edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>