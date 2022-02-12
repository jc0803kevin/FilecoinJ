package io.filecoin.crypto;


import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.AddressFormatException;

public class ActorAddress extends Address {

    public ActorAddress(NetworkParameters params, byte[] payload) {
        super(params, payload);
    }

    @Override
    public Protocol getProtocol() {
        return Protocol.ACTOR;
    }

    public static ActorAddress fromString(String address) {
        if (Protocol.ACTOR != getProtocol(address)) {
            throw new AddressFormatException("Address Protocol Mismatch.");
        }
        String rawPayload = address.substring(2);

        byte[] value = base32Encoding.decode(rawPayload);
        byte[] payload = ArrayUtils.subarray(value, 0, value.length - 4);
        return new ActorAddress(getNetworkParameters(address), ArrayUtils.addAll(new byte[]{0x02}, payload));
    }

    public String string() {
        byte[] f2AddressByte = ArrayUtils.addAll(ArrayUtils.subarray(payload, 1, payload.length), getChecksum(payload));
        String f2address = base32Encoding.encode(f2AddressByte);
        return params.getPrefix() + getProtocol().getCode() + f2address;
    }

    @Override
    public String toString() {
        return string();
    }
}
