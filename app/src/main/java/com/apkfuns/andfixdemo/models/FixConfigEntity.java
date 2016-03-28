package com.apkfuns.andfixdemo.models;

import java.io.Serializable;

/**
 * Created by pengwei on 16/3/28.
 */
public class FixConfigEntity implements Serializable {
    private String patchVersion;
    private String patchUrl;

    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }
}
