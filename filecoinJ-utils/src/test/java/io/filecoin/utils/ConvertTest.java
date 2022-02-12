package io.filecoin.utils;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;


public class ConvertTest {

    @Test
    public void testfromAttoFIL() {

        Assert.assertEquals(
                Convert.fromAttoFIL("21000000000000", Convert.Unit.AttoFIL),
                (new BigDecimal("21000000000000")));
        Assert.assertEquals(
                Convert.fromAttoFIL("21000000000000", Convert.Unit.FemtoFIL),
                (new BigDecimal("21000000000")));
        Assert.assertEquals(
                Convert.fromAttoFIL("21000000000000", Convert.Unit.PicoFIL), (new BigDecimal("21000000")));
        Assert.assertEquals(
                Convert.fromAttoFIL("21000000000000", Convert.Unit.NanoFIL), (new BigDecimal("21000")));
        Assert.assertEquals(Convert.fromAttoFIL("21000000000000", Convert.Unit.μFIL), (new BigDecimal("21")));
        Assert.assertEquals(
                Convert.fromAttoFIL("21000000000000", Convert.Unit.mFIL), (new BigDecimal("0.021")));
        Assert.assertEquals(
                Convert.fromAttoFIL("21000000000000", Convert.Unit.FIL),
                (new BigDecimal("0.000021")));
    }

    @Test
    public void testtoAttoFIL() {
        Assert.assertEquals(Convert.toAttoFIL("21", Convert.Unit.AttoFIL), (new BigDecimal("21")));
        Assert.assertEquals(Convert.toAttoFIL("21", Convert.Unit.FemtoFIL), (new BigDecimal("21000")));
        Assert.assertEquals(Convert.toAttoFIL("21", Convert.Unit.PicoFIL), (new BigDecimal("21000000")));
        Assert.assertEquals(Convert.toAttoFIL("21", Convert.Unit.NanoFIL), (new BigDecimal("21000000000")));
        Assert.assertEquals(Convert.toAttoFIL("21", Convert.Unit.μFIL), (new BigDecimal("21000000000000")));
        Assert.assertEquals(
                Convert.toAttoFIL("21", Convert.Unit.mFIL), (new BigDecimal("21000000000000000")));
        Assert.assertEquals(
                Convert.toAttoFIL("21", Convert.Unit.FIL), (new BigDecimal("21000000000000000000")));
    }

    @Test
    public void testUnit() {
        Assert.assertEquals(Convert.Unit.fromString("FIL"), (Convert.Unit.FIL));
        Assert.assertEquals(Convert.Unit.fromString("FIL"), (Convert.Unit.FIL));
        Assert.assertEquals(Convert.Unit.fromString("attoFIL"), (Convert.Unit.AttoFIL));
    }
}
