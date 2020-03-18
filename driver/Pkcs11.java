/*
 * Copyright (c) 2016, CJSC Aktiv-Soft.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

/* Derived from pkcs11f.h include file for PKCS #11. */

/* License to copy and use this software is granted provided that it is
 * identified as "RSA Security Inc. PKCS #11 Cryptographic Token Interface
 * (Cryptoki)" in all material mentioning or referencing this software.

 * License is also granted to make and use derivative works provided that
 * such works are identified as "derived from the RSA Security Inc. PKCS #11
 * Cryptographic Token Interface (Cryptoki)" in all material mentioning or 
 * referencing the derived work.

 * RSA Security Inc. makes no representations concerning either the 
 * merchantability of this software or the suitability of this software for
 * any particular purpose. It is provided "as is" without express or implied
 * warranty of any kind.
 */

/* This header file contains pretty much everything about all the */
/* Cryptoki function prototypes.  Because this information is */
/* used for more than just declaring function prototypes, the */
/* order of the functions appearing herein is important, and */
/* should not be altered. */

/*
 * @author Aktiv Co. <hotline@rutoken.ru>
 */

public interface Pkcs11 {
	/* General-purpose */

	/* C_Initialize initializes the Cryptoki library. */
	long C_Initialize(CK_C_INITIALIZE_ARGS pInitArgs /*
														 * if this is not null, it gets cast to CK_C_INITIALIZE_ARGS_PTR
														 * and dereferenced
														 */
	);

	/*
	 * C_Finalize indicates that an application is done with the Cryptoki library.
	 */
	long C_Finalize(long pReserved /* reserved. Should be null */
	);

	/* C_GetInfo returns general information about Cryptoki. */
	long C_GetInfo(CK_INFO pInfo /* location that receives information */
	);

	/* C_GetFunctionList returns the function list. */
	long C_GetFunctionList(long[] ppFunctionList /*
													 * receives long to function list
													 */
	);

	/* Slot and token management */

	/* C_GetSlotList obtains a list of slots in the system. */
	long C_GetSlotList(boolean tokenPresent, /* only slots with tokens */
			long[] pSlotList, /* receives array of slot IDs */
			long pulCount /* receives number of slots */
	);

	/*
	 * C_GetSlotInfo obtains information about a particular slot in the system.
	 */
	long C_GetSlotInfo(long slotID, /* the ID of the slot */
			CK_SLOT_INFO pInfo /* receives the slot information */
	);

	/*
	 * C_GetTokenInfo obtains information about a particular token in the system.
	 */
	long C_GetTokenInfo(long slotID, /* ID of the token's slot */
			CK_TOKEN_INFO pInfo /* receives the token information */
	);

	/*
	 * C_GetMechanismList obtains a list of mechanism types supported by a token.
	 */
	long C_GetMechanismList(long slotID, /* ID of token's slot */
			long[] pMechanismList, /* gets mech. array */
			long pulCount /* gets # of mechs. */
	);

	/*
	 * C_GetMechanismInfo obtains information about a particular mechanism possibly
	 * supported by a token.
	 */
	long C_GetMechanismInfo(long slotID, /* ID of the token's slot */
			long type, /* type of mechanism */
			CK_MECHANISM_INFO pInfo /* receives mechanism info */
	);

	/* C_InitToken initializes a token. */
	/* pLabel changed from CK_CHAR_PTR to byte[] for v2.10 */
	long C_InitToken(long slotID, /* ID of the token's slot */
			byte[] pPin, /* the SO's initial PIN */
			long ulPinLen, /* length in bytes of the PIN */
			byte[] pLabel /* 32-byte token label (blank padded) */
	);

	/* C_InitPIN initializes the normal user's PIN. */
	long C_InitPIN(long hSession, /* the session's handle */
			byte[] pPin, /* the normal user's PIN */
			long ulPinLen /* length in bytes of the PIN */
	);

	/* C_SetPIN modifies the PIN of the user who is logged in. */
	long C_SetPIN(long hSession, /* the session's handle */
			byte[] pOldPin, /* the old PIN */
			long ulOldLen, /* length of the old PIN */
			byte[] pNewPin, /* the new PIN */
			long ulNewLen /* length of the new PIN */
	);

	/* Session management */

