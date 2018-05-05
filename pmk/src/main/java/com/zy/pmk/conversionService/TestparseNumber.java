package com.zy.pmk.conversionService;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestparseNumber {

	/**
	 * 转换{@code text}为{@link Number}的类型
	 * <p>先对输入值{@code txt}去空
	 * <p>支撑"0x", "0X","#"的16进制类型的字符串
	 * @param txt 要转换的{@link String}
	 * @param targetClass 转换为什么{@link Class}类型的Number
	 * @return 转换后的Number
	 * @throws IllegalArgumentException if the target class is not supported
	 * (i.e. not a standard Number subclass as included in the JDK)
	 * @see Byte#decode(String)
	 * @see #decodeBigInteger(String)
	 * @see #isHexNumber(String)
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Number> T parese(String txt, Class<T> targetClass) throws IllegalArgumentException {
		T result = null;
		String trimmed = txt.trim();
		if (Byte.class == targetClass) {
			result = (T) (isHexNumber(trimmed) ? Byte.decode(trimmed) : Byte.valueOf(trimmed));
		} else if (Short.class == targetClass) {
			return (T) (isHexNumber(trimmed) ? Short.decode(trimmed) : Short.valueOf(trimmed));
		} else if (Integer.class == targetClass) {
			return (T) (isHexNumber(trimmed) ? Integer.decode(trimmed) : Integer.valueOf(trimmed));
		} else if (Long.class == targetClass) {
			return (T) (isHexNumber(trimmed) ? Long.decode(trimmed) : Long.valueOf(trimmed));
		} else if (BigInteger.class == targetClass) {
			return (T) (isHexNumber(trimmed) ? decodeBigInteger(trimmed) : new BigInteger(trimmed));
		} else if (Float.class == targetClass) {
			return (T) Float.valueOf(trimmed);
		} else if (Double.class == targetClass) {
			return (T) Double.valueOf(trimmed);
		} else if (BigDecimal.class == targetClass || Number.class == targetClass) {
			return (T) new BigDecimal(trimmed);

		} else {
			throw new IllegalArgumentException(
					"Cannot convert String [" + trimmed + "] to target class [" + targetClass.getName() + "]");
		}
		return result;

	}

	/**
	 * Determine whether the given {@code value} String indicates a hex number, i.e.
	 * needs to be passed into {@code Integer.decode} instead of
	 * {@code Integer.valueOf}, etc.
	 */
	private static boolean isHexNumber(String value) {
		int index = (value.startsWith("-") ? 1 : 0);
		return (value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index));
	}
	
	/**
	 * Decode a {@link java.math.BigInteger} from the supplied {@link String} value.
	 * <p>Supports decimal, hex, and octal notation.
	 * @see BigInteger#BigInteger(String, int)
	 */
	private static BigInteger decodeBigInteger(String value) {
		int radix = 10;
		int index = 0;
		boolean negative = false;

		// Handle minus sign, if present.
		if (value.startsWith("-")) {
			negative = true;
			index++;
		}

		// Handle radix specifier, if present.
		if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
			index += 2;
			radix = 16;
		}
		else if (value.startsWith("#", index)) {
			index++;
			radix = 16;
		}
		else if (value.startsWith("0", index) && value.length() > 1 + index) {
			index++;
			radix = 8;
		}

		BigInteger result = new BigInteger(value.substring(index), radix);
		return (negative ? result.negate() : result);
	}
}
