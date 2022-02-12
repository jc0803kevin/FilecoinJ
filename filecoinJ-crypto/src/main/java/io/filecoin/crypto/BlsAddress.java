package io.filecoin.crypto;


import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.AddressFormatException;

public class BlsAddress extends Address {

    public BlsAddress(NetworkParameters params, byte[] payload) {
        super(params, payload);
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.BLS;
    }

    public static BlsAddress fromString(String address) {
        if (Protocol.BLS != getProtocol(address)) {
            throw new AddressFormatException("Address Protocol Mismatch.");
        }
        String rawPayload = address.substring(2);
        byte[] value = base32Encoding.decode(rawPayload);
        byte[] payload = ArrayUtils.subarray(value, 0, value.length - 4);
        return new BlsAddress(getNetworkParameters(address), ArrayUtils.addAll(new byte[]{0x03}, payload));
    }

    public String string() {
        byte[] f3AddressByte = ArrayUtils.addAll(ArrayUtils.subarray(payload, 1, payload.length), getChecksum(payload));
        String f3address = base32Encoding.encode(f3AddressByte);
        return params.getPrefix() + getProtocol().getCode() + f3address;
    }

    @Override
    public String toString() {
        return string();
    }
}
