<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<form id="boardForm"  method="POST" enctype="multipart/form-data" autocomplete="off">
			<input type="hidden" id="boardId" name="boardId"  value="${board.boardId}">
			<input type="hidden" id="atchYn"  name="atchYn" value="${board.atchYn}">
			<input type="hidden" id="imgDeleteYn"  name="imgDeleteYn" value="N">
			<input type="hidden" id="attachId" name="attachId" value="${not empty boardAttachment.attachId ? boardAttachment.attachId : 0}">
			<!-- <input type="hidden" id="attachId"  name="attachId" value="${boardAttachment.attachId}"> -->
			<div class="contact-form">
				<div class="form-group">
				  <label class="control-label col-sm-2" for="title">제목</label>
				  <div class="col-sm-10">  
					<c:choose>
            			<c:when test="${not empty board.boardId}">        
							<input type="text" class="form-control title" id="title" placeholder="제목을 입력해주세요" name="title" value="${board.boardTitle}" readonly>
						</c:when>
						<c:otherwise>
							<input type="text" class="form-control" id="title" placeholder="제목을 입력해주세요" name="title">
						</c:otherwise>
					</c:choose>
				</div>
				</div>
				<div class="form-group">
				  <label class="control-label col-sm-2" for="content">내용</label>
				  <div class="col-sm-10">
					<c:choose>
            			<c:when test="${not empty board.boardId}">        
							<textarea class="form-control content" rows="10" id="content" name="content" placeholder="내용을 입력해주세요" readonly>${board.boardContent}</textarea>
						</c:when>
						<c:otherwise>
							<textarea class="form-control" rows="10" id="content" name="content" placeholder="내용을 입력해주세요"></textarea>
						</c:otherwise>
					</c:choose>
				  </div>
				</div>
				<div class="card mb-4">
					<div class="card-body" style="font-size: smaller;">
						** 첨부파일은 1장만 저장 됩니다. 재첨부시 재첨부한 파일만 저장 됩니다.
					</div>
				</div>
				<c:choose>
					<c:when test="${not empty boardAttachment.storedFileName}">  <!-- 첨부파일이 있다면 -->
						<img id="OriBoardImg" src="${pageContext.request.contextPath}/resources/img/upload/${boardAttachment.storedFileName}" style="width: 200px; height: 200px; padding: 10px; background-color: white;" alt="첨부 이미지"/>
						<div class="form-group file" id="fileshow" style="display: none;"> 
							<!-- <label class="control-label col-sm-4" for="file">첨부파일</label> -->
							<div class="col-sm-10">
								<input type="file" id="file" name="file" accept=".jpg, .jpeg, .png"/><br>
							</div>
						</div>
						<div class="form-group">        
							<div class="col-sm-offset-3 col-sm-12">
							  <button id="deleteImage" type="button" class="btn btn-default" style="display: none;">이미지 삭제</button>
							</div>
						</div>

						<!-- <button id="deleteImage" type="button" class="btn btn-danger" style="display: none;">이미지 삭제</button> -->
					</c:when>
					<c:otherwise> <!-- 첨부파일이 없다면 2가지 경우 (등록 or 첨부파일 x)-->
						<c:choose>
							<c:when test="${empty board.boardId}">  <!-- 첨부파일 x 상세보기 -->
								<div class="form-group"> 
									<label class="control-label col-sm-4" for="file">첨부파일</label>
									<div class="col-sm-10">
										<input type="file" id="file" name="file" accept=".jpg, .jpeg, .png"/><br>
									</div>
								</div>
							</c:when>
							<c:otherwise> <!--첨부파일 있을시-->
								<div class="form-group file" id="fileshow" style="display: none;"> 
									<label class="control-label col-sm-4" for="file">첨부파일</label>
									<div class="col-sm-10">
										<input type="file" id="file" name="file" accept=".jpg, .jpeg, .png"/><br>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
				<c:if test="${empty board.boardId}">
					<div class="form-group">        
					  <div class="col-sm-offset-2 col-sm-10">
						<button id="insert" type="button" class="btn btn-default">등록</button>
					  </div>
					</div>
				</c:if>
				<div class="form-group">        
					<div class="col-sm-offset-2 col-sm-10">
					  <button id="list" type="button" class="btn btn-default" onclick="location.href='/api/list'">리스트</button>
					</div>
				</div>
				<div class="form-group">        
					<div class="col-sm-offset-2 col-sm-10">
					  <button id="updateWrite" type="button" class="btn btn-default">수정</button>
					</div>
				</div>
			</div>
		</form>
		</div>
	</div>
</div>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/detail.js" ></script> 
