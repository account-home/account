package  com.home.account.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.home.account.entity.ProgressSingleton;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UploadServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4*1024);

        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> fileItems;
        try {
            fileItems = upload.parseRequest(new ServletRequestContext(request));
            //获取文件域
            FileItem fileItem = fileItems.get(0);
            //使用sessionid + 文件名生成文件号
            String id = request.getSession().getId() + fileItem.getName();
            //向单例哈希表写入文件长度和初始进度
            ProgressSingleton.put(id + "Size", fileItem.getSize());
            //文件进度长度
            long progress = 0;
            //用流的方式读取文件，以便可以实时的获取进度
            InputStream in = fileItem.getInputStream();
            File file = new File("D:/test");
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int readNumber = 0;
            while((readNumber = in.read(buffer)) != -1){
                //每读取一次，更新一次进度大小
                progress = progress + readNumber;
                //向单例哈希表写入进度
                ProgressSingleton.put(id + "Progress", progress);
                out.write(buffer);
            }
            //当文件上传完成之后，从单例中移除此次上传的状态信息
            ProgressSingleton.remove(id + "Size");
            ProgressSingleton.remove(id + "Progress");
            in.close();
            out.close();
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        response.getWriter().print("done");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
