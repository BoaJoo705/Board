$(document).ready(function(){

	// 회원 등록 버튼 클릭
	document.getElementById('insert').onclick = function() {
		
		var formData = new FormData($('#userForm')[0]);
		console.log("회원등록버튼 클릭");
		var userId = document.getElementById('userId').value;
        var userName = document.getElementById('userName').value;
		var userEmail = document.getElementById('userEmail').value;
		var userPw = document.getElementById('userPw').value;
		var inputPasswordConfirm = document.getElementById('inputPasswordConfirm').value;
		var mobileNo = document.getElementById('mobileNo').value;
		var birth = document.getElementById('birth').value;

		// 유효성 검사 시작
		// 기본적인 빈 값 확인
		if (userId === "") {
			alert("아이디를 입력해주세요.");
			$("#userId").focus();
			return false;
		}
	
		if (userName === "") {
			alert("이름을 입력해주세요.");
			$("#userName").focus();
			return false;
		}
	
		if (userEmail === "") {
			alert("이메일을 입력해주세요.");
			$("#userEmail").focus();
			return false;
		}
	
		if (userPw === "") {
			alert("비밀번호를 입력해주세요.");
			$("#userPw").focus();
			return false;
		}
	
		if (inputPasswordConfirm === "") {
			alert("비밀번호 확인을 입력해주세요.");
			$("#inputPasswordConfirm").focus();
			return false;
		}
	
		if (mobileNo === "") {
			alert("휴대폰 번호를 입력해주세요.");
			$("#mobileNo").focus();
			return false;
		}
	
		if (birth === "") {
			alert("생년월일을 입력해주세요.");
			$("#birth").focus();
			return false;
		}
	
		// 비밀번호 확인
		if (userPw !== inputPasswordConfirm) {
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			return false;
		}
	
		// 이메일 형식 확인 (간단한 형식 검사)
		var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
		if (!emailRegex.test(userEmail)) {
			alert("올바른 이메일 형식이 아닙니다.");
			return false;
		}
	
		// 휴대폰 번호 형식 확인 (간단한 형식 검사)
		var mobileRegex = /^\d{10,11}$/;
		if (!mobileRegex.test(mobileNo)) {
			alert("올바른 휴대폰 번호 형식이 아닙니다.");
			return false;
		}
	
		// 생년월일 유효성 검사 (간단한 형식 검사)
		var birthRegex = /^\d{4}-\d{2}-\d{2}$/;
		if (!birthRegex.test(birth)) {
			alert("올바른 생년월일 형식이 아닙니다.");
			return false;
		}
	


        var user = {
            "userId": userId,
            "userName": userName,
			"userEmail": userEmail,
			"userPw": userPw,
			"mobileNo": mobileNo,
			"birth": birth
        };
		
		$.ajax({
			url: '/api/signup',
			type: 'POST',
			// async: false,
			data: JSON.stringify(user),
			contentType: 'application/json',
			success : function(result) { 
				// 결과 성공 콜백함수        
				alert(result.message);  
				window.location.href = "/api/list";  
			},
			error: function (jqXHR, textStatus, errorThrown,error) {
				console.error('Ajax request failed:', textStatus, errorThrown);
				console.log('Server response:', jqXHR.responseText);
				alert('에러 발생: ' + error.responseText);
			}	
			
		});
	}
		
  });