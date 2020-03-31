<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>Hello, user</div>
    <a href="/app">App page</a>
    <div><@l.logout /></div>
</@c.page>