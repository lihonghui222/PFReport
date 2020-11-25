package com.example.excel.controller;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageSendToConversationRequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageSendToConversationResponse;
import com.taobao.api.ApiException;

import java.util.List;

public class Test5 {
    public static void main(String[] args) {
        String accessToken = "";
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest req = new OapiGettokenRequest();
            req.setAppkey("dingesj4ryirw7y5ex27");
            req.setAppsecret("pjKI1JqhTEPPwKineL7C1ZOz45enHCOvkWnhKZwJYG9Z0ng2GMEJSsWDOtH5xSxZ");
            req.setHttpMethod("GET");
            OapiGettokenResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
            accessToken = rsp.getAccessToken();
        } catch (ApiException e) {
            e.printStackTrace();
        }



        try {
            DingTalkClient client4 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/crm/org/get_hidden_corpid");
            OapiDepartmentListRequest req4 = new OapiDepartmentListRequest();
            req4.setHttpMethod("GET");
            OapiDepartmentListResponse rsp4 = client4.execute(req4, accessToken);
            System.out.println(rsp4.getBody());

        } catch (ApiException e) {
            e.printStackTrace();
        }

        try {
            DingTalkClient client4 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/crm/objectdata/contact/query");
            OapiDepartmentListRequest req4 = new OapiDepartmentListRequest();
            req4.setHttpMethod("GET");
            OapiDepartmentListResponse rsp4 = client4.execute(req4, accessToken);
            System.out.println(rsp4.getBody());

        } catch (ApiException e) {
            e.printStackTrace();
        }

        /*try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/message/send_to_conversation");

            OapiMessageSendToConversationRequest req = new OapiMessageSendToConversationRequest();
            req.setSender("01376814877479");
            req.setCid("14ac70d94e79377b88aa5fc75759fe84");
            OapiMessageSendToConversationRequest.Msg msg = new OapiMessageSendToConversationRequest.Msg();

            // 文本消息
            OapiMessageSendToConversationRequest.Text text = new OapiMessageSendToConversationRequest.Text();
            text.setContent("测试测试");
            msg.setText(text);
            msg.setMsgtype("text");
            req.setMsg(msg);
            OapiMessageSendToConversationResponse response = client.execute(req, accessToken);
            System.out.println(response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }*/
    }
}
