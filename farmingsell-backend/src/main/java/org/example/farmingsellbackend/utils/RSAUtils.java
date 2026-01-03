package org.example.farmingsellbackend.utils;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.stereotype.Component;

@Component
public class RSAUtils {

    // 填入刚才生成的私钥
    private static final String PRIVATE_KEY_STR = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKTGc11+o7BvZYiYu6Eoj9fy4K3RDZ4kDBmqvOxyiylaBNBHS8xxnzEt9orh7kaonI34C1egxw1g80EpHktVmV8l2hXkAO5y8LQHIRTvfyihmoaL1Mn6g5W71rgn2qfRyGIJ8MN0gJf2GhoR3y/uDzbUqRQ7v5UqEY8QQdwAfSJ3AgMBAAECgYAU2/yRyuzc8pU3cCJcmf5vt5MC3hKrwON5QliNVEjSsM8oMMZjOMUO3EM4PLbF4iWDgXGqI7PfOTVCO/rWTZ1PLYQbVPZ2VkCrLTardurQaASFW1jAieiQqrD/mKSsD1JNjv7hJlt5Xot/H85HShlq9JkscbNNZRfRTruNxorLmQJBAP+ChswXnOgbr289dik5eVsx3DTk2B1jq6vN1hCUtNr8ZPhvN4tnJY0xi5lwEwiJRy/pNRBJ+Uvf2Oracce83N8CQQClF13v4bAQcKjkS1alH7S2ANJ0/PBYyzoE4AFIR8NqIHYrpK/9Yjb24f6MA/IaQBdLI3mQRmk8YHJSfUq239VpAkB6pUrS+Jqmh3OEb/oh+6aW8a4D7b0XDlq/EwSn3NmbK//DiI8e6RoLVjbuJe/c7xgulUCVWwzBMOfMg6KN/8f9AkAkvOZyk06S2f87jmhhpoSYW+Z5kjAKEm9+1jpy00VxQHgLjkJ7qHeCyRfUIKynmsuUNPjAWmrgvLW0FZ2ixHzBAkBwVh6Xp+xsQokZqHgiLA9I4C6yVe19bH5g3cXDhrsyUut/Vd4aA+4KiyC/cQq2Mv73j3LkTLnU/n5Ah81iUBSY";

    // 填入刚才生成的公钥
    public static final String PUBLIC_KEY_STR = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkxnNdfqOwb2WImLuhKI/X8uCt0Q2eJAwZqrzscospWgTQR0vMcZ8xLfaK4e5GqJyN+AtXoMcNYPNBKR5LVZlfJdoV5ADucvC0ByEU738ooZqGi9TJ+oOVu9a4J9qn0chiCfDDdICX9hoaEd8v7g821KkUO7+VKhGPEEHcAH0idwIDAQAB";

    /**
     * 解密方法
     */
    public static String decrypt(String encryptStr) {
        // 使用私钥初始化
        RSA rsa = new RSA(PRIVATE_KEY_STR, null);
        // 解密并返回明文
        return rsa.decryptStr(encryptStr, KeyType.PrivateKey);
    }
}