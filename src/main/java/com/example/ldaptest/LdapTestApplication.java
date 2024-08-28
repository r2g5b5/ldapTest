package com.example.ldaptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Properties;

@SpringBootApplication
public class LdapTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdapTestApplication.class, args);
        System.out.println(authenticate("hazhir", "1234"));
    }

    public static boolean authenticate(String username, String password) {
        try {
            Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, "cn="+username+",ou=users,ou=system");
            env.put(Context.SECURITY_CREDENTIALS, password);
            DirContext dirContext = new InitialDirContext(env);
            dirContext.close();
            return true;
        } catch (NamingException e) {
            return false;
        }
    }

}
