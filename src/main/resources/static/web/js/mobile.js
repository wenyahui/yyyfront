//侧边栏返回顶部
$().ready(function(e) {
    $(".tooltip .return_top").click(function(){
		$("html,body").animate({scrollTop:"0px"},300);
		});
	if($(".tooltip").length!=0){
		$(".tooltip .return_top").hide();
		var win_hei2=$(window).height();
		$(window).scroll(function(){
			var win_hei=$(document).scrollTop();
			if(win_hei>win_hei2){
				$(".tooltip .return_top").fadeIn();
				}else{
					$(".tooltip .return_top").stop(true,true).fadeOut();
					}
			});
		}
});
	
//弹窗
function pop_box(){
	$(".show_pop_box").on('click', function(e){
		show_pop_box();
		e.stopPropagation();
	});
	function show_pop_box(){
		$(".js_dialog").fadeIn(200);
		box_center();
	}
	var box_w,box_h;
	function box_center(){
		box_w=$(".pop_box").outerWidth();
		box_h=$(".pop_box").outerHeight();
		$(".pop_box").css({"margin-top":-box_h/2,"margin-left":-box_w/2});
	}
	$(".pop_box_close").on('click',function(){
		$(".js_dialog").fadeOut(100);
	});
	$("body").on("click",function(){
		$(".pop_box_close").click();
	});
	$(".pop_box").on('click',function(e){
		e.stopPropagation();
	});
	//尺寸改变
	$(window).resize(function(){
		box_center();
	});
	$(".pop_box *").focus(function(){
		$(".pop_box").animate({scrollTop:$(this).offset().top-$(".pop_box").offset().top+$(".pop_box").scrollTop()-30},500);
	});
}
function pop_box2(){
	
	$(".pop_box_close").on('click',function(){
		$(".js_dialog").fadeOut(100);
	});
	$("body").on("click",function(){
		$(".pop_box_close").click();
	});
	$(".pop_box").on('click',function(e){
		e.stopPropagation();
	});
	//尺寸改变
	$(window).resize(function(){
		box_center();
	});
	$(".pop_box *").focus(function(){
		$(".pop_box").animate({scrollTop:$(this).offset().top-$(".pop_box").offset().top+$(".pop_box").scrollTop()-30},500);
	});
}


//radio	
$(function(){
	$(".inp_radio input").hide();
	$(".inp_radio input").each(function () {
		if ($(this).attr("checked") == "checked") {
			$(this).parent("span").addClass("on");
		}
	});
	$(".inp_radio").live("click",function(){
		$(this).children().attr("checked",'true');
		var name=$(this).find("input").attr("name");
		$(".inp_radio").each(function(){
			$(this).find("input[name='"+name+"']").parent().removeClass("on");
			});
		$(this).addClass("on");
	});
});

//checkbox	
$(function(){
	$(".inp_checkbox input").hide();
	$(".inp_checkbox input").each(function () {
		if ($(this).attr("checked") == "checked") {
			$(this).parent("span").addClass("on");
		}
	});
	$(".inp_checkbox").live("click",function(){
		if($(this).is(".on")){
			$(this).removeClass("on").children().attr("checked",null);
		}else{
			$(this).addClass("on").children().attr("checked",'true');
		}
	});
});

//属性筛选
$().ready(function(){
	$(".info_filt_btn").click(function(){
		$(".info_filt_div").show();
	});
	$(".info_filt_div .close").click(function(){
		$(".info_filt_div").hide();
	});
});

//条件筛选
$().ready(function(){
    $(".info_filt_div .con a").click(function(){
		if($(this).is(".on")){
			$(this).removeClass("on");
		} else{
			$(this).addClass("on");
			if(!$(this).parents(".con").is(".check_box")){
				$(this).siblings().removeClass("on");
			}
		}
	});

});


