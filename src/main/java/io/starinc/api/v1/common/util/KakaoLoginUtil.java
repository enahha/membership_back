/**
 * 
 */
package io.starinc.api.v1.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.starinc.api.v1.common.Constant;
import io.starinc.api.v1.user.vo.UserVo;

/**
 * @author ahn
 *
 */
public class KakaoLoginUtil {
	
	/**
	 * access_token 발급
	 * 
	 * @param autorize_code
	 * @return
	 */
	public static JsonNode getAccessToken(String autorize_code) {
		final String RequestUrl = Constant.KAKAO_TOKEN_REQUEST_URI;
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", Constant.KAKAO_REST_API_KEY)); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", Constant.KAKAO_REDIRECT_URI)); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정중 얻은 code 값
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		
		JsonNode returnNode = null;
		
		returnNode = requestHttp(postParams, client, post, returnNode);
		
		return returnNode;
	}
	
	/**
	 * access_token 발급 요청
	 * 
	 * @param postParams
	 * @param client
	 * @param post
	 * @param returnNode
	 * @return
	 */
	private static JsonNode requestHttp(final List<NameValuePair> postParams, final HttpClient client, final HttpPost post, JsonNode returnNode) {
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();
			// System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}
	
	/**
	 * 사용자 정보 조회
	 * 
	 * @param accessToken
	 * @return
	 */
	public static JsonNode getKakaoUserInfo(String accessToken) {
		// final String RequestUrl = "https://kapi.kakao.com/v1/user/me";
		final String RequestUrl = Constant.KAKAO_INFO_URI;
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		// add header
		post.addHeader("Authorization", "Bearer " + accessToken);
		JsonNode returnNode = null;
		
		returnNode = requestHttp2(client, post, returnNode);
		
		return returnNode;
	}
	
	/**
	 * 사용자 정보 조회 요청
	 * 
	 * @param client
	 * @param post
	 * @param returnNode
	 * @return
	 */
	private static JsonNode requestHttp2(final HttpClient client, final HttpPost post, JsonNode returnNode) {
		try {
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();
			//System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Response Code : " + responseCode);
			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;
	}
	
	
	
	public static UserVo changeData(JsonNode userInfo, UserVo userVo) {
		// 카카오톡 Response
		/*
			{
				"access_token":"Z9Pb6xWXeTvi0WiPvm2R5DyUMCtynQ6ln1VR4QopdaYAAAFn-OA82w"
				,"token_type":"bearer"
				,"refresh_token":"ujoUbW-idqZlRHAAarSbn0pUEjCY9J1O-wtV_QopdaYAAAFn-OA82Q"
				,"expires_in":7199
				,"scope":"age_range birthday account_email gender profile"
				,"refresh_token_expires_in":2591999
			}
			{
				"id":996942829
				,"properties":{
					"nickname":"안영대"
					,"profile_image":"http://k.kakaocdn.net/dn/S95Cn/btqrArTgCSX/WT3IA9hTndhLighTcLYX0k/profile_640x640s.jpg"
					,"thumbnail_image":"http://k.kakaocdn.net/dn/S95Cn/btqrArTgCSX/WT3IA9hTndhLighTcLYX0k/profile_110x110c.jpg"
				}
				,"kakao_account":{
					"has_email":true
					,"is_email_valid":true
					,"is_email_verified":true
					,"email":"ayd1029@nate.com"
					,"has_age_range":true
					,"age_range":"30~39"
					,"has_birthday":true
					,"birthday":"1029"
					,"has_gender":true
					,"gender":"male"
				}
			}
		 */
		return userVo;
	}
}
