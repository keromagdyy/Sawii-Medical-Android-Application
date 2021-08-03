package com.example.sawii;

import org.w3c.dom.Text;

import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    //get Advice
    @GET("apis/advice.php")
    public Call<List<PostAdvice>> getPost();

    //post sign up user
    @FormUrlEncoded
    @POST("apis/post_users.php?apicall=signup")
    Call<postSignUp> createSignUpUser(
            @Field("major") int MAjor,
            @Field("username") String Username,
            @Field("user_email") String Email,
            @Field("user_pass") String Pass,
            @Field("user_gender") int Gender,
            @Field("synd_pic") String SyncPic
    );


    //get log in user
    @GET("apis/get_users.php")
    public Call<List<postSignUp>> getloginUser();

    //post Doc Profile
    @FormUrlEncoded
    @POST("apis/doc_profilepost.php?apicall=add")
    Call<postDocProfile> createDocProfile(
            @Field("doc_id") int ID,
            @Field("price") int Price,
            @Field("bio") String Bio,
            @Field("country") String City,
            @Field("specialties") String Spec,
            @Field("year_of_ex") int YOEX
    );

    //get doctor profile
    @GET("apis/doc_profile_get.php")
    public Call<List<postDocProfile>> getDocProfile();


    //get posts
    @GET("apis/postsget.php")
    public Call<List<getPosts>> getPosts();


    //post posts
    @FormUrlEncoded
    @POST("apis/post_posts.php?apicall=addpost")
    Call<getPosts> createPost(
            @Field("pat_id") int Id,
            @Field("post_date_time") String DateTime,
            @Field("post_content") String PostCont
    );


    //get messages
    @GET("apis/chat_message_get.php")
    public Call<List<postMessages>> getMessages();


    //post messages
    @FormUrlEncoded
    @POST("apis/post_message.php?apicall=add")
    Call<postMessages> createMessage(
            @Field("to_user_id") int ToUserId,
            @Field("from_user_id") int FromUserId,
            @Field("chat_message") String Message,
            @Field("timestamp") String DateTime,
            @Field("status") int Statues
    );

}
