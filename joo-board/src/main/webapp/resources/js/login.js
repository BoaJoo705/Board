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
            "username": userId,
			"password": userPw,
        };
		console.log("username: "+userId);
		console.log("password: "+userPw);

		var formData = new FormData($('#userform')[0]);

		$.ajax({
			url: '/login',
			type: 'POST',
			data: formData,
			contentType: false,
         	 processData: false,
			// contentType: 'application/json',
			success : function(result,testStatus,jqXHR) { 
				console.log("ajax 성공!!!!!!");
				console.log(result.data);
				var token = jqXHR.getResponseHeader('Authorization');
				console.log("token: "+token);
				// 로그인 성공 
				if(token != ""){
					var expirationDate = new Date();  // 토큰의 만료 날짜를 나타내는 Date 객체 생성
					expirationDate.setHours(expirationDate.getHours() + 1);  // 예시로 1시간
					document.cookie = "jwtToken=" + token;
					document.cookie = "jwtToken=" + token + "; path=/; expires=" + expirationDate.toUTCString() + "; Secure; SameSite=None; HttpOnly";

					localStorage.setItem("jwtToken", token);
					sessionStorage.setItem("jwtToken", token);
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
				alert("아이디나 비밀번호를 다시 확인하세요.");
				console.log("ajax 에러!!!!!!");
				console.error('Ajax request failed:', textStatus, errorThrown);
				console.log('Server response:', jqXHR.responseText);
				// alert('에러 발생: ' + error.responseText);
			}	
		});
	};
  });