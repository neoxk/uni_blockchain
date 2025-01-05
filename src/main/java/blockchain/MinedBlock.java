package blockchain;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;

public class MinedBlock extends Block{
    @Getter
    private int nonce;
    @Getter
    private byte[] hash;

    public MinedBlock(Block block, int nonce, byte[] hash) {
        super(block.getIndex(), block.getData(), block.getTimestamp(), block.getDiff(), block.getPrev_hash(), block.getMiner());
        this.nonce = nonce;
        this.hash = hash;
    }

    public String toString() {
        return super.toString() + ", nonce: " + nonce + ", hash: " + Arrays.toString(hash);
    }


}
