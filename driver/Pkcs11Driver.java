/**
 * Pkcs11Driver is the implementation of the PKCS#11 API Specification
 * as simulated by using the same method names and basic data types
 */
public class Pkcs11Driver implements Pkcs11 {

	// driver "version"
	public static final byte VERSION_MAJOR = 0;
	public static final byte VERSION_MINOR = 7;
	
	@Override
	public long C_Initialize(CK_C_INITIALIZE_ARGS pInitArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_Finalize(long pReserved) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetInfo(CK_INFO pInfo) {
		
		// must have an object to populate!
		if ( pInfo == null ) return Pkcs11Constants.CKR_ARGUMENTS_BAD;
		
		// set object fields
		pInfo.libraryVersion = new CK_VERSION();
		pInfo.libraryVersion.major = VERSION_MAJOR;
		pInfo.libraryVersion.minor = VERSION_MINOR;
		
		return Pkcs11Constants.CKR_OK;
		
	}

	@Override
	public long C_GetFunctionList(long[] ppFunctionList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetSlotList(boolean tokenPresent, long[] pSlotList, long pulCount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetSlotInfo(long slotID, CK_SLOT_INFO pInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetTokenInfo(long slotID, CK_TOKEN_INFO pInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetMechanismList(long slotID, long[] pMechanismList, long pulCount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetMechanismInfo(long slotID, long type, CK_MECHANISM_INFO pInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_InitToken(long slotID, byte[] pPin, long ulPinLen, byte[] pLabel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_InitPIN(long hSession, byte[] pPin, long ulPinLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SetPIN(long hSession, byte[] pOldPin, long ulOldLen, byte[] pNewPin, long ulNewLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_OpenSession(long slotID, long flags, long pApplication, long Notify, long phSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_CloseSession(long hSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_CloseAllSessions(long slotID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetSessionInfo(long hSession, CK_SESSION_INFO pInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetOperationState(long hSession, byte[] pOperationState, long pulOperationStateLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SetOperationState(long hSession, byte[] pOperationState, long ulOperationStateLen,
			long hEncryptionKey, long hAuthenticationKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_Login(long hSession, long userType, byte[] pPin, long ulPinLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_Logout(long hSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_CreateObject(long hSession, CK_ATTRIBUTE[] pTemplate, long ulCount, long phObject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_CopyObject(long hSession, long hObject, CK_ATTRIBUTE[] pTemplate, long ulCount, long phNewObject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DestroyObject(long hSession, long hObject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetObjectSize(long hSession, long hObject, long pulSize) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetAttributeValue(long hSession, long hObject, CK_ATTRIBUTE[] pTemplate, long ulCount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SetAttributeValue(long hSession, long hObject, CK_ATTRIBUTE[] pTemplate, long ulCount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_FindObjectsInit(long hSession, CK_ATTRIBUTE[] pTemplate, long ulCount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_FindObjects(long hSession, long[] phObject, long ulMaxObjectCount, long pulObjectCount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_FindObjectsFinal(long hSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_EncryptInit(long hSession, CK_MECHANISM pMechanism, long hKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_Encrypt(long hSession, byte[] pData, long ulDataLen, byte[] pEncryptedData,
			long pulEncryptedDataLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_EncryptUpdate(long hSession, byte[] pPart, long ulPartLen, byte[] pEncryptedPart,
			long pulEncryptedPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_EncryptFinal(long hSession, byte[] pLastEncryptedPart, long pulLastEncryptedPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DecryptInit(long hSession, CK_MECHANISM pMechanism, long hKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_Decrypt(long hSession, byte[] pEncryptedData, long ulEncryptedDataLen, byte[] pData,
			long pulDataLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DecryptUpdate(long hSession, byte[] pEncryptedPart, long ulEncryptedPartLen, byte[] pPart,
			long pulPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DecryptFinal(long hSession, byte[] pLastPart, long pulLastPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DigestInit(long hSession, CK_MECHANISM pMechanism) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_Digest(long hSession, byte[] pData, long ulDataLen, byte[] pDigest, long pulDigestLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DigestUpdate(long hSession, byte[] pPart, long ulPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DigestKey(long hSession, long hKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DigestFinal(long hSession, byte[] pDigest, long pulDigestLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SignInit(long hSession, CK_MECHANISM pMechanism, long hKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_Sign(long hSession, byte[] pData, long ulDataLen, byte[] pSignature, long pulSignatureLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SignUpdate(long hSession, byte[] pPart, long ulPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SignFinal(long hSession, byte[] pSignature, long pulSignatureLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SignRecoverInit(long hSession, CK_MECHANISM pMechanism, long hKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SignRecover(long hSession, byte[] pData, long ulDataLen, byte[] pSignature, long pulSignatureLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_VerifyInit(long hSession, CK_MECHANISM pMechanism, long hKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_Verify(long hSession, byte[] pData, long ulDataLen, byte[] pSignature, long ulSignatureLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_VerifyUpdate(long hSession, byte[] pPart, long ulPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_VerifyFinal(long hSession, byte[] pSignature, long ulSignatureLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_VerifyRecoverInit(long hSession, CK_MECHANISM pMechanism, long hKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_VerifyRecover(long hSession, byte[] pSignature, long ulSignatureLen, byte[] pData, long pulDataLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DigestEncryptUpdate(long hSession, byte[] pPart, long ulPartLen, byte[] pEncryptedPart,
			long pulEncryptedPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DecryptDigestUpdate(long hSession, byte[] pEncryptedPart, long ulEncryptedPartLen, byte[] pPart,
			long pulPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SignEncryptUpdate(long hSession, byte[] pPart, long ulPartLen, byte[] pEncryptedPart,
			long pulEncryptedPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DecryptVerifyUpdate(long hSession, byte[] pEncryptedPart, long ulEncryptedPartLen, byte[] pPart,
			long pulPartLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GenerateKey(long hSession, CK_MECHANISM pMechanism, CK_ATTRIBUTE[] pTemplate, long ulCount,
			long phKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GenerateKeyPair(long hSession, CK_MECHANISM pMechanism, CK_ATTRIBUTE[] pPublicKeyTemplate,
			long ulPublicKeyAttributeCount, CK_ATTRIBUTE[] pPrivateKeyTemplate, long ulPrivateKeyAttributeCount,
			long phPublicKey, long phPrivateKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_WrapKey(long hSession, CK_MECHANISM pMechanism, long hWrappingKey, long hKey, byte[] pWrappedKey,
			long pulWrappedKeyLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_UnwrapKey(long hSession, CK_MECHANISM pMechanism, long hUnwrappingKey, byte[] pWrappedKey,
			long ulWrappedKeyLen, CK_ATTRIBUTE[] pTemplate, long ulAttributeCount, long phKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_DeriveKey(long hSession, CK_MECHANISM pMechanism, long hBaseKey, CK_ATTRIBUTE[] pTemplate,
			long ulAttributeCount, long phKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_SeedRandom(long hSession, byte[] pSeed, long ulSeedLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GenerateRandom(long hSession, byte[] RandomData, long ulRandomLen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_GetFunctionStatus(long hSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_CancelFunction(long hSession) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long C_WaitForSlotEvent(long flags, long pSlot, long pReserved) {
		// TODO Auto-generated method stub
		return 0;
	}

}
