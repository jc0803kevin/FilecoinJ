package io.filecoin.protocol;

import io.filecoin.protocol.core.Request;
import io.filecoin.protocol.core.Response;

import java.io.IOException;

public interface FilecoinjService {

    <T extends Response> T send(Request request, Class<T> responseType) throws IOException;

    void close() throws IOException;
}
