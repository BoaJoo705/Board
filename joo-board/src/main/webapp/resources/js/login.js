$(document).ready(function(){


	// 로그인 버튼 클릭
	document.getElementById('login').onclick = function() {
		
		console.log("로그인 버튼 클릭");
		var userId = document.getElementById('userId').value;
		var userPw = document.getElementById('userPw').value;

		// 유효성 검사 시작
		// 기본적인 빈 값 확인
		if (userId === "") {
			alert("아이디를 입력해주세요.");
			$("#userId").focus();
			return false;
		}
	
		if (userPw === "") {
			alert("비밀번호를 입력해주세요.");
			$("#userPw").focus();
			return false;
		}
	
        var user = {
            "userId": userId,
			"userPw": userPw,
        };
		
		$.ajax({
			url: '/api/login',
			type: 'POST',
			data: JSON.stringify(user),
			contentType: 'application/json',
			success : function(result) { 

				// 로그인 성공 
				if(result.code === "ok"){
					alert("로그인 되었습니다.");  
					window.location.href = "/api/list"; 
				}else{
					// 로그인 실패
					alert("아이디나 비밀번호를 다시 확인하세요.");
					$("#userId").focus();
					return false;  
				}
				// 로그인 실패
			},
			error: function (jqXHR, textStatus, errorThrown,error) {
				console.error('Ajax request failed:', textStatus, errorThrown);
				console.log('Server response:', jqXHR.responseText);
				alert('에러 발생: ' + error.responseText);
			}	
		});
	};
  });