<ul>
<#if sites??>
<#list sites as site>
	<li>
		${site.name} (${site.resources?size} resources)
		<form action="/sites/${site.name}/spiders" method="post">
			<input type="submit" value="Crawl" />
		</form>
	</li>
</#list>
</#if>
</ul>
<form action="/sites" method="post">
	<input type="text" name="url" />
	<input type="submit" value="Add new site" />
</form>