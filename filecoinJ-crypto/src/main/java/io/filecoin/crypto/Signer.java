package io.filecoin.crypto;

import io.filecoin.crypto.blake2b.Blake2b;
import io.filecoin.crypto.types.Message;
import io.filecoin.crypto.types.Signature;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

import static org.web3j.crypto.Sign.signMessage;

public class Signer {

    public static byte[] CID_PREFIX = new byte[]{0x01, 0x71, (byte) 0xa0, (byte) 0xe4, 0x02, 0x20};

    public static Signature sign(Message message, String priKeyAsHex) {
        byte[] encodedBytes = message.marshalCBOR();
        byte[] cidHashBytes = Signer.getCidHash(encodedBytes);
        String data = sign(cidHashBytes, priKeyAsHex);//签名

        return new Signature(SignType.SigTypeSecp256k1.getCode(), data);
    }

    public static byte[] createHash(byte[] message) {
        byte[] messageByte = Blake2b.Digest.newInstance(32).digest(message);
        return messageByte;
    }

    public static byte[] getCidHash(byte[] message) {
        byte[] messageByte = Blake2b.Digest.newInstance(32).digest(message);
        int xlen = CID_PREFIX.length;
        int ylen = messageByte.length;
        byte[] result = new byte[xlen + ylen];

        System.arraycopy(CID_PREFIX, 0, result, 0, xlen);
        System.arraycopy(messageByte, 0, result, xlen, ylen);

        byte[] prefixByte = Blake2b.Digest.newInstance(32).digest(result);
        return prefixByte;
    }

    private static String sign(byte[] cidHash, String priKeyAsHex) {
        ECKeyPair ecKeyPair = ECKeyPair.create(Hex.decode(priKeyAsHex));

        Sign.SignatureData signatureData = signMessage(cidHash, ecKeyPair, false);
        byte[] sig = getSignature(signatureData);
        String base64 = Base64.encodeBase64String(sig);
        return base64;
    }

    private static byte[] getSignature(Sign.SignatureData signatureData) {
        byte[] sig = new byte[65];
        System.arraycopy(signatureData.getR(), 0, sig, 0, 32);
        System.arraycopy(signatureData.getS(), 0, sig, 32, 32);
        sig[64] = (byte) ((signatureData.getV() & 0xFF) - 27);
        return sig;
    }

    public static byte[] unsigned(BigInteger value) {
        if (value == null) {
            return null;
        }
        if (value.compareTo(BigInteger.ZERO) == 0) {
            return new byte[]{};            //值为0返回空数组
        }

        byte[] array = value.toByteArray();

        if (array[0] != 0) {
            byte[] byte2 = new byte[array.length + 1];
            byte2[0] = 0;
            System.arraycopy(array, 0, byte2, 1, array.length);
            return byte2;
        } else {
            return array;
        }
    }
}

