package io.filecoin.crypto;


import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.AddressFormatException;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;

public class IDAddress extends Address {

    @Override
    public Protocol getProtocol() {
        return Protocol.ID;
    }

    public IDAddress(NetworkParameters params, byte[] payload) {
        super(params, payload);
    }

    public static IDAddress fromString(String address) {
        if (Protocol.ID != getProtocol(address)) {
            throw new AddressFormatException("Address Protocol Mismatch.");
        }

        BigInteger highOrderBitValue = new BigInteger(1, Hex.decode("80"));
        byte[] output = new byte[]{};
        BigInteger x = new BigInteger(address.substring(2));
        while (x.compareTo(highOrderBitValue) >= 0) {
            output = ArrayUtils.addAll(output, (byte) (x.byteValue() | 0x80));
            x = x.shiftRight(7);
        }
        output = ArrayUtils.addAll(output, x.byteValue());
        return new IDAddress(getNetworkParameters(address), ArrayUtils.addAll(new byte[]{0x00}, output));
    }

    public String string() {
        byte[] idAddressByte = ArrayUtils.subarray(payload, 1, payload.length);

        return params.getPrefix() + getProtocol().getCode() + fromUVariant(idAddressByte).toString();
    }

    @Override
    public String toString() {
        return string();
    }
}
