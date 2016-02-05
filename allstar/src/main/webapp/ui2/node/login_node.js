

var http = require('http');
var express = require('express');
var app = express();
var url= require('url');
var querystring = require('querystring');

var httpServer = http.createServer(function(request, response){
	
	response.writeHead(200, {
		'Content-Type' : 'text/html;charset=UTF-8'
	});
	
	if(request.url == '/favicon.ico'){
		response.end();
		return;
	}

	var mysql = require('mysql');

               var connection = mysql.createConnection({
                 host     : 'localhost',
                 user     : 'godg',
                 password : '1111',
                 database : 'allstar'
               });
               connection.connect();
               console.log("연결되었습니다.");
           	console.log(request.url);
           		response.write("<!DOCTYPE html>\n")
           		response.write("<html>\n")
           		response.write("<head>\n")
           		response.write("<meta charset=\"UTF-8\">\n")
           		response.write("<title>id 검사</title>\n")
           		response.write("</head>\n")
           		response.write("<body>\n")
           		response.write("<h1>url 상세정보</h1>\n")
           		
           		var urlInfo=url.parse(request.url);
           		var params=querystring.parse(urlInfo.query);
           		response.write("id="+params.id+"<br>\n");
           		var user_id =0 ;
               connection.query(
                   "select * from member where id=? ",
                   [params.id],
                   function(err, rows) {
                 if (err) {}
                 else if(rows == ''){
                	 console.log("사용가능한 아이디입니다");
                	 return ;
                 } else {
                	 user_id=1;
                	 console.log("이미존재하는 아이디입니다");
                	 console.log("in: "+user_id);
                 }
                 }
                   );
//               
          	 console.log("out: " +user_id);
              if(user_id== 1){
            	  response.send("<span id='id_res_Bool'>1</span>\n");
               } else {
            	   response.send("<span id='id_res_Bool'>0</span>\n");
               }
            		response.write("</body>\n")
               		response.write("</html>\n")
                 response.end();
           	
				});

httpServer.listen(8989);

console.log("서버실행");