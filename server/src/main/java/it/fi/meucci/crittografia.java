package it.fi.meucci;
import java.nio.charset.StandardCharsets;
import java.security.*;

import javax.crypto.Cipher;

public class crittografia 
{
    private static KeyPairGenerator keyGen;
    private static PublicKey pubK;
    private static PrivateKey privK;
    private static Cipher cipher;
    
    public crittografia() {
    }

    public void gen()
    {
        try
        {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            keyGen = KeyPairGenerator.getInstance("RSA", "BC");
            keyGen.initialize(1024);
            KeyPair pair = keyGen.generateKeyPair();
            pubK = pair.getPublic();
            privK = pair.getPrivate();
            cipher = Cipher.getInstance("RSA/NONE/NoPadding", "BC");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public PublicKey getpubK()
    {
        return this.pubK;
    }

    public PrivateKey getPriv()
    {
        return this.privK;
    }

    public String cifra(String m) throws Exception
    {
        cipher.init(Cipher.ENCRYPT_MODE, pubK);
        String x = new String(cipher.doFinal(m.getBytes()), StandardCharsets.UTF_8);
        return x;
    }

    public String decifra(String m) throws Exception
    {
        cipher.init(Cipher.DECRYPT_MODE, privK);
        String x = new String(cipher.doFinal(m.getBytes()), StandardCharsets.UTF_8);
        return x;
    }
}
