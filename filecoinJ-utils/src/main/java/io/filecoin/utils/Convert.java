package io.filecoin.utils;

import java.math.BigDecimal;

/**
 * Filecoin unit conversion functions.
 */
public final class Convert {
    private Convert() {
    }

    public static BigDecimal fromAttoFIL(String number, Unit unit) {
        return fromAttoFIL(new BigDecimal(number), unit);
    }

    public static BigDecimal fromAttoFIL(BigDecimal number, Unit unit) {
        return number.divide(unit.getWeiFactor());
    }

    public static BigDecimal toAttoFIL(String number, Unit unit) {
        return toAttoFIL(new BigDecimal(number), unit);
    }

    public static BigDecimal toAttoFIL(BigDecimal number, Unit unit) {
        return number.multiply(unit.getWeiFactor());
    }

    public enum Unit {
        AttoFIL("attoFIL", 0),
        FemtoFIL("femtoFIL", 3),
        PicoFIL("picoFIL", 6),
        NanoFIL("nanoFIL", 9),
        μFIL("μFIL", 12),
        mFIL("mFIL", 15),
        FIL("FIL", 18);

        private String name;
        private BigDecimal weiFactor;

        Unit(String name, int factor) {
            this.name = name;
            this.weiFactor = BigDecimal.TEN.pow(factor);
        }

        public BigDecimal getWeiFactor() {
            return weiFactor;
        }

        @Override
        public String toString() {
            return name;
        }

        public static Unit fromString(String name) {
            if (name != null) {
                for (Unit unit : Unit.values()) {
                    if (name.equalsIgnoreCase(unit.name)) {
                        return unit;
                    }
                }
            }
            return Unit.valueOf(name);
        }
    }
}

