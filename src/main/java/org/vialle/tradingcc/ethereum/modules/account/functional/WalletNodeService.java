package org.vialle.tradingcc.ethereum.modules.account.functional;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vialle.tradingcc.ethereum.modules.account.functional.config.EthereumNodeInfo;
import org.vialle.tradingcc.ethereum.modules.account.functional.exceptions.WalletException;
import org.vialle.tradingcc.ethereum.modules.account.technical.EthereumNodeClientAPI;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Eric on 16/10/2016.
 */
@Named
public class WalletNodeService {

    private static final Logger LOG = LoggerFactory.getLogger(WalletNodeService.class);

    @Inject
    private EthereumNodeClientAPI ethereumNodeClientAPI;

    @Inject
    private EthereumNodeInfo ethereumNodeInfo;

    @Inject
    private WalletCacheService walletCacheService;

    private File keyStoreDirectory;

    @PostConstruct
    private void init() {
        keyStoreDirectory = new File(ethereumNodeInfo.getKeystoreDirectory());
    }

    /**
     *
     * @param passphrase
     * @return the address
     */
    public String createWallet(final String passphrase) throws WalletException {
            String address = ethereumNodeClientAPI.personal_newAccountFromPhrase(passphrase);
            storeWallet(address);
            return address;
    }

    private void storeWallet(final String address) throws WalletException {
        File file= findFileForAddress(address);
        try {
            String content = Files.toString(file, Charsets.UTF_8);
            LOG.debug("File Content for address {}: {}", address, content);
            //TODO store le fichier dans dynamodb
            walletCacheService.addWalletInCache(address, file);
        } catch (IOException e) {
           throw new WalletException("Can't read content of the address " + address + " for the file " + file.getAbsolutePath(), e);
        }

    }

    private File findFileForAddress(String address) throws WalletException {
        File[] listFiles = keyStoreDirectory.listFiles((dir, name) -> {
            return name.endsWith(address.substring(2));
        });
        if (listFiles.length != 1) {
            throw new WalletException("Inadequate number of files (" + listFiles.length+ ") for the address " + address);
        } else {
            LOG.debug("Address {} is located at {}", address, listFiles[0]);
            return listFiles[0];
        }
    }

    public void importWalletInNode() {

        //ajout dans le node

        //ajout dans le cache
    }

    private void addWalletInNode() {

    }



    public String sendTransaction(String from, String to, BigInteger valueInWei, String passPhrase) throws Exception {
        makeAvailableWallet(from);

        Map map = Maps.newHashMap();
        map.put("from", from);
        map.put("to", to);
        map.put("value", valueInWei.toString());
        return ethereumNodeClientAPI.personal_signAndSendTransaction(map, passPhrase);
    }

    private void makeAvailableWallet(String from) throws Exception {
        if (walletCacheService.isWalletInCache(from) == false) {
            //on recupere le wallet de DynamoDb
        }
    }


}
