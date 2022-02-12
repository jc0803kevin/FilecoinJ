# Welcome to FilecoinJ

The FilecoinJ library is a Java implementation of the filecoin protocol, which allows it to maintain a wallet
and send/receive transactions. It comes with full documentation and some example apps showing how to use it.


+ Complete implementation of Filecoin's JSON-RPC client API over HTTP
+ offline wallet creation(secp256k1 & multisig), 
+ Transfer Signature, offline multi-signature, etc.
+ Filecoin wallet support

It has five runtime dependencies:
+ OKHttp for HTTP connections
+ Jackson Core for fast JSON serialisation/deserialization
+ web3j 

### Maven
```xml
<dependency>
    <groupId>io.filecoin.sdk</groupId>
    <artifactId>filecoinJ-core</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

