package com.maaii.automation.utils;

import com.maaii.automation.commons.Constants;
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
}