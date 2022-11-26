package it.fi.meucci;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class crittografia 
{
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private static final String PUBLIC_KEY_STRING = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlJ1zybPwmP1KOIGOAk5mRwieqhQ070TH37GuUDcNeAs8/cSsKEZaxgYgNCr2MKDu28qX7sj8D7KuOumrAP/M1teUYc1DLB4rDD7iE5y2eoUfhn8DykhgBzqa/n3jOyaAPo7GmY8+9y1EshX5/6vX4R2stIpcyEwW7ow63xI0VbRLV2IvaUAJeT4IPJ1MSAm0d5JFq1ZJnKcngKdvnftbkGXVND5gUPb8wS8HvTAeUBKSWt/WZyJ0cUjXUJceMinmOaeqT9aEq/IYW7bB/eE1/oW5fo8nQ4ozDI/1/Fwhw2+PSxLJ6gJTiPbvBMmCGh8byRYhfSrVf2SzlIse9WJzXQIDAQAB";
    private static final String PRIVATE_KEY_STRING = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCUnXPJs/CY/Uo4gY4CTmZHCJ6qFDTvRMffsa5QNw14Czz9xKwoRlrGBiA0KvYwoO7bypfuyPwPsq466asA/8zW15RhzUMsHisMPuITnLZ6hR+GfwPKSGAHOpr+feM7JoA+jsaZjz73LUSyFfn/q9fhHay0ilzITBbujDrfEjRVtEtXYi9pQAl5Pgg8nUxICbR3kkWrVkmcpyeAp2+d+1uQZdU0PmBQ9vzBLwe9MB5QEpJa39ZnInRxSNdQlx4yKeY5p6pP1oSr8hhbtsH94TX+hbl+jydDijMMj/X8XCHDb49LEsnqAlOI9u8EyYIaHxvJFiF9KtV/ZLOUix71YnNdAgMBAAECggEAHjGbK7i+Qib75F0WAAvzpazvp3hxhMdBoIYV90AoIWKPIrPJkmkw2hi2hELSP/ECdm9DzQTSiVHxKhD9aDeQY3nrMK+mXRlH/7Xq8rcdL+lKDWZS2882i2FxyhDDkOnylkMlwF8lGMz+rvveDf7Y3mjMBOYzkZI7+n1NHLMzJNIsJUaYfzw+ZX0eOAParcChqb2KGzKaWM/A9oJYoWX7xjasU7neU31JZrnJsqdh4RZIYBHqJvSYrCYze4qPdOxlKBVETBLhRTlTCGpzOhQ0cH08thkG4aw/aRqZUPhOhCAsXEXEiS1BAPGy4d/MPKyoTF+vmOR/iiigRu/47Y73tQKBgQC18GE7L5y7MZI3+l+VXVrSR7H1oWi5SUYPPMn790x5sYfC2QU4Lv45ANke18pSjy6MYkhJus7jVo52blQW3IDZQvtYapOoxVrfpJB/oYKI4wyxtVJMe2eSoDwFoeQ583ad026DIrJDYF3bgMFzejXY91YNPAKbFvdmGZbkRSGxwwKBgQDRHG3qIm/ETAaRYsBnCdfdxupLS37nuJrCWzge9qwrBfwS4xLReObi3AScu082vVSpgR0D4SpfKLVFc+HM9C9nR7lAgiBSWlKKLs41geQ7dzpAkxT1zPpTkX4OUQQnLKZbOTEYNpSGZbl54rB6g6to+7Pj3qOYjBHQnOweR+TUXwKBgQCsYua9vY8zjvSMNZ77V0kGlQf5RMaFSPgo1+BJ7Td41s1bwWVApsYaHMOchboeH6xrN3Rm56m/nfO+IytpveQX929PhywBbX19CeAgDlgwe3E7sCIadZLdSxWUAKDOh/CL+hlsByQA0v4bTRP+5ny+5xuRzpppeIlRJE3kW9xhIQKBgFjb1NU+hvIKQGw9Y3cllqZ2ZY9TaHz9VD4s1x9JQQYTR/eaqJ982dgjGI6lhdbEA5r386V3ZDMFsGnwAU6/oPkpYE5KPYPhP9vaYeQY6yxd0NIp7ik5TlJ+q+tXgAVJkvOGzRb18o1d4sAplUaqdwdolhvJuwkS1Gsqk6eWc2XPAoGAcaVsUTgf+HUXtrLGbPSZWuNMKdR9a4qGsnO6/A+w1T93xMH4gUptklyCVGr+c64oi6wMI6bx7HXTfGxZSuGMUXWdH0yONp9WePfiT/uSycewHzth2ChRbowUOxh2TPFuvql9T+qlJM+3kDvfnujDsUKVDthfE04VaG2Kgo7bbk0=";
    
    public crittografia()
    {

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
            PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY_STRING));
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
