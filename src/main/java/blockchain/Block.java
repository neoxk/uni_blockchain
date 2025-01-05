package blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Block {
    private int index;
    private String data;
    private long timestamp;
    private int diff;
    private byte[] prev_hash;
    private String miner;



}
