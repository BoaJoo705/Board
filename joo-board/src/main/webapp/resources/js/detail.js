$(document).ready(function(){

	// 등록 버튼 클릭
	document.getElementById('insert').onclick = function() {
	
		var formData = new FormData($('#boardForm')[0]);

		var title = $("#title").val();
		var content = $("#content").val();

		if (title === "") {
			alert("제목을 입력하세요.");
			return;
		} 
		if (content === "") {
			alert("내용을 입력하세요.");
			return;
		}

		$.ajax({
			url: '/api/insert',
			type: 'POST',
			async: false,
			data: formData,
			contentType:false,
			processData: false,
			// async: true,
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