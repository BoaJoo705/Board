<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="/resources/css/detail.css" rel="stylesheet" />
</head>

<!------ Include the above in your HEAD tag ---------->

<div class="container contact">
	<div class="row">
		<div class="col-md-3">
			<div class="contact-info">
				<img src="https://image.ibb.co/kUASdV/contact-image.png" alt="image"/>
				<h2>Board detail</h2>
			</div>
		</div>
		<div class="col-md-9">
				<form id="boardForm"  method="POST" enctype="multipart/form-data">
			<div class="contact-form">
				<div class="form-group">
				  <label class="control-label col-sm-2" for="title">제목</label>
				  <div class="col-sm-10">          
					<input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요" name="title" value="${board.boardTitle}">
				  </div>
				</div>
				<div class="form-group">
				  <label class="control-label col-sm-2" for="content">내용</label>
				  <div class="col-sm-10">
					<textarea class="form-control" rows="10" id="content" name="content" placeholder="내용을 입력해주세요">${board.boardContent}</textarea>
				  </div>
				</div>
				<div class="form-group"> 
				  <label class="control-label col-sm-4" for="file">첨부파일</label>
				  <div class="col-sm-10">
					<input type="file" id="file" name="file" accept=".jpg, .jpeg, .png"/><br>
					<c:if test="${not empty boardAttachment.filePath}">
						<img src="${pageContext.request.contextPath}/resources/img/upload/${boardAttachment.storedFileName}" style="width: 200px; height: 200px;" alt="첨부 이미지"/>
					</c:if>
				  </div>
 				</div>
				<div class="form-group">        
				  <div class="col-sm-offset-2 col-sm-10">
					<button id="insert" class="btn btn-default">등록</button>
				  </div>
				</div>
			</div>
		</form>
		</div>
	</div>
</div>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/detail.js" ></script> 
