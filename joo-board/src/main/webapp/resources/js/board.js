$(document).ready(function(){

    // 로그아웃 클릭시 
    document.getElementById('logout').onclick = function() {
        console.log("로그아웃 클릭");
        logout();

    }

    // 로그아웃 요청 보내는 함수
    function logout() {
        console.log("로그아웃 함수 들어옴");
        // 서버에 로그아웃 요청을 보내고, 성공 시 클라이언트에서 JWT를 삭제합니다.
        fetch('/logout', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwtToken'), // JWT를 Authorization 헤더에 넣어 전송
            'Content-Type': 'application/json',
        },
        })
        .then(response => response.json())
        .then(data => {
        console.log(data.message);
        // 로그아웃 성공 시 클라이언트에서 JWT를 삭제
        localStorage.removeItem('jwtToken');
        // 추가적으로 로그아웃 후에 클라이언트에서 필요한 처리를 수행할 수 있습니다.
        })
        .catch(error => console.error('Error:', error));
    }


});