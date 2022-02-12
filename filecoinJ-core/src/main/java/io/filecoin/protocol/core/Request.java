package io.filecoin.protocol.core;

import io.filecoin.protocol.FilecoinjService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Request<S, T extends Response> {

    private static AtomicLong nextId = new AtomicLong(0);

    private String jsonrpc = "2.0";
    private String method;
    private List<S> params;
    private long id;

    private FilecoinjService filecoinjService;

    // Unfortunately require an instance of the type too, see
    // http://stackoverflow.com/a/3437930/3211687
    private Class<T> responseType;

    public Request() {
    }

    public Request(String method, List<S> params, FilecoinjService filecoinjService, Class<T> type) {
        this.method = method;
        this.params = params;
        this.id = nextId.getAndIncrement();
        this.filecoinjService = filecoinjService;
        this.responseType = type;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<S> getParams() {
        return params;
    }

    public void setParams(List<S> params) {
        this.params = params;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public T send() throws IOException {
        return filecoinjService.send(this, responseType);
    }
}
