<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
    $(function(){
      var ws = new WebSocket("ws://localhost:8080/websocket/echo");

      ws.onopen = function(message){    	  
    	  $("#log").append("$ 연결되었습니다.").append("<br/>");
      };

      ws.onclose = function(){
    	  $("#log").append(message.data).append("<br/>");
      };

      ws.onmessage = function(message){
        $("#log").append(message.data).append("<br/>");
        $("#message").val("");
      };

      ws.onerror = function(event){
        alert(event.data);
        alert(event.message);
      };

      $("#form").submit(function(){
        ws.send($("#message").val());
        return false;
      });
    });
  </script>
</head>

<body>
  <div id="log"></div>
  <form id="form" action="#">
    <input type="text" id="message" /> <input type="submit" id="send" value="send" />
  </form>
</body>

</html>