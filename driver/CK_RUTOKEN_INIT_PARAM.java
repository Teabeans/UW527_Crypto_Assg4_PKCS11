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

/**
 * @author Aktiv Co. <hotline@rutoken.ru>
 */

/*
 * CK_RUTOKEN_INIT_PARAM uses in C_EX_InitToken - extended function for token
 * formatting (C_InitToken will format only PKCS#11-objects)
 */
public class CK_RUTOKEN_INIT_PARAM {

	/*
	 * init this field by size of this structure [in] - size of input structure
	 * [out] - return size of filled structure
	 */
	public long ulSizeofThisStructure;
	/*
	 * if field equals 0 - format procedure requires authentication as Administrator
	 * if field does not equal 0 - format procedure executes without authentication
	 * as Administrator
	 */
	public long UseRepairMode;
	/* long to byte array with new Administrator PIN */
	public long pNewAdminPin;
	/*
	 * length of new Administrator PIN: minimum bMinAdminPinLength bytes, maximum 32
	 * bytes
	 */
	public long ulNewAdminPinLen;
	/* long to byte array with new User PIN */
	public long pNewUserPin;
	/* length of new User PIN: minimum bMinUserPinLength bytes, maximum 32 bytes */
	public long ulNewUserPinLen;
	/* policy of change User PIN */
	/*
	 * TOKEN_FLAGS_ADMIN_CHANGE_USER_PIN (0x1) - Administrator can change User PIN
	 * TOKEN_FLAGS_USER_CHANGE_USER_PIN (0x2) - User can change User PIN
	 * TOKEN_FLAGS_ADMIN_CHANGE_USER_PIN | TOKEN_FLAGS_USER_CHANGE_USER_PIN (0x3) -
	 * Administrator and User can change User PIN In other cases - error
	 */
	public long ChangeUserPINPolicy;
	/* minimal size of Administrator PIN minimum 1, maximum 31 bytes */
	public long ulMinAdminPinLen;
	/* minimal size of User PIN minimum 1, maximum 31 bytes */
	public long ulMinUserPinLen;
	/* minimum 3, maximum 10 */
	public long ulMaxAdminRetryCount;
	/* minimum 1, maximum 10 */
	public long ulMaxUserRetryCount;
	/*
	 * long to byte array with new token symbol name, if pTokenLabel == null - token
	 * symbol name will not be set
	 */
	public long pTokenLabel;
	/* length of new token symbol name */
	public long ulLabelLen;
	/* secure messaging mode */
	public long ulSmMode;

	public CK_RUTOKEN_INIT_PARAM() {
	}

	public CK_RUTOKEN_INIT_PARAM(long ulSizeofThisStructure, long UseRepairMode, long pNewAdminPin,
			long ulNewAdminPinLen, long pNewUserPin, long ulNewUserPinLen, long ChangeUserPINPolicy,
			long ulMinAdminPinLen, long ulMinUserPinLen, long ulMaxAdminRetryCount, long ulMaxUserRetryCount,
			long pTokenLabel, long ulLabelLen, long ulSmMode) {
		this.ulSizeofThisStructure = ulSizeofThisStructure;
		this.UseRepairMode = UseRepairMode;
		this.pNewAdminPin = pNewAdminPin;
		this.ulNewAdminPinLen = ulNewAdminPinLen;
		this.pNewUserPin = pNewUserPin;
		this.ulNewUserPinLen = ulNewUserPinLen;
		this.ChangeUserPINPolicy = ChangeUserPINPolicy;
		this.ulMinAdminPinLen = ulMinAdminPinLen;
		this.ulMinUserPinLen = ulMinUserPinLen;
		this.ulMaxAdminRetryCount = ulMaxAdminRetryCount;
		this.ulMaxUserRetryCount = ulMaxUserRetryCount;
		this.pTokenLabel = pTokenLabel;
		this.ulLabelLen = ulLabelLen;
		this.ulSmMode = ulSmMode;
	}

}