package org.vialle.tradingcc.ethereum.modules.account.functional.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Eric on 16/10/2016.
 */
@Configuration
public class EthereumNodeInfo {

    @Value("${ethnode.keystore.location}")
    private String keystoreDirectory;

    @Value("${ethnode.data.location}")
    private String dataDirectory;

    @Value("${ethnode.json-rpc.endpoint}")
    private String nodeUrl;


    public String getKeystoreDirectory() {
        return keystoreDirectory;
    }

    public String getDataDirectory() {
        return dataDirectory;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }


}
