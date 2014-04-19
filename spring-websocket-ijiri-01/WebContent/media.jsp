<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<h1>이미지를 여기에 놓으세요</h1>
<head>
  <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
  
  	var wsUrl = "ws://echo.websocket.org/echo";
  	var ws = new WebSocket(wsUrl);
  	
  	ws.open = function() {
  		console.log("연결 열기 완료");
  	};
  	
  	ws.onmessage = function(e) {
  		
  		var blob = e.data;
  		
  		console.log("메시지 도착: " + blob.size + " 바이트");
  		
  		if (window.webkitURL) {
  			URL = webkitURL;
  		}
  		
  		var uri = URL.createObjectURL(blob);
  		var img = document.createElement("img");
  		img.src = uri;
  		document.body.appendChild(img);  		
  	};
  
  	document.ondrop = function(e) {
  		
  		document.body.style.backgroundColor = "#fff";
  		try {
  			//e.preventDefault();
  			handleFileDrop(e.dataTransfer.files[0]);
  			return false;
  		} catch(err) {
  			console.log(err);
  		}
  	};
  	
  	document.ondragover = function(e) {  		
  		e.preventDefault();
  		document.body.style.backgroundColor = "#6fff41";
  	};
  	
  	document.ondragleave = function(e) {  		
  		e.preventDefault();
  		document.body.style.backgroundColor = "#fff";
  	};
  	
  	function handleFileDrop(file) {
  		var reader = new FileReader();
  		reader.readerAsArrayBuffer(file);
  		reader.onload = function() {
  			console.log("송신 중: " + file.name);
  			ws.send(reader.result);
  		}
  	}
  	
  </script>
</head>


</html>