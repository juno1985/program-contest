$(function(){
	$('.course-item').hover(
			function(){
				var course_img = $(this).find("img");
				$(course_img).animate({padding:"4"});
			},
			function(){
				var course_img = $(this).find("img");
				$(course_img).animate({padding:"10px"});
			}
			);
})