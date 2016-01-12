$(document).ready(function() {
	var username = $('#username').val();
	var password = $('#password').val();
	var error = true;
	
	$('#loading').html("<img src='../images/sub_bg.jpg'/>").fadeIn('fast');
	$.ajax({
		type: "POST",
		url: "",
		dataType:"json",
		success:function(data) {
			$('#loading').fadeOut('fast');
			
			$.each(data, function(key, value){
				if(username == member.id && password == member.pwd) {
					error = false;
				}
			});
			if(error == false) {
				/*  로그인 실패시... */
			} else {
				$('#username').val();
				$('#password').val();
			}
		}
	});
	return false;
});
