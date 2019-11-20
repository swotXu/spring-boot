package com.testwork.demo.test;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipFile;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.fileupload.util.Streams;

//import xsf.Config;
//import xsf.util.StringHelper;
//import xsf.web.HttpContext;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;

/**
 * 文件处理工具类
 * 
 * @author xuhai
 * @date 2019/8/14
 */
public class FileUtil {
    private final static String SDF = "yyyyMMddHHmmss";
    public static final int UPLOAD_TYPE_WORD = 1;
    public static final int UPLOAD_TYPE_ZIP = 2;
    public static final int UPLOAD_TYPE_PDF = 3;
    public static final int UPLOAD_TYPE_EXCEL = 4;
    private static String templatePath;
    public static Object lock = new Object();
    
    /**
     * 获取当前项目资源路径
     * @return 如：D:\erp\workspaceWEB\CBM\WebContent\
     */
    /*public static String getTemplatePath(){
    	*//*if(StringHelper.isNullOrEmpty(templatePath)){
            synchronized (lock){
            	if (StringHelper.isNullOrEmpty(templatePath)){
            		String t = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            		return templatePath = t.substring(1, t.indexOf("WEB-INF")).replace("/", File.separator);
            	}
            }
    	}
    	return templatePath;*//*
    	return templatePath = Config.APPLICATION_PATH;
    }*/
    /**
     * 获取随机ID
     * @return
     */
    public static String getUUID() {
        SimpleDateFormat df = new SimpleDateFormat(SDF);
        String uid = UUID.randomUUID().toString();
        String date = df.format(new Date());
        return date + uid.split("-")[0];
    }
    /**
     * 删除当前文件
     * @param target
     * @return
     */
    public static boolean delFile(String target) {
        return delFile(new File(target));
    }
    /**
     * 删除当前文件
     * @param target
     * @return
     */
    public static boolean delFile(File target) {
        // 路径是否存在
        if(!target.exists())
            return false;
        //判断是否为文件夹
        if(!target.isDirectory()) {
            return target.delete();
        }
        // 获取所有文件
        File[] files = target.listFiles();
        for (File file : files) {
            // 是文件夹
            if(file.isDirectory()) {
                boolean b = delFile(file);
                if(b) file.delete();
            }else {
                file.delete();
            }
        }
        // 删除当前文件
        return target.delete();
    }
    public static void main(String[] args) {
		List<String> fileDirectory = Arrays.asList("vv/","底稿/");
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Map<String,String> map = new HashMap<String, String>();
		map.put("fileName", "8132009527358701.doc");
		map.put("filePath", "\\\\196.131.1.78\\temp\\personal\\cs\\8132009527358701.doc");
		map.put("fileDirectory", "底稿/");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("fileName", "8132009527564861.doc");
		map.put("filePath", "\\\\196.131.1.78\\temp\\personal\\cs\\8132009527564861.doc");
		map.put("fileDirectory", "底稿/");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("fileName", "8132009527644985.doc");
		map.put("filePath", "\\\\196.131.1.78\\temp\\personal\\cs\\8132009527644985.doc");
		map.put("fileDirectory", "底稿/");
		list.add(map);
		String zip = "C:\\Users\\li.lixing.SSE\\Desktop\\xuhai\\abc.zip";
		creatFile(zip);
		try {
			zipListWriter(fileDirectory, list, new FileOutputStream(new File(zip)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**
     * 文件压缩
     * 将多个文件流输入到压缩文件中
     * @param fileDirectory    压缩包中的文件夹集合  "aa/","wordTarget/"
     * @param list 待压缩文件集合
     * @param zipOut  zip文件流
     * @return
     */
    public static int zipListWriter(List<String> fileDirectory, List<Map<String,String>> list, OutputStream zipOut) {
        int i = 0;
        byte[] buffer = new byte[1024];
        try {
            ZipOutputStream zipout = new ZipOutputStream(zipOut);// 根据已经建立好的zip文件创建zip输出流
            zipout.setEncoding("utf-8");
            // 创建文件夹
            if(fileDirectory != null) {
                for (String directory: fileDirectory) {
                    if(directory != null && !"".equals(directory))
                        zipout.putNextEntry(new ZipEntry(directory));
                }
            }
            // 批量写入文件
            for (Map<String,String> bean : list) {
                String name = bean.get("fileName");    // 文件名     a.doc
                String filePath = bean.get("filePath");    // 文件路径    D:\wordTarget\a.doc
                String directory = bean.get("fileDirectory");  // 所属文件夹   wordTarget/

                File wjfile = new File(filePath);
                if (wjfile.exists()) {
                    InputStream input = new FileInputStream(wjfile);
//             input = EncryptionManger.encryption(input, Encryption.DECRYPT);// 对于加密的文件进行解密
                    // 创建
                    zipout.putNextEntry(new ZipEntry(directory + name));

                    // 写入数据
                    int len;
                    while ((len = input.read(buffer)) > 0) {
                        zipout.write(buffer, 0, len);
                    }
                    zipout.closeEntry();
                    input.close();
                    i++;
                }
            }
            zipout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 生成word
     * @param dataName 和ftl 循环体取名相同
     * @param listData 生成所需数据
     * @param templateFile  不需要带文件名
     * @param targetFile   生成word路径 带文件名
     * @param templateName 模板文件名
     */
    /*public static boolean createTempFile(String dataName,Object listData,String templateFile,String targetFile,String templateName) {
        Writer out = null;
        try {
            Map<String,Object> mapData = new HashMap<String,Object>();

            mapData.put(dataName, listData);
            // 创建文件生成目录
            createParentFilePath(targetFile);
            File file = new File(templateFile);
            File desfile = new File(targetFile);

            out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(desfile), "UTF-8"));
            Template t = null;
            Configuration config = new Configuration();
            config.setDefaultEncoding("UTF-8");
            config.setDirectoryForTemplateLoading(file);

            t = config.getTemplate(templateName,"UTF-8");
            t.process(mapData, out);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (TemplateException e) {
            e.printStackTrace();
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            writerClose(out);
        }
    }*/
    /**
     * word下载
     * @param response
     * @param fileName 文件名 含后缀
     * @param filePath 文件完整路径
     * @param contentType  文件类型  1-WORD  2-ZIP 3-pdf 4-excel
     */
    public static boolean download(HttpServletResponse response, String fileName, String filePath, int contentType) {
        String content = "";
        if(contentType == UPLOAD_TYPE_WORD)
            content = "msword";
        else if(contentType == UPLOAD_TYPE_ZIP)
            content = "zip";
        else if(contentType == UPLOAD_TYPE_PDF)
            content = "pdf";
        else if(contentType == UPLOAD_TYPE_EXCEL)
            content = "vnd.ms-excel";
        else
        	content =  "x-download";

        InputStream fin = null;
        ServletOutputStream out = null;
        try {
            File file = new File(filePath);
            fin = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/" + content + ";charset=UTF-8");
            response.addHeader(
                    "Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO-8859-1"));

            out = response.getOutputStream();

            byte[] buffer = new byte[1024];// 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
            out.flush();
            out.close();
            fin.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 解压目标文件到指定目录
     * @param archive     压缩包文件路径
     * @param decompressDir    解压文件路径
     */
    public static boolean unZip(String archive, String decompressDir) {
        ZipEntry entry = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            File targetFile = new File(decompressDir);
            //判断指定目录，若不存在则创建此目录
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //创建zip文件流
//            ZipFile zipfile = new ZipFile(archive,Charset.forName("GBK"));// 支持中文
            ZipFile zipfile = new ZipFile(archive);// 支持中文
            //所得zip文件条目对象
            Enumeration enumFiles = zipfile.entries();

            while(enumFiles.hasMoreElements()) {
                entry = (ZipEntry) enumFiles.nextElement();
                //判断是否为条目目录
                if (entry.isDirectory()) {
                    continue;
                }
//           long size = entry.getSize();      //文件大小
                String entryName = entry.getName();    //文件名
                File outFile = new File(decompressDir + File.separatorChar + entryName);

                //条目输出流
                os = new BufferedOutputStream(new FileOutputStream(outFile));
                //获取当前条目的输入流
                is = new BufferedInputStream(zipfile.getInputStream(entry));

                int len = 0;
                byte[] b = new byte[1024];
                while ((len = is.read(b, 0, b.length)) != -1) {
                    os.write(b, 0, len);
                }
                is.close();
                os.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(is != null)
                    is.close();
                if(os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件复制
     * @param archive     当前文件路径
     * @param decompressDir    复制文件路径
     * @param copyName    复制文件名
     * @return
     */
    public static boolean fileCopy(String archive , String decompressDir,String copyName) {
        //目标文件
        File inFile = new File(archive);
        //生成文件
        File outFile = new File(decompressDir);
        //判断指定目录，若不存在则创建此目录
        if (!outFile.exists()) {
            outFile.mkdirs();
        }
        OutputStream os = null;
        InputStream is = null;
        try {
            //条目输出流
            os = new BufferedOutputStream(new FileOutputStream(new File(decompressDir + File.separatorChar + copyName)));
            //获取当前条目的输入流
            is = new BufferedInputStream(new FileInputStream(inFile));

            int len = 0;
            byte[] b = new byte[1024];
            while ((len = is.read(b, 0, b.length)) != -1) {
                os.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    /**
     * 创建文件
     * @param filePath 具体的文件路径
     * @return
     */
    public static boolean creatFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()) {
            try {
                file.createNewFile();  // 创建文件
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    
    /**
     * 创建当前指向的文件夹
     * @param filePath 路径指向文件夹
     */
    public static void createFilePath(String filePath){
        File file=new File(filePath);
        if (!file.isFile() && !file.exists()) {
            file.mkdirs();
        }
    }
    /**
     * 创建当前文件的父级文件夹
     * @param filePath 路径指向文件夹
     */
    public static void createParentFilePath(String filePath){
        File file=new File(filePath);
        if (!file.getParentFile().isFile() && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    public static void inputStreamClose(InputStream in){
        if (in != null) {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }
    public static void readerClose(Reader in){
        if (in != null) {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
    }
    public static void outputStreamClose(OutputStream out){
        if (out != null) {
            try {
                out.close();
            } catch (Exception e) {
            }
        }
    }
    public static void writerClose(Writer out){
        if (out != null) {
            try {
                out.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取上传的文件列表
     *
     * @param request
     * @return
     * @throws FileUploadException
     */
    /*private static List<FileItem> getUploadFiles(HttpServletRequest request)
            throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置缓冲区大小，这里是4kb
//        factory.setSizeThreshold(1024 * 1024);
        // 获取缓冲文件路径
        File tempDir = new File(request.getSession().getServletContext()
                .getRealPath("/")
                + "tmp");
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        // 设置缓冲区目录
        factory.setRepository(tempDir);

        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件尺寸
        upload.setSizeMax(4 * 1024 * 1024 * 1024L);

        return upload.parseRequest(request);
    }

    *//**
     * 上传文件
     *
     * @param request
     * @throws FileUploadException 
     *//*
    public static String uploadFile(HttpServletRequest request, UploadProcess process) throws FileUploadException, IOException{
        List<FileItem> items = getUploadFiles(request);
        Iterator<FileItem> i = items.iterator(); // 迭代
        while (i.hasNext()) {
        	FileItem item = i.next();
        	if (!item.isFormField()){
                return process.execute(item);
                
                // 截取后缀
                *//*File fullFile = new File(new String(fileName.getBytes(),"utf-8")); // 解决文件名乱码问题
                // 存储上传文件
                File savedFile = new File("D:\\test", fullFile.getName());
                item.write(savedFile);*//*
            }
        }
        return null;
    }*/
    /**
     * 通过responce输出流写出内容到web
     * @param response
     * @param context
     */
    public static void context2OutWeb(HttpServletResponse response, String context){
    	PrintWriter writer = null;
        try {
        	response.setContentType("text/html;charset=GBK");
        	writer = response.getWriter();
        	writer.println(context);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writerClose(writer);
        }
    }

    /**
     * 获取request中的文件流
     * @param request
     * @return
     */
    public static byte[] requestAsbyteArr(HttpServletRequest request){
        InputStream inputStream = null;
        ByteArrayOutputStream outSteam = null;
        try {
            inputStream = request.getInputStream();
            outSteam = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;

            while((len = inputStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            return outSteam.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            outputStreamClose(outSteam);
            inputStreamClose(inputStream);
        }
        return null;
    }
    
    public static Boolean downLoadUrl2Html(String url,String path, HttpServletRequest request){
    	url = "http://196.131.7.102:8080/CBM/form/405670941313.xform?moduleId=920288&Info_ID=8124068504723625&v=1&target=1&APPLYID=8624007432037565&COMPANY_CODE=600603&timestamp=1566813264476";
//		Object o = request.getAttribute(xsf.SessionHelper.USERINFO_SESSION_KEY);
		String SESSION_ID = request.getSession().getId();
		Long stream = 0L;
		Boolean result = false;
		try {
			URL url1 = new URL(url);
			URLConnection openConnection = url1.openConnection();
//			openConnection.setRequestProperty(xsf.SessionHelper.USERINFO_SESSION_KEY, o.toString());
			openConnection.setRequestProperty("Cookie", "JSESSIONID=" + SESSION_ID);
			openConnection.connect();
			
//			InputStream in = url1.openStream();
//			InputStream in = openConnection.getInputStream();
			DataInputStream in = new DataInputStream(openConnection.getInputStream());
//			System.out.println(new String(getBytes(in)));
			File file = new File(path);
			FileOutputStream out = new FileOutputStream(file);
//			stream =  Streams.copy(in, out, true);
			in.close();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if (0!=stream) {
			result = true;
		}
		return result;
	}
    public static byte[] getBytes(InputStream is) throws Exception {
		byte[] data = null;
		Collection<byte[]> chunks = new ArrayList<byte[]>();
		byte[] buffer = new byte[1024 * 1000];
		int read = -1;
		int size = 0;
		while ((read = is.read(buffer)) != -1) {
			if (read > 0) {
				byte[] chunk = new byte[read];
				System.arraycopy(buffer, 0, chunk, 0, read);
				chunks.add(chunk);
				size += chunk.length;
			}
		}
		if (size > 0) {
			ByteArrayOutputStream bos = null;
			try {
				bos = new ByteArrayOutputStream(size);
				for (Iterator<byte[]> itr = chunks.iterator(); itr.hasNext();) {
					byte[] chunk = itr.next();
					bos.write(chunk);
				}
				data = bos.toByteArray();
			} finally {
				if (bos != null) {
					bos.close();
				}
			}
		}
		return data;
	}

    /**
     * 上传文件接口回调
     */
   public interface UploadProcess{
        /**
         * 上传文件特定操作
         * @param item
         */
//        String execute(FileItem item) throws IOException;
    }
}
