package io.haxerdevelopment.replace;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReplaceManager {
    public ArrayList<ReplaceRule> replaceRules;
    public RegexReplacer regularExpressionReplacer;

    public ReplaceManager() {
        replaceRules = new ArrayList<ReplaceRule>();
        regularExpressionReplacer = new RegexReplacer();
    }

    public void saveRules(String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(replaceRules);
        oos.flush();
        oos.close();
    }

    public void loadRules(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream oin = new ObjectInputStream(fis);
        replaceRules = (ArrayList<ReplaceRule>) oin.readObject();
    }

    public String replace(String url, String string)
    {
        string = replaceGlobal(string);
        string = replaceDependent(url, string);
        return string;
    }

    private String readAllPageData(String url) throws IOException {
        URL remoteURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)remoteURL.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent", "Mozilla/4.0");
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        if (conn.getResponseCode() == 200) // Check if response is OK
        {
            BufferedReader serverReader =
                    new BufferedReader(new InputStreamReader(conn.getInputStream())); // Read it

            String pageContent = ""; // Prepare variable for page content
            String line = "";

            while((line = serverReader.readLine()) != null) // Read content of page line-by-line
                pageContent += line;

            return pageContent;
        }
        else return "Server responded with error code " + conn.getResponseCode() + "<br><i>PacketHaxer v1.0</i>";
    }

    public String replaceDependent(String url, String string)
    {
        for (ReplaceRule rule : replaceRules) {
            if (rule.isGlobal || !url.contains(rule.url))
                continue;
            try {
                switch (rule.type)
                {
                    case PLAIN -> string = string.replace(rule.match, rule.replace);
                    case REGEX -> string = regularExpressionReplacer.processString(string, rule.match, rule.replace);
                    case OVERRIDE -> string = rule.replace;
                    case REDIRECT -> string = "<head>" +
                            "<meta http-equiv=\"refresh\" content=\"0;URL=" + rule.replace + "\" />\n" +
                            "</head>";
                    case OVERRIDE_PAGE -> string = readAllPageData(rule.replace);
                }
            }
            catch (IOException exception) {
                continue;
            }
        }
        return string;
    }

    public String replaceGlobal(String string) {
        for (ReplaceRule rule : replaceRules) {
            if (!rule.isGlobal)
                continue;
            try {
                switch (rule.type)
                {
                    case PLAIN -> string = string.replace(rule.match, rule.replace);
                    case REGEX -> string = regularExpressionReplacer.processString(string, rule.match, rule.replace);
                    case OVERRIDE -> string = rule.replace;
                    case REDIRECT -> string = "<head>" +
                            "<meta http-equiv=\"refresh\" content=\"1;URL=" + rule.replace + "\" />\n" +
                            "</head>";
                    case OVERRIDE_PAGE -> string = readAllPageData(rule.replace);
                }
            }
            catch (IOException exception) {
                continue;
            }
        }
        return string;
    }
}
