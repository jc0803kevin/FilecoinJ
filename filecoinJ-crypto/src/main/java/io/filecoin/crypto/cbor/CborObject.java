package io.filecoin.crypto.cbor;

import io.ipfs.cid.Cid;
import io.ipfs.multihash.Multihash;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static io.filecoin.crypto.cbor.CborConstants.TYPE_TEXT_STRING;

public interface CborObject extends Cborable {

    void serialize(CborEncoder encoder);

    List<Multihash> links();

    default byte[] toByteArray() {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        CborEncoder encoder = new CborEncoder(bout);
        serialize(encoder);
        return bout.toByteArray();
    }

    @Override
    default CborObject toCbor() {
        return this;
    }

    int LINK_TAG = 42;

    static CborObject fromByteArray(byte[] cbor) {
        return deserialize(new CborDecoder(new ByteArrayInputStream(cbor)), cbor.length);
    }

    static CborObject deserialize(CborDecoder decoder, int maxGroupSize) {
        try {
            CborType type = decoder.peekType();
            switch (type.getMajorType()) {
                case TYPE_TEXT_STRING:
                    return new CborString(decoder.readTextString(maxGroupSize));
                case CborConstants.TYPE_BYTE_STRING:
                    return new CborByteArray(decoder.readByteString(maxGroupSize));
                case CborConstants.TYPE_UNSIGNED_INTEGER:
                    return new CborLong(decoder.readInt());
                case CborConstants.TYPE_NEGATIVE_INTEGER:
                    return new CborLong(decoder.readInt());
                case CborConstants.TYPE_FLOAT_SIMPLE:
                    if (type.getAdditionalInfo() == CborConstants.NULL) {
                        decoder.readNull();
                        return new CborNull();
                    }
                    if (type.getAdditionalInfo() == CborConstants.TRUE) {
                        decoder.readBoolean();
                        return new CborBoolean(true);
                    }
                    if (type.getAdditionalInfo() == CborConstants.FALSE) {
                        decoder.readBoolean();
                        return new CborBoolean(false);
                    }
                    throw new IllegalStateException("Unimplemented simple type! " + type.getAdditionalInfo());
                case CborConstants.TYPE_MAP: {
                    long nValues = decoder.readMapLength();
                    if (nValues > maxGroupSize)
                        throw new IllegalStateException("Invalid cbor: more map elements than original bytes!");
                    SortedMap<CborObject, Cborable> result = new TreeMap<>();
                    for (long i = 0; i < nValues; i++) {
                        CborObject key = deserialize(decoder, maxGroupSize);
                        CborObject value = deserialize(decoder, maxGroupSize);
                        result.put(key, value);
                    }
                    return new CborMap(result);
                }
                case CborConstants.TYPE_ARRAY:
                    long nItems = decoder.readArrayLength();
                    if (nItems > maxGroupSize)
                        throw new IllegalStateException("Invalid cbor: more array elements than original bytes!");
                    List<CborObject> res = new ArrayList<>((int) nItems);
                    for (long i = 0; i < nItems; i++)
                        res.add(deserialize(decoder, maxGroupSize));
                    return new CborList(res);
                case CborConstants.TYPE_TAG:
                    long tag = decoder.readTag();
                    if (tag == LINK_TAG) {
                        CborObject value = deserialize(decoder, maxGroupSize);
                        if (value instanceof CborString)
                            return new CborMerkleLink(Cid.decode(((CborString) value).value));
                        if (value instanceof CborByteArray) {
                            byte[] bytes = ((CborByteArray) value).value;
                            if (bytes[0] == 0) // multibase for binary
                                return new CborMerkleLink(Cid.cast(Arrays.copyOfRange(bytes, 1, bytes.length)));
                            throw new IllegalStateException("Unknown Multibase decoding Merkle link: " + bytes[0]);
                        }
                        throw new IllegalStateException("Invalid type for merkle link: " + value);
                    }
                    throw new IllegalStateException("Unknown TAG in CBOR: " + type.getAdditionalInfo());
                default:
                    throw new IllegalStateException("Unimplemented cbor type: " + type);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    final class CborMap implements CborObject {
        public final SortedMap<CborObject, ? extends Cborable> values;

        public CborMap(SortedMap<CborObject, ? extends Cborable> values) {
            this.values = values;
        }

        public static CborMap build(Map<String, ? extends Cborable> values) {
            SortedMap<CborObject, Cborable> transformed = values.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            e -> new CborString(e.getKey()),
                            e -> e.getValue(),
                            (a, b) -> a, TreeMap::new));
            return new CborMap(transformed);
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeMapStart(values.size());
                for (Map.Entry<CborObject, ? extends Cborable> entry : values.entrySet()) {
                    entry.getKey().serialize(encoder);
                    entry.getValue().toCbor().serialize(encoder);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return values.values().stream()
                    .flatMap(cbor -> cbor.toCbor().links().stream())
                    .collect(Collectors.toList());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CborMap cborMap = (CborMap) o;

            return values != null ? values.equals(cborMap.values) : cborMap.values == null;

        }

        @Override
        public int hashCode() {
            return values != null ? values.hashCode() : 0;
        }
    }

    final class CborMerkleLink implements CborObject {
        public final Multihash target;

        public CborMerkleLink(Multihash target) {
            this.target = target;
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeTag(LINK_TAG);
                byte[] cid = target.toBytes();
                byte[] withMultibaseHeader = new byte[cid.length + 1];
                System.arraycopy(cid, 0, withMultibaseHeader, 1, cid.length);
                encoder.writeByteString(withMultibaseHeader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return Collections.singletonList(target);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CborMerkleLink that = (CborMerkleLink) o;

            return target != null ? target.equals(that.target) : that.target == null;

        }

        @Override
        public int hashCode() {
            return target != null ? target.hashCode() : 0;
        }
    }

    final class CborList implements CborObject {
        public final List<? extends Cborable> value;

        public CborList(List<? extends Cborable> value) {
            this.value = value;
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeArrayStart(value.size());
                for (Cborable object : value) {
                    object.toCbor().serialize(encoder);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return value.stream()
                    .flatMap(cbor -> cbor.toCbor().links().stream())
                    .collect(Collectors.toList());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CborList cborList = (CborList) o;

            return value != null ? value.equals(cborList.value) : cborList.value == null;
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

    final class CborBoolean implements CborObject {
        public final boolean value;

        public CborBoolean(boolean value) {
            this.value = value;
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeBoolean(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return Collections.emptyList();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CborBoolean that = (CborBoolean) o;

            return value == that.value;

        }

        @Override
        public int hashCode() {
            return (value ? 1 : 0);
        }

        @Override
        public String toString() {
            return "CborBoolean{" +
                    value +
                    '}';
        }
    }

    final class CborByteArray implements CborObject, Comparable<CborByteArray> {
        public final byte[] value;

        public CborByteArray(byte[] value) {
            this.value = value;
        }

        @Override
        public int compareTo(CborByteArray other) {
            return compare(value, other.value);
        }

        /**
         * This only matter so that we can have byte[]'s as keys in a sorted map deterministically
         *
         * @param a
         * @param b
         * @return
         */
        public static int compare(byte[] a, byte[] b) {
            if (a.length != b.length)
                return a.length - b.length;
            for (int i = 0; i < a.length; i++)
                if (a[i] != b[i])
                    return a[i] & 0xff - b[i] & 0xff;
            return 0;
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeByteString(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return Collections.emptyList();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CborByteArray that = (CborByteArray) o;

            return Arrays.equals(value, that.value);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(value);
        }
    }

    final class CborString implements CborObject, Comparable<CborString> {

        public final String value;

        public CborString(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(CborString cborString) {
            int lenDiff = value.length() - cborString.value.length();
            if (lenDiff != 0)
                return lenDiff;
            return value.compareTo(cborString.value);
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeTextString(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return Collections.emptyList();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CborString that = (CborString) o;

            return value.equals(that.value);

        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return "CborString{\"" +
                    value +
                    "\"}";
        }
    }

    final class CborLong implements CborObject, Comparable<CborLong> {
        public final long value;

        public CborLong(long value) {
            this.value = value;
        }

        @Override
        public int compareTo(CborLong other) {
            return Long.compare(value, other.value);
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeInt(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return Collections.emptyList();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CborLong cborLong = (CborLong) o;

            return value == cborLong.value;

        }

        @Override
        public int hashCode() {
            return (int) (value ^ (value >>> 32));
        }

        @Override
        public String toString() {
            return "CborLong{" +
                    value +
                    '}';
        }
    }

    final class CborNull implements CborObject, Comparable<CborNull> {
        public CborNull() {
        }

        @Override
        public int compareTo(CborNull cborNull) {
            return 0;
        }

        @Override
        public void serialize(CborEncoder encoder) {
            try {
                encoder.writeNull();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Multihash> links() {
            return Collections.emptyList();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public String toString() {
            return "CborNull{}";
        }
    }
}
