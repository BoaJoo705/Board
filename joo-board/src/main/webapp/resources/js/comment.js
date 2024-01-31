
$(document).ready(function(){

	// 댓글 수정 버튼 클릭시

	$('.cmtUpdate').on('click', function() {
		console.log("댓글 수정");

		//댓글 번호 
		var commentId = $(this).attr('name');
		console.log($(this).attr('name'));
		
		// 해당 댓글만 수정 가능하도록 readonly 해제
		$('#cmtNum'+commentId).attr('readonly',false);
	
		//댓글수정저장으로 버튼 변경 
		$(this).html('댓글수정저장');

		// 저장하도록 id값 변경
		$('.cmtUpdateNum'+commentId).prop('id', 'updateCommentSave').show();

	
		

		// 댓글 수정 내용 저장하기 
		$('#updateCommentSave').on('click', function() {

			//댓글 번호 
			var commentId = $(this).attr('name');
			console.log("댓글 수정 저장");
			console.log("commentId:"+commentId);

			// 게시판 번호
			var boardId = $("#boardId").val();

			var commentContent = $('#cmtNum'+commentId).val();
			console.log("수정 저장 댓글: "+commentContent);
			if (commentContent === "") {
				alert("댓글을 입력하세요.");
				return;
			} 
			
			// alert("중지");
			$.ajax({
				url: '/api/contentUpdate',
				type: 'POST',
				// async: false,
				data: {
					commentId : commentId,
					commentContent : commentContent
				},
				// data: JSON.stringify(board),
				// contentType: 'application/json',
				// processData: false,
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
		// ajax 끝
		})
	})
	// 댓글 수정 버튼 끝


	// 댓글달기 버튼 있으면 
	if(document.getElementById('showCommentForm')) {
		document.getElementById('showCommentForm').onclick = function() {
			$('#commentForm').show(); 
			$('#commentWrite').show(); 

		}

		if(document.getElementById('commentWrite')) {
			document.getElementById('commentWrite').onclick = function() {
				console.log("댓글 등록 ");

				// 게시판 번호
				var boardId = $("#boardId").val();
				var commentContent = $("#commentContent").val();

				if (commentContent === "") {
					alert(" 댓글을 입력하세요.");
					return;
				} 

				console.log("댓글 ajax 넘기기전");
				// alert("중지");
				$.ajax({
					url: '/api/commentWrite',
					type: 'POST',
					// async: false,
					data: {
						boardId : boardId,
						commentContent : commentContent
					},
					success : function(result) { 
						// 댓글 저장 성공시      
						alert(result.message);  
						window.location.href = "/board/write/" + boardId; 
					},
					error: function (jqXHR, textStatus, errorThrown,error) {
						console.error('Ajax request failed:', textStatus, errorThrown);
						console.log('Server response:', jqXHR.responseText);
						alert('에러 발생: ' + error.responseText);
					}	
				});



			}
		}

	};
	// 댓글 삭제 버튼 끝 
	$('.cmtDelete').on('click', function() {

		//댓글 삭제 번호
		var commentId = $(this).attr('name');
		console.log($(this).attr('name'));

		// 게시판 번호
		var boardId = $("#boardId").val();

		if (confirm("정말 삭제하시겠습니까??") == true){    //확인

			$.ajax({
				url: '/api/commentDelete',
				type: 'POST',
				// async: false,
				data: {
					commentId : commentId
				},
				success : function(result) { 
					// 삭제 성공시        
					alert(result.message);  
					window.location.href = "/board/write/" + boardId; 
				},
				error: function (jqXHR, textStatus, errorThrown, error) {
					console.error('Ajax request failed:', textStatus, errorThrown);
					console.log('Server response:', jqXHR.responseText);
					alert('에러 발생: ' + error.responseText);
				}   
			});
			// ajax 끝
		}

	})
	// 댓글 삭제 버튼 끝 

});