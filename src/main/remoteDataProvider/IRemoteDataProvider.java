package main.remoteDataProvider;

import java.io.IOException;

public interface IRemoteDataProvider {
    public String acquireRemoteData(String address) throws IOException;
}