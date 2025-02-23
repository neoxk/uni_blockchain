import blockchain.Block;
import blockchain.Blockchain;
import blockchain.MinedBlock;
import blockchain.Miner;
import lombok.Setter;
import network.MessageHandler;
import network.Server;
import ui.Logger;
import ui.UIListener;
import ui.Utils;
import ui.Window;

import java.awt.*;

public class App implements UIListener {
    public int PORT = 7600;
    private Server server;
    @Setter
    private Window window;
    private Miner miner;
    private Blockchain blockchain = new Blockchain();
    private boolean isMining = false;
    private String nodeName = "UNDEFINED";

    private class OnMessage implements MessageHandler {
        @Override
        public void handleMessage(Blockchain blk) {
            if (blk.shouldReplace(blockchain)) {
                blockchain = blk;
                Logger.writeBlockchain(blk);
            }
        }
    }

    public App() {

    }

    @Override
    public void onStartServer(String nodeName) {
        this.nodeName = nodeName;
        this.server = new Server(this.PORT);
        this.server.start();
        this.server.setMsgHander(new OnMessage());
        this.window.setServerStarted(this.server.getPort());
    }

    @Override
    public void onConnect(int port) {
        boolean success = this.server.connectTo(port);
        if (success) this.window.setConnectionEstablished(port);
        else this.window.addLogMsg("Connection failed ", Color.RED);
    }

    @Override
    public void onMine() {
        this.miner = new Miner();
        if (this.blockchain.getBlocks().isEmpty()) {
            this.blockchain.add(miner.mine(this.blockchain.getGenesisBlock()));
        }

        isMining = true;
        new Thread(() -> {
            while(isMining) {
                int new_index = this.blockchain.getBlocks().getLast().getIndex() + 1;
                long timestamp = System.currentTimeMillis() / 1000L;
                int curr_diff = this.blockchain.getCurr_diff();
                byte[] prev_hash = this.blockchain.getBlocks().getLast().getHash();

                Block newBlock = new Block(new_index, "Block " + new_index, timestamp, curr_diff, prev_hash, this.nodeName);

                MinedBlock blk = miner.mine(newBlock);
                boolean added = this.blockchain.add(blk);

                if (added) {
                    Logger.writeBlock(blk);
                }
                this.server.broadcast(this.blockchain);
            }
        }).start();
    }
}




