package org.vialle.tradingcc.ethereum.modules.account.functional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vialle.tradingcc.ethereum.modules.account.technical.beans.BlockRpcResult;
import org.vialle.tradingcc.ethereum.modules.account.technical.EthereumNodeClientAPI;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Eric on 16/10/2016.
 */
@Named
public class EthereumNodeService {

    private static final Logger LOG = LoggerFactory.getLogger(WalletNodeService.class);

    @Inject
    private EthereumNodeClientAPI ethereumNodeClientAPI;

    @Inject
    private WalletCacheService walletCacheService;

    public long retrieveGasPrice() {
        return Long.decode(ethereumNodeClientAPI.eth_gasPrice());
    }

    public long retrieveBlockNumber() {
        String blockRpcResult = ethereumNodeClientAPI.eth_blockNumber();
        return Long.decode(blockRpcResult);
    }

    public BlockRpcResult blocksSyncInfo() {
        return ethereumNodeClientAPI.eth_syncing();
    }

    public String retrieveProtocolVersion() {
        String version = ethereumNodeClientAPI.eth_protocolVersion();
        LOG.debug("Protocol version: {}", version);
        return version;
    }

    /**
     *
     * @param address
     */
    public long retrieveBalance(String address, String param) {
        String amountInWei = ethereumNodeClientAPI.eth_getBalance(address, param);
        LOG.debug("Balance for address {} / param {}: {} Wei", address, param, amountInWei);
        return Long.decode(amountInWei);
    }

}