package io.filecoin.crypto.types;

import io.filecoin.crypto.blake2b.Blake2b;
import io.filecoin.crypto.hash.CidHash;
import io.ipfs.multihash.Multihash;
import org.apache.commons.lang3.ArrayUtils;

import java.io.Serializable;


public class SignedMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Message message;

    private Signature signature;

    private Cid cid;

    public SignedMessage() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public Cid getCid() {
        return cid;
    }

    public void setCid(Cid cid) {
        this.cid = cid;
    }

    /**
     * calculate cid hash
     * 1. Cobor encoding the signature result
     * 2. Blake2b digest the encoded result
     * 3. Multihash calculates the cid hash result
     * @param signedMessage Result after signing
     * @return
     */
    public static Cid toCid(SignedMessage signedMessage){
        Cid cid = new Cid();

        byte[] messageByte = signedMessage.getMessage().marshalCBOR();

        byte[] signatureByte = signedMessage.getSignature().marshalCBOR();

        byte[] encodedBytes = ArrayUtils.addAll(messageByte, signatureByte);

        encodedBytes = ArrayUtils.addAll(new byte[]{-126}, encodedBytes);

        byte[] blake2bByte = Blake2b.Digest.newInstance(32).digest(encodedBytes);
        Multihash h = new Multihash(Multihash.Type.blake2b_256, blake2bByte);
        CidHash cidHash = CidHash.build(1, CidHash.Codec.DagCbor, h);

        cid.setStr(cidHash.toString());
        return cid;
    }

}