	/*
	 * C_OpenSession opens a session between an application and a token.
	 */
	long C_OpenSession(long slotID, /* the slot's ID */
			long flags, /* from CK_SESSION_INFO */
			long pApplication, /* passed to callback */
			long Notify, /* callback function */
			long phSession /* gets session handle */
	);

	/*
	 * C_CloseSession closes a session between an application and a token.
	 */
	long C_CloseSession(long hSession /* the session's handle */
	);

	/* C_CloseAllSessions closes all sessions with a token. */
	long C_CloseAllSessions(long slotID /* the token's slot */
	);

	/* C_GetSessionInfo obtains information about the session. */
	long C_GetSessionInfo(long hSession, /* the session's handle */
			CK_SESSION_INFO pInfo /* receives session info */
	);

	/*
	 * C_GetOperationState obtains the state of the cryptographic operation in a
	 * session.
	 */
	long C_GetOperationState(long hSession, /* session's handle */
			byte[] pOperationState, /* gets state */
			long pulOperationStateLen /* gets state length */
	);

	/*
	 * C_SetOperationState restores the state of the cryptographic operation in a
	 * session.
	 */
	long C_SetOperationState(long hSession, /* session's handle */
			byte[] pOperationState, /* holds state */
			long ulOperationStateLen, /* holds state length */
			long hEncryptionKey, /* en/decryption key */
			long hAuthenticationKey /* sign/verify key */
	);

	/* C_Login logs a user into a token. */
	long C_Login(long hSession, /* the session's handle */
			long userType, /* the user type */
			byte[] pPin, /* the user's PIN */
			long ulPinLen /* the length of the PIN */
	);

	/* C_Logout logs a user out from a token. */
	long C_Logout(long hSession /* the session's handle */
	);

	/* Object management */

	/* C_CreateObject creates a new object. */
	long C_CreateObject(long hSession, /* the session's handle */
			CK_ATTRIBUTE[] pTemplate, /* the object's template */
			long ulCount, /* attributes in template */
			long phObject /* gets new object's handle. */
	);

	/*
	 * C_CopyObject copies an object, creating a new object for the copy.
	 */
	long C_CopyObject(long hSession, /* the session's handle */
			long hObject, /* the object's handle */
			CK_ATTRIBUTE[] pTemplate, /* template for new object */
			long ulCount, /* attributes in template */
			long phNewObject /* receives handle of copy */
	);

	/* C_DestroyObject destroys an object. */
	long C_DestroyObject(long hSession, /* the session's handle */
			long hObject /* the object's handle */
	);

	/* C_GetObjectSize gets the size of an object in bytes. */
	long C_GetObjectSize(long hSession, /* the session's handle */
			long hObject, /* the object's handle */
			long pulSize /* receives size of object */
	);

	/*
	 * C_GetAttributeValue obtains the value of one or more object attributes.
	 */
	long C_GetAttributeValue(long hSession, /* the session's handle */
			long hObject, /* the object's handle */
			CK_ATTRIBUTE[] pTemplate, /* specifies attrs; gets vals */
			long ulCount /* attributes in template */
	);

	/*
	 * C_SetAttributeValue modifies the value of one or more object attributes
	 */
	long C_SetAttributeValue(long hSession, /* the session's handle */
			long hObject, /* the object's handle */
			CK_ATTRIBUTE[] pTemplate, /* specifies attrs and values */
			long ulCount /* attributes in template */
	);

	/*
	 * C_FindObjectsInit initializes a search for token and session objects that
	 * match a template.
	 */
	long C_FindObjectsInit(long hSession, /* the session's handle */
			CK_ATTRIBUTE[] pTemplate, /* attribute values to match */
			long ulCount /* attrs in search template */
	);

	/*
	 * C_FindObjects continues a search for token and session objects that match a
	 * template, obtaining additional object handles.
	 */
	long C_FindObjects(long hSession, /* session's handle */
			long[] phObject, /* gets obj. handles */
			long ulMaxObjectCount, /* max handles to get */
			long pulObjectCount /* actual # returned */
	);

	/*
	 * C_FindObjectsFinal finishes a search for token and session objects.
	 */
	long C_FindObjectsFinal(long hSession /* the session's handle */
	);

	/* Encryption and decryption */

	/* C_EncryptInit initializes an encryption operation. */
	long C_EncryptInit(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism, /* the encryption mechanism */
			long hKey /* handle of encryption key */
	);

