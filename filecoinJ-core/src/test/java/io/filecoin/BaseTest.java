package io.filecoin;

import io.filecoin.protocol.FileCoinJ;
import io.filecoin.protocol.http.HttpService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseTest {

    protected static List<String> signers = new ArrayList<>();
    protected static Map<String, String> signerPriKeys = new HashMap<>();
    protected static String sender = "t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy";

    protected static FileCoinJ filecoinJ;

    static {
        signers.add("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy");
        signers.add("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri");
        signers.add("t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq");
//        signers.add("t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota");
//        signers.add("t1vnqxq3326j4strwrfvz5tnmgj744jin4i4aiz6i");
//        signers.add("t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra");

        signerPriKeys.put("t1sfzb34kebjkiby4pvfskzkkqm4z5r3nlga6kuhy", "veTeK7MqM8SDuwoU5StGrug2ZRN8bYjtBnMj+J3Zzec=");
        signerPriKeys.put("t15i7o26vxacspx2pwbfbseeelza76a3uwny6oeri", "/5NDy3AFXtxFtlZ1pzM4ARbxAhEEWcfOvdZaN6RIdHY=");
        signerPriKeys.put("t16qfzbkwuzyme4cslko55uedxbibp5m6rz7qclcq", "Mth9YszUhryh+XYjiPcwZh/ZSE6FCzr4ajh9LKiETdU=");
        signerPriKeys.put("t1lbh4j633ajucb3cncuucdrz4a6tatgnqy3njota", "QxcZiwl0vKlx3+QJe58/hyL7SEDxI6U/sbOvdwqygqQ=");
        signerPriKeys.put("t1vnqxq3326j4strwrfvz5tnmgj744jin4i4aiz6i", "3epamPdR1Cy7hzLzMxQruHtu0Kec6c99aqoVpfA0268=");
        signerPriKeys.put("t1onakap7ouvco4zidbyjo2oyyy4pisju47twkrra", "IZdxOPuqAt8v6sJPfHNYPbGLkaZFrepTlIx92gX+/Gc=");


        HttpService httpService = new HttpService("http://localhost:8003/", true);

        httpService.addHeader("Authorization", "Bearer xxxxx");
        filecoinJ = FileCoinJ.build(httpService);
    }
}
