

var http = require('http');

var httpServer = http.createServer(function(request, response){
	
	response.writeHead(200, {
		'Content-Type' : 'text/html;charset=UTF-8'
	});
	


	var mysql = require('mysql');

               var connection = mysql.createConnection({
                 host     : 'localhost',
                 user     : 'root',
                 password : '1111',
                 database : 'allstar'
               });
               connection.connect();
               console.log("연결되었습니다.");
               
               connection.query(
                   "select from member where id=? ",
                   [new_id],
                   function(err, rows, fields) {
                 if (err) alert("사용가능한 아이디입니다");
                 alert("이미존재하는 아이디입니다");
                 response.end();
               });
              
           	
				});

httpServer.listen(8989);

console.log("서버실행");