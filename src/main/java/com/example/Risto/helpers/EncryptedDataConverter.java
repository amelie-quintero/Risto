package com.example.Risto.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.Risto.util.AESUtil;

import jakarta.persistence.AttributeConverter;

@Component
public class EncryptedDataConverter implements AttributeConverter<String, String> {

	private static Environment environment;
	
	@Autowired
	public void setEnvironment(Environment environment) {
		EncryptedDataConverter.environment = environment;
	}
	
	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			String SECRET_KEY = environment.getProperty("encrypt.SECRET_KEY");
			return AESUtil.encryptText(SECRET_KEY, attribute);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			String SECRET_KEY = environment.getProperty("encrypt.SECRET_KEY");
			return AESUtil.decryptText(SECRET_KEY, dbData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
