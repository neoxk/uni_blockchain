package blockchain;

import java.util.Arrays;

public class Run {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();
        Miner miner = new Miner();

        int index = 1;
        while (true) {
            if (blockchain.getBlocks().isEmpty()) {
                Block startingBlock = new Block(0, "Block 1", System.currentTimeMillis() / 1000L, 2, new byte[32], "null");
                MinedBlock block = miner.mine(startingBlock);
                boolean success = blockchain.add(block);
            }

            MinedBlock block = miner.mine(new Block(index, "Block " + index, System.currentTimeMillis() / 1000L, 2, blockchain.getBlocks().getLast().getHash(), "A"));
            blockchain.add(block);
            index++;

            System.out.println(Arrays.toString(blockchain.getBlocks().getLast().getHash()));
        }


    }
}
