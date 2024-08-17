#include <jni.h>
#include <string>

extern "C" {
JNIEXPORT jstring
JNICALL
Java_com_salem_moviesapp_data_di_NetworkModule_getEncryptedBaseUrl(JNIEnv *env, jobject object) {
    std::string encrypted_base_url = "https://api.themoviedb.org/3/";
    return env->NewStringUTF(encrypted_base_url.c_str());
  }

JNIEXPORT jstring JNICALL
Java_com_salem_moviesapp_data_di_NetworkModule_getEncryptedToken(JNIEnv *env, jobject object) {
    std::string getEncryptedToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMjFiNDMzYzU0YTQyOGQyNDkyMzA0N2M0YmYwMWZlNSIsIm5iZiI6MTcyMzc1MTY0Mi44MDM1OTQsInN1YiI6IjY2YmU1YmM0YmFjMjNmMGMzZjgyOGVkOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.fOPPMReFnWeQh9Xnqjj-zEkOjDvWn1g35Kt-zSHCSt4";
    return env->NewStringUTF(getEncryptedToken.c_str());
}
}

