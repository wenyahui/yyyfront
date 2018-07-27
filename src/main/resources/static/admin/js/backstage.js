//登录
$().ready(function(){
	$(".login_box span.yzm input").focus(function(){
		$(this).siblings(".code").stop(true,true).fadeIn(300)
		});
	$(".login_box span.yzm input").blur(function(){
		$(this).siblings(".code").stop(true,true).fadeOut(300)
		});
	$(".login_box span.yzm img").click(function(){
		$(".login_box span.yzm input").focus();
		});
	});

//navtab
$().ready(function(){
	$(".b_left .page_nav").eq(0).show();
	
	$(".b_left .page_nav").each(function(){
		$(this).find("li").eq(0).addClass("on");
		});
	
	$(".left li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var navind=$(this).index();
		$(".b_left .page_nav").eq(navind).show().siblings(".page_nav").hide();
		
		$(".logo_r .left li.on a").addClass("home");
		var nav1=$(this).html();
	    var nav2=$(".b_left .page_nav").eq(navind).find("li.on").html();
	    $(".location").html("当前位置："+nav1+">"+nav2)
		});
	
	$(".b_left .page_nav li").click(function(){
		$(this).addClass("on").siblings().removeClass("on");
		var nav1=$(".logo_r .left li.on").html();
	    var nav2=$(this).html();
	    $(".location").html("当前位置："+nav1+">"+nav2)
		});
	
	});

//单选
$(function(){
    $(".inp_radio input").hide();
	$(".inp_radio input").each(function () {
        if ($(this).attr("checked") == "checked") {
            $(this).siblings().addClass("on");
        }
    });
	$(".inp_radio span").click(function(){
		var name=$(this).siblings().attr("name");
		$(".inp_radio").each(function(){
			$(this).find("input[name='"+name+"']").removeAttr("checked").siblings().removeClass("on");
		});
		$(this).siblings().attr("checked","checked");
		$(this).addClass("on");
	});
});
	
//多选
$(function(){
    $(".inp_checkbox input").hide();
    $(".inp_checkbox input").each(function(){
    	if($(this).attr("checked")=="checked"){
    		$(this).siblings().addClass("on");
    	}
    });
	$(".inp_checkbox span").live('click',function(){
		if($(this).siblings().attr("checked")=="checked"){
			$(this).removeClass("on").siblings().removeAttr("checked");
		}else{
			$(this).addClass("on").siblings().attr("checked","checked");
		}
		});
	});
	
	
	
	
	
