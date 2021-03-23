package io.github.talelin.latticy.extension.file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import io.github.talelin.latticy.module.file.AbstractUploader;
import io.github.talelin.latticy.module.file.FileConstant;
import io.github.talelin.latticy.module.file.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;

/**
 * 文件上传服务实现，上传到阿里云
 *
 * @author pedro@TaleLin
 */
@Slf4j
@Component
public class AliyunUploader extends AbstractUploader {

    @Autowired
    private FileProperties fileProperties;

    @Value("${lin.file.aliyun.access-key-id}")
    private String accessKeyId;

    @Value("${lin.file.aliyun.access-key-secret}")
    private String accessKeySecret;

    private OSS ossClient;

    String bucketName;

    /**
     * 因为需要得到 spring-boot 提供的配置，所以不能在 constructor 中初始化
     * 使用 PostConstruct 来初始化
     */
    @PostConstruct
    public void initUploadManager() {
        String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
        bucketName = "b2b-anton";
        ossClient = new OSSClientBuilder().build(endpoint, this.accessKeyId, this.accessKeySecret);
    }

    @Override
    protected FileProperties getFileProperties() {
        return fileProperties;
    }

    @Override
    protected String getStorePath(String newFilename) {
        return fileProperties.getDomain() + newFilename;
    }

    @Override
    protected String getFileType() {
        return FileConstant.REMOTE;
    }

    /**
     * 处理一个文件数据
     *
     * @param bytes       文件数据，比特流
     * @param newFilename 新文件名称
     * @return 处理是否成功，如果出现异常则返回 false，避免把失败的写入数据库
     */
    @Override
    protected boolean handleOneFile(byte[] bytes, String newFilename) {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, newFilename, byteInputStream);
            log.info(putObjectResult.toString());
            return !StringUtils.isEmpty(putObjectResult.getETag());
        } catch (Exception ex) {
            String message = ex.getMessage();
            log.error("aliyun upload file error: {}", message);
            return false;
        }
    }
}
