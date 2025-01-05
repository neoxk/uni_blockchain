package blockchain;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;

public class MinedBlock extends Block implements Serializable {
    @Getter
    private int nonce;
    @Getter
    private byte[] hash;

    public MinedBlock(int index, String data, long timestamp, int diff, byte[] prev_hash, String miner, int nonce, byte[] hash) {
        super(index, data, timestamp, diff, prev_hash, miner);
        this.nonce = nonce;
        this.hash = hash;
    }

    public MinedBlock(Block block, int nonce, byte[] hash) {
        super(block.getIndex(), block.getData(), block.getTimestamp(), block.getDiff(), block.getPrev_hash(), block.getMiner());
        this.nonce = nonce;
        this.hash = hash;
    }

    public String toString() {
        return super.toString() + ", nonce: " + nonce + ", hash: " + Arrays.toString(hash);
    }


}
