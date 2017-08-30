package com.example.rishad.democab;

/**
 * Created by rishad on 16/8/17.
 */

public class ProfileDetails {

    int id;
    String name;
    String email;
    String imageUrl;

    public ProfileDetails()
    {

    }

    public ProfileDetails(int id,String name,String email,String imageUrl)
    {
        this.id= id;
        this.name=name;
        this.email=email;
        this.imageUrl=imageUrl;
    }

    public ProfileDetails(String name,String email,String imageUrl)
    {
        this.name=name;
        this.email=email;
        this.imageUrl=imageUrl;

    }

    public int getID()
    {
        return this.id;
    }

    public void setID(int id)
    {
        this.id=id;

    }

    public String getNames()
    {
        return this.name;
    }

    public void setNames(String name)
    {
        this.name=name;
    }

    public String getEmails()
    {
        return this.email;
    }

    public void setEmails(String email)
    {
        this.email=email;
    }

    public String getImageUrl()
    {
        return this.imageUrl;
    }

    public void setImageUrls(String imageUrl)
    {
        this.imageUrl=imageUrl;
    }



}
