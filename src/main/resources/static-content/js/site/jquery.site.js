(function($) {
	$.fn.sites = function() {
		load(this);
		return this

		
		function load(div) {
			$.get("/sites", function(data) {
				div.html($(data));	
				div.find("form").submit(function(e) {
					$(e.target).ajaxSubmit(function() {
						load(div);	
					})
					return false;
				})
			});
		}
	}
})(jQuery)