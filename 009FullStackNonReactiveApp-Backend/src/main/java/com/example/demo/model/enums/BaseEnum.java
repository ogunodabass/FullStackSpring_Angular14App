package com.example.demo.model.enums;

public interface BaseEnum<R> {

	R enumToValue(Enum<?> e);
}