	/* C_Encrypt encrypts single-part data. */
	long C_Encrypt(long hSession, /* session's handle */
			byte[] pData, /* the plaintext data */
			long ulDataLen, /* bytes of plaintext */
			byte[] pEncryptedData, /* gets ciphertext */
			long pulEncryptedDataLen /* gets c-text size */
	);

	/*
	 * C_EncryptUpdate continues a multiple-part encryption operation.
	 */
	long C_EncryptUpdate(long hSession, /* session's handle */
			byte[] pPart, /* the plaintext data */
			long ulPartLen, /* plaintext data len */
			byte[] pEncryptedPart, /* gets ciphertext */
			long pulEncryptedPartLen /* gets c-text size */
	);

	/*
	 * C_EncryptFinal finishes a multiple-part encryption operation.
	 */
	long C_EncryptFinal(long hSession, /* session handle */
			byte[] pLastEncryptedPart, /* last c-text */
			long pulLastEncryptedPartLen /* gets last size */
	);

	/* C_DecryptInit initializes a decryption operation. */
	long C_DecryptInit(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism, /* the decryption mechanism */
			long hKey /* handle of decryption key */
	);

	/* C_Decrypt decrypts encrypted data in a single part. */
	long C_Decrypt(long hSession, /* session's handle */
			byte[] pEncryptedData, /* ciphertext */
			long ulEncryptedDataLen, /* ciphertext length */
			byte[] pData, /* gets plaintext */
			long pulDataLen /* gets p-text size */
	);

	/*
	 * C_DecryptUpdate continues a multiple-part decryption operation.
	 */
	long C_DecryptUpdate(long hSession, /* session's handle */
			byte[] pEncryptedPart, /* encrypted data */
			long ulEncryptedPartLen, /* input length */
			byte[] pPart, /* gets plaintext */
			long pulPartLen /* p-text size */
	);

	/*
	 * C_DecryptFinal finishes a multiple-part decryption operation.
	 */
	long C_DecryptFinal(long hSession, /* the session's handle */
			byte[] pLastPart, /* gets plaintext */
			long pulLastPartLen /* p-text size */
	);

	/* Message digesting */

	/* C_DigestInit initializes a message-digesting operation. */
	long C_DigestInit(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism /* the digesting mechanism */
	);

	/* C_Digest digests data in a single part. */
	long C_Digest(long hSession, /* the session's handle */
			byte[] pData, /* data to be digested */
			long ulDataLen, /* bytes of data to digest */
			byte[] pDigest, /* gets the message digest */
			long pulDigestLen /* gets digest length */
	);

	/*
	 * C_DigestUpdate continues a multiple-part message-digesting operation.
	 */
	long C_DigestUpdate(long hSession, /* the session's handle */
			byte[] pPart, /* data to be digested */
			long ulPartLen /* bytes of data to be digested */
	);

	/*
	 * C_DigestKey continues a multi-part message-digesting operation, by digesting
	 * the value of a secret key as part of the data already digested.
	 */
	long C_DigestKey(long hSession, /* the session's handle */
			long hKey /* secret key to digest */
	);

	/*
	 * C_DigestFinal finishes a multiple-part message-digesting operation.
	 */
	long C_DigestFinal(long hSession, /* the session's handle */
			byte[] pDigest, /* gets the message digest */
			long pulDigestLen /* gets byte count of digest */
	);

	/* Signing and MACing */

	/*
	 * C_SignInit initializes a signature (private key encryption) operation, where
	 * the signature is (will be) an appendix to the data, and plaintext cannot be
	 * recovered from the signature.
	 */
	long C_SignInit(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism, /* the signature mechanism */
			long hKey /* handle of signature key */
	);

	/*
	 * C_Sign signs (encrypts with private key) data in a single part, where the
	 * signature is (will be) an appendix to the data, and plaintext cannot be
	 * recovered from the signature.
	 */
	long C_Sign(long hSession, /* the session's handle */
			byte[] pData, /* the data to sign */
			long ulDataLen, /* count of bytes to sign */
			byte[] pSignature, /* gets the signature */
			long pulSignatureLen /* gets signature length */
	);

