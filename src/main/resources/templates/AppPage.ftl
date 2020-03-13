<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <body>
    <div><@l.logout /></div>

    <div>
        <form method="post">
            <label>
                <input type="text" name="text" placeholder="Введите сообщение"/>
                <input type="text" name="tag" placeholder="Тег"/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit">Добавить</button>
            </label>
        </form>
    </div>

    <div>Список сообщений</div>

    <form method="get" action="/app">
        <label>
            <input type="text" name="filter" value="${filter}"/>
            <button type="submit">Найти</button>
        </label>
    </form>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>