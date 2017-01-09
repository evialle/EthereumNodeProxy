package org.vialle.tradingcc.ethereum.modules.account.functional.exceptions;

import java.io.IOException;

/**
 * Created by Eric on 16/10/2016.
 */
public class WalletException extends Exception {

    public WalletException(Throwable e) {
        super(e);
    }

    public WalletException(String s) {
        super(s);
    }

    public WalletException(String s, Throwable t) {
        super(s,t);
    }
}
