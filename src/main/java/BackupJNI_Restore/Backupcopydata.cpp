#include <jni.h>
#include "BackupJNI_Restore_Backupcopydata.h"
#include <iostream>
#include <windows.h>
#include <shellapi.h>


using namespace std;

JNIEXPORT void Java_BackupJNI_1Restore_Backupcopydata_backupcopydata(JNIEnv *env, jobject thisObj,jstring zipname,jstring filetobezipped) {

    const char * zname =env->GetStringUTFChars(zipname, NULL);// Converting from jstring to string
    const char * filezip =env->GetStringUTFChars(filetobezipped, NULL);

    string zip=zname;

    string fz=filezip;
    string cmdzip="7z a -tzip \""+zip+"\" \""+fz+"\"";
    system(cmdzip.c_str());

//    SHFILEOPSTRUCTA sf;
//    int result;
//    sf.pFrom = src;
//    sf.pTo = dest;
//    sf.wFunc = FO_MOVE;
//    sf.fFlags = FOF_NOCONFIRMATION | FOF_NOCONFIRMMKDIR | FOF_SILENT;
//    result = SHFileOperationA(&sf);
//    cout<<"\nresult="<<result;




   return;
}