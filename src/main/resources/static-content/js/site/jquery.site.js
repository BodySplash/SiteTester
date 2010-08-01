(function($) {
	$.fn.sites = function() {
		var div = this;
		var load = function() {
			$.get("/sites", function(data) {
				var content = $(data);
				content.find("form").submit(function(e) {
					$(e.target).ajaxSubmit(function() {
						load();	
					})
					return false;
				})
				div.html(content);	
			});
		}
		load();
		return div;
	}
})(jQuery)