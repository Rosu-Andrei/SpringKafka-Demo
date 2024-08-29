package com.second.kafkaspringobject;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String url = "https://gg.deals/";
        rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
    }
}
