package io.filecoin.tx.multisig.types;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.Base64;

public class ApproveReturnTest {

    @Test
    public void unmarshalCBOR() {

        ApproveReturn approveReturn = new ApproveReturn();

        approveReturn = approveReturn.unmarshalCBOR(Base64.getDecoder().decode("g/UAQA=="));

        System.out.println(JSON.toJSONString(approveReturn));

        Assert.assertEquals(true, approveReturn.isApplied());
        Assert.assertEquals(0, approveReturn.getExitCode().longValue());
        Assert.assertEquals("", approveReturn.getRet());
    }

}
