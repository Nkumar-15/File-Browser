package FileBrowserDisplay;

import java.util.*;
import java.sql.*;


public class FileBrowserdisplay {
    static {
        System.loadLibrary("FileBrowserdisplay"); // Load native library hello.dll
        // This library contains a native method called toFInd()
    }

    public native void toFind(String dir);

    String Filename,parentPath;
    int countFolder,countFiles,count;
    private void callback(String Filename,String parentPath,int countFolder,int countFiles ) {
        this.Filename=Filename;
        this.parentPath=parentPath;
        this.countFolder=countFolder;
        this.countFiles=countFiles;
//        System.out.println(Filename);
//        System.out.println(parentPath);
//        System.out.println(countFolder);
//        System.out.println(countFiles);
        sqlinsert();

    }

    public void sqlinsert()
    {
        String filename=Filename;
        String ppath=parentPath;
        int foldercount=countFolder;
//        String foldc=String.valueOf(foldercount);
        int filecount=countFiles;
//        String filec=String.valueOf(filecount);

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");
//            Statement st=conn.createStatement();
//            st.executeUpdate("insert into filedata(Filename,Parentpath,Foldercount,Filecount)values('"+filename+"','"+ppath+"','"+foldc+"','"+filec+"')");
            PreparedStatement stmt=conn.prepareStatement("insert into filedata values(?,?,?,?)");
            stmt.setString(1,filename);//1 specifies the first parameter in the query
            stmt.setString(2,ppath);
            stmt.setInt(3,foldercount);
            stmt.setInt(4,filecount);
            stmt.executeUpdate();
            //out.println("Thank you for register ! Please <a href='index.html'>Login</a> to continue.");

            conn.close();
        }
        catch(Exception e)
        {
            System.out.print(e);
            e.printStackTrace();
        }
    }

}



