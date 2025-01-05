package network;

import blockchain.Blockchain;

public interface MessageHandler {
    void handleMessage(Blockchain blk);

}
