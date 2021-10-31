#include <jni.h>
#include "BackupJNI_Restore_Restorecopydata.h"
#include <iostream>
#include <windows.h>
#include <shellapi.h>

using namespace std;


JNIEXPORT void Java_BackupJNI_1Restore_Restorecopydata_restorecopydata(JNIEnv *env, jobject thisObj,jstring zipname,jstring destpath) {

    const char * zname =env->GetStringUTFChars(zipname, NULL);// Converting from jstring to string
//    const char * hpath =env->GetStringUTFChars(homepath, NULL);
//    const char * src =env->GetStringUTFChars(srctocopy, NULL);
    const char * dest =env->GetStringUTFChars(destpath, NULL);
    string zip=zname;
    string hp=dest;
    string cmdzip="7z x \""+zip+"\" -o\""+hp+"\"";
    system(cmdzip.c_str());


//    SHFILEOPSTRUCTA sf;
//    int result;
//    sf.pFrom = src;
//    sf.pTo = dest;
//    sf.wFunc = FO_MOVE;
//    sf.fFlags = FOF_NOCONFIRMATION | FOF_NOCONFIRMMKDIR | FOF_SILENT;
//    result = SHFileOperationA(&sf);
//    cout<<"\nresult"<<result;



   return;
}