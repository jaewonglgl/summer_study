<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>    
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Chat</title>

<style>
  .inset {
    box-shadow: inset 0 0 4px #000000;
    -moz-box-shadow: inset 0 0 4px #000000;
    -webkit-box-shadow: inset 0 0 4px #000000;
    width: 400px;
    border-width: 1px;
    padding: 5px;
    overflow-y: scroll;
  }
  input.inset {
    height: 40px;
  }
  div.inset {
    height: 500px;
    white-space: pre-wrap
  }
</style>
</head>

<body>
<div id="chat" class="inset"></div>
<p>type text :</p>
<input id="input" type="text" onkeydown="send(event)" placeholder="press enter to send" class="inset">

<script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>

<script type="text/javascript">
(function poll(){
	console.log("polling start")
    $.ajax({ url: "/spring-test/chatDeferred/broadcast", 
    	success: function(data) {
    		$('#chat').append(JSON.stringify(data.content) + "\n");
    	}
    	,error: function(request,status,error) {
    		console.log("connection removed");
    	}
    	,dataType: "json"
    	,complete: poll 
    });
})();

function send(event) {
	if (event.keyCode == 13 || event.which == 13) {
		var message = $('#input').val();
      	if (message.length > 0) {
        	console.log('message send : ' + $('#input').val());
        	
        	var jsonArg = new Object();
        	jsonArg.content = message;
        	
        	$.ajax({
        		  type: 'POST',
        		  url: "/spring-test/chatDeferred/add",
        		  data: jsonArg,
        		  dataType:"json",
        		  success: function() {
        			  $('#input').val("");
        		  }
        	});
     	}
    }
}
</script>
</body>
</html>