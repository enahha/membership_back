<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
	</body>
</html>

<script>
	var email = '${email}';
	var authKey = '${authKey}';
	var frontPath = '${frontPath}';
	var frontLocale = '${frontLocale}';
	var adcd = '${adcd}';
	
	var redirectFrontBaseUri = '${domain}' + '/#/app/kakaoLoginRedirect';
	var params = "?email=" + email + "&authKey=" + authKey + "&frontLocale=" + frontLocale + '&frontPath=' + frontPath + '&adcd=' + adcd;
	
	var redirectUri = redirectFrontBaseUri + params;
	
	// alert(redirectUri);
	window.location.href = redirectUri;
</script>