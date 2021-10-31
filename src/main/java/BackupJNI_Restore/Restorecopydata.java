package BackupJNI_Restore;

public class Restorecopydata {
    static {
        System.loadLibrary("restorecopy");
    }
    public native void restorecopydata(String zipname,String destpath);
}
