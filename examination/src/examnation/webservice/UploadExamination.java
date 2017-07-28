package examnation.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystemException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.stereotype.Component;

@Component
@Path("/file")
public class UploadExamination {
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	@Produces("text/plain;charset=utf-8")
	public JSONObject upload(MultipartFormDataInput file) throws Exception{
		JSONObject obj = new JSONObject();
		String msg = "";
		if(file != null){
			//组合文件名称,";隔开"
			try{
				String path = getClass().getResource("/").getFile().toString();
				//当前目录路径  
//			       String currentPath1=getClass().getResource(".").getFile().toString();  
//			       String currentPath2=getClass().getResource("").getFile().toString();  
			       //当前目录的上级目录路径  
//			       String parentPath=getClass().getResource("../").getFile().toString(); 
				//上传文件，随机名称，";"分号隔开
                uploadImage(path+"../../img/", file);
				//上传成功
                obj.put("msg", msg);
					return obj;
			}catch(Exception e){
				e.printStackTrace();
				return obj;
			}
		}else{
			return obj;
		}
	}
	
	public static void inputtofile(InputStream in,File f){
		try{
			OutputStream out = new FileOutputStream(f);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while((bytesRead= in.read(buffer, 0, 8192))!=-1){
				out.write(buffer,0,bytesRead);
			}
			out.close();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String uploadImage(String path,MultipartFormDataInput file){
		try{
//			String filename = "111.png";
			Map<String, List<InputPart>> uploadForm = file.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("txt_file");
			for (InputPart inputPart : inputParts) {
				MultivaluedMap<String, String> multi = inputPart.getHeaders();
				String name = multi.getFirst("Content-Disposition");
				String filename = name.split(";")[2].split("=")[1].replace("\"", "");
				InputStream in = inputPart.getBody(InputStream.class,null);
				File f = new File(path+filename);
				inputtofile(in, f);
			}
			
//			String[] typeImg = {"gif","png","jpg"};
//			if(file != null){
//				String originName = file.getPreamble();
//				System.out.println("原文件名为："+originName);
//				//判断文件类型
//				String type = originName.indexOf(".")!=-1?originName.substring(originName.lastIndexOf(".")+1,originName.length()):null;
//				if(type != null){
//					Boolean booIsType = false;
//					for (int i = 0; i < typeImg.length; i++) {
//						if(typeImg[i].equals(type.toLowerCase())){
//							booIsType = true;
//						}
//					}
//					if(booIsType){
//						//存放文件路径
//						File targetFile = new File(path,originName);
//						if(!targetFile.exists()){
//							targetFile.mkdirs();//创建目录
//						}
//						try{
//							upload(targetFile);
//						}catch(Exception e){
//							e.printStackTrace();
//						}
//						
//					}
//				}
//				
//				
//			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public static void upload(File file) throws FileSystemException {
		FileObject localFile = null;
		StandardFileSystemManager manager = new StandardFileSystemManager();
		try {
			manager.init();
			FileSystemOptions opts = new FileSystemOptions();
			SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
					opts, "no");
			SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts,
					true);
			SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);
			localFile = manager.resolveFile(file.getAbsolutePath());
			String fileName = file.getName();
			FileObject remoteFile = null;
			String sftpUrl = "";
//			for (String sftpUrl : sftpUrls) {
				remoteFile = manager.resolveFile(sftpUrl + fileName, opts);
				if (!remoteFile.exists()) {
//					logger.info("执行上传操作...");
					remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
				}
//			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			manager.close();
		}
	}
	
	
	public String timeFormat(){
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
}
