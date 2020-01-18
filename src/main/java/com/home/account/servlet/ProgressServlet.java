package  com.home.account.servlet;
import com.alibaba.fastjson.JSONObject;
import com.home.account.entity.ProgressSingleton;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProgressServlet")
public class ProgressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProgressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        String id = request.getSession().getId();
        String filename = request.getParameter("filename");
        //使用sessionid + 文件名生成文件号，与上传的文件保持一致
        id = id + filename;
        Object size = ProgressSingleton.get(id + "Size");
        size = size == null ? 100 : size;
        Object progress = ProgressSingleton.get(id + "Progress");
        progress = progress == null ? 0 : progress;
        JSONObject json = new JSONObject();
        json.put("size", size);
        json.put("progress", progress);
        response.getWriter().print(json.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}