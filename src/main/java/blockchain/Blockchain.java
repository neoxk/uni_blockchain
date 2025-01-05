package blockchain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

public class Blockchain {
    @Getter
    private ArrayList<MinedBlock> blocks;
    private int DIFF_CORRECTION_INTERVAL = 100;
    private int BLOCK_GEN_TIME = 10;
    private int STARTING_DIFF = 2;

    @Getter
    private int curr_diff = STARTING_DIFF;

    public Blockchain(ArrayList<MinedBlock> blocks) {
        this.blocks = blocks;
    }

    public Blockchain() {
        this.blocks = new ArrayList<>();
    }


    public boolean add(MinedBlock block) {
        byte[] calculated_hash = Miner.calcHash(block, block.getNonce());
        if (Arrays.equals(block.getHash(), calculated_hash) && Miner.hashIsValid(calculated_hash, block.getDiff()) && block.getDiff() == curr_diff) {
            if (blocks.isEmpty()) {
                blocks.add(block);
                adjustDiff();
                return true;
            } else if (block.getPrev_hash() == (blocks.getLast().getHash())) {
                blocks.add(block);
                adjustDiff();
                return true;
            }
        }
        return false;
    }

    private void adjustDiff() {
        if (blocks.size() < DIFF_CORRECTION_INTERVAL) return;

        long prevTimestamp = blocks.get(blocks.size() - DIFF_CORRECTION_INTERVAL).getTimestamp();
        long currTimestamp = blocks.getLast().getTimestamp();
        long timeDiff = currTimestamp - prevTimestamp;

        long expectedTime = BLOCK_GEN_TIME * DIFF_CORRECTION_INTERVAL;
        if (timeDiff / 2 < expectedTime) curr_diff++;
        if (timeDiff / 2 > expectedTime) curr_diff--;
    }

    public void newBlocks(ArrayList<MinedBlock> newBlocks) {
        int newCommDiff = 0;
        for (int i = 0; i < newBlocks.size(); i++) {
            newCommDiff += 2^newBlocks.get(i).getDiff();
        }

        int myCommDiff = 0;
        for (int i = 0; i < blocks.size(); i++) {
            myCommDiff += 2^blocks.get(i).getDiff();
        }

        if (newCommDiff > myCommDiff) blocks = newBlocks;
    }

    public Block getGenesisBlock() {
        return new Block(0, "Genesis Block", System.currentTimeMillis() / 1000L, STARTING_DIFF, new byte[32], "null");
    }

}



