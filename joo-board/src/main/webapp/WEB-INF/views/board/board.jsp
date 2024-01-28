
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="KO">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Tables - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="/api/list">Boa's board</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <!--로그인 되어있으면 노출 X-->
                        <li><a class="dropdown-item" href="/user/signup">회원가입</a></li>
                        <!-- <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li> -->
                        <!-- 로그인 되어있으면 노출 O-->
                        <li><a class="dropdown-item" href="#!">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <!-- <div class="sb-sidenav-menu-heading">Core</div> -->
                            <!-- <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt">123</i></div>
                                게시판123
                            </a> -->
<!--                             <div class="sb-sidenav-menu-heading">Interface</div> -->
<!--                             <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts"> -->
<!--                                 <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div> -->
<!--                                 Layouts -->
<!--                                 <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div> -->
<!--                             </a> -->
<!--                             <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion"> -->
<!--                                 <nav class="sb-sidenav-menu-nested nav"> -->
<!--                                     <a class="nav-link" href="layout-static.html">Static Navigation</a> -->
<!--                                     <a class="nav-link" href="layout-sidenav-light.html">Light Sidenav</a> -->
<!--                                 </nav> -->
<!--                             </div> -->
<!--                             <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages"> -->
<!--                                 <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div> -->
<!--                                 Pages -->
<!--                                 <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div> -->
<!--                             </a> -->
<!--                             <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion"> -->
<!--                                 <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages"> -->
<!--                                     <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth"> -->
<!--                                         Authentication -->
<!--                                         <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div> -->
<!--                                     </a> -->
<!--                                     <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages"> -->
<!--                                         <nav class="sb-sidenav-menu-nested nav"> -->
<!--                                             <a class="nav-link" href="login.html">Login</a> -->
<!--                                             <a class="nav-link" href="register.html">Register</a> -->
<!--                                             <a class="nav-link" href="password.html">Forgot Password</a> -->
<!--                                         </nav> -->
<!--                                     </div> -->
<!--                                     <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError"> -->
<!--                                         Error -->
<!--                                         <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div> -->
<!--                                     </a> -->
<!--                                     <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages"> -->
<!--                                         <nav class="sb-sidenav-menu-nested nav"> -->
<!--                                             <a class="nav-link" href="401.html">401 Page</a> -->
<!--                                             <a class="nav-link" href="404.html">404 Page</a> -->
<!--                                             <a class="nav-link" href="500.html">500 Page</a> -->
<!--                                         </nav> -->
<!--                                     </div> -->
<!--                                 </nav> -->
<!--                             </div> -->
<!--                             <div class="sb-sidenav-menu-heading">Addons</div> -->
<!--                             <a class="nav-link" href="charts.html"> -->
<!--                                 <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div> -->
<!--                                 Charts -->
<!--                             </a> -->
<!--                             <a class="nav-link" href="tables.html"> -->
<!--                                 <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div> -->
<!--                                 Tables -->
<!--                             </a> -->
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">로그인 후 이용해주세요</div>
<!--                         Start Bootstrap -->
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Tables</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                            <li class="breadcrumb-item active">Tables</li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                게시판을 이용해주시기 바랍니다.
                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Dashboard
                            	<div class="button-contents button" align="right">
							    	<span class="button-text" onClick="location.href='/board/write'">등록</span>
							  	</div>
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>제목</th>
                                            <th>조회수</th>
                                            <th>작성일</th>
                                        </tr>
                                    </thead>
                                    <tbody>
										<c:if test="${not empty list}">
											<c:forEach var="boardList" items="${list}" varStatus="st">
											<fmt:parseDate value="${boardList.regDate}" var="dateValue" pattern="yyyy-MM-dd"/>
											<fmt:formatDate value="${dateValue}" var="date" pattern="yyyy-MM-dd"/>
												<tr>	
													<td>${boardList.boardId }</td>
													<td><a href="/board/write/${boardList.boardId}">${boardList.boardTitle }</a></td>
													<td>${boardList.boardCnt }</td>
													<td>${date }</td>
												<tr>
											</c:forEach>
										</c:if>
										</tbody>
<!--                                     <tbody> -->
<%--                                         <tr th:if="${#lists.size(list)} > 0 " th:each="list : ${list }"> --%>
<%--                                             <td th:text="${list.boardId }"></td> --%>
<%--                                             <td class="title" th:text="${list.title }"></td> --%>
<%--                                             <td th:text="${list.boardCnt }"></td> --%>
<%--                                             <td th:text="${#temporals.format(list.regDate,'yyyy-MM-dd' }"></td> --%>
<!--                                         </tr> -->
<%--                                         <tr th:unless="${#list.size(list) } > 0"> --%>
<!--                                         	<td colspan="4">조회된 결과가 없습니다.</td>  -->
<!--                                         </tr> -->
<!--                                     </tbody> -->
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/datatables-simple-demo.js"></script>
    </body>
</html>
