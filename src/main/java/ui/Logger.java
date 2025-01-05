package ui;

import blockchain.Block;
import blockchain.Blockchain;
import blockchain.MinedBlock;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Logger {
    public static Window window;

    public static void logLedger(String msg, Color color) {
        Logger.window.addLedgerMsg(msg, color);
    }

    public static void log(String msg, Color color) {
        Logger.window.addLogMsg(msg, color);
    }

    public static void writeBlock(MinedBlock blk) {
        Logger.logLedger("------------------------------------", Color.GREEN);
        Logger.logLedger("Index: " + blk.getIndex() + " ||| " + "Timestamp: " + blk.getTimestamp(), Color.GREEN);
        Logger.logLedger("Data: " + blk.getData(), Color.GREEN);
        Logger.logLedger("Previous hash: " + Utils.convertByteToHexadecimal(blk.getPrev_hash()), Color.GREEN);
        Logger.logLedger("Difficulty: " + blk.getDiff() + " ||| " + "Nonce: " + blk.getNonce(), Color.GREEN);
        Logger.logLedger("Miner: " + blk.getMiner(), Color.GREEN);
        Logger.logLedger("Hash: " + Utils.convertByteToHexadecimal(blk.getHash()), Color.GREEN);
        Logger.logLedger("----------------------------------", Color.GREEN);
    }

    public static void writeBlockchain(Blockchain blk) {
        Logger.window.clearLedger();
        blk.getBlocks().forEach(block -> Logger.writeBlock(block));
    }


}
