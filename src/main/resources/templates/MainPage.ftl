<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>Hello, user</div>
    <a href="/app">App page</a>
    <div><@l.logout /></div>
</@c.page>