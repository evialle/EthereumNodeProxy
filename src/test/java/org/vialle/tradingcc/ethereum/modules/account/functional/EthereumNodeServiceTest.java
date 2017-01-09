package org.vialle.tradingcc.ethereum.modules.account.functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vialle.tradingcc.ethereum.Application;
import org.vialle.tradingcc.ethereum.modules.account.technical.beans.BlockRpcResult;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class EthereumNodeServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(EthereumNodeServiceTest.class);

    @Inject
    private EthereumNodeService ethereumNodeService;

    @Test
    public void testRetrieveGasPrice() throws Exception {
        long gasPrice = ethereumNodeService.retrieveGasPrice();
        LOG.info("Gas Price: {} Wei", gasPrice);

    }

    @Test
    public void testRetrieveBlockNumber() throws Exception {
        long blockInfo = ethereumNodeService.retrieveBlockNumber();
        LOG.info("Block: {}", blockInfo);
    }


    @Test
    public void testRetrieveBalance() throws Exception {
        String localAccountWith1EthAddress = "0xbf7248fb860312706ac5b31c3f557c7ac889e2db";
        long localBalance = ethereumNodeService.retrieveBalance(localAccountWith1EthAddress, "latest");
        LOG.info("Account Managed On My PC: {} has {} Wei", localAccountWith1EthAddress, localBalance);
    }


    @Test
    public void testBlocksSyncInfo() throws Exception {
        BlockRpcResult blockInfo = ethereumNodeService.blocksSyncInfo();
        LOG.info("Block Current : {} / Highest {} / Known States {} / Pulled States {} / Starting Block {}",
                blockInfo.getCurrentBlock(),
                blockInfo.getHighestBlock(),
                blockInfo.getKnownStates(),
                blockInfo.getPulledStates(),
                blockInfo.getStartingBlock());
    }

    @Test
    public void testRetrieveProtocolVersion() throws Exception {
        String version = ethereumNodeService.retrieveProtocolVersion();
        LOG.info("Eth version: {}", version);
    }
}