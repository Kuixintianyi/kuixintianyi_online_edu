package com.zrj.oss;

import com.aliyun.oss.OSSClient;
import org.junit.Test;

/**
 * @ClassName: OSSTest
 * @Description:
 * @Author Ruijin Zhou
 * @Date 2022/7/20
 * @Version 1.0
 */
public class OSSTest {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "oss-cn-chengdu.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "LTAI5tBiVjQ51oSTW2iDXgay";
    String accessKeySecret = "bms7ID8PUZtTtIbZt3ef1sZGNm3WZJ";
    String bucketName = "ruijin0914";

    @Test
    public void testCreateBucket() {

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
