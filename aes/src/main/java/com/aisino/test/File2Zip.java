/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: File2Zip.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月28日        | Aisino)Jack    | original version
 */
package com.aisino.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.aisino.service.impl.Excel2DBImpl;

/**
 * class name:File2Zip <BR>
 * class description: 多个文件打包成Zip <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月28日
 * @author Aisino)weihaohao
 */
public class File2Zip {
	//打印日志
	private static Logger logger = Logger.getLogger(Excel2DBImpl.class);
		
	/**
	 * Method name: file2Zip <BR>
	 * Description: 将文件下的所有文件及文件夹压缩成zip文件 <BR>
	 * Remark: <BR>
	 * @param sourceFilePath
	 * @param zipFilePath
	 * @param fileName
	 * @return  boolean<BR>
	 */
	public static boolean file2Zip(String sourceFilePath,String zipFilePath,String fileName){  
        boolean flag = false;//压缩成不成功
        File sourceFile = new File(sourceFilePath);//待压缩的文件夹  
        FileInputStream fis = null;
        BufferedInputStream bis = null;  
        FileOutputStream fos = null;
        ZipOutputStream zos = null;  
          
        if(sourceFile.exists() == false){  
        	logger.error("待压缩的文件目录："+sourceFilePath+"不存在!");  
        }else{  
            try {  
                File zipFile = new File(zipFilePath + "/" + fileName +".zip");  
                if(zipFile.exists()){  
                	logger.error(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件!!");  
                }else{  
                    File[] sourceFiles = sourceFile.listFiles();  
                    if(null == sourceFiles || sourceFiles.length<1){  
                    	logger.error("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩!!!");  
                    }else{  
                        fos = new FileOutputStream(zipFile);  
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
                        byte[] bufs = new byte[1024*10];  
                        for(int i=0;i<sourceFiles.length;i++){  
                            //创建ZIP实体，并添加进压缩包  
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
                            zos.putNextEntry(zipEntry);  
                            //读取待压缩的文件并写进压缩包里  
                            fis = new FileInputStream(sourceFiles[i]);  
                            bis = new BufferedInputStream(fis, 1024*10);  
                            int read = 0;  
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
                                zos.write(bufs,0,read);  
                            }  
                        }  
                        flag = true;  
                    }  
                }  
            } catch (FileNotFoundException e) {  
                logger.error("压缩文件不存在!!"); 
                logger.error(e);
            } catch (IOException e) {  
            	logger.error("压缩文件出现异常!!");
            	logger.error(e);
            } finally{  
                //关闭流  
                try {  
                    if(null != bis) bis.close();  
                    if(null != zos) zos.close();  
                } catch (IOException e) {
                	logger.error("压缩文件出现异常!!");
                	logger.error(e); 
                }  
            }  
        }  
        return flag;  
	}  
	 
	 @Test
	 public void test2() {
		String sourceFilePath = "D:\\tmp\\logs\\aes";
        String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf('\\'));  
        boolean flag = file2Zip(sourceFilePath, sourceFilePath, fileName);  
        if(flag){  
            System.out.println(fileName+"文件打包成功!");  
        }else{  
            System.out.println(fileName+"文件打包失败!");  
        }  
	 }
}
