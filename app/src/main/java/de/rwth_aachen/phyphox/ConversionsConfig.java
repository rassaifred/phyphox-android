package de.rwth_aachen.phyphox;


import java.io.Serializable;
import java.lang.reflect.Method;

// The class holds public static functions which convert values from a string to a byte array.
public class ConversionsConfig {

    public static class ConfigConversion implements Serializable {
        ConfigConversion() {

        }
        protected byte[] convert(String data) {
            return null;
        }
    }

    public static class SimpleConfigConversion extends ConfigConversion implements Serializable {
        private Method conversionFunction;
        SimpleConfigConversion(Method conversionFunction) {
            super();
            this.conversionFunction = conversionFunction;
        }

        @Override
        protected byte[] convert(String data) {
            try {
                return (byte[]) conversionFunction.invoke(null, data);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public static byte[] stringAsByteArray (String data) {
        return (data).getBytes();
    }

    public static byte[] int16LittleEndian (String data) {
        return ConversionsOutput.int16LittleEndian(Double.parseDouble(data));
    }

    public static byte[] uInt16LittleEndian (String data) {
        return int16LittleEndian(data);
    }

    public static byte[] int24LittleEndian (String data) {
        return ConversionsOutput.int24LittleEndian(Double.parseDouble(data));
    }

    public static byte[] uInt24LittleEndian (String data) {
        return int24LittleEndian(data);
    }

    public static byte[] int32LittleEndian (String data) {
        return ConversionsOutput.int32LittleEndian(Double.parseDouble(data));
    }

    public static byte[] uInt32LittleEndian (String data) {
        return ConversionsOutput.uInt32LittleEndian(Double.parseDouble(data));
    }

    public static byte[] parseByte (String data) {
        return new byte[]{Byte.parseByte(data)};
    }

    public static byte[] hexadecimal (String data) {
        byte[] result = new byte[data.length()/2];
        for (int i = 0; i < data.length(); i+=2) {
            result[i/2] = (byte) ((Character.digit(data.charAt(i), 16) << 4) + Character.digit(data.charAt(i+1), 16));
        }
        return result;
    }
}