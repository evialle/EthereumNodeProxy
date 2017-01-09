package org.vialle.tradingcc.ethereum.api.jsonrpc.config;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;
import org.vialle.tradingcc.ethereum.modules.account.functional.EthereumNodeService;
import org.vialle.tradingcc.ethereum.modules.account.functional.WalletNodeService;
import org.vialle.tradingcc.ethereum.modules.account.technical.beans.BlockRpcResult;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Eric on 18/10/2016.
 */
@Service
@AutoJsonRpcServiceImpl
public class EthJsonRpcServiceImpl implements EthJsonRpcService {

    @Inject
    private EthereumNodeService ethereumNodeService;

    @Inject
    private WalletNodeService walletNodeService;


    @Override
    public String personal_signAndSendTransaction(@JsonRpcParam(value = "tx") Map<String, Object> tx, @JsonRpcParam(value = "passphrase") String passphrase) throws Exception {
       String addressOrigin = (String) tx.get("from");
       String addressDestination = (String) tx.get("to");
        String value = (String) tx.get("value");

       return walletNodeService.sendTransaction(addressOrigin, addressDestination, new BigInteger(value), passphrase);
    }

    @Override
    public String eth_protocolVersion() {
        return ethereumNodeService.retrieveProtocolVersion();
    }

    @Override
    public BlockRpcResult eth_syncing() {
        return null;
    }

    @Override
    public long eth_gasPrice() {
        return ethereumNodeService.retrieveGasPrice();
    }

    @Override
    public long eth_blockNumber() {
        return ethereumNodeService.retrieveBlockNumber();
    }

    @Override
    public long eth_getBalance(String address, String param) {
        return ethereumNodeService.retrieveBalance(address, param);
    }

    @Override
    public String eth_getStorageAt(String address, long position, String param) {
        return eth_getStorageAt(address, position, param);
    }

    @Override
    public String eth_getTransactionCount(String address, String param) {
        return null;
    }

    @Override
    public String eth_getBlockTransactionCountByHash(String hashBlock) {
        return null;
    }

    @Override
    public String eth_getBlockTransactionCountByNumber(String numberOfTransactionInTheBlock) {
        return null;
    }

    @Override
    public String eth_getUncleCountByBlockHash(String uncleCountByBlockHash) {
        return null;
    }

    @Override
    public String eth_getCode(String address, String param) {
        return null;
    }

    @Override
    public String eth_sign(String address, String sha3) {
        return null;
    }

    @Override
    public String eth_call(Map<String, Object> transactionCallObject, String param) {
        return null;
    }

    @Override
    public String eth_estimateGas(Map<String, Object> tx, String param) {
        return null;
    }

    @Override
    public String eth_getBlockByHash(String hash, boolean isFullTransactionObject) {
        return null;
    }
}
