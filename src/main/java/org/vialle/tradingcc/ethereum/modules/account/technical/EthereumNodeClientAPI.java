package org.vialle.tradingcc.ethereum.modules.account.technical;

import org.omg.CORBA.Object;
import org.vialle.tradingcc.ethereum.modules.account.technical.beans.BlockRpcResult;

import java.util.Map;

/**
 * Created by Eric on 16/10/2016.
 */
public interface EthereumNodeClientAPI {

    public String personal_newAccountFromPhrase(String passPhrase);

    public String personal_signAndSendTransaction(Map<String, Object> tx, String passPhrase);

    public String eth_protocolVersion();

    public BlockRpcResult eth_syncing();

    public String eth_gasPrice();

    public String eth_blockNumber();

    public String eth_getBalance(String address, String param);

    public String eth_getStorageAt(String address, long position, String param);

    public String eth_getTransactionCount(String address, String param);

    public String eth_getBlockTransactionCountByHash(String hashBlock);

    public String eth_getBlockTransactionCountByNumber(String numberOfTransactionInTheBlock);

    public String eth_getUncleCountByBlockHash(String uncleCountByBlockHash);

    public String eth_getCode(String address, String param);

    public String eth_sign(String address, String sha3);

    public String eth_call(Map<String, Object> transactionCallObject, String param);

    public String eth_estimateGas(Map<String, Object> tx, String param);

    public String eth_getBlockByHash(String hash, boolean isFullTransactionObject);

    //eth_sendRawTransaction

    //eth_getBlockByNumber

    //eth_getTransactionByHash

    //eth_getTransactionByBlockHashAndIndex

    //eth_getTransactionByBlockNumberAndIndex

    //public String eth_getTransactionReceipt(String hashOfTheTransaction)

    //eth_getUncleByBlockHashAndIndex

    //eth_getUncleByBlockNumberAndIndex

}
