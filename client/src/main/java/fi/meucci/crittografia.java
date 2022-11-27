package fi.meucci;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class crittografia 
{
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private static final String PUBLIC_KEY_STRING = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyTok9H+Xr4HYrj6nC88wXdWDYcKUApOYSZMOxWEZueDNe3vHdxIyLmf7dlyvI64IsXyKYA2QbISAT6t79U42OfevG4j0fo6YLUNPAbfQsEn8sqZMDwejP/wQgK+Kdz+yuI4mlV4m5POlaSEPUX1Hj5M97GMd/AEfBIQiTgLoCScVHeEBBBIu2+rP+9Tzqs5KFvME7M2EvBfAw6ucEbgLny8e1GtfbzGB0HvsXbqViq/Jlit7lGWw8JajvRFafAtF9Zr4T1OZFGi7OZYFeqJ2Iwgmvp2RobWPpesXiXvJ7ip5xspb2Lft+tlJdXkjModton/RFOWTSdHFdl4LbOZOHQIDAQAB";
    private static final String PRIVATE_KEY_STRING = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDJOiT0f5evgdiuPqcLzzBd1YNhwpQCk5hJkw7FYRm54M17e8d3EjIuZ/t2XK8jrgixfIpgDZBshIBPq3v1TjY5968biPR+jpgtQ08Bt9CwSfyypkwPB6M//BCAr4p3P7K4jiaVXibk86VpIQ9RfUePkz3sYx38AR8EhCJOAugJJxUd4QEEEi7b6s/71POqzkoW8wTszYS8F8DDq5wRuAufLx7Ua19vMYHQe+xdupWKr8mWK3uUZbDwlqO9EVp8C0X1mvhPU5kUaLs5lgV6onYjCCa+nZGhtY+l6xeJe8nuKnnGylvYt+362Ul1eSMyh22if9EU5ZNJ0cV2Xgts5k4dAgMBAAECggEAE7fk4yiVnpckun5dNCULYkaH505/3njEFYGZkvHe6X4D5LPOafaYgD+kPob6io6FP48qqBrsKhYZ1LTDItiVK7z/pb0X6Ebk1pqf5VUz1/KIFqGBFn/zGVQD8fhBo0nUo40absyZFxpg/RpGPAAAwSr2iHF2ALnDZlfkXKygPDUxtlihVLyZXuG/KrsLmHqC0JKR1sWkSw+g1odDChYaJStq0aIWR3lCOD9mWiC0U3mTKBETJX9OoyGSX0GyhWJyT55E35E/I/WdhOaZiMMwa66mRQQnpU9L+wUa8hZVRg6edaoKmzp6gXcYNWp8EqpVsa3Gd3HxK16NqOnJnwOdaQKBgQDpqqkp+mSrPmgsHj30fspIxDHg89Uql83mc4x2rG+KW/hRwZSS2inFsDtQkm1v+LUk40bapIsTQsvv/eLa8CCcoqgOHMJW/O5XlAgK1i/zaBVp3D6g4YRsrjbMCRl3oTiLPnBB66S0xLi8NSwrpq5yYdr4maB/v/N+ghnaHF0EdwKBgQDcdcF1t7jpIIkEJ85krpo8rQdc1+eRNFEIOpd4QcJ59PDNWcJQJcBQCYuvvpUzb3YxglSsyxUMuDpCLx004UO6OSyd6ATVI1O+Md4WF/D9EymC79PfO32K1n64cHe2qPP76lCuzbMVp+l5YQpWIpfUvo1mgSc7/K9HDh6e03oLCwKBgQCf1Jh5mkiZyco/wz/lqYR8WZc0pBS/Wa8BXzBJTJHPNjGY2qzh9lB0ZKNt7XrmCFz/qmagz8RBnIMa2bL+xxnHioshfBKCpefyB9OodfG+sQdUrc45BYkrPc9FqUO+TIz80ey/aovzCkzCkCxyf0T8AGtjeNIMECRh+GPuiVAm6QKBgQC6sXHqSXJq4eWGQltzQbVVysWgZyov+hjhGhUpfq69bqL5k5iNHNsWLG9BnehlzW9PD+q7BaPGyMB1Sca3AEjFjCNveYuWHzuMplhHZ3DqL+8IdiZ7Nbrj9y8K7BtE8SDk5ZH75jBRF4gNBROmy9a+D7xY4Oij0SFxz9A4eigw7wKBgQCh5Z1HtS2JH1lf/idzvgme8ldzQGGVFI2bFW89Hp1pgnD6nLuOn3+3G5dCHpi0tGhfBqdwyi+jGcK21+EYMij3VBbvarIcO7ZnDUPIekI+ZVNBH+W+xp+nKRlDvFnG35D5trOXStRGDTfVThFP0bg3cLhVrqo++7N6OHCIseshZg==";
    private String extPrivateString;
    
    public crittografia()
    {

    }

    public String getPrivKeyString()
    {
        return PRIVATE_KEY_STRING;
    }

    public void setExtPrivateString(String s)
    {
        this.extPrivateString = s;
    }

    public void init()
    {
        try 
        {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();
            privateKey = pair.getPrivate();
            publicKey = pair.getPublic();        
        } 
        catch (Exception e) 
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void initFromStrings()
    {
        
        try
        {
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY_STRING));
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(extPrivateString));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpecPublic);
            privateKey = keyFactory.generatePrivate(keySpecPrivate);
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public String encrypt(String m)
    {
        try
        {
            byte[] mBytes = m.getBytes();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(mBytes);
            return encode(encrypted);
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    private String encode(byte[] m)
    {
        return Base64.getEncoder().encodeToString(m); 
    }

    public String decrypt(String encrypted)
    {
        try
        {
            byte[] eBytes = decode(encrypted);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] mBytes = cipher.doFinal(eBytes);
            return new String(mBytes, "UTF8");
        }
        catch(Exception e)
        {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    private byte[] decode(String s)
    {
        return  Base64.getDecoder().decode(s);
    }

    public void printKeys()
    {
        System.out.println("Public key \n" + encode(publicKey.getEncoded()));
        System.out.println("Private key \n" + encode(privateKey.getEncoded()));
    }
}
