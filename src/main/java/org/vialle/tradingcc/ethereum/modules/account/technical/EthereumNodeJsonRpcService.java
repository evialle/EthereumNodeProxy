package org.vialle.tradingcc.ethereum.modules.account.technical;

import com.google.common.collect.Maps;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vialle.tradingcc.ethereum.modules.account.functional.config.EthereumNodeInfo;
import org.vialle.tradingcc.ethereum.modules.account.technical.EthereumNodeClientAPI;

import javax.inject.Inject;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 16/10/2016.
 */
@Configuration
public class EthereumNodeJsonRpcService {

        @Autowired
        private EthereumNodeInfo ethereumNodeInfo;

        @Bean
        public JsonRpcHttpClient jsonRpcHttpClient() {
            URL url = null;
            try {
                url = new URL(ethereumNodeInfo.getNodeUrl());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            HashMap<String, String> headers = Maps.newHashMapWithExpectedSize(1);
            headers.put("Content-Type", "application/json");
            JsonRpcHttpClient client = new JsonRpcHttpClient(url, headers);
            return client;
        }

        @Bean
        public EthereumNodeClientAPI ethereumNodeClientAPI(JsonRpcHttpClient jsonRpcHttpClient) {
            return ProxyUtil.createClientProxy(getClass().getClassLoader(), EthereumNodeClientAPI.class, jsonRpcHttpClient);
        }

}
