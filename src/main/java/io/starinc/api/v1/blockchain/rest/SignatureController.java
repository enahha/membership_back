package io.starinc.api.v1.blockchain.rest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class SignatureController {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int NONCE_BYTE_SIZE = 16; // 16 bytes = 128 bits
    
    /**
     * Ethereum에서 사용자 정의한 모든 서명 메시지는 다음 문자로 시작합니다.
     */
    private static final String PERSONAL_MESSAGE_PREFIX = "\u0019Ethereum Signed Message:\n";

	/**
	 * 지갑 서명을 위한 Nonce 생성
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/signature/generateNonce")
	public static String generateNonce() {
		byte[] nonceBytes = new byte[NONCE_BYTE_SIZE];
		secureRandom.nextBytes(nonceBytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(nonceBytes);
	}
	
	/**
	 * 지갑 검증
	 * 
	 * @param message
	 * @param signature
	 * @param userAddress
	 * @return boolean
	 * @throws Exception
	 */
	public boolean verifySignature(String message, String signature, String userAddress) {
	    System.out.println("verifySignature in ....");

	    // Ethereum Signed Message Prefix
	    String prefix = "\u0019Ethereum Signed Message:\n" + message.length();
	    byte[] msgHash = Hash.sha3((prefix + message).getBytes(StandardCharsets.UTF_8));
	    System.out.println("msgHash 값: " + Numeric.toHexString(msgHash));

	    byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
	    if (signatureBytes.length != 65) {
	        System.out.println("Invalid signature length");
	        return false;
	    }

	    byte v = signatureBytes[64];
	    if (v < 27) {
	        v += 27;
	    }

	    SignatureData sd = new SignatureData(
	            v,
	            Arrays.copyOfRange(signatureBytes, 0, 32),
	            Arrays.copyOfRange(signatureBytes, 32, 64)
	    );

	    String addressRecovered = null;
	    boolean match = false;
	    BigInteger publicKey = null;

	    // Iterate for each possible key to recover
	    for (int i = 0; i < 4; i++) {
	        publicKey = Sign.recoverFromSignature(
	                (byte) i,
	                new ECDSASignature(new BigInteger(1, sd.getR()), new BigInteger(1, sd.getS())),
	                msgHash);

	        if (publicKey != null) {
	            addressRecovered = "0x" + Keys.getAddress(publicKey);
	            System.out.println("Recovered address: " + addressRecovered);

	            if (addressRecovered.equalsIgnoreCase(userAddress.trim())) {
	                match = true;
	                break;
	            }
	        }
	    }

	    if (publicKey == null) {
	        System.out.println("Public key recovery failed.");
	    } else {
	        String pubKey = "0x" + publicKey.toString(16);
	        System.out.println("Recovered Public Key: " + pubKey);
	    }

	    System.out.println("User Address: " + userAddress);
	    System.out.println("Match? " + match);
	    return match;
	}
}
