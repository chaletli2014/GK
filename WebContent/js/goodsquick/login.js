jQuery(document).ready(function($){
	$("#aToReg").click(function(e) {
	    toregscreen();
	});

	if(window.location.hash==="#ToReg"||window.location.hash==="#mReg"){
	    toregscreen();
	}

	$("#aToLogin").click(function(e) {
	    $("#divReg").animate({
	        "margin-top": '-1300px'
	    });
	    $("#mLogin").css("margin-top", "1000px");
	    $("#mLogin").animate({
	        "margin-top": '-160px'
	    }, 'slow',function(){
	        $("#divReg").css({
	            'display':'none'
	        });
	    });
	    window.isReg = false;
	});
	$("#btnLogin").click(function(e) {
		submitLogin();
	});

	$("#btnToReg").click(function(e) {
	    if (window.isReg !== true) {
	        $(window).scrollTop(0);
	        $("#aToReg").trigger("click");
	    }

	});
});

window.isReg = false;
function toregscreen(e){
    $("#divReg").css({
        "margin-top": '1000px','display':'block'
    });

    $("#mLogin").animate({
        "margin-top": '-1300px'
    }, 'slow',function(){
    });
    
    $("#divReg").animate({
        "margin-top": '140px'
    });
    window.isReg = true;
}