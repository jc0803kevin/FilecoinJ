package io.filecoin.tx.multisig.types;

import io.filecoin.crypto.NetworkParameters;
import io.filecoin.protocol.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CreateMultisigMsgParamTest {

    private static List<String> signers = new ArrayList<>();

    static {
        signers.add("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        signers.add("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri");
        signers.add("t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq");
    }

    @Test
    public void marshalCBOR() {
        CreateMultisigMsgParam param = new CreateMultisigMsgParam();

        param.setCodeCID("fil/6/multisig");
        param.setRequiredNumberOfApprovals(2);
        param.setSigners(signers);
        param.setStartEpoch(0);
        param.setUnlockDuration(0);

        String verifyParams = "gtgqUwABVQAOZmlsLzYvbXVsdGlzaWdYR4SDVQGRch3xRApUgOOPqWSsqVBnM9jtq1UB6j7tercApPvp9glDIhCLyD/gbpZVAfQLkKrUzhhOCktTu9oQdwoC/rPRAgAA";
        String params = Base64.getEncoder().encodeToString(param.marshalCBOR());

        System.out.println(params);
        Assert.assertEquals(verifyParams, params);
    }

    @Test
    public void unmarshalCBOR() {
        String verifyParams = "gtgqUwABVQAOZmlsLzYvbXVsdGlzaWdYR4SDVQGRch3xRApUgOOPqWSsqVBnM9jtq1UB6j7tercApPvp9glDIhCLyD/gbpZVAfQLkKrUzhhOCktTu9oQdwoC/rPRAgAA";
        CreateMultisigMsgParam createMultisigMsgParam = new CreateMultisigMsgParam();
        createMultisigMsgParam.setNetworkParameters(NetworkParameters.TEST_NET);

        createMultisigMsgParam.unmarshalCBOR(Base64.getDecoder().decode(verifyParams));
        System.out.println(JsonUtils.toJsonString(createMultisigMsgParam));

        Assert.assertEquals(2, createMultisigMsgParam.getRequiredNumberOfApprovals());
        Assert.assertEquals(0, createMultisigMsgParam.getStartEpoch());
        Assert.assertEquals(0, createMultisigMsgParam.getUnlockDuration());
        Assert.assertEquals(signers.toString(), createMultisigMsgParam.getSigners().toString());
    }
}
