$(document).ready(function() {
/*	$("ul#nav li a.home").mouseover(function () {
		$(this).css("background-position","left center");		
		//$("span", this).css("display", "block");
		$("ul#nav li a.about, ul#nav li a.blog, ul#nav li a.contact").css("background-position","left bottom");
	});
	$("ul#nav li a.home").mouseout(function () {
		$("ul#nav li a.home").css("background-position","left top");
		$("ul#nav li a:hover.home span").css("display", "none");
		$("ul#nav li a.about, ul#nav li a.blog, ul#nav li a.contact").css("background-position","left top");
	});
	$("ul#nav li a.about").mouseover(function () {
		$("ul#nav li a.about").css("background-position","left center");	
		$("ul#nav li a:hover.about span").css("display", "block");
		$("ul#nav li a.home, ul#nav li a.blog, ul#nav li a.contact").css("background-position","left bottom");
	});
	$("ul#nav li a.about").mouseout(function () {
		$("ul#nav li a.about").css("background-position","left top");
		$("ul#nav li a:hover.about span").css("display", "none");
		$("ul#nav li a.home, ul#nav li a.blog, ul#nav li a.contact").css("background-position","left top");
	});
	$("ul#nav li a.blog").mouseover(function () {
		$("ul#nav li a.blog").css("background-position","left center");
		$("ul#nav li a:hover.blog span").css("display", "block");
		$("ul#nav li a.home, ul#nav li a.about, ul#nav li a.contact").css("background-position","left bottom");
	});
	$("ul#nav li a.blog").mouseout(function () {
		$("ul#nav li a.blog").css("background-position","left top");
		$("ul#nav li a:hover.blog span").css("display", "none");
		$("ul#nav li a.home, ul#nav li a.about, ul#nav li a.contact").css("background-position","left top");
	});
	$("ul#nav li a.contact").mouseover(function () {
		$("ul#nav li a.contact").css("background-position","left center");
		$("ul#nav li a:hover.contact span").css("display", "block");
		$("ul#nav li a.home, ul#nav li a.about, ul#nav li a.blog").css("background-position","left bottom");
	});
	$("ul#nav li a.contact").mouseout(function () {
		$("ul#nav li a.contact").css("background-position","left top");
		$("ul#nav li a:hover.contact span").css("display", "none");
		$("ul#nav li a.home, ul#nav li a.about, ul#nav li a.blog").css("background-position","left top");
	});	
*/

	/*$("ul#nav li a").mouseover(function () {		
		$("span", this).show();
		$("ul#nav li a").css("background-position","left bottom");
		$(this).css("background-position","left center");
	});

	$("ul#nav li a").mouseout(function () {		
		$("span", this).hide();
		$("ul#nav li a").css("background-position","left top");
	});*/
	
	$("ul#nav li a").hover(function () {		
		$("span", this).show();
		$("ul#nav li a").css("background-position","left bottom");
		$(this).css("background-position","left center");
	}, function () {		
		$("span", this).hide();
		$("ul#nav li a").css("background-position","left top");
	});	
});