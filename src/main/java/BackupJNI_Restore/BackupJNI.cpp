#include <jni.h>       // JNI header provided by JDK
#include <iostream>    // C++ standard IO header
#include "BackupJNI_Restore_BackupJNI.h"  // Generated
#include <Windows.h>
#include <string>
#include <vector>
#include <windows.h>
#include <shellapi.h>
using namespace std;



wstring parent_path,full_path;     //variables to store parent path and the full path of the file/folder
                                    //A value to determine whether the file/folder is present/not
vector<string> v1;                      //To store the name of the files and folders\

vector<string> final;

void Findfold(const wstring &directory);
void calcFileFold(int &countfiles,int &countfolders);

void FindFile(const wstring &directory,JNIEnv *env,jobject thisObj)
{
    wstring tmp = directory + L"\\*";

    parent_path = directory + L"\\*";
    WIN32_FIND_DATAW file;
    HANDLE search_handle = FindFirstFileW(tmp.c_str(), &file);

    if (search_handle != INVALID_HANDLE_VALUE)
    {
        vector<wstring> directories;

        do
        {
            if (file.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)
            {
                if ((!lstrcmpW(file.cFileName, L".")) || (!lstrcmpW(file.cFileName, L"..")))
                    continue;
            }

            tmp = directory + L"\\" + wstring(file.cFileName); //Fullpath
            parent_path = directory + L"\\" ;   //Parent Directory
            wstring ws=file.cFileName;
            string s;
            s.assign(ws.begin(),ws.end());  //FileName
            string p_path;
            p_path.assign(parent_path.begin(),parent_path.end());



//            cout<<"File/Folder Name : "<<s<<endl;
//            cout<<"Parent Path : ";
//            wcout<<parent_path<<endl;

            Findfold(tmp);
            int countfiles = 0, countfolders = 0;
            calcFileFold(countfiles,countfolders);

            //string num1=to_String(countfolders);
            //string num2=to_String(countfiles);
            jclass thisClass = env->GetObjectClass(thisObj);


            jmethodID midCallBackStr = env->GetMethodID(thisClass,"callback", "(Ljava/lang/String;Ljava/lang/String;II)V");             //"(Ljava/lang/String;)V"
            jstring FileName = env->NewStringUTF(s.c_str());
            jstring ParentPath = env->NewStringUTF(p_path.c_str());
            jint countFolder=(jint)countfolders;
            jint countFile=(jint)countfiles;
            //env->CallStaticVoidMethod(cl, mid, js);
            env->CallVoidMethod(thisObj, midCallBackStr, FileName,ParentPath,countFolder,countFile);
            //cout<<out;

            //cout<<"\n";


            //wcout << tmp << endl;
            //wcout<<wstring(file.cFileName)<<endl;
            //wcout<<s;


            if (file.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)   //Here the files path are pushed into the directories vector
                directories.push_back(tmp);


        }
        while (FindNextFileW(search_handle, &file));

        FindClose(search_handle);
        for(vector<wstring>::iterator iter = directories.begin(), end = directories.end(); iter != end; ++iter)
            FindFile(*iter,env,thisObj);



    }
}


void Findfold(const wstring &directory)
{
    wstring tmp = directory + L"\\*";
    WIN32_FIND_DATAW file;
    HANDLE search_handle = FindFirstFileW(tmp.c_str(), &file);
    if (search_handle != INVALID_HANDLE_VALUE)
    {
        vector<wstring> directories;

        do
        {
            if (file.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)
            {
                if ((!lstrcmpW(file.cFileName, L".")) || (!lstrcmpW(file.cFileName, L"..")))
                    continue;
            }

            tmp = directory + L"\\" + wstring(file.cFileName);
            wstring ws=file.cFileName;
            string s;
            s.assign(ws.begin(),ws.end());      //Converting to a string to push in the vector v1
            v1.push_back(s);

            //wcout << tmp << endl;


            if (file.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY)
                directories.push_back(tmp);
        }
        while (FindNextFileW(search_handle, &file));

        FindClose(search_handle);

//            for(vector<wstring>::iterator iter = directories.begin(), end = directories.end(); iter != end; ++iter)
//                Findfold(*iter);
    }
}


void calcFileFold(int &countfiles,int &countfolders) {
    int  files = 0;//Finding no.of.files and no.of.folders
    //cout<<v1.size()<<" ";
    for (int i = 0; i < v1.size(); i++) {
        files = 0;
        for (int j = 0; j < v1[i].size(); j++) {
            if (v1[i][j] == '.') {
                countfiles++;
                files = 1;
                break;
            }
        }
        if (files == 0) {
            countfolders++;
        }

    }
//    cout << "No.of.Folders : " << countfolders << endl;
//    cout << "No.of.Files : " << countfiles << endl;

    v1.clear();
}







// Implementation of the native method toFind()
JNIEXPORT void JNICALL Java_BackupJNI_1Restore_BackupJNI_toFind(JNIEnv *env, jobject thisObj,jstring s) {
	cout << "Hello World from C++!" << endl;
	cout << "Hello World from NK!" << endl;

//	const char * inString =env->GetStringUTFChars(s, NULL); // Converting from jstring to string
//    string search=inString;
//
//    wstring search_dir;
//    search_dir.assign(search.begin(),search.end());
//    //cout<<search_dir;
//
//    FindFile(search_dir,env,thisObj);

   return;
}

