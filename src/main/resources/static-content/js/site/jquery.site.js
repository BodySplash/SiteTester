(function($) {
	$.fn.sites = function() {
		var div = this;
		$.get("/sites", function(data) {
			div.html(data);	
		});
		return div;
	}
})(jQuery)