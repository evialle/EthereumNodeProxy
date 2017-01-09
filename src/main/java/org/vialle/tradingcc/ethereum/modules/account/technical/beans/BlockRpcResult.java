package org.vialle.tradingcc.ethereum.modules.account.technical.beans;

import java.io.Serializable;

/**
 * Created by Eric on 16/10/2016.
 */
public class BlockRpcResult implements Serializable {

    private long currentBlock;

    private long highestBlock;

    private long knownStates;

    private long pulledStates;

    private long startingBlock;

    public long getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(String currentBlock) {
        this.currentBlock = Long.decode(currentBlock);
    }

    public long getHighestBlock() {
        return highestBlock;
    }

    public void setHighestBlock(String highestBlock) {
        this.highestBlock = Long.decode(highestBlock);

    }

    public long getKnownStates() {
        return knownStates;
    }

    public void setKnownStates(String knownStates) {
        this.knownStates = Long.decode(knownStates);
    }

    public long getPulledStates() {
        return pulledStates;
    }

    public void setPulledStates(String pulledStates) {
        this.pulledStates = Long.decode(pulledStates);
    }

    public long getStartingBlock() {
        return startingBlock;
    }

    public void setStartingBlock(String startingBlock) {
        this.startingBlock = Long.decode(startingBlock);
    }
}
