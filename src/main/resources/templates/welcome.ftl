<#import "common.ftl" as common>

<@common.wrap "Welcome to site Tester">
	<script type="text/javascript" src="js/site/jquery.site.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#sites").sites();
		})
	</script>
	<h2>Available sites</h2>
	<div id="sites">
	
	</div>
	
</@common.wrap>