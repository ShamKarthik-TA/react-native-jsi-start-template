#include <jni.h>
#include "JSIStartHostObject.h"
#include "helpers.cpp"

extern "C"
JNIEXPORT void JNICALL
Java_com_margelo_jsistart_JSIStartModule_nativeInstall(JNIEnv *env, jobject thiz, jlong jsi) {
    auto runtime = reinterpret_cast<Runtime *>(jsi);

    if (runtime) {
        margelo::install(*runtime);
    }
}
