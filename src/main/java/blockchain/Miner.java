package blockchain;

import lombok.Setter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Miner {
    @Setter
    private Blockchain blockchain;

    public MinedBlock mine(Block block) {
        byte[] curr_hash = new byte[]{1, 2, 3};
        int nonce = -1;

        while (!hashIsValid(curr_hash, block.getDiff())) {
            nonce++;
            curr_hash = calcHash(block, nonce);
            System.out.println(Arrays.toString(curr_hash));
        }

        return new MinedBlock(block, nonce, curr_hash);
    }

    public static boolean hashIsValid(byte[] hash, int diff) {
        for (int i = 0; i < diff; i++) {
            if (hash[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static byte[] calcHash(Block block, int nonce) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = block.getIndex() + block.getData() + block.getTimestamp() +
                           block.getDiff() + new String(block.getPrev_hash()) +
                           block.getMiner() + nonce;
            return digest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e);
        }
    }
    


}
