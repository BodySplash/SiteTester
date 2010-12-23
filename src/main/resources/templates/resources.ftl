<ul>
<#list resources as resource>
	<li>
		<a href="${resource.fullPath()}">${resource.relativePath()}</a>
	</li>
</#list>
</ul>