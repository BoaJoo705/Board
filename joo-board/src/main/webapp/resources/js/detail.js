$(document).ready(function(){
//console.log("진입??");
		$("#insert").click(function(){
			var jsonData = JSON.stringify({
				boardTitle: $('#title').val(),
				boardContent: $('#content').val(),
			});
			$.ajax({
					url:"/api/board/insert",
					type:"POST",
					data:jsonData,
					async: true,
					contentType:"application/json",
					success:function(){
						alert('등록되었습니다');
// 						location.herf='/main/main'
						window.location.reload();
					},
					error:function(e){
						console.log(e.message);
						alert('저장실패');
					}
				
			})
		})
  });