package io.filecoin.crypto;

public interface UnmarshalCBOR<T> {

    T unmarshalCBOR(byte[] payload);
}
