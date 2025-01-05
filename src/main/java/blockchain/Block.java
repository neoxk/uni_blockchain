package blockchain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Block implements Serializable {
    private int index;
    private String data;
    private long timestamp;
    private int diff;
    private byte[] prev_hash;
    private String miner;



}
