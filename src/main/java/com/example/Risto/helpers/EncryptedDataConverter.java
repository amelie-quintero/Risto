package com.example.Risto.helpers;

import com.example.Risto.util.AESUtil;

import jakarta.persistence.AttributeConverter;

public class EncryptedDataConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			return AESUtil.encryptText(attribute);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			return AESUtil.decryptText(dbData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
