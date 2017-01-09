package org.vialle.tradingcc.ethereum.modules.account.functional;

import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Named;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 16/10/2016.
 */
@Named
public class WalletCacheService {

    private static final int INITIAL_CAPACITY = 8192;

    private static final Map<String, WalletCacheBean> CACHE_WALLET = Collections.synchronizedMap(new HashMap<>(INITIAL_CAPACITY));
    public static final int MAX_CACHE_DURATION = 20 * 60 * 1000;    //en millisecondes

    public void addWalletInCache(String publicAddress, File file) {
        CACHE_WALLET.put(publicAddress, new WalletCacheBean(file));
    }

    public void removeWalletFromCache(String publicAddress) {
        CACHE_WALLET.remove(publicAddress);
    }

    @Scheduled(fixedRate = MAX_CACHE_DURATION)
    private void cleanWalletCache() {
        CACHE_WALLET.forEach((address, walletCacheBean) -> {
            if (walletCacheBean.getTimestamp() + MAX_CACHE_DURATION < System.currentTimeMillis()) {
                removeWalletFromCache(address);
            }
        });
    }

    public boolean isWalletInCache(String from) {
        return CACHE_WALLET.containsKey(from);
    }


    /**
     * Object representing a wallet in cache
     */
    private static class WalletCacheBean {

        private long timestamp;

        private File fileLocation;

        public WalletCacheBean(File fileLocation) {
            this.fileLocation = fileLocation;
            updateTimeStamp();
        }

        public void updateTimeStamp() {
            this.timestamp = System.currentTimeMillis();
        }



        public long getTimestamp() {
            return timestamp;
        }


        public File getFileLocation() {
            return fileLocation;
        }

    }
}
