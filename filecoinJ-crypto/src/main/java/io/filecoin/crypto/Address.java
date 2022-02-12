package io.filecoin.crypto;

import com.google.common.io.BaseEncoding;
import io.filecoin.crypto.blake2b.Blake2b;
import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.AddressFormatException;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;

public abstract class Address {

    protected final byte[] payload;
    protected final NetworkParameters params;
    public static BaseEncoding base32Encoding = BaseEncoding.base32().lowerCase().omitPadding();

    public abstract Protocol getProtocol();

    public Address(NetworkParameters params, byte[] payload) {
        this.params = params;
        this.payload = payload;
    }

    protected static NetworkParameters getNetworkParameters(String address) {
        NetworkParameters networkParameters = NetworkParameters.getByPrefix(address.substring(0, 1));
        if (networkParameters == null) {
            throw new AddressFormatException("networkParameters is null.");
        }
        return networkParameters;
    }

    protected static Protocol getProtocol(String address) {
        Protocol protocol = Protocol.getByCode(address.substring(1, 2));
        if (protocol == null) {
            throw new AddressFormatException("protocol illegal.");
        }
        return protocol;
    }

    /**
     * @param address f23643quwckqjrykwmptcvqiap6ph77y3a4yv4wqi
     * @return
     */
    public static Address fromString(String address) {
        Protocol protocol = getProtocol(address);
        switch (protocol) {
            case ID:
                return IDAddress.fromString(address);
            case SECP256K1:
                return Secp256k1Address.fromString(address);
            case ACTOR:
                return ActorAddress.fromString(address);
            case BLS:
                return BlsAddress.fromString(address);
            default:
                throw new AddressFormatException("Invalid address format. address:" + address);
        }
    }

    public static Address fromByte(NetworkParameters params, byte[] payload) {
        byte[] protocolByte = ArrayUtils.subarray(payload, 0, 1);
        Protocol protocol = Protocol.getByCode(new BigInteger(protocolByte).toString());
        switch (protocol) {
            case ID:
                return new IDAddress(params, payload);
            case SECP256K1:
                return new Secp256k1Address(params, payload);
            case ACTOR:
                return new ActorAddress(params, payload);
            case BLS:
                return new BlsAddress(params, payload);
            default:
                throw new AddressFormatException("Invalid address format. payload:" + Hex.toHexString(payload));
        }
    }


    public byte[] getPayload() {
        return payload;
    }

    public NetworkParameters getParams() {
        return params;
    }

    public static BigInteger fromUVariant(final byte[] bytes) {
        BigInteger x = BigInteger.ZERO;
        int s = 0;

        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i] & 0xff; // convert to unsigned value
            if (b < 0x80) {
                if (i > 9 || (i == 9 && b > 1)) {
                    return BigInteger.ZERO;
                }
                return x.or(BigInteger.valueOf(b).shiftLeft(s));
            }
            x = x.or(BigInteger.valueOf(b & 0x7f).shiftLeft(s));
            s += 7;
        }
        return BigInteger.ZERO;
    }

    public static byte[] getChecksum(byte[] payload) {
        Blake2b.Digest blake2b2 = Blake2b.Digest.newInstance(4);

        byte[] checksumBytes = blake2b2.digest(payload);
        return checksumBytes;
    }

}
