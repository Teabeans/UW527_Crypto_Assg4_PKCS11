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

/* CK_TOKEN_INFO_EXTENDED provides extended information about a token */
public class CK_TOKEN_INFO_EXTENDED {

	/*
	 * init this field by size of this structure [in] - size of input structure
	 * [out] - return size of filled structure
	 */
	public long ulSizeofThisStructure;
	/* type of token: */
	public long ulTokenType; /* see below */
	/* exchange protocol number */
	public long ulProtocolNumber;
	/* microcode number */
	public long ulMicrocodeNumber;
	/* order number */
	public long ulOrderNumber;
	/* information flags */
	/*
	 * TOKEN_FLAGS_ADMIN_CHANGE_USER_PIN - Administrator can change User PIN
	 * TOKEN_FLAGS_USER_CHANGE_USER_PIN - User can change User PIN
	 * TOKEN_FLAGS_ADMIN_PIN_NOT_DEFAULT - Administrator PIN is not default
	 * TOKEN_FLAGS_USER_PIN_NOT_DEFAULT - User PIN is not default
	 * TOKEN_FLAGS_SUPPORT_FKN - token supports CryptoPro FKN
	 */
	public long flags; /* see below */
	/* maximum and minimum PIN length */
	public long ulMaxAdminPinLen;
	public long ulMinAdminPinLen;
	public long ulMaxUserPinLen;
	public long ulMinUserPinLen;
	/* max count of unsuccessful Administrator login attempts */
	public long ulMaxAdminRetryCount;
	/*
	 * count of unsuccessful login attempts left (for Administrator PIN) if field
	 * equals 0 - that means that PIN is blocked
	 */
	public long ulAdminRetryCountLeft;
	/* min counts of unsuccessful User login attempts */
	public long ulMaxUserRetryCount;
	/*
	 * count of unsuccessful login attempts left (for User PIN) if field equals 0 -
	 * that means that PIN is blocked
	 */
	public long ulUserRetryCountLeft;
	/* token serial number in Big Endian format */
	public byte[] serialNumber = new byte[8];
	/* size of all memory */
	public long ulTotalMemory; /* in bytes */
	/* size of free memory */
	public long ulFreeMemory; /* in bytes */
	/* ATR of the token */
	public byte[] ATR = new byte[64];
	/* size of ATR */
	public long ulATRLen;
	/* class of token */
	public long ulTokenClass; /* see below */
	/* Battery voltage */
	public long ulBatteryVoltage; /* microvolts */

	public long ulBodyColor;

	public CK_TOKEN_INFO_EXTENDED() {
	}

	public CK_TOKEN_INFO_EXTENDED(long ulSizeofThisStructure, long ulTokenType, long ulProtocolNumber,
			long ulMicrocodeNumber, long ulOrderNumber, long flags, long ulMaxAdminPinLen, long ulMinAdminPinLen,
			long ulMaxUserPinLen, long ulMinUserPinLen, long ulMaxAdminRetryCount, long ulAdminRetryCountLeft,
			long ulMaxUserRetryCount, long ulUserRetryCountLeft, byte[] serialNumber, long ulTotalMemory,
			long ulFreeMemory, byte[] ATR, long ulATRLen, long ulTokenClass, long ulBatteryVoltage, long ulBodyColor) {
		this.ulSizeofThisStructure = ulSizeofThisStructure;
		this.ulTokenType = ulTokenType;
		this.ulProtocolNumber = ulProtocolNumber;
		this.ulMicrocodeNumber = ulMicrocodeNumber;
		this.ulOrderNumber = ulOrderNumber;
		this.flags = flags;
		this.ulMaxAdminPinLen = ulMaxAdminPinLen;
		this.ulMinAdminPinLen = ulMinAdminPinLen;
		this.ulMaxUserPinLen = ulMaxUserPinLen;
		this.ulMinUserPinLen = ulMinUserPinLen;
		this.ulMaxAdminRetryCount = ulMaxAdminRetryCount;
		this.ulAdminRetryCountLeft = ulAdminRetryCountLeft;
		this.ulMaxUserRetryCount = ulMaxUserRetryCount;
		this.ulUserRetryCountLeft = ulUserRetryCountLeft;
		this.serialNumber = serialNumber;
		this.ulTotalMemory = ulTotalMemory;
		this.ulFreeMemory = ulFreeMemory;
		this.ATR = ATR;
		this.ulATRLen = ulATRLen;
		this.ulTokenClass = ulTokenClass;
		this.ulBatteryVoltage = ulBatteryVoltage;
		this.ulBodyColor = ulBodyColor;
	}

}