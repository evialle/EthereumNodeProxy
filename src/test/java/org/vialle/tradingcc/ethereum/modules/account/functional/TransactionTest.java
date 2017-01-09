package org.vialle.tradingcc.ethereum.modules.account.functional;

import com.google.common.io.BaseEncoding;
import org.ethereum.core.Transaction;
import org.ethereum.crypto.ECKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.util.encoders.Hex;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vialle.tradingcc.ethereum.Application;

import java.math.BigInteger;

/**
 * Created by Eric on 30/10/2016.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//@ActiveProfiles("test")
public class TransactionTest {

    private static final Logger LOG = LoggerFactory.getLogger(WalletNodeService.class);

    @Test
    public void createSignedTransaction() {


        ECKey key = new ECKey();

        byte[] addr = key.getAddress();
        byte[] priv = key.getPrivKeyBytes();


        byte[] nonce = BigInteger.valueOf(1).toByteArray();
        byte[] gasPrice= BigInteger.valueOf(10).toByteArray();
        byte[] gasLimit = BigInteger.valueOf(1000).toByteArray();;
        byte[] receiveAddress = Hex.decode("bf7248fb860312706ac5b31c3f557c7ac889e2db");
        byte[] value = BigInteger.valueOf(1).toByteArray();
        byte[] data = null;

        Transaction tx = new Transaction(nonce, gasPrice, gasLimit, receiveAddress,value, data);
        tx.sign(key);

        tx.getEncodedRaw();
        tx.getEncoded();
    }
}
