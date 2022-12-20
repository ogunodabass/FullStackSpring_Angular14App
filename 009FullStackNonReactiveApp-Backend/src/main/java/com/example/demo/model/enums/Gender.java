package com.example.demo.model.enums;

public enum Gender implements BaseEnum<String> {
	MALE((byte) 0), FAMELE((byte) 1);

	private byte value;

	Gender(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	@Override
	public String enumToValue(Enum<?> key) {
		return switch ((Gender) key) {
		case MALE -> "Male";
		case FAMELE -> "Famele";
		};
	}


}