	/*
	 * C_SignUpdate continues a multiple-part signature operation, where the
	 * signature is (will be) an appendix to the data, and plaintext cannot be
	 * recovered from the signature.
	 */
	long C_SignUpdate(long hSession, /* the session's handle */
			byte[] pPart, /* the data to sign */
			long ulPartLen /* count of bytes to sign */
	);

	/*
	 * C_SignFinal finishes a multiple-part signature operation, returning the
	 * signature.
	 */
	long C_SignFinal(long hSession, /* the session's handle */
			byte[] pSignature, /* gets the signature */
			long pulSignatureLen /* gets signature length */
	);

	/*
	 * C_SignRecoverInit initializes a signature operation, where the data can be
	 * recovered from the signature.
	 */
	long C_SignRecoverInit(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism, /* the signature mechanism */
			long hKey /* handle of the signature key */
	);

	/*
	 * C_SignRecover signs data in a single operation, where the data can be
	 * recovered from the signature.
	 */
	long C_SignRecover(long hSession, /* the session's handle */
			byte[] pData, /* the data to sign */
			long ulDataLen, /* count of bytes to sign */
			byte[] pSignature, /* gets the signature */
			long pulSignatureLen /* gets signature length */
	);

	/* Verifying signatures and MACs */

	/*
	 * C_VerifyInit initializes a verification operation, where the signature is an
	 * appendix to the data, and plaintext cannot be recovered from the signature
	 * (e.g. DSA).
	 */
	long C_VerifyInit(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism, /* the verification mechanism */
			long hKey /* verification key */
	);

	/*
	 * C_Verify verifies a signature in a single-part operation, where the signature
	 * is an appendix to the data, and plaintext cannot be recovered from the
	 * signature.
	 */
	long C_Verify(long hSession, /* the session's handle */
			byte[] pData, /* signed data */
			long ulDataLen, /* length of signed data */
			byte[] pSignature, /* signature */
			long ulSignatureLen /* signature length */
	);

	/*
	 * C_VerifyUpdate continues a multiple-part verification operation, where the
	 * signature is an appendix to the data, and plaintext cannot be recovered from
	 * the signature.
	 */
	long C_VerifyUpdate(long hSession, /* the session's handle */
			byte[] pPart, /* signed data */
			long ulPartLen /* length of signed data */
	);

	/*
	 * C_VerifyFinal finishes a multiple-part verification operation, checking the
	 * signature.
	 */
	long C_VerifyFinal(long hSession, /* the session's handle */
			byte[] pSignature, /* signature to verify */
			long ulSignatureLen /* signature length */
	);

	/*
	 * C_VerifyRecoverInit initializes a signature verification operation, where the
	 * data is recovered from the signature.
	 */
	long C_VerifyRecoverInit(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism, /* the verification mechanism */
			long hKey /* verification key */
	);

	/*
	 * C_VerifyRecover verifies a signature in a single-part operation, where the
	 * data is recovered from the signature.
	 */
	long C_VerifyRecover(long hSession, /* the session's handle */
			byte[] pSignature, /* signature to verify */
			long ulSignatureLen, /* signature length */
			byte[] pData, /* gets signed data */
			long pulDataLen /* gets signed data len */
	);

	/* Dual-function cryptographic operations */

	/*
	 * C_DigestEncryptUpdate continues a multiple-part digesting and encryption
	 * operation.
	 */
	long C_DigestEncryptUpdate(long hSession, /* session's handle */
			byte[] pPart, /* the plaintext data */
			long ulPartLen, /* plaintext length */
			byte[] pEncryptedPart, /* gets ciphertext */
			long pulEncryptedPartLen /* gets c-text length */
	);

	/*
	 * C_DecryptDigestUpdate continues a multiple-part decryption and digesting
	 * operation.
	 */
	long C_DecryptDigestUpdate(long hSession, /* session's handle */
			byte[] pEncryptedPart, /* ciphertext */
			long ulEncryptedPartLen, /* ciphertext length */
			byte[] pPart, /* gets plaintext */
			long pulPartLen /* gets plaintext len */
	);

	/*
	 * C_SignEncryptUpdate continues a multiple-part signing and encryption
	 * operation.
	 */
	long C_SignEncryptUpdate(long hSession, /* session's handle */
			byte[] pPart, /* the plaintext data */
			long ulPartLen, /* plaintext length */
			byte[] pEncryptedPart, /* gets ciphertext */
			long pulEncryptedPartLen /* gets c-text length */
	);

