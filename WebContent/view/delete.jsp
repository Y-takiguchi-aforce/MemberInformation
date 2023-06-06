<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報削除</title>
</head>
<body>
<p>会員情報削除</p>
<form action="delete" method="post">
■会員番号削除
<input type="text" name="member_id">
<input type="submit" name="delete" value="削除">
<br>
<input type="button" name="back" onclick="history.back()" value="戻る">
<br>
</form>

</body>
</html>