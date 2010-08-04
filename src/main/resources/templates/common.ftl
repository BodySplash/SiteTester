<#macro wrap title>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title}</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" ></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.2/jquery-ui.min.js" ></script>
		<script type="text/javascript" src="/js/jquery.form.js" ></script>
		<link rel="stylesheet" href="/theme/layout.css" type="text/css"/>
		<link rel="stylesheet" href="/theme/default.css" type="text/css"/>
	</head>
	<body>
		<div id="header">
			<div class="content">
				<img src="/theme/images/arpinum.png" />
				<h1>
					Site Tester
				</h1>
			</div>
		</div>
		<div id="body">
			<div class="content">
				<#nested />
			</div>
		</div>
		<div id="footer">
			<div class="content">
				© Arpinum 2010
			<div class="content">
		</div>
	</body>
</html>
</#macro>