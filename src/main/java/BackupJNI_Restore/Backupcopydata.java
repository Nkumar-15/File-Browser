package BackupJNI_Restore;

public class Backupcopydata {
    static {
        System.loadLibrary("backupcopy");
    }
    public native void backupcopydata(String zipname,String filetobezipped);
}