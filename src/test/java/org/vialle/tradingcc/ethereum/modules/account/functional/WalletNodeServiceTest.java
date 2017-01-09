package org.vialle.tradingcc.ethereum.modules.account.functional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vialle.tradingcc.ethereum.Application;
import org.vialle.tradingcc.ethereum.modules.account.functional.exceptions.WalletException;

import javax.inject.Inject;
import java.math.BigInteger;

/**
 * Created by Eric on 16/10/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class WalletNodeServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(WalletNodeService.class);

    @Inject
    private WalletNodeService walletService;

    @Test
    @Ignore
    public void testCreateWallet() throws Exception, WalletException {
        String address = walletService.createWallet("test");
        LOG.info("Created Account: {}", address);
    }


    @Test
    public void testSendTransaction() throws Exception {
       BigInteger bigInteger = new BigInteger("100000000000");
       String hash = walletService.sendTransaction("0xbf7248fb860312706ac5b31c3f557c7ac889e2db", "0x7f57baf1499ddac999aEA2C42977eD78887Bf37C", bigInteger, "eric5ever" );
       LOG.info("Hash of the transaction: {}", hash);
    }
}