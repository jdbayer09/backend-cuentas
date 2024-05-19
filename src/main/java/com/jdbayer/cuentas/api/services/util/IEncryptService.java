package com.jdbayer.cuentas.api.services.util;

public interface IEncryptService {
    String encrypt(String plainText);
    String decrypt(String cipherText);
}
