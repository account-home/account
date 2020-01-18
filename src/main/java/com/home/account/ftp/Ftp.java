package  com.home.account.ftp;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.home.account.config.FtpConfig;
import com.home.account.util.CharSetName;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



/**
 * @Description: FTP服务器上传下载文件工具类
 * @Author: ruanyanghui
 * @Date: 2019/8/27 15:53
 */
@Component
public class Ftp {

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 本地要上传的文件的 输入流
     * @return 成功返回true，否则返回false
     */

    @Autowired
    private FtpConfig ftpConfig;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");


    /**
     * 上传web网络文件
     *
     * @param file 网络文件
     * @return 存在ftp下的文件路径+名称
     * @throws FileNotFoundException
     */
    public String uploadFileServer(MultipartFile file) throws IOException {
        //自己 创建一个文件 即可以再本地搞一个文件
        InputStream inputStream = file.getInputStream();//获取前端文件的输入流
        String fileName = file.getOriginalFilename();//获取文件名称
        String path = simpleDateFormat.format(new Date());
        //此时，为例避免文件名重复，一般会生成一个文件名，并存到数据库，返回ID，后面以此Id来取路径
        //此处暂时不这样做

        //创建一个客户端
        FTPClient ftpClient = null;
        try {
            //获取已经连接的客户端
            ftpClient = this.getFtpClient();
            //创建文件夹
            this.CreateDirecroty(ftpClient, path);
            //上传的开始时间
            long start = System.currentTimeMillis();
            //正式上传文件，如果文件名有中文，需要转码
            boolean fig = ftpClient.storeFile(fileName, inputStream);
            if (fig) {
                long transformTime = System.currentTimeMillis() - start;
                System.out.println(String.format("文件:%s上传成功,耗时：%d毫秒", "fileNewName", transformTime));
            } else {
                System.out.println("上传失败");
                return "";
            }
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("保存图片到文件服务器ERROR:" + io.getMessage());
            return "";
        } finally {
            //关闭资源
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                //退出
                ftpClient.logout();
                //断开连接
                ftpClient.disconnect();
            } catch (IOException io) {
                System.out.println("保存图片到文件服务器关闭资源ERROR" + io.getMessage());
            }
        }
        return "/cs/" + "fileName";
    }

    /**
     * ftp上传文件
     *
     * @param inputStream 文件输入流
     * @param fileName    文件名称
     * @return String 上传结果
     */
    public String uploadFile(InputStream inputStream, String fileName) {
        String basePath = simpleDateFormat.format(new Date());
        //创建一个客户端
        FTPClient ftpClient = null;
        //1.获取一个已连接的ftp客户顿 2.这是切换文件夹 3.转码后可以文件名可以为中文,也可以不转码，建议使用新的文件名  utf-8会乱码
        try {
            ftpClient = this.getFtpClient();
            CreateDirecroty(ftpClient, basePath);
            long start = System.currentTimeMillis();
            boolean fig = ftpClient.storeFile(new String(fileName.getBytes(CharSetName.GBK), CharSetName.ISO), inputStream);
            //正式上传文件
            if (fig) {
                long transformTime = System.currentTimeMillis() - start;
                System.out.println(fileName + " | 本次上传所耗费时间：" + transformTime + "ms");
            } else {
                System.out.println("上传失败");
                return "";
            }
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("保存图片到文件服务器ERROR:" + io.getMessage());
            return "";
        } finally {
            //关闭资源
            this.closeFtpClient(ftpClient);
        }
        return basePath + fileName;
    }

    /**
     * ftp下载文件
     *
     * @param outputStream 文件输出流
     * @param fileName     文件名称
     * @return String 上传结果
     */
    public OutputStream downFile(OutputStream outputStream, String fileName, String path) {
        String basePath = simpleDateFormat.format(new Date());
        //创建一个客户端
        FTPClient ftpClient = null;
        try {
            ftpClient = this.getFtpClient();
            Boolean b = ftpClient.changeWorkingDirectory(path);
            if (!b) {
                return null;
            }
            FTPFile[] ftpFiles = ftpClient.listFiles(fileName);
            for (FTPFile ftpfile : ftpFiles) {
                //存在乱码问题，解决方式，放在数据库里面，然后去取ftp的路径和文件名
                String ftpfileName = ftpfile.getName();
                if (ftpfileName.equals(fileName)) {
                    boolean bool = ftpClient.retrieveFile(new String(fileName.getBytes(CharSetName.GBK), CharSetName.ISO), outputStream);
                    break;
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            //关闭资源
            this.closeFtpClient(ftpClient);
            return outputStream;
        }

    }


    /**
     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
     *
     * @param ftpClient ftpClient
     * @param remote    paths
     * @throws IOException
     */
    public void CreateDirecroty(FTPClient ftpClient, String remote) throws IOException {
        //3步走，1.将remote切割开来 成为数组， 2将ftpClient切换到目录下，然后再到该目录下创建文件夹
        if (ftpClient.changeWorkingDirectory(remote)) {
            return;
        }
        String[] paths = remote.split("/");
        for (String path : paths) {
            //创建目录，创建成功说明没有，创建失败说明已经存在
            ftpClient.makeDirectory(path);
            //切换到目录文件夹
            ftpClient.changeWorkingDirectory(path);
        }
    }

    //这只是个工具类
    //改进，1.单独连接ftp方法 2. 上传下载方法，3.单独关闭ftp连接

    /**
     * 工具类，根据配置文件获取ftpClient
     *
     * @return 返回一个ftpClient
     * @throws IOException
     */
    public FTPClient getFtpClient() throws IOException {
        FTPClient ftpClient = new FTPClient();
        // 连接
        ftpClient.connect(ftpConfig.getHost());// 连接FTP服务器
        ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword());// 登陆FTP服务器
        ftpClient.setControlEncoding("GBK"); // 中文支持
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); //二进制
        ftpClient.enterLocalPassiveMode();  // 被动模式
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.disconnect();
            throw new RuntimeException("未连接到FTP，用户名或密码错误。");
        }
        return ftpClient;
    }

    /**
     * 关闭ftpClient端
     *
     * @param ftpClient ftpClient
     */
    public void closeFtpClient(FTPClient ftpClient) {
        try {
            if (!ftpClient.isConnected())
                ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
