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

/* Derived from rtpkcs11t.h include file for PKCS #11. */

/*
 * @author Aktiv Co. <hotline@rutoken.ru>
 */

public interface RtPkcs11Constants {

	public static final long CKR_CORRUPTED_MAPFILE = Pkcs11Constants.CKR_VENDOR_DEFINED + 1;
	public static final long CKR_WRONG_VERSION_FIELD = Pkcs11Constants.CKR_VENDOR_DEFINED + 2;
	public static final long CKR_WRONG_PKCS1_ENCODING = Pkcs11Constants.CKR_VENDOR_DEFINED + 3;
	public static final long CKR_RTPKCS11_DATA_CORRUPTED = Pkcs11Constants.CKR_VENDOR_DEFINED + 4;
	public static final long CKR_RTPKCS11_RSF_DATA_CORRUPTED = Pkcs11Constants.CKR_VENDOR_DEFINED + 5;
	public static final long CKR_SM_PASSWORD_INVALID = Pkcs11Constants.CKR_VENDOR_DEFINED + 6;
	public static final long CKR_LICENSE_READ_ONLY = Pkcs11Constants.CKR_VENDOR_DEFINED + 7;

	/* GOST KEY TYPES */
	public static final long CKK_GOSTR3410 = 0x00000030;
	public static final long CKK_GOSTR3411 = 0x00000031;
	public static final long CKK_GOST28147 = 0x00000032;

	/* GOST OBJECT ATTRIBUTES */
	public static final long CKA_GOSTR3410_PARAMS = 0x00000250;
	public static final long CKA_GOSTR3411_PARAMS = 0x00000251;
	public static final long CKA_GOST28147_PARAMS = 0x00000252;

	/* GOST MECHANISMS */
	public static final long CKM_GOSTR3410_KEY_PAIR_GEN = 0x00001200;
	public static final long CKM_GOSTR3410 = 0x00001201;
	public static final long CKM_GOSTR3410_WITH_GOSTR3411 = 0x00001202;
	public static final long CKM_GOSTR3410_KEY_WRAP = 0x00001203;
	public static final long CKM_GOSTR3410_DERIVE = 0x00001204;
	public static final long CKM_GOSTR3411 = 0x00001210;
	public static final long CKM_GOSTR3411_HMAC = 0x00001211;
	public static final long CKM_GOST28147_KEY_GEN = 0x00001220;
	public static final long CKM_GOST28147_ECB = 0x00001221;
	public static final long CKM_GOST28147 = 0x00001222;
	public static final long CKM_GOST28147_MAC = 0x00001223;
	public static final long CKM_GOST28147_KEY_WRAP = 0x00001224;

	public static final long CKD_CPDIVERSIFY_KDF = 0x00000009;
	public static final long CKP_PKCS5_PBKD2_HMAC_GOSTR3411 = 0x00000002;

	/*
	 * Token flags (field "flags" from CK_TOKEN_INFO_EXTENDED + field
	 * "ChangeUserPINPolicy" from CK_RUTOKEN_INIT_PARAM)
	 */
	/*
	 * TOKEN_FLAGS_ADMIN_CHANGE_USER_PIN - if it is set, that means that
	 * Administrator (SO) can change User PIN
	 */
	public static final long TOKEN_FLAGS_ADMIN_CHANGE_USER_PIN = 0x00000001;

	/*
	 * TOKEN_FLAGS_USER_CHANGE_USER_PIN - if it is set, that means that User can
	 * change User PIN
	 */
	public static final long TOKEN_FLAGS_USER_CHANGE_USER_PIN = 0x00000002;

	/*
	 * TOKEN_FLAGS_ADMIN_PIN_NOT_DEFAULT - if it is set, that means that current
	 * Administrator (SO) PIN is not default
	 */
	public static final long TOKEN_FLAGS_ADMIN_PIN_NOT_DEFAULT = 0x00000004;

	/*
	 * TOKEN_FLAGS_USER_PIN_NOT_DEFAULT - if it is set, that means that current User
	 * PIN is not default
	 */
	public static final long TOKEN_FLAGS_USER_PIN_NOT_DEFAULT = 0x00000008;

	/* Token types (field "ulTokenType") */
	public static final long TOKEN_TYPE_UNKNOWN = 0xFF;
	public static final long TOKEN_TYPE_RUTOKEN_ECP = 0x01;
	public static final long TOKEN_TYPE_RUTOKEN_LITE = 0x02;
	public static final long TOKEN_TYPE_RUTOKEN = 0x03;
	public static final long TOKEN_TYPE_RUTOKEN_PINPAD_FAMILY = 0x04;
	public static final long TOKEN_TYPE_RUTOKEN_ECPDUAL_USB = 0x09;
	public static final long TOKEN_TYPE_RUTOKEN_ECPDUAL_BT = 0x69;
	public static final long TOKEN_TYPE_RUTOKEN_ECPDUAL_UART = 0xA9;
	public static final long TOKEN_TYPE_RUTOKEN_WEB = 0x23;
	public static final long TOKEN_TYPE_RUTOKEN_SC_JC = 0x41;
	public static final long TOKEN_TYPE_RUTOKEN_LITE_SC_JC = 0x42;
	public static final long TOKEN_TYPE_RUTOKEN_ECP_SD = 0x81;
	public static final long TOKEN_TYPE_RUTOKEN_LITE_SD = 0x82;

	/*
	 * TOKEN_FLAGS_SUPPORT_FKN - if it is set, that means that token support
	 * CryptoPro FKN
	 */
	public static final long TOKEN_FLAGS_SUPPORT_FKN = 0x00000010;

	/*
	 * TOKEN_FLAGS_SUPPORT_SM - if it is set, that means that token supports Secure
	 * Messaging
	 */
	public static final long TOKEN_FLAGS_SUPPORT_SM = 0x00000040;

	/* Body color of the token */
	public static final long TOKEN_BODY_COLOR_UNKNOWN = 0;
	public static final long TOKEN_BODY_COLOR_WHITE = 1;
	public static final long TOKEN_BODY_COLOR_BLACK = 2;

}