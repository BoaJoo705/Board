$(document).ready(function(){


	console.log("진입??");
			document.getElementById('insert').onclick = function() {

			console.log("insert 진입");
			var formData = new FormData($('#boardForm')[0]);
			console.log("FormData: "+FormData);
			alert(FormData);
            $.ajax({
				url: '/api/insert',
                type: 'POST',
                data: formData,
				contentType:false,
                processData: false,
				// async: true,
                success: function (response) {
					console.log("성공이야?");
					console.log('Post created successfully:', response);
                    alert(response);
					alert("등록되었습니다");
                    // 추가적인 처리 로직 추가 가능
                },
                error: function (jqXHR, textStatus, errorThrown,error) {
					console.error('Ajax request failed:', textStatus, errorThrown);
        			console.log('Server response:', jqXHR.responseText);
                    alert('에러 발생: ' + error.responseText);
                }	
				
			})
		};
		
  });