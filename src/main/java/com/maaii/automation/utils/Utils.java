package com.maaii.automation.utils;

import com.maaii.automation.commons.Constants;
import com.maaii.automation.commons.LOCATORFIELD;
import com.maaii.automation.shell.ShellUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by cpuser on 14/9/15.
 */
public class Utils {

    public static void iOSSimActive() throws IOException, InterruptedException {
        String activeSimCMD = "osascript " + Constants.SHELL_DIRECTORY + File.separator + "activate_sim.applescript";
        String resizeSimCMD = "osascript " + Constants.SHELL_DIRECTORY + File.separator + "resize_sim 3";
        ShellUtil.executeShell(activeSimCMD);
        Thread.sleep(1000);
//        ShellUtil.executeShell(resizeSimCMD);

    }

    public static synchronized Long getThreadID() {
        return Thread.currentThread().getId();
    }

    public static synchronized String removeAllSpace(String str) {
        return str.isEmpty() ? null : str.replaceAll(" ", "");
    }

    public static synchronized String toBold(String str) {
        return str.trim().isEmpty() ? null : "<span style='font-weight:bold;'>" + str + "</span>";
    }

    public static synchronized String withPre(String str) {
        return str.trim().isEmpty() ?  null : "<pre>" + str + "</pre>";
    }

}