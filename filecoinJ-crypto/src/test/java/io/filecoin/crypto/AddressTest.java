package io.filecoin.crypto;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class AddressTest {

    @Test
    public void fromStringID() {
        String addressStr = "f01415803";
        Address address = Address.fromString(addressStr);
        IDAddress idAddress = new IDAddress(address.params, address.payload);

        Assert.assertEquals(addressStr, idAddress.string());
    }

    @Test
    public void fromStringActor() {
        String addressStr = "f23643quwckqjrykwmptcvqiap6ph77y3a4yv4wqi";
        Address address = Address.fromString(addressStr);
        ActorAddress actorAddress = new ActorAddress(address.params, address.payload);

        Assert.assertEquals(addressStr, actorAddress.string());
    }

    @Test
    public void fromStringSecp256k1() {
        String addressStr = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        Address address = Address.fromString(addressStr);
        Secp256k1Address f1Address = new Secp256k1Address(address.params, address.payload);

        Assert.assertEquals(addressStr, f1Address.string());
    }

    @Test
    public void fromByteSecp256k1() {
        String addressStr = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";
        Address address = Address.fromString(addressStr);

//        String bytes = Hex.de

        Secp256k1Address f1Address = new Secp256k1Address(address.params, address.payload);

        Assert.assertEquals(addressStr, f1Address.string());
    }

    @Test
    public void fromByteBlsAddress() throws DecoderException {
        // length 48
        String pubKeyHex = "ad58df696e2d4e91ea86c881e938ba4ea81b395e12797b84b9cf314b9546705e839c7a99d606b247ddb4f9ac7a3414dd";

        byte[] payload = ArrayUtils.addAll(new byte[]{0x03}, Hex.decodeHex(pubKeyHex));

        BlsAddress actorAddress = new BlsAddress(NetworkParameters.MAIN_NET, payload);

        Assert.assertEquals("f3vvmn62lofvhjd2ugzca6sof2j2ubwok6cj4xxbfzz4yuxfkgobpihhd2thlanmsh3w2ptld2gqkn2jvlss4a", actorAddress.string());
    }

}
