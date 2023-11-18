package com.example.pshare;

public class ZipFolder {
    private String FolderName;

    public ZipFolder(){
        //Requiered  empty public constructor for Firebase
    }

    public ZipFolder(String FolderName){
        this.FolderName=FolderName;
    }

    public String getFolderName(){
        return FolderName;
    }
}
