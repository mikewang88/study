package com;


import okhttp3.*;

import java.io.IOException;

/**
 * @Author: MikeWang
 * @Date: 2020/11/26 6:02 PM
 * @Description:
 */
public class Dataupload {

    public static void main(String[] args) {
        Dataupload dataupload = new Dataupload();
        dataupload.marketScore();
    }

    //短信模板上传
    public void smsTempLoad(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "[\n    {\n      \"templateId\": \"33333\",\n      \"templateContent\": \"{{familyName}}{{sex}}你好，剩余额度为:{{loanLeftBehindAmount}}\"\n    }\n  ]");
        Request request = new Request.Builder()
                .url("https://imgservice-hz.tongdun.cn/xh/sms_template/upload/v1")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-access-key", "demo") //同盾分配
                .addHeader("x-secret-key", "0ad911d5faee48ad8c36cefb570ba1fb")//同盾分配
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //评分及营销
    public void marketScore(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"batchId\": \"333\",\n  \"taskId\": \"111\",\n  \"productCode\": \"01\",\n  \"businessType\": \"scoreMarket\",\n  \"sms\": {\n    \"smsTemplateId\": \"33333\",\n    \"mobiles\": [\n      {\n        \"mobile\": \"4f0d26aa66b0386a359c830c48a17bc1\",\n        \"smsTemplateVariables\": \"{\\\"age\\\":23,\\\"name\\\":\\\"zengx\\\"}\"\n      },\n      {\n        \"mobile\": \"4f0d26aa66b0386a359c830c48a17bc2\",\n        \"smsTemplateVariables\": \"{\\\"age\\\":24,\\\"name\\\":\\\"zengx\\\"}\"\n      }\n    ]\n  },\n  \"ai\": {\n    \"mobiles\": [\n      {\n        \"mobile\": \"4f0d26aa66b0386a359c830c48a17bc3\",\n        \"aiTemplateVariables\": \"{\\\"age\\\":23,\\\"name\\\":\\\"zengx\\\"}\"\n      },\n      {\n        \"mobile\": \"4f0d26aa66b0386a359c830c48a17bc4\",\n        \"aiTemplateVariables\": \"{\\\"age\\\":24,\\\"name\\\":\\\"zengx\\\"}\"\n      }\n    ]\n  }\n}");
        Request request = new Request.Builder()
                .url("https://imgservice-hz.tongdun.cn/xh/market_score/v1")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-access-key", "demo") //同盾分配
                .addHeader("x-secret-key", "0ad911d5faee48ad8c36cefb570ba1fb")//同盾分配
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.toString());
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