	/*
	 * C_DecryptVerifyUpdate continues a multiple-part decryption and verification
	 * operation.
	 */
	long C_DecryptVerifyUpdate(long hSession, /* session's handle */
			byte[] pEncryptedPart, /* ciphertext */
			long ulEncryptedPartLen, /* ciphertext length */
			byte[] pPart, /* gets plaintext */
			long pulPartLen /* gets p-text length */
	);

	/* Key management */

	/*
	 * C_GenerateKey generates a secret key, creating a new key object.
	 */
	long C_GenerateKey(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism, /* key generation mech. */
			CK_ATTRIBUTE[] pTemplate, /* template for new key */
			long ulCount, /* # of attrs in template */
			long phKey /* gets handle of new key */
	);

	/*
	 * C_GenerateKeyPair generates a public-key/private-key pair, creating new key
	 * objects.
	 */
	long C_GenerateKeyPair(long hSession, /* session handle */
			CK_MECHANISM pMechanism, /* key-gen mech. */
			CK_ATTRIBUTE[] pPublicKeyTemplate, /* template for pub. key */
			long ulPublicKeyAttributeCount, /* # pub. attrs. */
			CK_ATTRIBUTE[] pPrivateKeyTemplate, /* template for priv. key */
			long ulPrivateKeyAttributeCount, /* # priv. attrs. */
			long phPublicKey, /* gets pub. key handle */
			long phPrivateKey /* gets priv. key handle */
	);

	/* C_WrapKey wraps (i.e., encrypts) a key. */
	long C_WrapKey(long hSession, /* the session's handle */
			CK_MECHANISM pMechanism, /* the wrapping mechanism */
			long hWrappingKey, /* wrapping key */
			long hKey, /* key to be wrapped */
			byte[] pWrappedKey, /* gets wrapped key */
			long pulWrappedKeyLen /* gets wrapped key size */
	);

	/*
	 * C_UnwrapKey unwraps (decrypts) a wrapped key, creating a new key object.
	 */
	long C_UnwrapKey(long hSession, /* session's handle */
			CK_MECHANISM pMechanism, /* unwrapping mech. */
			long hUnwrappingKey, /* unwrapping key */
			byte[] pWrappedKey, /* the wrapped key */
			long ulWrappedKeyLen, /* wrapped key len */
			CK_ATTRIBUTE[] pTemplate, /* new key template */
			long ulAttributeCount, /* template length */
			long phKey /* gets new handle */
	);

	/*
	 * C_DeriveKey derives a key from a base key, creating a new key object.
	 */
	long C_DeriveKey(long hSession, /* session's handle */
			CK_MECHANISM pMechanism, /* key deriv. mech. */
			long hBaseKey, /* base key */
			CK_ATTRIBUTE[] pTemplate, /* new key template */
			long ulAttributeCount, /* template length */
			long phKey /* gets new handle */
	);

	/* Random number generation */

	/*
	 * C_SeedRandom mixes additional seed material into the token's random number
	 * generator.
	 */
	long C_SeedRandom(long hSession, /* the session's handle */
			byte[] pSeed, /* the seed material */
			long ulSeedLen /* length of seed material */
	);

	/* C_GenerateRandom generates random data. */
	long C_GenerateRandom(long hSession, /* the session's handle */
			byte[] RandomData, /* receives the random data */
			long ulRandomLen /* # of bytes to generate */
	);

	/* Parallel function management */

	/*
	 * C_GetFunctionStatus is a legacy function; it obtains an updated status of a
	 * function running in parallel with an application.
	 */
	long C_GetFunctionStatus(long hSession /* the session's handle */
	);

	/*
	 * C_CancelFunction is a legacy function; it cancels a function running in
	 * parallel.
	 */
	long C_CancelFunction(long hSession /* the session's handle */
	);

	/* Functions added in Cryptoki Version 2.01 or later */

	/*
	 * C_WaitForSlotEvent waits for a slot event (token insertion, removal, etc.) to
	 * occur.
	 */
	long C_WaitForSlotEvent(long flags, /* blocking/nonblocking flag */
			long pSlot, /* location that receives the slot ID */
			long pReserved /* reserved. Should be null */
	);
}