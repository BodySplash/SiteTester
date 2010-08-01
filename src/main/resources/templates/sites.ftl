<ul>
<#if sites??>
<#list sites as site>
	<li>${site.url}</li>
</#list>
</#if>
</ul>
<form action="/sites" method="post">
	<input type="text" name="url" />
	<input type="submit" value="Add" />
</form>