package com.example.assignment_sof3011_caondph20015.util;

import com.example.assignment_sof3011_caondph20015.infrastructure.contanst.Contanst;
import com.example.assignment_sof3011_caondph20015.viewmodel.request.AccountRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

/**
 * @author caodinh
 */
public class GoogleUtils {

    public static String getToken(final String code) throws ClientProtocolException, IOException, IOException {
        String response = Request.Post(Contanst.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Contanst.GOOGLE_CLIENT_ID)
                        .add("client_secret", Contanst.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Contanst.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Contanst.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static AccountRequest getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Contanst.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        AccountRequest accountRequest = new Gson().fromJson(response, AccountRequest.class);
        return accountRequest;
    }

}
