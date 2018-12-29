/*
 * All rights Reserved, Copyright (C) Aisino LIMITED 2018
 * FileName: ZipUtil.java
 * Version:  $Revision$
 * Modify record:
 * NO. |     Date       |    Name         |      Content
 * 1   | 2018年12月28日        | Aisino)Jack    | original version
 */
package com.aisino.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import com.aisino.common.ErrCode;

/**
 * class name:ZipUtil <BR>
 * class description: 打包文件夹以及子文件夹为zip包 <BR>
 * Remark: <BR>
 * @version 1.00 2018年12月28日
 * @author Aisino)weihaohao
 */
public class ZipUtil {
	//打印日志
    private static Logger logger = Logger.getLogger(ZipUtil.class);
    private ZipUtil() {
    } 
    
    /** 
     * 创建ZIP文件 
     * @param sourcePath 文件或文件夹路径 
     * @param zipPath 生成的zip文件存在路径（包括文件名） 
     * @param isDrop  是否删除原文件:true删除、false不删除
     */  
    public static void createZip(String sourcePath, String zipPath,Boolean isDrop) {  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;
        File file =new File(sourcePath);
        try {  
            fos = new FileOutputStream(zipPath);  
            zos = new ZipOutputStream(fos);  
            writeZip(file, "", zos,isDrop);  
        } catch (FileNotFoundException e) {  
            logger.error("[创建ZIP文件失败]---没有找到文件!");  
        } finally {  
            try {  
                if (zos != null) {  
                    zos.close();  
                } 
                if(fos != null) {
                	fos.close();
                }
            } catch (IOException e) {  
                logger.error("[创建ZIP文件失败]---文件关闭错误!");  
            }  
        }  
       if(isDrop){//是否删除原文件夹下所有的内容
            clean(file);
            if(!file.exists()) {//删除完成之后,要新建原始文件夹
            	file.mkdir();
            }
       }
       logger.info("压缩包创建成功:)--->"+zipPath);
    }  
    /**
     * 清空文件和文件目录
     * @param f 文件
     */
    public static void clean(File f) {
        String cs[] = f.list();
        if (cs == null || cs.length <= 0) {
            if (!f.delete()) {
            	logger.error("!文件删除失败！[ "+f.getAbsoluteFile()+" ]");
            }else {
            	logger.info("删除了:[ "+f.getAbsoluteFile()+" ]");            	
            }
        } else {
            for (int i = 0; i < cs.length; i++) {
                String cn = cs[i];
                String cp = f.getPath() + File.separator + cn;
                File f2 = new File(cp);
                if (f2.exists() && f2.isFile()) {
                    if (!f2.delete()) {
                        logger.error("!文件删除失败！[ "+f2.getAbsoluteFile()+" ]");
                    }else {
                    	logger.info("删除了:[ "+f2.getAbsoluteFile()+" ]");                    	
                    }
                } else if (f2.exists() && f2.isDirectory()) {
                    clean(f2);
                }
            }
            if (!f.delete()) {
            	logger.error("!文件删除失败！[ "+f.getAbsoluteFile()+"]");
            }else {
            	logger.info("删除了:[ "+f.getAbsoluteFile()+" ]");
            }
        }
    }
    
    /**
     * Method name: writeZip <BR>
     * Description: writeZip <BR>
     * Remark: <BR>
     * @param file 待压缩的文件夹
     * @param parentPath 父文件夹
     * @param zos zip输出的文件
     * @param isDrop 是否删除源文件
     *   void<BR>
     */
    private static void writeZip(File file, String parentPath, ZipOutputStream zos,Boolean isDrop) {  
        if(file.exists()){  
            if(file.isDirectory()){//处理文件夹  
                parentPath+=file.getName()+File.separator;  
                File [] files=file.listFiles();  
                if(files.length != 0){  
                    for(File f:files){  
                        writeZip(f, parentPath, zos,isDrop);  
                    }  
                }else{//空目录则创建当前目录  
                    try {  
                        zos.putNextEntry(new ZipEntry(parentPath));  
                    } catch (IOException e) {  
                    	logger.error(ErrCode.SYSTEM_ZIP_FAILURE.getMsg(),e);  
                    }  
                }  
            }else{  
                FileInputStream fis=null;  
                try {  
                    fis=new FileInputStream(file);  
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());  
                    zos.putNextEntry(ze);  
                    byte [] content=new byte[1024];  
                    int len;  
                    while((len=fis.read(content))!=-1){  
                        zos.write(content,0,len);  
                        zos.flush();  
                    }  
                }catch (IOException e) {  
                    logger.error(ErrCode.SYSTEM_ZIP_FAILURE.getMsg(),e);  
                }finally{  
                    try {  
                        if(fis!=null){  
                            fis.close();  
                        }  
                    }catch (Exception e) {
                    	logger.error(ErrCode.SYSTEM_ZIP_FAILURE.getMsg(),e);  
                    }  
                }  
            }  
        }  
    }    
    
    /**
     * Method name: deleteZip <BR>
     * Description: 根据文件名删除文件 <BR>
     * Remark: <BR>
     * @param file  void<BR>
     */
    public static void deleteZip(File file) {
    	if(file.isFile()) {
    		file.delete();
    		logger.info("删除zip文件成功:)--->"+file.getAbsolutePath());
    	}else {
    		logger.error("删除zip文件失败:(--->"+file.getAbsolutePath());
    	}
    }
}
