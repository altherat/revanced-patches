package app.revanced.patches.reddit.customclients.redditisfun.api

import app.revanced.patcher.fingerprint
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal fun baseClientIdFingerprint(string: String) = fingerprint {
    strings("yyOCBp.RHJhDKd", string)
}

internal val basicAuthorizationFingerprint = baseClientIdFingerprint(
    string = "fJOxVwBUyo*=f:<OoejWs:AqmIJ", // Encrypted basic authorization string.
)

internal val buildAuthorizationStringFingerprint = baseClientIdFingerprint(
    string = "client_id",
)

internal val getUserAgentFingerprint = fingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.STATIC)
    returns("Ljava/lang/String;")
    parameters()
    opcodes(
        Opcode.NEW_ARRAY,
        Opcode.CONST_4,
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT_OBJECT,
        Opcode.APUT_OBJECT,
        Opcode.CONST,
    )
}

internal val oAuth2ActivityD0Fingerprint = fingerprint {
    strings("redditisfun://auth")
    custom { method, classDef ->
        if (!classDef.endsWith("OAuth2Activity${'$'}b;")) return@custom false

        method.name == "d0"
    }
}

internal val oAuth2ActivityShouldOverrideUrlLoadingFingerprint = fingerprint {
    strings("redditisfun://auth")
    custom { method, classDef ->
        if (!classDef.endsWith("OAuth2Activity${'$'}a;")) return@custom false

        method.name == "shouldOverrideUrlLoading"
    }
}

internal val cActivityJFingerprint = fingerprint {
    strings("redditisfun://auth")
    custom { method, classDef ->
        if (!classDef.endsWith("c;")) return@custom false

        method.name == "j"
    }
}
