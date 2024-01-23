$(document).ready(function(){

	document.getElementById('insert').onclick = function() {
	
		var formData = new FormData($('#boardForm')[0]);

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
			},
			error: function (jqXHR, textStatus, errorThrown,error) {
				console.error('Ajax request failed:', textStatus, errorThrown);
				console.log('Server response:', jqXHR.responseText);
				alert('에러 발생: ' + error.responseText);
			}	
			
		});
	}
		
  });