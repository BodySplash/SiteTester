<@common.wrap "${site.name} - SiteTester">
<script type="text/javascript" src="/js/site/jquery.site.js"></script>
<script type="text/javascript">
$(function() {
	$("#resources").resources("${site.name}");
});

</script>
<h2>${site.name}</h2>
<form action="${site.name}/spiders" method="post">
	<input type="submit" value="Crawl" />
</form>
<form action="${site.name}/tests" method="post">
	<input type="submit" value="Run new test" />
</form>
<div id="resources">

</div>
</@>