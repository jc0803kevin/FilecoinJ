package io.filecoin.protocol;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.filecoin.protocol.core.Request;
import io.filecoin.protocol.core.Response;

import java.io.*;


/**
 * Base service implementation.
 */
public abstract class Service implements FilecoinjService {

    protected final ObjectMapper objectMapper;

    public Service(boolean includeRawResponses) {
        objectMapper = ObjectMapperFactory.getObjectMapper(includeRawResponses);
    }

    protected abstract InputStream performIO(String payload) throws IOException;

    @Override
    public <T extends Response> T send(Request request, Class<T> responseType) throws IOException {
        String payload = objectMapper.writeValueAsString(request);

        try (InputStream result = performIO(payload)) {
            if (result != null) {
//                System.out.println("result.available() -> "+result.available());
//                byte[] bytes = new byte[result.available()];
//                result.read(bytes);
//                String str = new String(bytes);
//                System.out.println("str -> "+str);

//                String str = inputStr2Str_Reader(result, null);
//                System.out.println("str -> "+str);

                return objectMapper.readValue(result, responseType);
            } else {
                return null;
            }
        }
    }

    public static String inputStr2Str_Reader(InputStream in, String encode) {

        String str = "";
        try {
            if (encode == null || encode.equals("")) {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
            StringBuffer sb = new StringBuffer();

            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

}
