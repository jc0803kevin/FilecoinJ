package io.filecoin.crypto;

import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.AddressFormatException;

public class Secp256k1Address extends Address {

    public Secp256k1Address(NetworkParameters params, byte[] payload) {
        super(params, payload);
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.SECP256K1;
    }

    public static Secp256k1Address fromString(String address) {
        if (Protocol.SECP256K1 != getProtocol(address)) {
            throw new AddressFormatException("Address Protocol Mismatch.");
        }
        String rawPayload = address.substring(2);
        byte[] value = base32Encoding.decode(rawPayload);
        byte[] payload = ArrayUtils.subarray(value, 0, value.length - 4);
        return new Secp256k1Address(getNetworkParameters(address), ArrayUtils.addAll(new byte[]{0x01}, payload));
    }

    public String string() {
        byte[] f1AddressByte = ArrayUtils.addAll(ArrayUtils.subarray(payload, 1, payload.length), getChecksum(payload));
        String f1address = base32Encoding.encode(f1AddressByte);
        return params.getPrefix() + getProtocol().getCode() + f1address;
    }

    @Override
    public String toString() {
        return string();
    }
}
