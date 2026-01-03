import JSEncrypt from 'jsencrypt'

// 使用你后端 RSAUtils 里的 PUBLIC_KEY_STR
const publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkxnNdfqOwb2WImLuhKI/X8uCt0Q2eJAwZqrzscospWgTQR0vMcZ8xLfaK4e5GqJyN+AtXoMcNYPNBKR5LVZlfJdoV5ADucvC0ByEU738ooZqGi9TJ+oOVu9a4J9qn0chiCfDDdICX9hoaEd8v7g821KkUO7+VKhGPEEHcAH0idwIDAQAB'

/**
 * RSA 加密方法
 * @param {string} txt 需要加密的明文密码
 * @returns {string} 加密后的 Base64 密文
 */
export function encrypt(txt) {
    const encryptor = new JSEncrypt()
    encryptor.setPublicKey(publicKey) // 设置公钥
    return encryptor.encrypt(txt)     // 执行加密
}