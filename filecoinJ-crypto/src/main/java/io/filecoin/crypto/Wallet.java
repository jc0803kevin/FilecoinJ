package io.filecoin.crypto;

import com.google.common.collect.ImmutableList;
import io.filecoin.crypto.blake2b.Blake2b;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.web3j.crypto.MnemonicUtils;
import org.web3j.utils.Numeric;

import static io.filecoin.crypto.NetworkParameters.MAIN_NET;

public class Wallet {

    public static final ChildNumber FIL_HARDENED = new ChildNumber(461, true);

    public static String getBip44Credentials(String mnemonicWords) {
        return getBip44Credentials(mnemonicWords, "", 0);
    }

    public static String getBip44Credentials(String mnemonicWords, String passPhrase, int index) {
        byte[] seed = MnemonicUtils.generateSeed(mnemonicWords, passPhrase);
        DeterministicKey rootPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(rootPrivateKey);
        ImmutableList path = ImmutableList.of(new ChildNumber(44, true), FIL_HARDENED, ChildNumber.ZERO_HARDENED);
        DeterministicKey fourpath = deterministicHierarchy.get(path, true, true);
        DeterministicKey fourpathhd = HDKeyDerivation.deriveChildKey(fourpath, 0);
        DeterministicKey fivepathhd = HDKeyDerivation.deriveChildKey(fourpathhd, index);
        Blake2b.Digest blake2b1 = Blake2b.Digest.newInstance(20);
        ECKey ecKey = ECKey.fromPrivate(fivepathhd.getPrivKey(), false);
        String pulStr = ecKey.getPublicKeyAsHex();

        byte[] bytes = Numeric.hexStringToByteArray(pulStr);
        byte[] black2HashByte = blake2b1.digest(bytes);
        String black2HashStr = Numeric.toHexStringNoPrefix(black2HashByte);
        String black2HashSecond = "0x01" + black2HashStr;
        Blake2b.Digest blake2b2 = Blake2b.Digest.newInstance(4);

        byte[] checksumBytes = blake2b2.digest(Numeric.hexStringToByteArray(black2HashSecond));
        byte[] addressBytes = new byte[black2HashByte.length + checksumBytes.length];
        System.arraycopy(black2HashByte, 0, addressBytes, 0, black2HashByte.length);
        System.arraycopy(checksumBytes, 0, addressBytes, black2HashByte.length, checksumBytes.length);

        return new Secp256k1Address(MAIN_NET, addressBytes).string();
    }

    public static String fromPub(String pubKeyAsHex, NetworkParameters networkParameters) {
        Blake2b.Digest blake2b1 = Blake2b.Digest.newInstance(20);
        byte[] bytes = Numeric.hexStringToByteArray(pubKeyAsHex);
        byte[] black2HashByte = blake2b1.digest(bytes);
        String black2HashStr = Numeric.toHexStringNoPrefix(black2HashByte);
        String black2HashSecond = "0x01" + black2HashStr;
        Blake2b.Digest blake2b2 = Blake2b.Digest.newInstance(4);

        byte[] checksumBytes = blake2b2.digest(Numeric.hexStringToByteArray(black2HashSecond));
        byte[] addressBytes = new byte[black2HashByte.length + checksumBytes.length];
        System.arraycopy(black2HashByte, 0, addressBytes, 0, black2HashByte.length);
        System.arraycopy(checksumBytes, 0, addressBytes, black2HashByte.length, checksumBytes.length);

        return new Secp256k1Address(networkParameters, addressBytes).string();
    }

}
