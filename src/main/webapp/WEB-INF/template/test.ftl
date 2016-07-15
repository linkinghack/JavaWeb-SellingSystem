<html>
<head>
<meta charset="utf-8">
<title>TestPage</title>
</head>
<body>
	<#if user??>
            <#if user.usertype==1>卖家<#else>买家</#if>你好，<span class="name">${user.username}</span>！<a href="/logout">[退出]</a>
        <#else>
            请<a href="/login">[登录]</a>
        </#if>
	<p>This is the index page.</p>
</body>
</html>