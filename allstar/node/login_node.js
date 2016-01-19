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
                   [$('#id_signin').val()],
                   function(err, rows, fields) {
                 if (err) alert("사용가능한 아이디입니다");
                 alert("이미존재하는 아이디입니다");
               });
               connection.end();
               console.log("연결끊었습니다.");