package io.digikraft.data.database.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.crypto.tink.Aead
import com.google.protobuf.InvalidProtocolBufferException
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.digikraft.domain.model.UserSession
import io.digikraft.domain.model.proto.UserProfile
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileSerializer @Inject constructor() : Serializer<UserProfile> {
    override val defaultValue: UserProfile = UserProfile.getDefaultInstance().apply {
        //noop: can set default value
    }

    override suspend fun readFrom(input: InputStream): UserProfile {
        try {
            return UserProfile.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserProfile, output: OutputStream) = t.writeTo(output)
}

@Singleton
@OptIn(ExperimentalSerializationApi::class)
class UserSessionSerializer @Inject constructor(val aead: Aead) : Serializer<UserSession> {

    override suspend fun readFrom(input: InputStream): UserSession {
        return try {
            val encryptedInput = input.readBytes()

            val decryptedInput = if (encryptedInput.isNotEmpty()) {
                aead.decrypt(encryptedInput, null)
            } else {
                encryptedInput
            }

            ProtoBuf.decodeFromByteArray(UserSession.serializer(), decryptedInput)
        } catch (e: SerializationException) {
            throw CorruptionException("Error deserializing proto", e)
        }
    }

    override suspend fun writeTo(userSession: UserSession, output: OutputStream) {
        val byteArray = ProtoBuf.encodeToByteArray(UserSession.serializer(), userSession)
        val encryptedBytes = aead.encrypt(byteArray, null)

        output.write(encryptedBytes)
    }

    override val defaultValue: UserSession
        get() = UserSession()
}