package com.zrj.vodtest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @ClassName: TestVod
 * @Description: 根据视频id获取视频地址
 * @Author Ruijin Zhou
 * @Date 2022/7/27
 * @Version 1.0
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tBiVjQ51oSTW2iDXgay", "bms7ID8PUZtTtIbZt3ef1sZGNm3WZJ");
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            response = getVideoPlayAuth(client);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");

    }

    //获取视频凭证
    /*获取播放凭证函数*/
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("35b83a3fe4f04949b2c9b7c92f623b42");
        return client.getAcsResponse(request);
    }

    public static void getVideoURL() throws Exception {
        //1.根据视频id获取视频播放地址
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tBiVjQ51oSTW2iDXgay", "bms7ID8PUZtTtIbZt3ef1sZGNm3WZJ");
        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象里面设置视频id
        request.setVideoId("35b83a3fe4f04949b2c9b7c92f623b42");
        //调用初始化对象方法，传递request，获取数据
        response = client.getAcsResponse(request);
        //输出请求结果
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
