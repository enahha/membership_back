<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>${ogTitle}</title>
		<meta charset="UTF-8">
		<meta name="description" content="${ogDescription}">
		
		<meta property="og:type" content="website">
		<meta property="og:url" content="${ogUrl}">
		<meta property="og:title" content="${ogTitle}">
		<meta property="og:description" content="${ogDescription}">
		<meta property="og:image" content="${ogImage}">
		<meta name="keywords" content="beastar,비어스타,crowdfunding,크라우드펀딩,klay,클레이,klaytn,클레이튼,coin,코인,ido,token,토큰,cryptocurrency,암호화폐,creator,generator,생성"/>
		
		<link rel="icon" type="image/png" sizes="16x16" href="icons/favicon-16x16.png">
		<link rel="icon" type="image/png" sizes="32x32" href="icons/favicon-32x32.png">
		<link rel="icon" type="image/png" sizes="96x96" href="icons/favicon-96x96.png">
		<link rel="icon" type="image/png" sizes="128x128" href="icons/favicon-128x128.png">
		<link rel="icon" type="image/png" sizes="180x180" href="icons/favicon-180x180.png">
		<link rel="icon" type="image/png" sizes="192x192" href="icons/favicon-192x192.png">
		<link rel="icon" type="image/png" sizes="256x256" href="icons/favicon-256x256.png">
		<link rel="icon" type="image/png" sizes="512x512" href="icons/favicon-512x512.png">
		<link rel="icon" type="image/ico" href="favicon.ico">
	</head>
	<body>
		<div>
			<a href="${frontPath}" style="text-decoration: none;">
				<span style="color:#FFFFFF; text-decoration: none;">${ogTitle}</span>
				<span style="color:#FFFFFF; text-decoration: none;">${ogDescription}</span>
				<span style="color:#FFFFFF; text-decoration: none;">${description}</span>
			</a>
		</div>
	</body>
</html>

<script>
	window.location.href = '${frontPath}';
</script>
