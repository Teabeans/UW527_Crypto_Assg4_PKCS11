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

/* Derived from rtpkcs11f.h include file for PKCS #11. */

/*
 * @author Aktiv Co. <hotline@rutoken.ru>
 */

public interface RtPkcs11 extends Pkcs11 {

	/* C_EX_GetFunctionListExtended returns the extended function list. */
	long C_EX_GetFunctionListExtended(long[] ppFunctionList /* receives long to extended function list */
	);

	/* C_EX_InitToken initializes a token with full format. */
	long C_EX_InitToken(long slotID, /* ID of the token's slot */
			byte[] pPin, /* the SO's initial PIN */
			long ulPinLen, /* length in bytes of the PIN */
			CK_RUTOKEN_INIT_PARAM pInitInfo /* init parameters */
	);

	/*
	 * C_EX_GetTokenInfoExtended obtains information about the particular token in
	 * the system.
	 */
	long C_EX_GetTokenInfoExtended(long slotID, /* ID of the token's slot */
			CK_TOKEN_INFO_EXTENDED pInfo /* receives the token information */
	);

	/*
	 * C_EX_UnblockUserPIN unblocks the blocked User's PIN. C_EX_UnblockUserPIN
	 * requires same conditions as a C_InitPIN
	 */
	long C_EX_UnblockUserPIN(long hSession /* the session's handle */
	);

	/*
	 * C_EX_SetTokenName modifies the token symbol name (label) if User is logged
	 * in. C_EX_SetTokenName can only be called in the "R/W User Functions" state.
	 */
	long C_EX_SetTokenName(long hSession, /* the session's handle */
			byte[] pLabel, /* the new label */
			long ulLabelLen /* length of the new label */
	);

	/*
	 * C_EX_SetLicense modifies the token license if User or SO is logged in.
	 * C_EX_SetLicense can only be called in the "R/W User Functions" state or
	 * "R/W SO Functions" state.
	 */
	long C_EX_SetLicense(long hSession, /* the session's handle */
			long ulLicenseNum, /* the number of the new license, can only be 1 or 2 */
			byte[] pLicense, /* byte buffer with the data of new license */
			long ulLicenseLen /* length of the new license, can only be 72 */
	);

	/*
	 * C_EX_GetLicense reads the token license. C_EX_GetLicense can be called in any
	 * state. pulLicenseLen [in/out] - [in]- sets license length, can only be 72
	 * [out] - gets license length (if pLicense is null)
	 */
	long C_EX_GetLicense(long hSession, /* the session's handle */
			long ulLicenseNum, /* the number of the license, can only be 1 or 2 */
			byte[] pLicense, /* receives the license */
			long pulLicenseLen /* length of the license */
	);

	/*
	 * C_EX_GetCertificateInfoText get text information about certificate.
	 * C_EX_GetCertificateInfoText can be called in any state.
	 */
	long C_EX_GetCertificateInfoText(long hSession, /* the session's handle */
			long hCert, /* the object's handle */
			long pInfo, /* returns address of allocated buffer with text information */
			long pulInfoLen /* length of the allocated buffer */
	);

	/*
	 * C_EX_PKCS7Sign signs data and packs it to PKCS#7 format certificate.
	 * C_EX_PKCS7Sign can only be called in the "R/W User Functions" or
	 * "R User Functions" state.
	 */
	long C_EX_PKCS7Sign(long hSession, byte[] pData, long ulDataLen, long hCert, long ppEnvelope, long pEnvelopeLen,
			long hPrivKey, long[] phCertificates, long ulCertificatesLen, long flags);

	/*
	 * C_EX_CreateCSR creates a certification request and packs it in PKCS#10
	 * format. C_EX_CreateCSR can only be called in the "R/W User Functions" or
	 * "R User Functions" state.
	 */
	long C_EX_CreateCSR(long hSession, long hPublicKey, String[] dn, long dnLength, long pCsr, long pulCsrLength,
			long hPrivKey, long[] pAttributes, long ulAttributesLength, String[] pExtensions, long ulExtensionsLength);

	/*
	 * C_EX_FreeBuffer frees buffer, allocated in extended functions.
	 */
	long C_EX_FreeBuffer(long pBuffer /* long to the buffer */
	);

	/*
	 * C_EX_GetTokenName returns the token symbol name (label).
	 */
	long C_EX_GetTokenName(long hSession, /* the session's handle */
			byte[] pLabel, /* byte buffer for label */
			long pulLabelLen /* length of the label */
	);

	/*
	 * C_EX_SetLocalPIN modifies the local PIN for devices which supported it.
	 */
	long C_EX_SetLocalPIN(long slotID, /* ID of the token's slot */
			byte[] pUserPin, /* the current User PIN */
			long ulUserPinLen, /* length of current User PIN */
			byte[] pNewLocalPin, /* the new local PIN */
			long ulNewLocalPinLen, /* length of the new local PIN */
			long ulLocalID /* ID of the local PIN */
	);

	/* C_EX_LoadActivationKey */
	long C_EX_LoadActivationKey(long hSession, /* the session's handle */
			byte[] key, long keySize);

	/* C_EX_SetActivationPassword */
	long C_EX_SetActivationPassword(long slotID, /* ID of the token's slot */
			byte[] password);
}