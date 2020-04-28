package com.lyp.learn;

import org.junit.jupiter.api.Test;

import javax.crypto.KeyAgreement;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.NamedParameterSpec;
import java.security.spec.XECPublicKeySpec;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-28 18:40
 */
public class KeyPairGeneratorTest {

    @Test
    public void test01() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeySpecException, InvalidKeyException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("XDH");
        NamedParameterSpec paramSpec = new NamedParameterSpec("X25519");
        kpg.initialize(paramSpec);
        KeyPair kp = kpg.generateKeyPair();

        KeyFactory kf = KeyFactory.getInstance("XDH");
        BigInteger u = new BigInteger("66666666666");
        XECPublicKeySpec pubSpec = new XECPublicKeySpec(paramSpec, u);
        PublicKey pubKey = kf.generatePublic(pubSpec);

        KeyAgreement ka = KeyAgreement.getInstance("XDH");
        ka.init(kp.getPrivate());
        ka.doPhase(pubKey, true);
        byte[] secret = ka.generateSecret();
    }
}
