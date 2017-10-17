package com.webproject.sppj.utils;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSClientUtils {
	
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageClient storageClient = null;
	private StorageServer storageServer = null;
	
	/**
	 * tracker.conf
	 * @param trackerConfigPath
	 * @throws MyException 
	 * @throws IOException 
	 */
	public FastDFSClientUtils() {
		//2.动态加载当前Web工程的类路径绝对物理地址
		String projectPath = System.getProperty("user.dir");
		
		//4.初始化
		try {
			ClientGlobal.init(projectPath+"/src/main/resources/properties/traker.conf");
			
			//5.创建trackerClient对象
			trackerClient = new TrackerClient();
			
			//6.创建trackerServer对象
			trackerServer = trackerClient.getConnection();
			
			//7.创建storageClient对象
			storageClient = new StorageClient(trackerServer, storageServer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 计算文件扩展名
	 * @param fileName
	 * @return
	 */
	public String getExtName(String fileName) {
		
		if(fileName != null && fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		
		return fileName;
	}
	
	public String[] uploadFile(byte[] file_buff, String fileName) {
		
		//1.计算文件扩展名
		String file_ext_name = getExtName(fileName);
		
		String[] resultArray = null;
		
		try {
			//2.基于字节数组实现文件上传.第三个参数是文件的元数据,即属性中的详细信息
			resultArray = storageClient.upload_file(file_buff, file_ext_name, null);
		} catch (Exception e) {
			e.printStackTrace();
			//如果上传失败，抛出自定义异常
			throw new RuntimeException("文件上传失败！");
		}
		
		//3.返回上传结果
		return resultArray;
	}
	

}
