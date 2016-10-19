package com.ms.service.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SerializableSimpleAuthenticationInfo extends SimpleAuthenticationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected byte[] credentialsSalt;
	
	
	/**
     * Default no-argument constructor.
     */
    public SerializableSimpleAuthenticationInfo() {
    }

    public SerializableSimpleAuthenticationInfo(Object principal, Object credentials, String realmName) {
        this.principals = new SimplePrincipalCollection(principal, realmName);
        this.credentials = credentials;
    }

    public SerializableSimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName) {
        this.principals = new SimplePrincipalCollection(principal, realmName);
        this.credentials = hashedCredentials;
        this.credentialsSalt = credentialsSalt.getBytes();
    }
    public SerializableSimpleAuthenticationInfo(PrincipalCollection principals, Object credentials) {
        this.principals = new SimplePrincipalCollection(principals);
        this.credentials = credentials;
    }

    public SerializableSimpleAuthenticationInfo(PrincipalCollection principals, Object hashedCredentials, ByteSource credentialsSalt) {
        this.principals = new SimplePrincipalCollection(principals);
        this.credentials = hashedCredentials;
        this.credentialsSalt = credentialsSalt.getBytes();
    }
	
	
	@Override
	public ByteSource getCredentialsSalt() {
		return new SimpleByteSource(this.credentialsSalt);
	}
	
	@Override
	public void setCredentialsSalt(ByteSource salt) {
        this.credentialsSalt = salt.getBytes();
    }
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void merge(AuthenticationInfo info) {
		if (info == null || info.getPrincipals() == null || info.getPrincipals().isEmpty()) {
            return;
        }

        if (this.principals == null) {
            this.principals = info.getPrincipals();
        } else {
            if (!(this.principals instanceof MutablePrincipalCollection)) {
                this.principals = new SimplePrincipalCollection(this.principals);
            }
            ((MutablePrincipalCollection) this.principals).addAll(info.getPrincipals());
        }

        //only mess with a salt value if we don't have one yet.  It doesn't make sense
        //to merge salt values from different realms because a salt is used only within
        //the realm's credential matching process.  But if the current instance's salt
        //is null, then it can't hurt to pull in a non-null value if one exists.
        //
        //since 1.1:
        if (this.credentialsSalt == null && info instanceof SaltedAuthenticationInfo) {
            this.credentialsSalt = ((SaltedAuthenticationInfo) info).getCredentialsSalt().getBytes();
        }

        Object thisCredentials = getCredentials();
        Object otherCredentials = info.getCredentials();

        if (otherCredentials == null) {
            return;
        }

        if (thisCredentials == null) {
            this.credentials = otherCredentials;
            return;
        }

        if (!(thisCredentials instanceof Collection)) {
            Set newSet = new HashSet();
            newSet.add(thisCredentials);
            setCredentials(newSet);
        }

        // At this point, the credentials should be a collection
        Collection credentialCollection = (Collection) getCredentials();
        if (otherCredentials instanceof Collection) {
            credentialCollection.addAll((Collection) otherCredentials);
        } else {
            credentialCollection.add(otherCredentials);
        }
	}

}
