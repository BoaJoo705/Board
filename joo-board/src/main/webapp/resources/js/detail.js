$(document).ready(function(){
// 수정 버튼 클릭시
if(document.getElementById('updateWrite')) {
	document.getElementById('updateWrite').onclick = function() {

	// 첨부된 이미지 삭제 여부 
	var imgDeleteClick = "N";

	// updateWriteButton.click(function() {
		console.log("수정버튼 클릭시 작동");
		// 작성자가 로그인한 사람과 같은지 확인 로직
		$(this).html('저장');
		// readonly -> false 로 수정
		$('.title').attr('readonly',false);
		$('.content').attr('readonly',false);

		$('#updateWrite').prop('id', 'modify').show();
		$('#fileshow').show(); // 첨부파일 첨부가능하도록 변경
		$('#deleteImage').show();  // 첨부파일 존재시 삭제 가능하도록 버튼 

	// 이미지 삭제 버튼 클릭시 
	if(document.getElementById('deleteImage')) {
		document.getElementById('deleteImage').onclick = function() {
			console.log("첨부파일 삭제");

			if (confirm("정말 삭제하시겠습니까??") == true){    //확인
				$('#imgDeleteYn').val("Y");
				$('#OriBoardImg').remove();
			}
		}
	}
	// 수정 내용 저장하기 
	if(document.getElementById('modify')) {
		document.getElementById('modify').onclick = function() {
			console.log("수정 저장");

				var formData = new FormData($('#boardForm')[0]);
				var boardId = $("#boardId").val();
				// var title = $("#title").val();
				// var content = $("#content").val();

				if (title === "") {
					alert("제목을 입력하세요.");
					return;
				} 
				if (content === "") {
					alert("내용을 입력하세요.");
					return;
				}

				$.ajax({
					url: '/api/update',
					type: 'POST',
					async: false,
					data: formData,
					// data: JSON.stringify(board),
					contentType: false,
					processData: false,
					success : function(result) { 
						// 수정 저장 성공시        
						alert(result.message);  
						window.location.href = "/board/write/" + boardId;  
					},
					error: function (jqXHR, textStatus, errorThrown, error) {
						console.error('Ajax request failed:', textStatus, errorThrown);
						console.log('Server response:', jqXHR.responseText);
						alert('에러 발생: ' + error.responseText);
					}   
				});
			}
		}
	

		}
	}
	// 수정 버튼 끝

	// 삭제버튼 
	if(document.getElementById('delete')) {
		document.getElementById('delete').onclick = function() {
			console.log("삭제버튼 클릭");
			if (confirm("정말 삭제하시겠습니까??") == true){    //확인

				var formData = new FormData($('#boardForm')[0]);

				$.ajax({
					url: '/api/delete',
					type: 'POST',
					async: false,
					data: formData,
					// data: JSON.stringify(board),
					contentType: false,
					processData: false,
					success : function(result) { 
						// 삭제 성공시        
						alert(result.message);  
						window.location.href = "/api/list";  
					},
					error: function (jqXHR, textStatus, errorThrown, error) {
						console.error('Ajax request failed:', textStatus, errorThrown);
						console.log('Server response:', jqXHR.responseText);
						alert('에러 발생: ' + error.responseText);
					}   
				});
				// ajax 끝

			}
			}
	}
	// 삭제버튼 끝

});