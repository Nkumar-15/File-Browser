package BackupJNI_Restore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BackupJNI {
    static {
        System.loadLibrary("backup");
    }


    public native void toFind(String dir);
//    public native void backupcopydata(String dir);
//    public native void restorecopydata(String fname,String dir);

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
        //sqlinsert();

    }

    public String Fileupload(String fname)
    {
        String ppath=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");

            PreparedStatement stmt=conn.prepareStatement("select * from filedata where Filename=?");
            stmt.setString(1,fname);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                PreparedStatement stmt1=conn.prepareStatement("insert into backupdata values(?,?,?,?)");
                stmt1.setString(1, rs.getString(1));
                stmt1.setString(2, rs.getString(2));
                stmt1.setInt(3, rs.getInt(3));
                stmt1.setInt(4, rs.getInt(4));
                stmt1.executeUpdate();
                ppath=rs.getString(2)+rs.getString(1);
                System.out.println("Hello from fileupload");

            }
//            PreparedStatement stmt2=conn.prepareStatement("DELETE from filedata where Filename=?");
//            stmt2.setString(1, fname);
//            stmt2.executeUpdate();
            conn.close();

            return ppath;

            //out.println("Thank you for register ! Please <a href='index.html'>Login</a> to continue.");


        }
        catch(Exception e)
        {
            System.out.print(e);
            e.printStackTrace();
        }

        return ppath;
    }

    public void backupDataCopyFileupload(String filename,String parentPath,String destPath,String pass)
    {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");

            PreparedStatement stmt=conn.prepareStatement("select * from filedata where Filename=? and Parentpath=?");
            stmt.setString(1,filename);
            stmt.setString(2,parentPath);

            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                PreparedStatement stmt1=conn.prepareStatement("insert into backupdata values(?,?,?,?,?,?)");
                stmt1.setString(1, rs.getString(1));
                stmt1.setString(2, rs.getString(2));
                stmt1.setInt(3, rs.getInt(3));
                stmt1.setInt(4, rs.getInt(4));
                stmt1.setString(5, destPath);
                stmt1.setString(6, pass);
                stmt1.executeUpdate();

                System.out.println("Hello from backupdatasubfileupload");

            }

            conn.close();



            //out.println("Thank you for register ! Please <a href='index.html'>Login</a> to continue.");


        }
        catch(Exception e)
        {
            System.out.print(e);
            e.printStackTrace();
        }

    }

    public boolean passCheck(String filename,String parentPath,String destPath,String pass)
    {
        boolean flag=false;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/filebrowser", "root", "toor");

            PreparedStatement stmt=conn.prepareStatement("select * from backupdata where Filename=? and Parentpath=? and Destinationpath=? and Password=?");
            stmt.setString(1,filename);
            stmt.setString(2,parentPath);
            stmt.setString(3, destPath);
            stmt.setString(4, pass);

            ResultSet rs=stmt.executeQuery();
            flag=rs.next();

            conn.close();
            return flag;


            //out.println("Thank you for register ! Please <a href='index.html'>Login</a> to continue.");


        }
        catch(Exception e)
        {
            System.out.print(e);
            e.printStackTrace();
        }
        return flag;

    }






}
