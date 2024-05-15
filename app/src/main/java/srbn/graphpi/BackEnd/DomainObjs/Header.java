package srbn.graphpi.BackEnd.DomainObjs;

import java.io.Serializable;
import java.util.ArrayList;

public class Header implements Serializable {
    private String title="";
    private String desc="";
    private ArrayList<String> keys;
    private String header="";
    private String footer="";
    private String back="";
    private String font="";
    private String fontSize="";

    public Header() {
        this.title = "";
        this.desc = "";
        this.keys = new ArrayList<>();
        this.header = "";
        this.footer = "";
        this.back = "";
        this.font = "";
        this.fontSize = "";
    }
    public Header(String title, String desc, ArrayList<String> keys, String header, String footer, String back, String font, String fontSize) {
        this.title = title.replace("\"", "");
        this.desc = desc.replace("\"", "");
        this.keys = keys;
        this.header = header.replace("\"", "");
        this.footer = footer.replace("\"", "");
        this.back = back.replace("\"", "");
        this.font = font.replace("\"", "");
        this.fontSize = fontSize.replace("\"", "");
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public String getKeysString() { //todo: make this a better string

        String returnString = "";

        for (String key : keys) {
            returnString += "\t-"+key + "\n";
        }

        return returnString;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public String getBack() {
        return back;
    }

    public String getFont() {
        return font;
    }

    public String getFontSize() {
        return fontSize;
    }
}
